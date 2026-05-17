# Deploiement Docker et Google Cloud Platform

Ce projet est deploye avec 3 images/conteneurs :

1. `project-management-backend:1.0.0` : API Spring Boot
2. `project-management-frontend:1.0.0` : frontend Angular servi par Nginx
3. `mysql:8.4` : base de donnees MySQL

## 1. Deploiement local avec Docker Compose

### Prerequis

- Docker Desktop installe et lance
- Port `4200` disponible pour le frontend
- Port `8080` disponible pour le backend
- Port `3307` disponible pour MySQL expose localement

### Configuration

Copier le fichier d'exemple :

```powershell
copy .env.example .env
```

Variables principales :

```env
MYSQL_DATABASE=project_db
MYSQL_ROOT_PASSWORD=root
MYSQL_USER=project_user
MYSQL_PASSWORD=project_password
MYSQL_PORT=3307
BACKEND_PORT=8080
FRONTEND_PORT=4200
```

### Build et lancement

Depuis la racine du projet :

```powershell
docker compose up --build -d
```

### Verification

Voir les conteneurs :

```powershell
docker compose ps
```

Logs du backend :

```powershell
docker compose logs -f backend
```

URLs locales :

```text
Frontend Angular : http://localhost:4200
Backend API      : http://localhost:8080
Swagger UI       : http://localhost:8080/swagger-ui.html
MySQL            : localhost:3307
```

### Arret

```powershell
docker compose down
```

Arret avec suppression du volume MySQL :

```powershell
docker compose down -v
```

## 2. Images Docker

Construire les images sans lancer les conteneurs :

```powershell
docker compose build
```

Lister les images :

```powershell
docker images
```

Images attendues :

```text
project-management-backend:1.0.0
project-management-frontend:1.0.0
mysql:8.4
```

## 3. Deploiement Google Cloud Platform avec 3 conteneurs

Pour conserver les 3 images/conteneurs, l'approche la plus directe est :

```text
Google Compute Engine VM + Docker Compose
```

Cloud Run est tres utile pour backend/frontend, mais il ne convient pas pour executer durablement une base MySQL en conteneur avec volume persistant. Pour respecter l'exigence "3 images", Compute Engine + Docker Compose est le choix le plus coherent.

### 3.1 Creer une VM Compute Engine

Exemple avec `gcloud` :

```bash
gcloud compute instances create project-management-vm \
  --zone=us-central1-a \
  --machine-type=e2-medium \
  --image-family=debian-12 \
  --image-project=debian-cloud \
  --boot-disk-size=30GB \
  --tags=project-management
```

Ouvrir les ports HTTP :

```bash
gcloud compute firewall-rules create allow-project-management-http \
  --allow=tcp:80,tcp:8080 \
  --target-tags=project-management \
  --description="Allow frontend and backend HTTP traffic"
```

### 3.2 Installer Docker sur la VM

Connexion SSH :

```bash
gcloud compute ssh project-management-vm --zone=us-central1-a
```

Installation Docker :

```bash
sudo apt update
sudo apt install -y ca-certificates curl gnupg git
sudo install -m 0755 -d /etc/apt/keyrings
curl -fsSL https://download.docker.com/linux/debian/gpg | sudo gpg --dearmor -o /etc/apt/keyrings/docker.gpg
sudo chmod a+r /etc/apt/keyrings/docker.gpg
echo "deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.gpg] https://download.docker.com/linux/debian $(. /etc/os-release && echo $VERSION_CODENAME) stable" | sudo tee /etc/apt/sources.list.d/docker.list > /dev/null
sudo apt update
sudo apt install -y docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin
sudo usermod -aG docker $USER
```

Se deconnecter puis se reconnecter pour appliquer le groupe Docker.

### 3.3 Copier le projet sur la VM

Option simple :

```bash
git clone <URL_DU_REPOSITORY>
cd project-management
```

Ou copier le dossier manuellement avec SCP.

### 3.4 Configurer le `.env` cloud

Sur la VM :

```bash
cp .env.example .env
nano .env
```

Exemple cloud :

```env
MYSQL_DATABASE=project_db
MYSQL_ROOT_PASSWORD=change_this_root_password
MYSQL_USER=project_user
MYSQL_PASSWORD=change_this_user_password
MYSQL_PORT=3306
BACKEND_PORT=8080
FRONTEND_PORT=80
```

### 3.5 Lancer l'application sur GCP

```bash
docker compose up --build -d
```

Verification :

```bash
docker compose ps
docker compose logs -f backend
```

Recuperer l'adresse IP externe :

```bash
gcloud compute instances describe project-management-vm \
  --zone=us-central1-a \
  --format="get(networkInterfaces[0].accessConfigs[0].natIP)"
```

URLs cloud :

```text
Frontend : http://EXTERNAL_IP
Backend  : http://EXTERNAL_IP:8080
Swagger  : http://EXTERNAL_IP:8080/swagger-ui.html
```

## 4. Variante GCP plus professionnelle

Pour un deploiement plus cloud-native :

- Backend Spring Boot sur Cloud Run
- Frontend Angular/Nginx sur Cloud Run
- Base MySQL sur Cloud SQL
- Images stockees dans Artifact Registry

Cette variante est plus robuste, mais elle ne conserve pas MySQL comme image Docker. Elle remplace le conteneur MySQL par un service manage Cloud SQL.

## 5. Commandes utiles

Rebuild complet :

```powershell
docker compose build --no-cache
docker compose up -d
```

Voir les logs :

```powershell
docker compose logs -f
```

Entrer dans MySQL :

```powershell
docker exec -it project-management-database mysql -u project_user -p project_db
```

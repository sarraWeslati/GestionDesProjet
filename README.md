# Project Management

Application de gestion des projets avec budgets et ressources.

## Modules

- Backend Spring Boot : API REST, JPA, validation, Swagger/OpenAPI
- Frontend Angular : interface utilisateur
- Base de donnees MySQL

## Deploiement Docker

Le deploiement se fait avec 3 images :

- backend Spring Boot
- frontend Angular/Nginx
- base de donnees MySQL

Voir le guide complet : [DEPLOYMENT.md](DEPLOYMENT.md)

## Lancement rapide

```powershell
copy .env.example .env
docker compose up --build -d
```

Puis ouvrir :

```text
http://localhost:4200
```

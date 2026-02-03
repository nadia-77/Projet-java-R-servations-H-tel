# Vidéo sur les interfaces de l'application :
Vous pouvez visionner la vidéo explicative du projet via le lien suivant : https://drive.google.com/file/d/1zfceNThp5RJBErrWOhP-wS9u_Oq3r6Ez/view?usp=sharing


# Application Java de Réservation d’Hôtel
## Contexte
Dans le secteur hôtelier, la gestion efficace des réservations, des chambres et des clients est essentielle pour assurer un bon fonctionnement de l’établissement et offrir un service de qualité. Avec l’augmentation du nombre de clients et de réservations, les méthodes manuelles ou semi-informatisées deviennent rapidement insuffisantes.
L’application Java de réservation d’hôtel a été développée afin d’informatiser et de centraliser la gestion des chambres, des clients et des réservations, tout en offrant une interface simple et intuitive destinée au personnel de l’hôtel.
## Problématique
Avant la mise en place de cette application, la gestion des réservations pouvait présenter plusieurs difficultés :
* Risque d’erreurs dans l’enregistrement des réservations.
* Difficulté à vérifier rapidement la disponibilité des chambres.
* Manque de centralisation des informations sur les clients et les chambres.
* Perte de temps dans la recherche et la mise à jour des données.
Ces problèmes peuvent entraîner une mauvaise organisation, des doubles réservations et une baisse de la qualité du service.
##  Objectifs
Les principaux objectifs de cette application sont :
* Centraliser les données : Regrouper toutes les informations relatives aux chambres, aux clients et aux réservations dans une base de données unique.
* Automatiser la gestion des réservations : Faciliter l’ajout, la modification et la suppression des réservations.
* Gérer les chambres : Permettre la gestion des chambres (ajout, modification, suppression, type, prix, disponibilité).
* Améliorer le suivi : Consulter rapidement l’état des réservations et la disponibilité des chambres.
## Fonctionnalités principales
* Gestion des chambres (CRUD : créer, lire, modifier, supprimer).
* Gestion des clients.
* Gestion des réservations (dates, client, chambre, statut).
* Consultation de la liste des réservations.
* Vérification de la disponibilité des chambres selon les dates.
## Diagramme de cas d’utilisation (Use Case)
Le diagramme de cas d’utilisation met en évidence les interactions entre l’utilisateur et le système :
* Gérer les chambres.
* Gérer les clients.
* Effectuer une réservation.
* Modifier ou annuler une réservation.
* Consulter les réservations.
## Diagramme de classes
Le diagramme de classes représente la structure statique de l’application et les relations entre les différentes entités principales :
* Chambre
* Client
* Réservation
## Structure de la Base de Données
La base de données de l’application repose principalement sur les tables suivantes :
*Chambre
id : identifiant de la chambre.
numero : numéro de la chambre.
type : type de chambre (Simple, Double, Suite, etc.).
prix : prix par nuit.
* Client
id : identifiant du client.
nom : nom du client.
telephone : numéro de téléphone.
* Reservation
id : identifiant de la réservation.
dateDebut : date de début de séjour.
dateFin : date de fin de séjour.
client_id : référence au client.
chambre_id : référence à la chambre.

## Script de la Base de Données

CREATE TABLE chambre (
id INT AUTO_INCREMENT PRIMARY KEY,
numero INT NOT NULL UNIQUE,
type VARCHAR(50) NOT NULL,
prix DOUBLE NOT NULL,
statut VARCHAR(50) NOT NULL
);

CREATE TABLE client (
id INT AUTO_INCREMENT PRIMARY KEY,
nom VARCHAR(100),
telephone VARCHAR(50)
);

CREATE TABLE reservation (
id INT AUTO_INCREMENT PRIMARY KEY,
dateDebut DATE NOT NULL,
dateFin DATE NOT NULL,
client_id INT NOT NULL,
chambre_id INT NOT NULL,
FOREIGN KEY (client_id) REFERENCES client(id) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (chambre_id) REFERENCES chambre(id) ON DELETE CASCADE ON UPDATE CASCADE
);

## Architecture de l’application
L’application adopte une architecture en couches :
* Couche présentation : Interfaces graphiques développées avec Java Swing.
* Couche métier : Classes métier gérant la logique de l’application (Chambre, Client, Réservation).
* Couche accès aux données (DAO) : Classes assurant la communication avec la base de données via JDBC.
* Base de données : MySQL pour le stockage des données.
## Technologies utilisées
* Langage : Java
* Interface graphique : Java Swing
* Base de données : MySQL
* Accès aux données : JDBC
* IDE : NetBeans
* Outil de gestion de base de données : phpMyAdmin
## Conclusion
Cette application Java de réservation d’hôtel permet d’améliorer considérablement la gestion des chambres et des réservations. Elle offre une solution centralisée, fiable et facile à utiliser, réduisant les erreurs et optimisant le travail du personnel hôtelier. Des améliorations futures peuvent inclure la gestion des paiements, des statistiques avancées.



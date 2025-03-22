# Système Multi-Agents Intelligent

Ce projet implémente un système multi-agents où plusieurs agents interagissent dans un environnement dynamique. Chaque agent possède une stratégie différente pour naviguer, collecter des ressources, éviter des obstacles et gérer ses ressources internes, comme la santé. L'objectif est de simuler un environnement complexe où plusieurs agents autonomes coopèrent ou se concurrencent pour atteindre leurs objectifs.

## Description

Le système comprend plusieurs types d'agents, chacun ayant sa propre logique et sa propre stratégie :

1. **AgentLocal** : Utilise une stratégie de recherche du chemin le plus court pour atteindre les ressources les plus proches. Il est capable de calculer la distance et se déplacer vers l'objectif le plus proche.
2. **AgentVorace** : Cet agent utilise une approche vorace pour maximiser ses gains immédiats. Il cherche les ressources avec la plus grande valeur et les collecte en priorité.
3. **AgentRentable** : Conçu pour maximiser son efficacité tout en tenant compte de son état de santé. Cet agent évalue la rentabilité de chaque action en fonction de ses ressources et de sa santé.

Le projet repose sur une carte 2D où les agents interagissent avec différents types d'éléments : des ressources (ex. : objets ou nourriture), des obstacles (ex. : murs), et des zones accessibles. Les agents prennent des décisions en temps réel pour naviguer dans l'environnement, collecter des ressources et éviter les obstacles.

## Fonctionnalités

- **Navigation et recherche de chemin** : Les agents utilisent des algorithmes comme la recherche en largeur (BFS) pour déterminer le chemin le plus court vers une ressource ou une zone.
- **Gestion de la santé** : Les agents, notamment l'AgentRentable, gèrent leur santé et peuvent ajuster leurs priorités en fonction de leur état de santé.
- **Stratégies variées** : Chaque agent possède une stratégie différente pour accomplir sa mission, que ce soit en suivant un chemin court, en maximisant la collecte de ressources, ou en prenant en compte la rentabilité.
- **Système multi-agents** : Plusieurs agents peuvent interagir dans un même environnement, coopérer pour collecter des ressources ou se concurrencer pour les obtenir.


## Prérequis logiciels

1. **Java Development Kit (JDK) 21 ou supérieur**
   - Ce projet est développé avec Java. Vous devez avoir installé le JDK 21 ou une version plus récente.
   - Pour vérifier si Java est installé, exécutez la commande suivante dans votre terminal :
     ```bash
     java -version
     ```

2. **Maven 3.6 ou supérieur** (ou un autre système de gestion de dépendances compatible)
   - Maven est utilisé pour la gestion des dépendances et la compilation du projet.
   - Pour vérifier si Maven est installé, exécutez la commande suivante :
     ```bash
     mvn -version
     ```

3. **Git**
   - Git est nécessaire pour cloner le dépôt et gérer les versions du projet.
   - Vous pouvez vérifier si Git est installé avec cette commande :
     ```bash
     git --version
     ```

# Etudiants
Le projet a été réalisé par les étudiants suivants :
- AMAL Saad
- ID BENOUAKRIM Brahim
- NASRI Ayoub

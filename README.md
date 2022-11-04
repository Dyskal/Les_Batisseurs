# Bienvenue dans Les Bâtisseurs - Moyen-Âge !

Les Bâtisseurs - Moyen-Âge est un jeu se jouant de 2 à 4 joueurs.

Tout les fichiers nécessaires au jeu (fichiers des cartes, images pour l'interface graphique) se situent directement dans le JAR.

Au lancement, un dossier data sera créé au même niveau que le fichier JAR pour stocker les sauvegardes.

Les fichiers de sauvegarde sont en format .sav et sont nommés `save_date_heure.sav`, des exemples de sauvegarde sont disponibles dans le dossier data fourni.

Lors du lancement du jeu, un choix de mode de jeu sera demandé : il faut choisir un H pour un utilisateur humain et un A pour un utilisateur automatique.

Pour compiler le projet, il faut utiliser le fichier Ant `build.xml` fourni.

Pour lancer le jeu en mode graphique, il faut double cliquer sur le fichier JAR ou lancer la commande `java -jar Les Bâtisseurs.jar` dans un terminal.

Pour lancer le jeu en mode console, il faut lancer la commande `java -jar Les Bâtisseurs.jar console` dans un terminal.

Musique libre de droit créée par `ƧIMӨП#6666`.

Les cartes seront affichées sous la forme :

- Bâtiments : (gain écus/points|pierre/bois/savoir/tuiles)
- Ouvriers libres : (coût écus|pierre/bois/savoir/tuiles)
- Bâtiments en cours de construction : (gain écus/points|pierre/bois/savoir/tuiles | ressources restantes)

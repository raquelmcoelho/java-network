## Exemple de création/lancement de BDD via Docker Compose

Lancement de la BDD, aller dans le répertoire correspondant à la BDD choisie et faire :
`sudo docker compose up -d`

Pour chaque BDD :
- Le script d'initialisation est dans le répertoire `db_init`.
- Le volume persistent pour la BDD est dans le répertoire `db_data`.
- Les login/pass + nom de la BDD sont dans le fichier `.db_env` (à éditer)

### MariaDB
Se placer dans le répertoire `./mariadb`

### MySql
Se placer dans le répertoire `./mysql`

### PhpMyAdmin
Dans les 2 cas, un PhpMyAdmin est lancé sur le port 9306.
Dans la configuration du ReverseProxy, il est accessible sur /dbadmin/
- Le login fonctionne, mais n'envoie pas sur la bonne page.
- Il suffit de revenir sur /dbadmin/ et tout est bon pour la suite.

#### ReverseProxy Apache2
La configuration de ReverseProxy pour Apache2 est présente dans le répertoire `./apache2`
Les fichiers sont à copier (en sudo) dans le répertoire `/etc/apache2/sites-available`
Ensuite, il suffit de relancer Apache2 via `sudo systemctl restart apache2`

### MongoDB
Dans le répertoire `./mongodb`
Exemple de montage d'une BDD mongo associée à un projet ExpressJs

- La BDD est inaccessible sauf par le service ExpressJs au sein d'un sous-réseau docker. Et le service se lance avec des droits limités.
- Des scripts permettent de lancer un backup/restore à partir du conteneur de la BDD mais dont le backup est généré/restauré depuis un volume local.


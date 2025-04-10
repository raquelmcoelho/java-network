# manually create a backup of the database (sudo this script if needed)
docker exec --user=mongodb myapp-mongo-run /bin/sh -c "/data/db_backup.sh"

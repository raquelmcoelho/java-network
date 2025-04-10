# manually restore a backup from the file as argument (sudo this script if needed)
docker exec --user=mongodb myapp-mongo-run /bin/sh -c "/data/db_restore.sh $1"

## Usage:
## > docker build -t myapp-mongo .
## > docker run -p 27017:27017 -v ./db_data:/data/db -v ./db_backup:/data/backup --name myapp-mongo-run myapp-mongo
## Note: the 'db_data' and 'db_backup' directories must have 777 permissions

## Either from the latest version
#FROM mongo
## or a specific version
FROM mongo:8.0.4

# first possibility : copying db into container (careful, this does not persist)
#COPY ./db_data_prod /data/db

# second possibility : local shared volume
USER mongodb
VOLUME /data/db
VOLUME /data/backup

WORKDIR /data
# copy backup/restore scripts
COPY --chown=1002 ./*.sh .

# launch db daemon
EXPOSE 27017
#CMD ["mongod", "--wiredTigerCacheSizeGB 1"]
CMD ["mongod"]

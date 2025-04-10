#!/bin/bash

# dump database whose name is "MyAppDB" into folder (collections as json files)
mongodump --uri="mongodb://localhost:27017/MyAppDB" --out="backup"
cd backup
# compress the directory into a timestamped file
file_name=/data/backup/db_back_$(date +"%Y-%m-%d_%H-%M-%S").tgz
tar czf $file_name MyAppDB
#chown 1002:1002 $file_name
rm -r /data/backup/MyAppDB

#!/bin/bash

if [ ! $1 ]; then
        echo "Missing first argument = <backup_file_name>"
        exit 1
fi
# uncompress the backup file
cd /data/backup
tar xzf $1
# import the uncompressed json files into DB
mongorestore --uri="mongodb://localhost:27017/MyAppDB" MyAppDB
rm -r ./MyAppDB

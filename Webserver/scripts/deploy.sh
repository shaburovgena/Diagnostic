#!/usr/bin/env bash

mvn clean package

echo 'Copy files...'

scp -i ~/.ssh/id_rsa_RSC223 \
    target/Webserver-1.0-SNAPSHOT.jar \
    admin@10.14.62.1:/home/admin/

echo 'Restart server...'

ssh -i ~/.ssh/id_rsa_RSC223 admin@10.14.62.1 << EOF
pgrep java | xargs kill -9
nohup java -jar Webserver-1.0-SNAPSHOT.jar > log.txt &
EOF

echo 'Bye'
#!/bin/sh 

if [ $# -eq 1 ]; then 
DUMP_NAME=$1
echo "Using name $DUMP_NAME"
else
DUMP_NAME=`ls -rtc dump*.zip | tail -1`
echo "using dump_name $DUMP_NAME"
fi

dropdb metagenomics_staging 
createdb metagenomics_staging 
rm -f dump.sql 
unzip -p $DUMP_NAME> dump.sql 
psql -U ubuntu metagenomics_staging < dump.sql ; 



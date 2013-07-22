#!/bin/sh 

if [ $# -eq 1 ]; then 
DUMP_NAME=$1
echo "Using name $DUMP_NAME"
else
DUMP_NAME=`ls -rtc dump*.zip | tail -1`
echo "using dump_name $DUMP_NAME"
fi


dropdb metagenomics_dev 
createdb metagenomics_dev 
rm -f dump.sql 
unzip -p $DUMP_NAME> dump.sql 
psql -U ndunn metagenomics_dev < dump.sql ; 



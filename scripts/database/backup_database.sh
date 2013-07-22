#!/bin/bash

DUMPFILE=dump`date +%F`
DUMPDIRECTORY=/home/ndunn/DATABASE_BACKUPS

cd $DUMPDIRECTORY
pg_dump -U postgres metagenomics_production > $DUMPFILE.sql 
zip $DUMPFILE.zip $DUMPFILE.sql 
rm -f $DUMPFILE.sql 


#!/bin/sh 
echo "alter table isolate_condition alter isolated_when drop not null ; " | psql metagenomics_dev 


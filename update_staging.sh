#!/bin/sh
#hg pull -u 

grails clean
grails war 
sudo cp target/metagenomicsdb-0.1.war /var/lib/tomcat7/webapps/metagenomicsdb.war



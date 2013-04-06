#!/bin/sh
#hg pull -u 

grails war 
sudo cp target/stockdb-0.1.war /var/lib/tomcat7/webapps/stockdb.war



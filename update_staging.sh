#!/bin/sh
#hg pull -u 

if [ "$HOSTNAME" = ubuntu ]; then 
	grails clean
	grails war 
	sudo service tomcat7 stop
	sudo rm -rf /var/lib/tomcat7/webapps/metagenomicsdb*
	sudo cp target/metagenomicsdb-0.1.war /var/lib/tomcat7/webapps/metagenomicsdb.war
	sudo service tomcat7 start 
else
   echo "$HOSTNAME is not the staging server" 
fi 


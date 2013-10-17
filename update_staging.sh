#!/bin/sh
#hg pull -u 

THIS_HOST=$USER
if [ "$THIS_HOST" = ubuntu ]; then 
	grails clean
	grails -Dgrails.env=staging war 
	sudo service tomcat7 stop
	sudo rm -rf /var/lib/tomcat7/webapps/metagenomicsdb*
	sudo cp target/metagenomicsdb-0.1.war /var/lib/tomcat7/webapps/metagenomicsdb.war
	sudo service tomcat7 start 
else
   echo "$THIS_HOST is not the staging server" 
fi 


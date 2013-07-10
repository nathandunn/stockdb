#!/bin/sh
#hg pull -u 

if [ "$HOSTNAME" = omero.uoregon.edu ]; then 
	grails clean
	grails war 
	sudo service tomcat6 stop
	sudo rm -rf /var/lib/tomcat6/webapps/metagenomicsdb*
	sudo cp target/metagenomicsdb-0.1.war /var/lib/tomcat6/webapps/metagenomicsdb.war
	sudo service tomcat6 start 
else
   echo "$HOSTNAME is not the production server" 
fi 


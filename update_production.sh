#!/bin/sh
#hg pull -u 

THIS_HOST=`hostname`
if [ "$THIS_HOST" = omero.uoregon.edu ]; then 
	grails clean
	grails war 
	sudo service tomcat6 stop
	sudo rm -rf /var/lib/tomcat6/webapps/metagenomicsdb*
	sudo cp target/metagenomicsdb-0.1.war /var/lib/tomcat6/webapps/metagenomicsdb.war
	sudo service tomcat6 start 
else
   echo "$THIS_HOST is not the production server" 
fi 


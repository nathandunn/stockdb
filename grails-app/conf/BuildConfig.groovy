dependencyManager.ivySettings.defaultCacheIvyPattern = "[organisation]/[module](/[branch])/ivy-[revision](-[classifier]).xml"
grails.servlet.version = "2.5" // Change depending on target container compliance (2.5 or 3.0)
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.target.level = 1.6
grails.project.source.level = 1.6
//grails.project.war.file = "target/${appName}-${appVersion}.war"
gwt.version = "2.5.1"

grails.project.dependency.resolver = "maven"

grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // specify dependency exclusions here; for example, uncomment this to disable ehcache:
        // excludes 'ehcache'
//        excludes "grails-spock", "spock"

    }
    log "error" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    checksums true // Whether to verify checksums on resolve

    repositories {
        inherits true // Whether to inherit repository definitions from plugins

        grailsPlugins()
        grailsHome()
        grailsCentral()

        mavenLocal()
        mavenCentral()

        //grailsRepo "https://grails.org/plugins"

        // uncomment these (or add new ones) to enable remote dependency resolution from public Maven repositories
        //mavenRepo "http://snapshots.repository.codehaus.org"
        //mavenRepo "http://repository.codehaus.org"
        //mavenRepo "http://download.java.net/maven/2/"
        //mavenRepo "http://repository.jboss.com/maven2/"

        // added for mail
        mavenRepo "http://download.java.net/maven/2/"

        mavenRepo "http://repo.grails.org/grails/core"
        mavenRepo "http://repo.grails.org/grails/plugins"
        //mavenRepo "repo.grails.org/grails/repo/"

    }
    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes eg.

        // runtime 'mysql:mysql-connector-java:5.1.20'
        runtime 'postgresql:postgresql:9.0-801.jdbc4'
//        test "org.spockframework:spock-grails-support:0.7-groovy-2.0"

    }

    plugins {
        //runtime ":hibernate:$grailsVersion"
        runtime ':hibernate:3.6.10.2'
//        runtime ':hibernate4:4.3.8.1' // or ':hibernate:3.6.10.19'
        runtime ":database-migration:1.4.1"
        //runtime ":jquery:1.8.0"
        runtime ":jquery:1.10.2.2"
        compile ":jquery-ui:1.10.3"
        compile ":resources:1.2.1"
        compile ':cache:1.1.8'
        compile ':cache-ehcache:1.0.5'

        // Uncomment these (or add new ones) to enable additional resources capabilities
        //runtime ":zipped-resources:1.0"
        //runtime ":cached-resources:1.0"
        //runtime ":yui-minify-resources:0.1.4"

        //build ":tomcat:$grailsVersion"
        build ':tomcat:7.0.55.2'

//        runtime ":database-migration:1.1"

        //compile ':cache:1.0.0'
        //compile ':cache:1.1.8'
//        plugins.gwt=0.8

        //compile ':gwt:0.9.1'

        // GWT Plugin
        runtime ":extended-dependency-manager:0.5.5"
        compile ":gwt:1.0.3", {
            transitive = false
        }

//        compile(':gwt:0.8') {
//            exclude 'spock'
////            exclude "org.spockframework:spock-grails-support:0.7-groovy-2.0"
//        }
        compile ":csv:0.3.1"

//        #plugins.navigation=1.3.2
//        compile ":navigation:1.3.2"
//        compile ":platform-core:1.0.RC5"

//        compile ":mail:1.0.1"
        compile ":mail:1.0.7"

        // plugins.shiro=1.1.4
        compile(":shiro:1.2.1") {
            excludes([name: 'quartz', group: 'org.opensymphony.quartz'])
        }
        compile ":crypto:2.0"

//        exclude "spock-grails-support"
//        test(":spock:0.7") {
//            exclude "spock-grails-support"
//        }

    }
}

//gwt {
//    version = "2.5.1"
////    home = "${grailsHome}/../gwt"
//}


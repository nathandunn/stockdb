import edu.uoregon.stockdb.Researcher
import edu.uoregon.stockdb.ResearcherService
import org.apache.shiro.SecurityUtils

def loggedIn = {->
    return SecurityUtils.subject.authenticated
}
def loggedOut = {->
    return !SecurityUtils.subject.authenticated
}

def username = {->
//    if(loggedIn){
    return ((Researcher) SecurityUtils.subject.principal).fullName
//    }
//    else{
//        return "N/A"
//    }
}

def getProfileName() {
    return ((Researcher) SecurityUtils.subject.principal).fullName
}

def isAdmin = {->
    try {
        SecurityUtils.subject.checkRole(ResearcherService.ROLE_ADMINISTRATOR)
        return true
    } catch (e) {
        println "is not admin ${SecurityUtils.subject.principal}"
        return false
    }
}

navigation = {
    // Declare the "app" scope, used by default in tags
    app {

        strain(controller: 'strain', action: 'list') {
            stock(controller: 'stock', action: 'list')
            isolateConditions(controller: 'isolate', action: 'list')
            location(controller: 'location', action: 'list')
            genotype(controller: 'strainGenotype', action: 'list')
            rastGenomes(controller: 'genome', action: 'listRast')
            allGenomes(controller: 'genome', action: 'list')
            genomeTypes(controller: 'genomeType', action: 'list')
            phylum(controller: 'phylum', action: 'list')
            genus(controller: 'genus', action: 'list')
        }

//        // A nav item pointing to HomeController, using the default action
        experiment(controller: 'experiment', action: 'list') {
            measuredValue(controller: 'measuredValue', action: 'list')
            category(controller: 'category', action: 'list')
        }

        host(controller: 'hostOrigin', action: 'list') {
            genotype(controller: 'hostGenotype', action: 'list')
            species(controller: 'species', action: 'list')
            origin(controller: 'hostOrigin', action: 'list')
            facility(controller: 'hostFacility', action: 'list')

        }

        labs(controller: 'lab', action: 'list') {
            researcher(controller: 'researcher', action: 'list')
        }

        auth(visible: false)

    }

// Some back-end admin scaffolding stuff in a separate scope
//    admin {
//        // Use "list" action as default item, even if its not default action
//        // and create automatic sub-items for the other actions
//        books(controller:'bookAdmin', action:'list, create, search')
//
//        // User admin, with default screen using "search" action
//        users(controller:'userAdmin', action:'search') {
//            // Declare action alias so "create" is active for both "create" and "update" actions
//            create(action:'create', actionAliases:'update')
//        }
//    }
}
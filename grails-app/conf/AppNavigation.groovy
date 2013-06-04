import edu.uoregon.stockdb.Researcher
import edu.uoregon.stockdb.ResearcherService
import edu.uoregon.stockdb.StubData
import org.apache.shiro.SecurityUtils

def loggedIn = {->
    return SecurityUtils.subject.authenticated
}
def loggedOut = {->
    return !SecurityUtils.subject.authenticated
}

def username = {->
//    if(loggedIn){
        return ( (Researcher) SecurityUtils.subject.principal).fullName
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
            isolate(controller: 'isolate', action: 'list')
            location(controller: 'location', action: 'list')
            genotype(controller: 'strainGenotype', action: 'list')
            genome(controller: 'genome', action: 'list')
            phylym(controller: 'phylum', action: 'list')
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

        auth(visible:false)

//        access(controller: 'auth',action:'login'){
//            signOut(controller: 'auth',action:'signOut')
//        }

//        login(controller: 'auth', action: 'login', visible: loggedOut)
//        editProfile(controller: 'researcher', action: 'edit', visible: loggedIn)
//        logout(controller: 'auth', action: 'signOut', visible: loggedIn)

//        user {
//            login controller: 'auth', action: 'login', visible: loggedOut
////            signup controller: 'auth', action: 'signup', visible: notLoggedIn
//        }
//
//        // Items pointing to ContentController, using the specific action
//        about(controller:'content')
//        contact(controller:'content')
//        help(controller:'content')
//
//        // Some user interface actions in second-level nav
//        // All in BooksController
//        books {
//            // "list" action in "books" controller
//            list()
//            // "create" action in "books" controller
//            create()
//        }
//
//        // More convoluted stuff split across controllers/locations
//        support(controller:'content', action:'support') {
//            faq(url:'http://faqs.mysite.com') // point to CMS
//            makeRequest(controller:'supportRequest', action:'create')
//        }
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
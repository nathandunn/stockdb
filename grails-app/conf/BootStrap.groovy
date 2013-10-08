import edu.uoregon.stockdb.Population
import edu.uoregon.stockdb.StubData
import grails.util.Environment

class BootStrap {

    StubData stubData = new StubData()

    def init = { servletContext ->

//        if(Environment.current==Environment.DEVELOPMENT){
//            stubData.stubUsers()
//            stubData.stubData()
//            stubData.stubRawlsData()
//            stubData.importExperiments()
//        }

        if(Population.count()==0){
            stubData.stubPhylogeny()
        }

    }
    def destroy = {
    }
}

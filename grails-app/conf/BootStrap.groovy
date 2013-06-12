import edu.uoregon.stockdb.StubData
import grails.util.Environment

class BootStrap {

    StubData stubData = new StubData()

    def init = { servletContext ->

        if(Environment.current!=Environment.TEST){
            stubData.stubUsers()
            stubData.stubData()
            stubData.stubRawlsData()
            stubData.importExperiments()
        }

    }
    def destroy = {
    }
}

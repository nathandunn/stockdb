import edu.uoregon.stockdb.HostFacility
import edu.uoregon.stockdb.StubData

class BootStrap {

    def init = { servletContext ->

        StubData.stubData()


    }
    def destroy = {
    }
}

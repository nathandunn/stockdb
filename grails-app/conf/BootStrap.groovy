import edu.uoregon.stockdb.HostFacility
import edu.uoregon.stockdb.StubData
import edu.uoregon.stockdb.Phylum
import edu.uoregon.stockdb.Genus

class BootStrap {

    def init = { servletContext ->

        StubData.stubData()


    }
    def destroy = {
    }
}

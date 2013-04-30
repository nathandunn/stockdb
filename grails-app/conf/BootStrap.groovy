import edu.uoregon.stockdb.StubData

class BootStrap {

    StubData stubData = new StubData()

    def init = { servletContext ->


        stubData.stubUsers()
        stubData.stubData()
        stubData.stubRawlsData()
        stubData.importExperiments()


    }
    def destroy = {
    }
}

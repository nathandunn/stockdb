import edu.uoregon.stockdb.StubData

class BootStrap {

    StubData stubData = new StubData()

    def init = { servletContext ->

        stubData.stubUsers()
        stubData.stubData()

    }
    def destroy = {
    }
}

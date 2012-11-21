import edu.uoregon.stockdb.StubData

class BootStrap {

    def init = { servletContext ->

        new StubData().stubData()


    }
    def destroy = {
    }
}

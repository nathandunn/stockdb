import edu.uoregon.stockdb.HostFacility
import edu.uoregon.stockdb.Population
import edu.uoregon.stockdb.Species
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

        // TODO: run this once
        if(Population.count()==0){
            Species zebrafish = Species.findByCommonName("Zebrafish")
            zebrafish.prefix = "Z"
            zebrafish.save()
            Species stickleback = Species.findByCommonName("Stickleback")
            stickleback.prefix = "SB"
            zebrafish.save()

            HostFacility oregon = HostFacility.findByName("University of Oregon")
            oregon.prefix = "OR"
            oregon.save()

            HostFacility northCarolina = HostFacility.findByName("University of North Carolina")
            northCarolina.prefix = "NC"
            northCarolina.save()

            HostFacility washington= HostFacility.findByName("Washington University")
            washington.prefix = "WU"
            washington.save()

            stubData.stubPhylogeny()
            Map<String,String> strainMap = stubData.stubStickleback1()
            stubData.stubStickleback1Experiments(strainMap)
        }

    }
    def destroy = {
    }
}

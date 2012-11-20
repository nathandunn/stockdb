package edu.uoregon.stockdb

/**
 * Created with IntelliJ IDEA.
 * User: NathanDunn
 * Date: 11/19/12
 * Time: 2:34 PM
 * To change this template use File | Settings | File Templates.
 */
class StubData {

    static stubData() {

        if (!HostFacility.count) {
            new HostFacility([name: "Washington University"]).save()
            new HostFacility([name: "University of Oregon"]).save()
        }
        Stock guilleminFreezer = Stock.findByName("Guillemin -80 C Freezer")?:new Stock([name: "Guillemin -80 C Freezer"]).save()

        Phylum proteobacteria = Phylum.findByName("Proteobacteria (gamma)") ?: new Phylum([name: "Proteobacteria (gamma)"]).save()
        Phylum frimicutes = Phylum.findByName("Firmicutes") ?: new Phylum([name: "Firmicutes"]).save()
        Phylum tenerictues = Phylum.findByName("Tenericutes (Mollicutes)") ?: new Phylum([name: "Tenericutes (Mollicutes)"]).save()
        Phylum bacteroidetes = Phylum.findByName("Bacteroidetes") ?: new Phylum([name: "Bacteroidetes"]).save()
        Phylum fusobacteria = Phylum.findByName("Fusobacteria") ?: new Phylum([name: "Fusobacteria"]).save()

        Origin unknownOrigin = Origin.findByName("unknown")?:new Origin([name: "unknown"]).save()
        Origin zebrafishWtOrigin = Origin.findByName("Larval Zebrafish (WT) Intestine")?:new Origin([name: "Larval Zebrafish (WT) Intestine"]).save()

        if (!Genus.count) {
            Genus plesiomans = new Genus([name: "Plesiomonas",phylum:fusobacteria]).save()
            Genus aeromonas = new Genus([name: "Aeromonas",phylum:fusobacteria]).save()
            Genus pseudomonas = new Genus([name: "Pseudomonas",phylum:fusobacteria]).save()
            Genus shewanella = new Genus([name: "Shewanella",phylum:fusobacteria]).save()

            Strain s1 = new Strain([name: "ZWU0023",genus:plesiomans
                    ,phylum: proteobacteria
                    ,origin:unknownOrigin
            ]).save()
            Strain s2 = new Strain([name: "ZOR0001",genus:aeromonas
                    ,phylum: proteobacteria
                    ,origin:unknownOrigin
            ]).save()
        }


    }

}

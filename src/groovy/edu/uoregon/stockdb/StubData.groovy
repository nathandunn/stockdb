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
        Location guilleminFreezer = Location.findByName("Guillemin -80 C Freezer") ?: new Location([name: "Guillemin -80 C Freezer"]).save(failOnError: true)

        Phylum proteobacteria = Phylum.findByName("Proteobacteria (gamma)") ?: new Phylum([name: "Proteobacteria (gamma)"]).save()
        Phylum frimicutes = Phylum.findByName("Firmicutes") ?: new Phylum([name: "Firmicutes"]).save()
        Phylum tenerictues = Phylum.findByName("Tenericutes (Mollicutes)") ?: new Phylum([name: "Tenericutes (Mollicutes)"]).save()
        Phylum bacteroidetes = Phylum.findByName("Bacteroidetes") ?: new Phylum([name: "Bacteroidetes"]).save()
        Phylum fusobacteria = Phylum.findByName("Fusobacteria") ?: new Phylum([name: "Fusobacteria"]).save()


        if (!Genus.count) {
            Genus plesiomans = new Genus([name: "Plesiomonas", phylum: fusobacteria]).save()
            Genus aeromonas = new Genus([name: "Aeromonas", phylum: fusobacteria]).save()
            Genus pseudomonas = new Genus([name: "Pseudomonas", phylum: fusobacteria]).save()
            Genus shewanella = new Genus([name: "Shewanella", phylum: fusobacteria]).save()

            HostOrigin unknownOrigin = HostOrigin.findByName("unknown") ?: new HostOrigin([name: "unknown",genus: plesiomans,phylum: proteobacteria]).save(failOnError: true)
            HostOrigin zebrafishWtOrigin = HostOrigin.findByName("Larval Zebrafish (WT) Intestine") ?: new HostOrigin([name: "Larval Zebrafish (WT) Intestine",genus: plesiomans,phylum: proteobacteria]).save()

            Stock stock1 = new Stock([name: "Box 1-D", location: guilleminFreezer]).save()
            Stock stock2 = new Stock([name: "Box 2-D", location: guilleminFreezer]).save()
            guilleminFreezer.addToStocks(stock1)
            guilleminFreezer.addToStocks(stock2)

            Strain s1 = new Strain([name: "ZWU0023",
                    genus: Genus.findByName("Plesiomonas")
                    , phylum: proteobacteria
                    , origin: unknownOrigin
                    , stock: stock1
            ]).save(failOnError: true,flush: true,insert: true)
            Strain s2 = new Strain([name: "ZOR0001",
                    genus: Genus.findByName("Aeromonas")
                    , phylum: proteobacteria
                    , origin: unknownOrigin
                    , stock: stock2
            ]).save()

            stock1.strain = s1
            stock2.strain = s2
            stock1.save()
            stock2.save()
        }


    }

}

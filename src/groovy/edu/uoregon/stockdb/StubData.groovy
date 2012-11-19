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

        Phylum proteobacteria = Phylum.findByName("Proteobacteria (gamma)") ?: new Phylum([name: "Proteobacteria (gamma)"]).save()
        Phylum frimicutes = Phylum.findByName("Firmicutes") ?: new Phylum([name: "Firmicutes"]).save()
        Phylum tenerictues = Phylum.findByName("Tenericutes (Mollicutes)") ?: new Phylum([name: "Tenericutes (Mollicutes)"]).save()
        Phylum bacteroidetes = Phylum.findByName("Bacteroidetes") ?: new Phylum([name: "Bacteroidetes"]).save()
        Phylum fusobacteria = Phylum.findByName("Fusobacteria") ?: new Phylum([name: "Fusobacteria"]).save()

        if (!Genus.count) {
            Genus plesiomans = new Genus([name: "Plesiomonas"]).save()
            plesiomans.phylum = fusobacteria
        }

    }

}

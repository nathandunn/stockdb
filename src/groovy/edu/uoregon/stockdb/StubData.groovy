package edu.uoregon.stockdb

import au.com.bytecode.opencsv.CSVReader

/**
 * Created with IntelliJ IDEA.
 * User: NathanDunn
 * Date: 11/19/12
 * Time: 2:34 PM
 * To change this template use File | Settings | File Templates.
 */
class StubData {

    static stubData() {

        File file = new File("doc/database1.csv")
        if (!file.exists()) {
            throw new RuntimeException("File does not exist: " + file)
        }

//        file.eachCsvLine([skipLines:1,'charset':'UTF-8']) { tokens ->
        CSVReader csvReader = file.toCsvReader(skipLines: 1, 'charset': 'UTF-8')
        csvReader.eachLine { tokens ->
//            String phylum = tokens[3]
            Genus genus = tokens[1] ? Genus.findOrSaveByName(tokens[1]) : null
            Phylum phylum = tokens[3] ? Phylum.findOrSaveByName(tokens[3]) : null


//            String hostFacility = tokens[8]
            HostFacility hostFacility = tokens[8] ? HostFacility.findOrSaveByName(tokens[8]) : null

            HostOrigin hostOrigin = tokens[7] ? HostOrigin.findOrSaveByName(tokens[7]) : null

            Location location = tokens[9] ? Location.findOrSaveByName(tokens[9]) : null
            Stock stock = tokens[2] ? Stock.findOrSaveByName(tokens[2]) : null

            Strain strain = tokens[0] ? Strain.findOrSaveByName(tokens[0]) : null
            if (strain) {
                strain.sequenceUrl = tokens[11]?.startsWith("http") ? tokens[11] : null
                if (tokens[16]) {
                    strain.isolatedWhen = Date.parse("d/M/yyyy", tokens[16])
                }
                strain.formerCloneAlias = tokens[17]
                strain.motility = tokens[18]
                strain.notes = tokens[23]

                // we only want to record these if a valid Strain

                if(genus && phylum){
                    genus.phylum = phylum
                    phylum.addToGenuses(genus)
                    strain.genus = genus
                    strain.phylum = phylum
                }
                if (hostOrigin && hostFacility) {
                    hostOrigin.hostFacility = hostFacility
                    hostFacility.addToOrigins(hostOrigin)
                    strain.origin = hostOrigin
                }

                if (stock && location) {
                    stock.location = location
                    location.addToStocks(stock)
                    strain.addToStocks(stock)
                }
            }

//            String strainName = tokens[0]
//            String sequenceUrl = tokens[11]
//            String dateOfIsolation = tokens[16]
//            String formerCloneAlias = tokens[17]
//            String motility = tokens[18]
//            String notes = tokens[23]
        }

//        if (!HostFacility.count) {
//            new HostFacility([name: "Washington University"]).save()
//            new HostFacility([name: "University of Oregon"]).save()
//        }
//        Location guilleminFreezer = Location.findByName("Guillemin -80 C Freezer") ?: new Location([name: "Guillemin -80 C Freezer"]).save(failOnError: true)
//
//        Phylum proteobacteria = Phylum.findByName("Proteobacteria (gamma)") ?: new Phylum([name: "Proteobacteria (gamma)"]).save()
//        Phylum frimicutes = Phylum.findByName("Firmicutes") ?: new Phylum([name: "Firmicutes"]).save()
//        Phylum tenerictues = Phylum.findByName("Tenericutes (Mollicutes)") ?: new Phylum([name: "Tenericutes (Mollicutes)"]).save()
//        Phylum bacteroidetes = Phylum.findByName("Bacteroidetes") ?: new Phylum([name: "Bacteroidetes"]).save()
//        Phylum fusobacteria = Phylum.findByName("Fusobacteria") ?: new Phylum([name: "Fusobacteria"]).save()
//
//
//        if (!Genus.count) {
//            Genus plesiomans = new Genus([name: "Plesiomonas", phylum: fusobacteria]).save()
//            Genus aeromonas = new Genus([name: "Aeromonas", phylum: fusobacteria]).save()
//            Genus pseudomonas = new Genus([name: "Pseudomonas", phylum: fusobacteria]).save()
//            Genus shewanella = new Genus([name: "Shewanella", phylum: fusobacteria]).save()
//
//            HostOrigin unknownOrigin = HostOrigin.findByName("unknown") ?: new HostOrigin([name: "unknown",genus: plesiomans,phylum: proteobacteria]).save(failOnError: true)
//            HostOrigin zebrafishWtOrigin = HostOrigin.findByName("Larval Zebrafish (WT) Intestine") ?: new HostOrigin([name: "Larval Zebrafish (WT) Intestine",genus: plesiomans,phylum: proteobacteria]).save()
//
//            Stock stock1 = new Stock([name: "Box 1-D", location: guilleminFreezer]).save()
//            Stock stock2 = new Stock([name: "Box 2-D", location: guilleminFreezer]).save()
//            guilleminFreezer.addToStocks(stock1)
//            guilleminFreezer.addToStocks(stock2)
//
//            Strain s1 = new Strain([name: "ZWU0023",
//                    genus: Genus.findByName("Plesiomonas")
//                    , phylum: proteobacteria
//                    , origin: unknownOrigin
//                    , stock: stock1
//            ]).save(failOnError: true,flush: true,insert: true)
//            Strain s2 = new Strain([name: "ZOR0001",
//                    genus: Genus.findByName("Aeromonas")
//                    , phylum: proteobacteria
//                    , origin: unknownOrigin
//                    , stock: stock2
//            ]).save()
//
//            stock1.strain = s1
//            stock2.strain = s2
//            stock1.save()
//            stock2.save()
//        }


    }

}

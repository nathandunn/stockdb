package edu.uoregon.stockdb

import au.com.bytecode.opencsv.CSVReader
import org.apache.log4j.Logger

/**
 * Created with IntelliJ IDEA.
 * User: NathanDunn
 * Date: 11/19/12
 * Time: 2:34 PM
 * To change this template use File | Settings | File Templates.
 */
//@CompileStatic
class StubData {

    private static final log = Logger.getLogger(this)

    def stubData() {

        if (Strain.count > 0) return

        // stub crap for demo, not for use with a real database

        def servletContext = org.codehaus.groovy.grails.web.context.ServletContextHolder.servletContext
        def file = servletContext.getResourceAsStream("/WEB-INF/database1.csv")
//        def file = servletContext.getResourceAsStream("database1.csv")
//        if ((new File("web-app")).exists()) {
//            file = new File("web-app/WEB-INF/database1.csv")
//        }
//        else {
//            file = new File("./database1.csv")
//        }
        if (!file) {
            throw new RuntimeException("File does not exist: " + file)
        }

//        file.eachCsvLine([skipLines:1,'charset':'UTF-8']) { tokens ->
        CSVReader csvReader = file.toCsvReader(skipLines: 1, 'charset': 'UTF-8')
        csvReader.eachLine { tokens ->
//            String phylum = tokens[3]
            Strain strain = tokens[0] ? Strain.findOrSaveByName(tokens[0]) : null
            Genus genus = tokens[1] ? Genus.findOrSaveByName(tokens[1]) : null
            Stock stock = tokens[2] ? Stock.findOrSaveByName(tokens[2]) : null
            Phylum phylum = tokens[3] ? Phylum.findOrSaveByName(tokens[3]) : null

            // 4 ignore
            // 5 ignore
            // 6 ignore

            String originString = tokens[7]
            Origin origin
            if (originString) {
                String originSpecies = "Zebrafish" // for now
                if (originString.indexOf(originSpecies) > 0) {
                    String stage = originString.substring(0, originString?.indexOf(originSpecies))?.trim()
                    Integer startParens = originString.indexOf("(")
                    Integer endParens = originString.indexOf(")")
                    if (startParens > 0 && endParens > 0) {
//                        println "startParens ${startParens} endParens ${endParens}"
                        String genotypeString = originString.substring(startParens + 1, endParens)
                        String anatomy = originString.substring(endParens)

                        ZebrafishGenotype zebrafishGenotype = ZebrafishGenotype.findOrSaveByName(genotypeString)
                        origin = Origin.findOrSaveByGenotypeAndAnatomyAndStage(zebrafishGenotype, anatomy, stage)
                    }
                }
            }

//            String hostFacility = tokens[8]
            HostFacility hostFacility = tokens[8] ? HostFacility.findOrSaveByName(tokens[8]) : null


            Location location = tokens[9] ? Location.findOrSaveByName(tokens[9]) : null

            if (strain) {
                Genome genome = new Genome()
                genome.url = tokens[11]?.startsWith("http") ? tokens[11] : null
                genome.save()

                strain.genome = genome

                Isolate isolate = new Isolate()
                if (tokens[16]) {
                    isolate.isolatedWhen = Date.parse("d/M/yyyy", tokens[16])
                    isolate.save()
//                    isolate.isolatedBy = Researcher // 17
                    strain.isolate = isolate
                }

                strain.formerCloneAlias = tokens[18]
//                strain.motility = tokens[19] // will be a measured value
                strain.notes = tokens[24]

                // we only want to record these if a valid Strain

                if (genus && phylum) {
                    genus.phylum = phylum
                    phylum.addToGenuses(genus)
                    strain.genus = genus
                    strain.phylum = phylum
                }
                if (origin && hostFacility) {
                    origin.hostFacility = hostFacility
                    hostFacility.addToOrigins(origin)
                    strain.origin = origin
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

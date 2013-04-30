package edu.uoregon.stockdb

import au.com.bytecode.opencsv.CSVReader
import org.apache.log4j.Logger

/**
 */
//@CompileStatic
class StubData {

    private static final log = Logger.getLogger(this)

    def stubData() {

        Lab lab = Lab.findOrSaveByName("Guillemin")

        if (Strain.count > 0) return 0

        Phylum chordata = Phylum.findOrSaveByName("Chordata")
        chordata.host = true
        chordata.save()
        Genus danio = Genus.findOrSaveByNameAndPhylum("Danio", chordata)
        danio.host = true
        danio.save()
        Species rerio = Species.findOrSaveByNameAndCommonNameAndGenus("Rerio", "Zebrafish", danio)

        def servletContext = org.codehaus.groovy.grails.web.context.ServletContextHolder.servletContext
        def file = servletContext.getResourceAsStream("/WEB-INF/database1.csv")
        if (!file) {
            throw new RuntimeException("File does not exist: " + file)
        }

        CSVReader csvReader = file.toCsvReader(skipLines: 1, 'charset': 'UTF-8')

        int rowIndex = 1
        csvReader.eachLine { tokens ->
            if (!tokens[0]) return Strain.count

            println "row ${rowIndex} - ${tokens[0]}"

            Strain strain = new Strain(name: tokens[0])

            Phylum phylum = Phylum.findByName(tokens[3].trim())
            if (phylum == null) {
                phylum = new Phylum(name: tokens[3]).save(flush: true)
            }
            Genus genus = Genus.findByNameAndPhylum(tokens[1].trim(), phylum)
            if (genus == null) {
                genus = new Genus(name: tokens[1].trim(), phylum: phylum).save(flush: true)
            }
            strain.genus = genus
            strain.save()

            // 4 ignore
            // 5 ignore
            // 6 ignore
            String originString = tokens[7]
            HostOrigin hostOrigin
            HostFacility hostFacility = tokens[8] ? HostFacility.findOrSaveByName(tokens[8]?.trim()) : null
            if (originString) {
                if (originString.indexOf(rerio.commonName) > 0) {
                    String stage = originString.substring(0, originString?.indexOf(rerio.commonName))?.trim()
                    Integer startParens = originString.indexOf("(")
                    Integer endParens = originString.indexOf(")")
                    if (startParens > 0 && endParens > 0) {
//                        println "startParens ${startParens} endParens ${endParens}"
                        String genotypeString = originString.substring(startParens + 1, endParens)
                        String anatomy = originString.substring(endParens + 1)?.trim()

                        HostGenotype hostGenotype = HostGenotype.findOrSaveByName(genotypeString)
                        Set<HostGenotype> genotypes = new HashSet<>()
                        if (hostGenotype) {
                            genotypes.add(hostGenotype)
                        }

                        hostOrigin = HostOrigin.findOrSaveByAnatomyAndStageAndHostFacilityAndSpecies(anatomy, stage, hostFacility, rerio)
                        hostOrigin.setStageAndDpf(stage)
                        if (hostOrigin?.genotypes != genotypes) {
                            hostOrigin.genotypes = genotypes
                            hostOrigin.save(insert: true, failOnError: true, flush: true)
                        }

                        hostOrigin.species = rerio
                        hostOrigin.anatomyUrl = "http://zfin.org/action/ontology/term-detail/ZDB-TERM-100331-1295"
                    }
                    // just age and species
                    else {
                        hostOrigin = HostOrigin.findOrSaveByAnatomyAndStageAndHostFacilityAndSpecies(null, stage, hostFacility, rerio)
                        hostOrigin.setStageAndDpf(stage)
                        hostOrigin.species = rerio
                    }
                }
            }

//            String hostFacility = tokens[8]


            String[] isolateData = tokens[10]?.split(";")


            IsolateCondition isolateCondition
            if (isolateData.length > 2) {
                String oxygenCondition = isolateData[0]?.trim()
                Float temperature
                try {
                    temperature = Float.parseFloat(isolateData[1]?.trim()?.replaceAll("C", ""))
                } catch (e) {
                    println(e)
                    temperature = Float.MIN_VALUE
                }
                String media = isolateData[2]?.trim()
                String notes = ""
                if (isolateData.length > 3) {
                    notes = isolateData[3..isolateData.length - 1]?.join("")
                }
                isolateCondition = IsolateCondition.findOrSaveByOxygenConditionAndTemperatureAndMediaAndNotes(oxygenCondition, temperature, media, notes)
                strain.isolateCondition = isolateCondition
            }

//            if (strain) {
            Genome genome = new Genome()
            genome.url = tokens[11]?.startsWith("http") ? tokens[11] : null
            genome.size = tokens[12] ? Float.parseFloat(tokens[12]) : null
            genome.quality = tokens[13] ? Float.parseFloat(tokens[13]) : null
            genome.note = tokens[14] ?: null
            if (genome.hasValues()) {
                genome.save(flush: true)
                strain.genome = genome
            }


            strain.dateEntered = tokens[15] ? Date.parse("d/M/yyyy", tokens[15]) : null

            if (tokens[16]) {
                if (isolateCondition) {
                    isolateCondition.isolatedWhen = Date.parse("d/M/yyyy", tokens[16])
                    String[] isolatedByString = tokens[17]?.split(" ")
                    if (isolatedByString.size() == 2) {
                        Researcher researcher = Researcher.findOrSaveByFirstNameAndLastName(isolatedByString[0], isolatedByString[1])
                        isolateCondition.isolatedBy = researcher
                        researcher.lab = lab
                        researcher.save(flush: true)
                    }
                    isolateCondition.save()
                }
            }

            strain.formerCloneAlias = tokens[18]

            strain.notes = tokens[24]

            // we only want to record these if a valid Strain

            if (hostOrigin && hostFacility) {
                hostOrigin.hostFacility = hostFacility
                hostFacility.addToOrigins(hostOrigin)
                strain.hostOrigin = hostOrigin
            }

            Stock stock = new Stock()
            stock.strain = strain

            String physicalLocation = tokens[2]

            if (physicalLocation.contains("-") && physicalLocation.startsWith("Box")) {
                println "parsing location ${physicalLocation}"
                Integer boxNumber = physicalLocation.substring(3, physicalLocation.indexOf("-") - 1).trim() == "I" ? 1 : null
                Integer boxIndex = Integer.parseInt(physicalLocation.substring(physicalLocation.indexOf("-") + 1).trim())
                println "parsing location result ${boxNumber} - ${boxNumber}"

                stock.boxIndex = boxIndex
                stock.boxNumber = boxNumber
            } else {
                println "bad location ${physicalLocation}"
            }

            stock.save()

//            Location generalLocation = tokens[9] ? Location.findOrSaveByName(tokens[9]) : null
            String guilleminLabFreezer = "Guillemin -80 C Freezer"
            Location generalLocation = Location.findOrSaveByName(guilleminLabFreezer)

            if (stock && generalLocation) {
                stock.generalLocation = generalLocation
                generalLocation.addToStocks(stock)
                strain.addToStocks(stock)
            }
//            }

//            String strainName = tokens[0]
//            String sequenceUrl = tokens[11]
//            String dateOfIsolation = tokens[16]
//            String formerCloneAlias = tokens[17]
//            String motility = tokens[18]
//            String notes = tokens[23]

            ++rowIndex
        }


    }

    def stubUsers() {
        if (Researcher.count() > 0) return

        new Researcher(
                firstName: "Adam"
                , lastName: "Burns"
                , email: "aburns2@uoregon.edu"
        ).save()

        new Researcher(
                firstName: "Travis"
                , lastName: "Carney"
                , email: "tcarney@uoregon.edu"
        ).save()

        new Researcher(
                firstName: "Robert"
                , lastName: "Steury"
                , email: "steury@uoregon.edu"
        ).save()
    }

    def stubRawlsData() {
        def servletContext = org.codehaus.groovy.grails.web.context.ServletContextHolder.servletContext
        def file = servletContext.getResourceAsStream("/WEB-INF/RawlsLabIsolateDatabase.tsv")
        if (!file) {
            throw new RuntimeException("File does not exist: " + file)
        }

        CSVReader csvReader = file.toCsvReader(skipLines: 1, 'charset': 'UTF-8', separatorChar: '\t')

        int rowIndex = 1
        csvReader.eachLine { tokens ->
            if (!tokens[0]) return Strain.count

            println "row ${rowIndex} - ${tokens[0]}"

            Strain strain
            // if Strain exists . . .
            strain = Strain.findByName(tokens[0]?.trim())

            if (strain) {
                Location generalLocation = tokens[9] ? Location.findOrSaveByName(tokens[9]) : null

                Stock stock = new Stock()
                stock.strain = strain

                if (stock && generalLocation) {
                    stock.generalLocation = generalLocation
                    generalLocation.addToStocks(stock)
                    strain.addToStocks(stock)
                }
                stock.save(insert: true, flush: true)

            } else {

                // else new Strain
                strain = new Strain(name: tokens[0])

                Phylum phylum = tokens[3] ? Phylum.findOrSaveByName(tokens[3].trim()) : null
                phylum?.save(flush: true)

                Genus genus = tokens[1] ? Genus.findByName(tokens[1].trim()) : null
                if (!genus && tokens[1]) {
                    genus = new Genus(name: tokens[1].trim(), phylum: phylum).save(insert: true, flush: true)
                }
                strain.genus = genus
                strain.save()

                // 4 ignore
                // 5 ignore
                // 6
                Phylum chordata = Phylum.findOrSaveByName("Chordata")
                chordata.host = true
                chordata.save()
                Genus danio = Genus.findOrSaveByNameAndPhylum("Danio", chordata)
                danio.host = true
                danio.save()
                Species rerio = Species.findOrSaveByNameAndCommonNameAndGenus("Rerio", "Zebrafish", danio)

                String originString = tokens[7]
                HostOrigin hostOrigin
                if (originString) {
                    if (originString.indexOf(rerio.commonName) > 0) {
                        String stage = originString.substring(0, originString?.indexOf(rerio.commonName))?.trim()
                        Integer startParens = originString.indexOf("(")
                        Integer endParens = originString.indexOf(")")
                        if (startParens > 0 && endParens > 0) {
//                        println "startParens ${startParens} endParens ${endParens}"
                            String genotypeString = originString.substring(startParens + 1, endParens)
                            String anatomy = originString.substring(endParens + 1)?.trim()

                            HostGenotype hostGenotype = HostGenotype.findOrSaveByName(genotypeString)
                            Set<HostGenotype> genotypes = new HashSet<>()
                            if (hostGenotype) {
                                genotypes.add(hostGenotype)
                            }

                            hostOrigin = HostOrigin.findOrSaveByAnatomyAndStage(anatomy, stage)
                            hostOrigin.setStageAndDpf(stage)
                            if (hostOrigin?.genotypes != genotypes) {
                                hostOrigin.genotypes = genotypes
                                hostOrigin.save(insert: true, failOnError: true, flush: true)
                            }

                            hostOrigin.species = rerio
                            hostOrigin.anatomyUrl = "http://zfin.org/action/ontology/term-detail/ZDB-TERM-100331-1295"
                        }
                        // just age and species
                        else {
                            hostOrigin = HostOrigin.findOrSaveByAnatomyAndStage(null, stage)
                            hostOrigin.setStageAndDpf(stage)
                            hostOrigin.species = rerio
                        }
                    }
                }

                HostFacility hostFacility = tokens[8] ? HostFacility.findOrSaveByName(tokens[8]?.trim()) : null

                Location generalLocation = tokens[9] ? Location.findOrSaveByName(tokens[9]) : null

                String[] isolateData = tokens[10]?.split(";")


                IsolateCondition isolateCondition
                if (isolateData.length > 2) {
                    String oxygenCondition = isolateData[0]?.trim()
                    Float temperature
                    try {
                        temperature = Float.parseFloat(isolateData[1]?.trim()?.replaceAll("C", ""))
                    } catch (e) {
                        println(e)
                        temperature = Float.MIN_VALUE
                    }
                    String media = isolateData[2]?.trim()
                    String notes = ""
                    if (isolateData.length > 3) {
                        notes = isolateData[3..isolateData.length - 1]?.join("")
                    }
                    isolateCondition = IsolateCondition.findOrSaveByOxygenConditionAndTemperatureAndMediaAndNotes(oxygenCondition, temperature, media, notes)
                    strain.isolateCondition = isolateCondition
                }

                Genome genome = new Genome()
                genome.url = tokens[11]?.startsWith("http") ? tokens[11] : null
                genome.size = tokens[12] ? Float.parseFloat(tokens[12]) : null
                genome.quality = tokens[13] ? Float.parseFloat(tokens[13]) : null
                genome.note = tokens[14] ?: null
                if (genome.hasValues()) {
                    genome.save(flush: true)
                    strain.genome = genome
                }


                strain.dateEntered = tokens[15] ? Date.parse("d/M/yyyy", tokens[15]) : null

                if (tokens[16]) {
                    if (isolateCondition) {
                        isolateCondition.isolatedWhen = Date.parse("d/M/yyyy", tokens[16])
                        String[] isolatedByString = tokens[17]?.split(" ")
                        if (isolatedByString.size() == 2) {
                            Researcher researcher = Researcher.findOrSaveByFirstNameAndLastName(isolatedByString[0], isolatedByString[1])
                            isolateCondition.isolatedBy = researcher
                            researcher.lab = lab
                            researcher.save(flush: true)
                        }
                        isolateCondition.save()
                    }
                }

                strain.formerCloneAlias = tokens[18]

                strain.notes = tokens[24]

                // we only want to record these if a valid Strain
                if (hostOrigin && hostFacility) {
                    hostOrigin.hostFacility = hostFacility
                    hostFacility.addToOrigins(hostOrigin)
                    strain.hostOrigin = hostOrigin
                }

                Stock stock = new Stock()
                stock.strain = strain

                if (stock && generalLocation) {
                    stock.generalLocation = generalLocation
                    generalLocation.addToStocks(stock)
                    strain.addToStocks(stock)
                }
            }
            ++rowIndex
        }
    }

    def importExperiments() {

        Category motilityCategory = new Category(name: "Motility").save()
        Experiment motilityExperiment = new Experiment(name: "Motility 1", categories: [motilityCategory]).save()
        Category hemolyticActivityCategory = new Category(name: "HemolyticActivity").save()
        Experiment hemolyticActivityExperiment = new Experiment(name: "HemolyticActivity 1", categories: [hemolyticActivityCategory]).save()
        Category antibioticCategory = new Category(name: "Antibiotic").save()
        Experiment antibioticExperiment = new Experiment(name: "Antibiotic 1", categories: [antibioticCategory]).save()

        Category doublingCategory = new Category(name: "Doubling Time", type: MeasuredValueTypeEnum.DECIMAL).save()
        Experiment doublingExperiment = new Experiment(name: "Doubling Time 1", categories: [doublingCategory]).save()

        Category adherenceCategory = new Category(name: "Adherence").save()
        Experiment adherenceExperiment = new Experiment(name: "Adherence 1", categories: [adherenceCategory]).save()

        def servletContext = org.codehaus.groovy.grails.web.context.ServletContextHolder.servletContext
        def file = servletContext.getResourceAsStream("/WEB-INF/database1.csv")
        if (!file) {
            throw new RuntimeException("File does not exist: " + file)
        }
        CSVReader csvReader = file.toCsvReader(skipLines: 1, 'charset': 'UTF-8')

        int rowIndex = 1
        csvReader.eachLine { tokens ->

            if (tokens[0].trim().size() == 0) return

            Strain strain = Strain.findByName(tokens[0]?.trim())
            if (tokens[19]) {
                MeasuredValue measuredValue = new MeasuredValue(
                        category: motilityCategory
                        ,experiment: motilityExperiment
                        , strain: strain
                        , value: tokens[19].trim()
                )
                        .save(insert: true)
            }

            if (tokens[21]) {
                MeasuredValue measuredValue = new MeasuredValue(
                        category: hemolyticActivityCategory
                        ,experiment: hemolyticActivityExperiment
                        , strain: strain
                        , value: tokens[21].trim()
                )
                        .save(insert: true)
            }

            if (tokens[22]) {
                MeasuredValue measuredValue = new MeasuredValue(
                        category: antibioticCategory
                        ,experiment: antibioticExperiment
                        , strain: strain
                        , value: tokens[22].trim()
                )
                        .save(insert: true)
            }

            if (tokens[23]) {
                MeasuredValue measuredValue = new MeasuredValue(
                        category: doublingCategory
                        ,experiment: doublingExperiment
                        , strain: strain
                        , value: tokens[23].trim()
                )
                        .save(insert: true)
            }

            if (tokens[25]) {
                MeasuredValue measuredValue = new MeasuredValue(
                        category: adherenceCategory
                        ,experiment: adherenceExperiment
                        , strain: strain
                        , value: (tokens[25].trim().substring(1))
                )
                        .save(insert: true, flush: true)
            }

            ++rowIndex
        }

        println "${MeasuredValue.count} Measured Values imported"
    }
}

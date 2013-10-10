package edu.uoregon.stockdb

import au.com.bytecode.opencsv.CSVReader
import org.apache.log4j.Logger
import org.apache.shiro.crypto.hash.Sha256Hash

/**
 */
//@CompileStatic
class StubData {

    private static final log = Logger.getLogger(this)


    def stubData() {

        if (Strain.count > 0) return 0

        Lab lab = Lab.findOrSaveByName("Guillemin")

        GenomeType rastGenomeType = GenomeType.findOrSaveByBaseUrlAndOrganizationName(
                "http://rast.nmpdr.org/rast.cgi?page=JobDetails&job="
                , "Rast"
        )
        GenomeType ncbiBioProjectGenomeType = GenomeType.findOrSaveByBaseUrlAndOrganizationName(
                "http://www.ncbi.nlm.nih.gov/bioproject/"
                , "NCBI BioProject"
        )

        Population riverBend = Population.findOrSaveByNameAndLatitudeAndLongitude("Riber Bend",44.0958354,-123.1253738)


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

//            println "row ${rowIndex} - ${tokens[0]}"

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
            strain.save(flush: true, failOnError: true)

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
//                isolateCondition = IsolateCondition.findOrSaveByOxygenConditionAndTemperatureAndMediaAndNotes(oxygenCondition, temperature, media, notes)
                isolateCondition = IsolateCondition.findOrSaveByOxygenConditionAndTemperatureAndMedia(oxygenCondition, temperature, media)
                if (isolateCondition.notes) {
                    isolateCondition.notes += "\n"
                }
                isolateCondition.notes += notes

//                strain.isolateCondition = isolateCondition
                isolateCondition.addToStrains(strain)
                isolateCondition.isolatedWhen = Date.parse("d/M/yyyy", "1/1/1901")
                isolateCondition.save(failOnError: true)
            }

//            if (strain) {
            if (tokens[11]?.startsWith(rastGenomeType.baseUrl)) {

                Genome genome = new Genome(
                        strain: strain
                        , genomeType: rastGenomeType
                )
                genome.externalId = tokens[11]?.startsWith(rastGenomeType.baseUrl) ? tokens[11].substring(rastGenomeType.baseUrl.length()) : null
                genome.size = tokens[12] ? Float.parseFloat(tokens[12]) : null
                genome.quality = tokens[13] ? Float.parseFloat(tokens[13]) : null
//            genome.note = tokens[14] ?: null
                if (genome.hasValues()) {
                    genome.save(flush: true, failOnError: true)
                    strain.addToGenomes(genome)
                }
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
                        researcher.save(flush: true, failOnError: true)
                    }
                    isolateCondition.save(flush: true, failOnError: true)
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
//                println "parsing location ${physicalLocation}"
                Integer boxNumber = physicalLocation.substring(3, physicalLocation.indexOf("-") - 1).trim() == "I" ? 1 : Integer.MAX_VALUE
                Integer boxIndex = Integer.parseInt(physicalLocation.substring(physicalLocation.indexOf("-") + 1).trim()) ?: Integer.MAX_VALUE
//                println "parsing location result ${boxIndex} - ${boxNumber}"

                stock.boxIndex = boxIndex
                stock.boxNumber = boxNumber
            } else {
                println "bad location ${physicalLocation}"
                stock.boxIndex = Integer.MAX_VALUE
                stock.boxNumber = Integer.MAX_VALUE
            }

            stock.save(failOnError: true)

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

//        def user = new ShiroUser(username: "user123", passwordHash: new Sha256Hash("password").toHex())
//        user.addToPermissions("*:*")
//        user.save()
        def adminRole = new Role(name: ResearcherService.ROLE_ADMINISTRATOR)
        adminRole.addToPermissions("*:*")
        adminRole.save(failOnError: true)

        def userRole = new Role(name: ResearcherService.ROLE_USER)
        userRole.addToPermissions("*:list")
        userRole.addToPermissions("*:show")
        userRole.addToPermissions("experiment:edit")
        userRole.addToPermissions("experiment:update")
        userRole.addToPermissions("researcher:edit")
        userRole.addToPermissions("researcher:update")
        userRole.addToPermissions("strain:addFilter")
        userRole.addToPermissions("strain:showFilter")
        userRole.save(failOnError: true)

        new Researcher(
                firstName: "Adam"
                , lastName: "Burns"
                , username: "aburns2@uoregon.edu"
                , passwordHash: new Sha256Hash("ilikesr16").toHex()
        ).addToRoles(adminRole).save()


        new Researcher(
                firstName: "Travis"
                , lastName: "Carney"
                , username: "tcarney@uoregon.edu"
                , passwordHash: new Sha256Hash("ilikesr16").toHex()
        ).addToRoles(adminRole).save()

        new Researcher(
                firstName: "Robert"
                , lastName: "Steury"
                , username: "steury@uoregon.edu"
                , passwordHash: new Sha256Hash("ilikesr16").toHex()
        ).addToRoles(adminRole).save()

        new Researcher(
                firstName: "Nathan"
                , lastName: "Dunn"
                , username: "ndunn@cas.uoregon.edu"
                , passwordHash: new Sha256Hash("ilikesr16").toHex()
        ).addToRoles(adminRole).save()

        new Researcher(
                firstName: "Test"
                , lastName: "Me"
                , username: "ndunn@me.com"
                , passwordHash: new Sha256Hash("test").toHex()
        ).addToRoles(userRole).save()

        new Researcher(
                firstName: "Chris"
                , lastName: "Wreden"
                , username: "cwreden@uoregon.edu"
                , passwordHash: new Sha256Hash("ilikesr16").toHex()
        ).addToRoles(adminRole).save()

        new Researcher(
                firstName: "Zac"
                , lastName: "Stephens"
                , username: "wzacs1@gmail.com"
                , passwordHash: new Sha256Hash("ilikesr16").toHex()
        ).addToRoles(adminRole).save()

        new Researcher(
                firstName: "Mark"
                , lastName: "Currey"
                , username: "mcurrey@uoregon.edu"
                , passwordHash: new Sha256Hash("ilikesr16").toHex()
        ).addToRoles(adminRole).save()

        new Researcher(
                firstName: "Scott"
                , lastName: "Peirson"
                , username: "pierson2@cas.uoregon.edu"
                , passwordHash: new Sha256Hash("ilikesr16").toHex()
        ).addToRoles(adminRole).save()

        new Researcher(
                firstName: "Don"
                , lastName: "Dixon"
                , username: "ddixon@cas.uoregon.edu"
                , passwordHash: new Sha256Hash("ilikesr16").toHex()
        ).addToRoles(adminRole).save()

        new Researcher(
                firstName: "Daniel"
                , lastName: "Mulkey"
                , username: "mulkey@cas.uoregon.edu"
                , passwordHash: new Sha256Hash("ilikesr16").toHex()
        ).addToRoles(adminRole).save()

        new Researcher(
                firstName: "Emily"
                , lastName: "Schwarz"
                , username: "eshwarz@cs.uoregon.edu"
                , passwordHash: new Sha256Hash("ilikesr16").toHex()
        ).addToRoles(adminRole).save()

    }

    def stubRawlsData() {
        println "start - RAWLS Data"


        GenomeType rastGenomeType = GenomeType.findOrSaveByBaseUrlAndOrganizationName(
                "http://rast.nmpdr.org/rast.cgi?page=JobDetails&job="
                , "Rast"
        )
        def servletContext = org.codehaus.groovy.grails.web.context.ServletContextHolder.servletContext
        def file = servletContext.getResourceAsStream("/WEB-INF/RawlsLabIsolateDatabase.tsv")
        if (!file) {
            throw new RuntimeException("File does not exist: " + file)
        }

        CSVReader csvReader = file.toCsvReader(skipLines: 1, 'charset': 'UTF-8', separatorChar: '\t')

        int rowIndex = 1
        csvReader.eachLine { tokens ->
            if (!tokens[0]) return Strain.count

//            println "row ${rowIndex} - ${tokens[0]}"

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
                generalLocation.save(flush: true, failOnError: true)
                stock.save(insert: true, flush: true)

            } else {

                // else new Strain
                strain = new Strain(name: tokens[0])

                Phylum phylum = tokens[3] ? Phylum.findOrSaveByName(tokens[3].trim()) : null
                phylum?.save(flush: true, failOnError: true)

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
                generalLocation.save(flush: true, failOnError: true)

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

                if (tokens[11]?.startsWith(rastGenomeType.baseUrl)) {
                    Genome genome = new Genome(
                            strain: strain
                            , genomeType: rastGenomeType
                    )
                    genome.externalId = tokens[11]?.startsWith(rastGenomeType.baseUrl) ? tokens[11].substring(rastGenomeType.baseUrl.length()) : null
                    genome.size = tokens[12] ? Float.parseFloat(tokens[12]) : null
                    genome.quality = tokens[13] ? Float.parseFloat(tokens[13]) : null
//                genome.note = tokens[14] ?: null
                    if (genome.hasValues()) {
                        genome.save(flush: true)
                        strain.addToGenomes(genome)
                    }
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
                        isolateCondition.save(failOnError: true)
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
                        , experiment: motilityExperiment
                        , strain: strain
                        , value: tokens[19].trim()
                )
                        .save(insert: true, failOnError: true)
            }

            if (tokens[21]) {
                MeasuredValue measuredValue = new MeasuredValue(
                        category: hemolyticActivityCategory
                        , experiment: hemolyticActivityExperiment
                        , strain: strain
                        , value: tokens[21].trim()
                )
                        .save(insert: true, failOnError: true)
            }

            if (tokens[22]) {
                MeasuredValue measuredValue = new MeasuredValue(
                        category: antibioticCategory
                        , experiment: antibioticExperiment
                        , strain: strain
                        , value: tokens[22].trim()
                )
                        .save(insert: true, failOnError: true)
            }

            if (tokens[23]) {
                MeasuredValue measuredValue = new MeasuredValue(
                        category: doublingCategory
                        , experiment: doublingExperiment
                        , strain: strain
                        , value: tokens[23].trim()
                )
                        .save(insert: true, failOnError: true)
            }

            if (tokens[25]) {
                MeasuredValue measuredValue = new MeasuredValue(
                        category: adherenceCategory
                        , experiment: adherenceExperiment
                        , strain: strain
                        , value: (tokens[25].trim().substring(1))
                )
                        .save(insert: true, flush: true, failOnError: true)
            }

            ++rowIndex
        }

        println "${MeasuredValue.count} Measured Values imported"
    }

    def stubPhylogeny() {

        def servletContext = org.codehaus.groovy.grails.web.context.ServletContextHolder.servletContext
        def file = servletContext.getResourceAsStream("/WEB-INF/phylogenymap.csv")
        if (!file) {
            throw new RuntimeException("File does not exist: " + file)
        }

        CSVReader csvReader = file.toCsvReader(skipLines: 1, 'charset': 'UTF-8')
        int rowIndex = 1
        csvReader.eachLine { tokens ->
            if (tokens[0].trim().size() == 0) return

            // column 0 = Phylum
            // column 4 = Genus
            Phylum phylum = Phylum.findOrSaveByName(tokens[0]?.trim())
            Genus genus = Genus.findOrSaveByName(tokens[4]?.trim())

            if (genus.phylum != phylum) {
                genus.phylum = phylum
                genus.save(flush: true)
            }


        }
    }

    def stubStickleback1() {
        // Strain[0],Box[1],Position[2],Genus[3],Species[4-unused],
        // Ana/ aerobic[5],Expt date[6],fish age[7],Med[8],
        // FishOrigin[9],Origin[10],Anatomy of Origin[11],Size[12],
        // Color[13],Edge[14],Raised?[15],Shape[16],Notes?[17]

        //To change body of created methods use File | Settings | File Templates.
        def servletContext = org.codehaus.groovy.grails.web.context.ServletContextHolder.servletContext
        def file = servletContext.getResourceAsStream("/WEB-INF/stickleback-data.csv")
        if (!file) {
            throw new RuntimeException("File does not exist: " + file)
        }

        Genus sticklebackGenus = Genus.findByName("Gasterosteus")
        Species sticklebackSpecies = Species.findByName("Stickleback")
        Location guilleminLabFreezer = Location.findByName("Guillemin -80 C Freezer")

        Category categorySize = Category.findOrSaveByName("Size")
        Category categoryColor = Category.findOrSaveByName("Color")
        Category categoryEdge = Category.findOrSaveByName("Edge")
        Category categoryRaised = Category.findOrSaveByName("Raised")
        Category categoryShape= Category.findOrSaveByName("Shape")

        Researcher kat = Researcher.findByUsername("kmilliga@uoregon.edu")


        CSVReader csvReader = file.toCsvReader(skipLines: 1, 'charset': 'UTF-8')
        int rowIndex = 1
        csvReader.eachLine { tokens ->
            if (tokens[0].trim().size() == 0) return

            String strainName = generateStrainName()

            Strain strain = new Strain(name:strainName)
            strain.notes = tokens[0]? "Old Strain Index: ${tokens[0]?.trim()}": ""

            if (tokens[3]?.trim()) {
                Genus genus = Genus.findByName(tokens[3]?.trim())
                strain.genus = genus
            }

            strain.save(flush: true, failOnError: true)


            Stock stock = new Stock()
            stock.strain = strain

            Integer boxIndex = tokens[1]?.trim() ? tokens[1]?.trim() as Integer : Integer.MAX_VALUE
            Integer boxNumber = tokens[2]?.trim() ? tokens[2]?.trim() as Integer : Integer.MAX_VALUE
            stock.boxIndex = boxIndex
            stock.boxNumber = boxNumber
            stock.generalLocation = guilleminLabFreezer

            strain.addToStocks(stock)
            stock.save(failOnError: true)


            // 4 ignore
            // 5 ignore
            // 6 ignore

            // isolate stuff
            String oxygenCondition = tokens[5]?.trim()
            String media = tokens[8]?.trim()
            IsolateCondition isolateCondition = IsolateCondition.findOrSaveByMediaAndOxygenConditionAndIsolatedBy(media,oxygenCondition,kat)
            strain.isolateCondition = isolateCondition
            isolateCondition.addToStrains(strain)
            isolateCondition.save(flush: true,failOnError: true)



            // experiment stuff
            String experimentDateString = tokens[6]?.trim()
            Experiment experiment = Experiment.findOrSaveByName(experimentDateString)
            experiment.save(flush: true)
            strain.save()

            String sizeValue = tokens[12]?.trim()
            MeasuredValue measuredValueSize = new MeasuredValue(
                    experiment: experiment
                    ,value: sizeValue
                    ,category: categorySize
                    ,strain: strain
            ).save(flush: true, failOnError: true,insert:true)

            String colorValue = tokens[13]?.trim()
            MeasuredValue measuredValueColor = new MeasuredValue(
                    experiment: experiment
                    ,value: colorValue
                    ,category: categoryColor
                    ,strain: strain
            ).save(flush: true, failOnError: true,insert:true)
            String edgeValue = tokens[14]?.trim()
            MeasuredValue measuredValueEdge = new MeasuredValue(
                    experiment: experiment
                    ,value: edgeValue
                    ,category: categoryEdge
                    ,strain: strain
            ).save(flush: true, failOnError: true,insert:true)
            String raisedValue = tokens[15]?.trim()
            MeasuredValue measuredValueRaised = new MeasuredValue(
                    experiment: experiment
                    ,value: raisedValue
                    ,category: categoryRaised
                    ,strain: strain
            ).save(flush: true, failOnError: true,insert:true)

            String shapeValue = tokens[16]?.trim()
            MeasuredValue measuredValueShape = new MeasuredValue(
                    experiment: experiment
                    ,value: shapeValue
                    ,category: categoryShape
                    ,strain: strain
            ).save(flush: true, failOnError: true,insert:true)
            String note = tokens[17]?.trim()
            experiment.note = note


//            String anatomy = "Intenstine"
            String anatomy = tokens[11]?.trim()
            // host origin

            String fishAgeString = tokens[7]?.trim()
            HostOrigin hostOrigin
            HostFacility hostFacility = tokens[10] ? HostFacility.findOrSaveByName(tokens[10]?.trim()) : null
            hostOrigin = HostOrigin.findOrSaveByAnatomyAndStageAndHostFacilityAndSpecies(anatomy, fishAgeString, hostFacility,sticklebackSpecies)
            String populationString = tokens[9]?.trim()
            if(populationString){
                if(populationString.startsWith("R")){
                    populationString = "Rabbit Slough"
                }
                else
                if(populationString.startsWith("B")){
                    populationString = "Boot Lake"
                }
                else
                if(populationString.startsWith("R")){
                    populationString = "River Bend"
                }
                Population population = Population.findByName(populationString)
                if(!population){
                    population = new Population(name:populationString).save()
                }
                hostOrigin.population = population
            }


//            String originString = tokens[7]
//            HostOrigin hostOrigin
//            HostFacility hostFacility = tokens[8] ? HostFacility.findOrSaveByName(tokens[8]?.trim()) : null
//            if (originString) {
//                if (originString.indexOf(rerio.commonName) > 0) {
//                    String stage = originString.substring(0, originString?.indexOf(rerio.commonName))?.trim()
//                    Integer startParens = originString.indexOf("(")
//                    Integer endParens = originString.indexOf(")")
//                    if (startParens > 0 && endParens > 0) {
////                        println "startParens ${startParens} endParens ${endParens}"
//                        String genotypeString = originString.substring(startParens + 1, endParens)
//                        String anatomy = originString.substring(endParens + 1)?.trim()
//
//                        HostGenotype hostGenotype = HostGenotype.findOrSaveByName(genotypeString)
//                        Set<HostGenotype> genotypes = new HashSet<>()
//                        if (hostGenotype) {
//                            genotypes.add(hostGenotype)
//                        }
//
//                        hostOrigin = HostOrigin.findOrSaveByAnatomyAndStageAndHostFacilityAndSpecies(anatomy, stage, hostFacility, rerio)
//                        hostOrigin.setStageAndDpf(stage)
//                        if (hostOrigin?.genotypes != genotypes) {
//                            hostOrigin.genotypes = genotypes
//                            hostOrigin.save(insert: true, failOnError: true, flush: true)
//                        }
//
//                        hostOrigin.species = rerio
//                        hostOrigin.anatomyUrl = "http://zfin.org/action/ontology/term-detail/ZDB-TERM-100331-1295"
//                    }
//                    // just age and species
//                    else {
//                        hostOrigin = HostOrigin.findOrSaveByAnatomyAndStageAndHostFacilityAndSpecies(null, stage, hostFacility, rerio)
//                        hostOrigin.setStageAndDpf(stage)
//                        hostOrigin.species = rerio
//                    }
//                }
//            }


        }
    }

    String generateStrainName() {
        List<Strain> strainList = Strain.createCriteria().list{
            like("name","SBOR%")
            order("name","desc")
            maxResults(1)
        }
        Strain maxStrain = strainList ? strainList.get(0) : null
        String maxStrainName = maxStrain?.name?.substring(3)
        Integer maxInteger = Integer.parseInt(maxStrainName)
        ++maxInteger

//        String returnString = "ZOR" + String.pa(maxInteger+1).

        String returnString = "SBOR" + maxInteger.toString().padLeft(4,"0")

        return returnString
    }
}

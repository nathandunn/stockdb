package edu.uoregon.stockdb

import org.grails.datastore.mapping.query.api.Criteria
import org.springframework.dao.DataIntegrityViolationException

class StrainController {

    def strainService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    private static String STRAIN_FILTER = "strainFilter"
    private static String CLASS_DELIMITER = ":"
    private static String HOST_SPECIES_FILTER = "host.species"
    private static String HOST_ANATOMY_FILTER = "host.anatomy"
    private static String GENUS_FILTER = "genus"
    private static String PHYLUM_FILTER = "phylum"
    private static String GENOME_AVAILABLE_FILTER = "genome.available"

//    static navigation = [
//            title: 'Strain', action: 'list', order: 0
//    ]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max ?: Math.min(max ?: 10, 100)

//		params.max = params.max == "" || params.max == null ? 10 : params.max
		
        Map<String, String> strainFilters = (Map<String, String>) request.session.getAttribute(STRAIN_FILTER)

//        println "filters ${strainFilters}"

        // TODO: put this in in 2.3
        // http://jira.grails.org/browse/GRAILS-8257?page=com.atlassian.jira.plugin.system.issuetabpanels:all-tabpanel
//        def criteria = new DetachedCriteria(Strain).build {
//            for (def filter in strainFilters?.keySet()) {
//                if (filter == GENUS_FILTER) {
//                    eq("genus.id", Long.valueOf(strainFilters.get(filter)))
//                }
//                if (filter == PHYLUM_FILTER) {
////                    eq "assignee.id",
//                    Phylum phylum = Phylum.findById(Long.valueOf(strainFilters.get(filter)))
//                    List<Genus> genusList = Genus.findAllByPhylum(phylum)
////                    join "phylum"
////                    eq("phylum.id", Long.valueOf(strainFilters.get(Phylum.class.canonicalName)))
//                    inList("genus", genusList)
//                }
//                if (filter == HOST_SPECIES_FILTER) {
//                    Species species = Species.findById(Long.valueOf(strainFilters.get(filter)))
//                    List<HostOrigin> hostOriginList = HostOrigin.findAllBySpecies(species)
//
//                    inList("hostOrigin", hostOriginList)
//                }
//                if (filter == HOST_ANATOMY_FILTER) {
//                    List<HostOrigin> hostOriginList = HostOrigin.findAllByAnatomy(strainFilters.get(filter))
//                    inList("hostOrigin", hostOriginList)
//                }
//
//            }
//        }
//
//        [strainInstanceList: criteria.list(params), strainInstanceTotal: criteria.list().size(), strainFilters: strainFilters]

        Criteria criteria1 = Strain.createCriteria()
        def result1 = criteria1.list(params) {
            for (def filter in strainFilters?.keySet()) {
                if (filter == GENUS_FILTER) {
                    eq("genus.id", Long.valueOf(strainFilters.get(filter)))
                }
                if (filter == PHYLUM_FILTER) {
//                    eq "assignee.id",
                    Phylum phylum = Phylum.findById(Long.valueOf(strainFilters.get(filter)))
                    List<Genus> genusList = Genus.findAllByPhylum(phylum)
//                    join "phylum"
//                    eq("phylum.id", Long.valueOf(strainFilters.get(Phylum.class.canonicalName)))
                    inList("genus", genusList)
                }
                if (filter == HOST_SPECIES_FILTER) {
                    Species species = Species.findById(Long.valueOf(strainFilters.get(filter)))
                    List<HostOrigin> hostOriginList = HostOrigin.findAllBySpecies(species)

                    inList("hostOrigin", hostOriginList)
                }
                if (filter == HOST_ANATOMY_FILTER) {
                    List<HostOrigin> hostOriginList = HostOrigin.findAllByAnatomy(strainFilters.get(filter))
                    inList("hostOrigin", hostOriginList)
                }
                if (filter == GENOME_AVAILABLE_FILTER && strainFilters.get(filter)=="true") {
                    isNotEmpty("genomes")
                }
            }
        }

        // NOTE: NO Params . . notice fix above with 2.3
        Criteria criteria2 = Strain.createCriteria()
        def result2 = criteria2.list {
            for (def filter in strainFilters?.keySet()) {
                if (filter == GENUS_FILTER) {
                    eq("genus.id", Long.valueOf(strainFilters.get(filter)))
                }
                if (filter == PHYLUM_FILTER) {
//                    eq "assignee.id",
                    Phylum phylum = Phylum.findById(Long.valueOf(strainFilters.get(filter)))
                    List<Genus> genusList = Genus.findAllByPhylum(phylum)
//                    join "phylum"
//                    eq("phylum.id", Long.valueOf(strainFilters.get(Phylum.class.canonicalName)))
                    inList("genus", genusList)
                }
                if (filter == HOST_SPECIES_FILTER) {
                    Species species = Species.findById(Long.valueOf(strainFilters.get(filter)))
                    List<HostOrigin> hostOriginList = HostOrigin.findAllBySpecies(species)

                    inList("hostOrigin", hostOriginList)
                }
                if (filter == HOST_ANATOMY_FILTER) {
                    List<HostOrigin> hostOriginList = HostOrigin.findAllByAnatomy(strainFilters.get(filter))
                    inList("hostOrigin", hostOriginList)
                }
                if (filter == GENOME_AVAILABLE_FILTER && strainFilters.get(filter)=="true") {
                    isNotEmpty("genomes")
                }
            }
        }

        [strainInstanceList: result1, strainInstanceTotal: result2.size(), strainFilters: strainFilters]

    }

    def addFilter() {
        Map<String, String> strainFilter = request.session.getAttribute(STRAIN_FILTER)
        String newFilter = params.strainFilter
        String filterName = newFilter.split(CLASS_DELIMITER)[0].trim()
        String filterValue = newFilter.split(CLASS_DELIMITER)[1].trim()

        if (!strainFilter) {
            strainFilter = new HashMap<String, String>()
        }
        if (filterValue && !filterValue.contains("null")) {
            strainFilter[filterName] = filterValue
        } else {
            strainFilter.remove(filterName)
        }
        request.session.setAttribute(STRAIN_FILTER, strainFilter)
        redirect(action: "list")
    }

    def create() {
//        HostFacility hostFacility = HostFacility.findByName("University of Oregon")
//        Species species = Species.findByCommonName("Zebrafish")
//        params.name = strainService.createStrainName(species,hostFacility)
        [strainInstance: new Strain(params)]
    }


    def showFilter(String strainName){
        List<Strain> strainList = Strain.findAllByNameIlike("%"+strainName+"%")
        if(strainList.empty){
            flash.message = "No strains found for '${strainName}'"
            render(view:"list",model:[strainInstanceList: strainList, strainInstanceTotal: strainList.size(),strainName:strainName])
        }
        else
        if(strainList.size()==1){
            redirect(action: "show",id:strainList.get(0).id)
        }
        if(strainList.size()>1){
//            render(view:"strainNotFound",model:[name:strainName])
//            render(view:"list",model:[name:strainName])
            render(view:"list",model:[strainInstanceList: strainList, strainInstanceTotal: strainList.size(),strainName:strainName])
        }
    }

    def findNextStrainId(Long hostOriginId){
        HostOrigin hostOrigin = HostOrigin.findById(hostOriginId)
        if(hostOrigin){
            String strainName = strainService.createStrainName(hostOrigin)
            render strainName
        }
        else{
            render ""
        }
    }

    def save() {

        if(params.newGenus){
            String newGenusName = params.newGenus
            Phylum phylum = Phylum.findById(params.phylum.id)
            Genus newGenus = Genus.findOrSaveByNameAndPhylum(newGenusName,phylum)
            params.genus = newGenus
        }



        def strainInstance = new Strain(params)

        handleNewStock(strainInstance,params)


        if (!strainInstance.save(flush: true)) {
            render(view: "create", model: [strainInstance: strainInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'strain.label', default: 'Strain'), strainInstance.name])
        redirect(action: "show", id: strainInstance.id)
    }

    def handleNewStock(Strain strainInstance,def params) {
        if(params.newStockBox && params.newStockIndex && params.newStockLocation){
            println "trying to create a new stockBox and Index "
            Integer boxNumber = Integer.parseInt(params.newStockBox)
            Integer boxIndex = Integer.parseInt(params.newStockIndex)
            Location location = Location.findById(params.newStockLocation)
            Stock stock = Stock.findOrSaveByBoxNumberAndBoxIndexAndGeneralLocation(boxNumber,boxIndex,location)
            strainInstance.addToStocks(stock)
            stock.strain = strainInstance
        }
    }

    def show(Long id) {
        def strainInstance = Strain.get(id)
        if (!strainInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'strain.label', default: 'Strain'), id])
            redirect(action: "list")
            return
        }

//        def measuredValues = MeasuredValue.executeQuery("from MeasuredValue mv where mv.strain = :strain order by mv.category.name asc",[strain: strainInstance])

        def measuredValues = MeasuredValue.createCriteria().list{
            eq("strain",strainInstance)
            order("category","asc")
        }


        [strainInstance: strainInstance,measuredValues:measuredValues]
    }

    def edit(Long id) {
        def strainInstance = Strain.get(id)
        if (!strainInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'strain.label', default: 'Strain'), id])
            redirect(action: "list")
            return
        }

        [strainInstance: strainInstance]
    }

    def update(Long id, Long version) {
        def strainInstance = Strain.get(id)


        if (!strainInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'strain.label', default: 'Strain'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (strainInstance.version > version) {
                strainInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'strain.label', default: 'Strain')] as Object[],
                        "Another user has updated this Strain while you were editing")
                render(view: "edit", model: [strainInstance: strainInstance])
                return
            }
        }

        if(params.newGenus){
            String newGenusName = params.newGenus
            Phylum phylum = Phylum.findById(params.phylum.id)
            Genus newGenus = Genus.findOrSaveByNameAndPhylum(newGenusName,phylum)
            params.genus = newGenus
        }

        strainInstance.genomes?.each{ genome ->
            genome.strain = null
        }
        strainInstance.genomes = null

        strainInstance.properties = params

        if (params.addstockid && params.addstockid != 'null') {
            Stock stock = Stock.get(params.addstockid)
            strainInstance.addToStocks(stock)
            stock.strain = strainInstance
            if (!stock.save(flush: true)) {
                render(view: "edit", model: [strainInstance: strainInstance])
                return
            }
        }
        else{
            handleNewStock(strainInstance,params)
        }

        if (!strainInstance.save(flush: true)) {
            render(view: "edit", model: [strainInstance: strainInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'strain.label', default: 'Strain'), strainInstance.name])
        redirect(action: "show", id: strainInstance.id)
    }

    def delete(Long id) {
        def strainInstance = Strain.get(id)
        if (!strainInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'strain.label', default: 'Strain'), id])
            redirect(action: "list")
            return
        }

        try {
            strainInstance.stocks.each { stock ->
                stock.strain = null
                stock.save(flush: true)
            }
            strainInstance.stocks = null

            strainInstance.genomes.each { genome ->
                genome.strain = null
                genome.save(flush: true)
            }
            strainInstance.genomes = null

            strainInstance.save(flush: true)


            strainInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'strain.label', default: 'Strain'), strainInstance.name])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'strain.label', default: 'Strain'), id])
            redirect(action: "show", id: id)
        }
    }

    def removeStockFromStrain() {
        def stockId = params.stockId
        def strainId = params.strainId
        def stockInstance = Stock.get(stockId)
        if (!stockInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'stock.label', default: 'Stock'), stockId])
            redirect(action: "edit", id: strainId, controller: "strain")
            return
        }

        def strainInstance = Strain.get(strainId)
        if (!stockInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'strain.label', default: 'Strain'), strainId])
            redirect(action: "edit", id: strainId, controller: "strain")
            return
        }

        stockInstance.strain = null
        strainInstance.removeFromStocks(stockInstance)
        stockInstance.save(flush: true)
        strainInstance.save(flush: true)

        flash.message = message(code: 'default.updated.message', args: [message(code: 'strain.label', default: 'Strain'), strainInstance.name])
        redirect(action: "show", id: strainId, controller: "strain")

    }


}

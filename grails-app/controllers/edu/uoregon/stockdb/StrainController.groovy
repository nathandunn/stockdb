package edu.uoregon.stockdb

import org.grails.datastore.mapping.query.api.Criteria
import org.springframework.dao.DataIntegrityViolationException

class StrainController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    private static String STRAIN_FILTER = "strainFilter"
    private static String CLASS_DELIMITER = ":"
    private static String HOST_SPECIES_FILTER = "host.species"
    private static String HOST_ANATOMY_FILTER = "host.anatomy"
    private static String GENUS_FILTER = "genus"
    private static String PHYLUM_FILTER = "phylum"

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)

        Map<String, String> strainFilters = (Map<String, String>) request.session.getAttribute(STRAIN_FILTER)

        Criteria criteria = Strain.createCriteria()
        def results = criteria.list(params) {
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
                    inList("genus",genusList)
                }
                if (filter == HOST_SPECIES_FILTER) {
                    Species species = Species.findById(Long.valueOf(strainFilters.get(filter)))
                    List<HostOrigin> hostOriginList = HostOrigin.findAllBySpecies(species)

                    inList("hostOrigin",hostOriginList)
                }
                if (filter == HOST_ANATOMY_FILTER) {
                    List<HostOrigin> hostOriginList = HostOrigin.findAllByAnatomy(strainFilters.get(filter))
                    inList("hostOrigin",hostOriginList)
                }

            }
        }

        [strainInstanceList: results, strainInstanceTotal: results.size(),strainFilters:strainFilters]
    }

    def addFilter() {
        Map<String, String> strainFilter = request.session.getAttribute(STRAIN_FILTER)
        String newFilter = params.strainFilter
        String filterName = newFilter.split(CLASS_DELIMITER)[0].trim()
        String filterValue = newFilter.split(CLASS_DELIMITER)[1].trim()

        if (!strainFilter) {
            strainFilter = new HashMap<String, String>()
        }
        if(filterValue && !filterValue.contains("null")){
            strainFilter[filterName] = filterValue
        }
        else{
            strainFilter.remove(filterName)
        }
        request.session.setAttribute(STRAIN_FILTER, strainFilter)
        redirect(action: "list")
    }

    def create() {
        [strainInstance: new Strain(params)]
    }

    def save() {
        def strainInstance = new Strain(params)
        if (!strainInstance.save(flush: true)) {
            render(view: "create", model: [strainInstance: strainInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'strain.label', default: 'Strain'), strainInstance.id])
        redirect(action: "show", id: strainInstance.id)
    }

    def show(Long id) {
        def strainInstance = Strain.get(id)
        if (!strainInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'strain.label', default: 'Strain'), id])
            redirect(action: "list")
            return
        }

        [strainInstance: strainInstance]
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

        strainInstance.properties = params

        if (!strainInstance.save(flush: true)) {
            render(view: "edit", model: [strainInstance: strainInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'strain.label', default: 'Strain'), strainInstance.id])
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
            strainInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'strain.label', default: 'Strain'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'strain.label', default: 'Strain'), id])
            redirect(action: "show", id: id)
        }
    }
}

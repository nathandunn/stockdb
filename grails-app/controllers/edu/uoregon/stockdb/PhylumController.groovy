package edu.uoregon.stockdb

import org.springframework.dao.DataIntegrityViolationException

class PhylumController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

//    static navigation = [
//            title: 'Strain Phylum', action: 'list', order: 5
//    ]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [phylumInstanceList: Phylum.findAllByHost(false,params), phylumInstanceTotal: Phylum.countByHost(false)]
    }

    def create() {
        params.host = true
        [phylumInstance: new Phylum(params)]
    }

    def save() {
        def phylumInstance = new Phylum(params)
        if (!phylumInstance.save(flush: true)) {
            render(view: "create", model: [phylumInstance: phylumInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'phylum.label', default: 'Phylum'), phylumInstance.name])
        redirect(action: "show", id: phylumInstance.id)
    }

    def show(Long id) {
        def phylumInstance = Phylum.get(id)
        if (!phylumInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'phylum.label', default: 'Phylum'), id])
            redirect(action: "list")
            return
        }

//        def strains = Strain.findAllByGenusInList(phylumInstance.genuses,[sort:"genus.name",order:"asc"])
        def strains = Strain.findAllByGenusInList([phylumInstance.genuses],[sort:"genus.name",order:"asc"])

        [phylumInstance: phylumInstance,genuses:Genus.findAllByPhylum(phylumInstance,[sort:"name",order:"asc"]),strains:strains]
    }

    def edit(Long id) {
        def phylumInstance = Phylum.get(id)
        if (!phylumInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'phylum.label', default: 'Phylum'), id])
            redirect(action: "list")
            return
        }

        [phylumInstance: phylumInstance]
    }

    def update(Long id, Long version) {
        def phylumInstance = Phylum.get(id)
        if (!phylumInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'phylum.label', default: 'Phylum'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (phylumInstance.version > version) {
                phylumInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'phylum.label', default: 'Phylum')] as Object[],
                        "Another user has updated this Phylum while you were editing")
                render(view: "edit", model: [phylumInstance: phylumInstance])
                return
            }
        }

        phylumInstance.properties = params

//        if (params.addgenusid && params.addgenusid!='null') {
//            Genus genus = Genus.findById(params.addgenusid)
//            if (genus) {
//                phylumInstance.addToGenuses(genus)
//            }
//        }

        if (!phylumInstance.save(flush: true)) {
            render(view: "edit", model: [phylumInstance: phylumInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'phylum.label', default: 'Phylum'), phylumInstance.name])
        redirect(action: "show", id: phylumInstance.id)
    }

    def delete(Long id) {
        def phylumInstance = Phylum.get(id)
        if (!phylumInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'phylum.label', default: 'Phylum'), id])
            redirect(action: "list")
            return
        }

        try {
            if (phylumInstance.genuses) {
                flash.error = "You must remove ${phylumInstance.genuses.size()} associated genuses before you remove this phylum ${phylumInstance.name}"
                redirect(action: "edit", id: id)
                return
            }

            phylumInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'phylum.label', default: 'Phylum'), phylumInstance.name])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'phylum.label', default: 'Phylum'), id])
            redirect(action: "show", id: id)
        }
    }
}

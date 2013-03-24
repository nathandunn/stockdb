package edu.uoregon.stockdb

import org.springframework.dao.DataIntegrityViolationException

class GenusController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    static navigation = [
            title:'Genome',action: 'list',order:8
    ]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [genusInstanceList: Genus.list(params), genusInstanceTotal: Genus.count()]
    }

    def create() {
        [genusInstance: new Genus(params)]
    }

    def save() {
        def genusInstance = new Genus(params)
        if (!genusInstance.save(flush: true)) {
            render(view: "create", model: [genusInstance: genusInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'genus.label', default: 'Genus'), genusInstance.name])
        redirect(action: "show", id: genusInstance.id)
    }

    def show(Long id) {
        def genusInstance = Genus.get(id)
        if (!genusInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'genus.label', default: 'Genus'), id])
            redirect(action: "list")
            return
        }

        [genusInstance: genusInstance]
    }

    def edit(Long id) {
        def genusInstance = Genus.get(id)
        if (!genusInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'genus.label', default: 'Genus'), id])
            redirect(action: "list")
            return
        }

        [genusInstance: genusInstance]
    }

    def update(Long id, Long version) {
        def genusInstance = Genus.get(id)
        if (!genusInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'genus.label', default: 'Genus'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (genusInstance.version > version) {
                genusInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'genus.label', default: 'Genus')] as Object[],
                          "Another user has updated this Genus while you were editing")
                render(view: "edit", model: [genusInstance: genusInstance])
                return
            }
        }

        genusInstance.properties = params

        if (!genusInstance.save(flush: true)) {
            render(view: "edit", model: [genusInstance: genusInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'genus.label', default: 'Genus'), genusInstance.name])
        redirect(action: "show", id: genusInstance.id)
    }

    def delete(Long id) {
        def genusInstance = Genus.get(id)
        if (!genusInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'genus.label', default: 'Genus'), id])
            redirect(action: "list")
            return
        }

        try {
            Phylum phylum = genusInstance.phylum
            phylum.removeFromGenuses(genusInstance)
//            phylum.save(flush: true)
//            genusInstance.phylum = null
//            genusInstance.save(flush: true)

            genusInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'genus.label', default: 'Genus'), genusInstance.name])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'genus.label', default: 'Genus'), id])
            redirect(action: "show", id: id)
        }
    }
}

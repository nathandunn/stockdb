package edu.uoregon.stockdb

import org.springframework.dao.DataIntegrityViolationException

class SpeciesController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [speciesInstanceList: Species.list(params), speciesInstanceTotal: Species.count()]
    }

    def create() {
        [speciesInstance: new Species(params)]
    }

    def save() {
        def speciesInstance = new Species(params)
        if (!speciesInstance.save(flush: true)) {
            render(view: "create", model: [speciesInstance: speciesInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'species.label', default: 'Species'), speciesInstance.id])
        redirect(action: "show", id: speciesInstance.id)
    }

    def show(Long id) {
        def speciesInstance = Species.get(id)
        if (!speciesInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'species.label', default: 'Species'), id])
            redirect(action: "list")
            return
        }

        [speciesInstance: speciesInstance]
    }

    def edit(Long id) {
        def speciesInstance = Species.get(id)
        if (!speciesInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'species.label', default: 'Species'), id])
            redirect(action: "list")
            return
        }

        [speciesInstance: speciesInstance]
    }

    def update(Long id, Long version) {
        def speciesInstance = Species.get(id)
        if (!speciesInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'species.label', default: 'Species'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (speciesInstance.version > version) {
                speciesInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'species.label', default: 'Species')] as Object[],
                          "Another user has updated this Species while you were editing")
                render(view: "edit", model: [speciesInstance: speciesInstance])
                return
            }
        }

        speciesInstance.properties = params

        if (!speciesInstance.save(flush: true)) {
            render(view: "edit", model: [speciesInstance: speciesInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'species.label', default: 'Species'), speciesInstance.id])
        redirect(action: "show", id: speciesInstance.id)
    }

    def delete(Long id) {
        def speciesInstance = Species.get(id)
        if (!speciesInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'species.label', default: 'Species'), id])
            redirect(action: "list")
            return
        }

        try {
            speciesInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'species.label', default: 'Species'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'species.label', default: 'Species'), id])
            redirect(action: "show", id: id)
        }
    }
}

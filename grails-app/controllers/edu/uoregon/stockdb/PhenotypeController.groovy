package edu.uoregon.stockdb

import org.springframework.dao.DataIntegrityViolationException

class PhenotypeController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    static navigation = [
            title: 'Phenotypes', action: 'list', order: 5
    ]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [phenotypeInstanceList: Phenotype.list(params), phenotypeInstanceTotal: Phenotype.count()]
    }

    def create() {
        [phenotypeInstance: new Phenotype(params)]
    }

    def save() {
        def phenotypeInstance = new Phenotype(params)
        if (!phenotypeInstance.save(flush: true)) {
            render(view: "create", model: [phenotypeInstance: phenotypeInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'phenotype.label', default: 'Phenotype'), phenotypeInstance.id])
        redirect(action: "show", id: phenotypeInstance.id)
    }

    def show(Long id) {
        def phenotypeInstance = Phenotype.get(id)
        if (!phenotypeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'phenotype.label', default: 'Phenotype'), id])
            redirect(action: "list")
            return
        }

        [phenotypeInstance: phenotypeInstance]
    }

    def edit(Long id) {
        def phenotypeInstance = Phenotype.get(id)
        if (!phenotypeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'phenotype.label', default: 'Phenotype'), id])
            redirect(action: "list")
            return
        }

        [phenotypeInstance: phenotypeInstance]
    }

    def update(Long id, Long version) {
        def phenotypeInstance = Phenotype.get(id)
        if (!phenotypeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'phenotype.label', default: 'Phenotype'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (phenotypeInstance.version > version) {
                phenotypeInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'phenotype.label', default: 'Phenotype')] as Object[],
                          "Another user has updated this Phenotype while you were editing")
                render(view: "edit", model: [phenotypeInstance: phenotypeInstance])
                return
            }
        }

        phenotypeInstance.properties = params

        if (!phenotypeInstance.save(flush: true)) {
            render(view: "edit", model: [phenotypeInstance: phenotypeInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'phenotype.label', default: 'Phenotype'), phenotypeInstance.id])
        redirect(action: "show", id: phenotypeInstance.id)
    }

    def delete(Long id) {
        def phenotypeInstance = Phenotype.get(id)
        if (!phenotypeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'phenotype.label', default: 'Phenotype'), id])
            redirect(action: "list")
            return
        }

        try {
            phenotypeInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'phenotype.label', default: 'Phenotype'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'phenotype.label', default: 'Phenotype'), id])
            redirect(action: "show", id: id)
        }
    }
}

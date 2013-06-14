package edu.uoregon.stockdb

import org.springframework.dao.DataIntegrityViolationException

class GenomeTypeController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [genomeTypeInstanceList: GenomeType.list(params), genomeTypeInstanceTotal: GenomeType.count()]
    }

    def create() {
        [genomeTypeInstance: new GenomeType(params)]
    }

    def save() {
        def genomeTypeInstance = new GenomeType(params)
        if (!genomeTypeInstance.save(flush: true)) {
            render(view: "create", model: [genomeTypeInstance: genomeTypeInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'genomeType.label', default: 'GenomeType'), genomeTypeInstance.id])
        redirect(action: "show", id: genomeTypeInstance.id)
    }

    def show(Long id) {
        def genomeTypeInstance = GenomeType.get(id)
        if (!genomeTypeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'genomeType.label', default: 'GenomeType'), id])
            redirect(action: "list")
            return
        }

        [genomeTypeInstance: genomeTypeInstance]
    }

    def edit(Long id) {
        def genomeTypeInstance = GenomeType.get(id)
        if (!genomeTypeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'genomeType.label', default: 'GenomeType'), id])
            redirect(action: "list")
            return
        }

        [genomeTypeInstance: genomeTypeInstance]
    }

    def update(Long id, Long version) {
        def genomeTypeInstance = GenomeType.get(id)
        if (!genomeTypeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'genomeType.label', default: 'GenomeType'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (genomeTypeInstance.version > version) {
                genomeTypeInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'genomeType.label', default: 'GenomeType')] as Object[],
                          "Another user has updated this GenomeType while you were editing")
                render(view: "edit", model: [genomeTypeInstance: genomeTypeInstance])
                return
            }
        }

        genomeTypeInstance.properties = params

        if (!genomeTypeInstance.save(flush: true)) {
            render(view: "edit", model: [genomeTypeInstance: genomeTypeInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'genomeType.label', default: 'GenomeType'), genomeTypeInstance.id])
        redirect(action: "show", id: genomeTypeInstance.id)
    }

    def delete(Long id) {
        def genomeTypeInstance = GenomeType.get(id)
        if (!genomeTypeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'genomeType.label', default: 'GenomeType'), id])
            redirect(action: "list")
            return
        }

        try {
            genomeTypeInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'genomeType.label', default: 'GenomeType'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'genomeType.label', default: 'GenomeType'), id])
            redirect(action: "show", id: id)
        }
    }
}

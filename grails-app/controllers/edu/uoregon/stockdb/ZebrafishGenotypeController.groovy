package edu.uoregon.stockdb

import org.springframework.dao.DataIntegrityViolationException

class ZebrafishGenotypeController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [zebrafishGenotypeInstanceList: ZebrafishGenotype.list(params), zebrafishGenotypeInstanceTotal: ZebrafishGenotype.count()]
    }

    def create() {
        [zebrafishGenotypeInstance: new ZebrafishGenotype(params)]
    }

    def save() {
        def zebrafishGenotypeInstance = new ZebrafishGenotype(params)
        if (!zebrafishGenotypeInstance.save(flush: true)) {
            render(view: "create", model: [zebrafishGenotypeInstance: zebrafishGenotypeInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'zebrafishGenotype.label', default: 'ZebrafishGenotype'), zebrafishGenotypeInstance.id])
        redirect(action: "show", id: zebrafishGenotypeInstance.id)
    }

    def show(Long id) {
        def zebrafishGenotypeInstance = ZebrafishGenotype.get(id)
        if (!zebrafishGenotypeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'zebrafishGenotype.label', default: 'ZebrafishGenotype'), id])
            redirect(action: "list")
            return
        }

        [zebrafishGenotypeInstance: zebrafishGenotypeInstance]
    }

    def edit(Long id) {
        def zebrafishGenotypeInstance = ZebrafishGenotype.get(id)
        if (!zebrafishGenotypeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'zebrafishGenotype.label', default: 'ZebrafishGenotype'), id])
            redirect(action: "list")
            return
        }

        [zebrafishGenotypeInstance: zebrafishGenotypeInstance]
    }

    def update(Long id, Long version) {
        def zebrafishGenotypeInstance = ZebrafishGenotype.get(id)
        if (!zebrafishGenotypeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'zebrafishGenotype.label', default: 'ZebrafishGenotype'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (zebrafishGenotypeInstance.version > version) {
                zebrafishGenotypeInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'zebrafishGenotype.label', default: 'ZebrafishGenotype')] as Object[],
                          "Another user has updated this ZebrafishGenotype while you were editing")
                render(view: "edit", model: [zebrafishGenotypeInstance: zebrafishGenotypeInstance])
                return
            }
        }

        zebrafishGenotypeInstance.properties = params

        if (!zebrafishGenotypeInstance.save(flush: true)) {
            render(view: "edit", model: [zebrafishGenotypeInstance: zebrafishGenotypeInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'zebrafishGenotype.label', default: 'ZebrafishGenotype'), zebrafishGenotypeInstance.id])
        redirect(action: "show", id: zebrafishGenotypeInstance.id)
    }

    def delete(Long id) {
        def zebrafishGenotypeInstance = ZebrafishGenotype.get(id)
        if (!zebrafishGenotypeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'zebrafishGenotype.label', default: 'ZebrafishGenotype'), id])
            redirect(action: "list")
            return
        }

        try {
            zebrafishGenotypeInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'zebrafishGenotype.label', default: 'ZebrafishGenotype'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'zebrafishGenotype.label', default: 'ZebrafishGenotype'), id])
            redirect(action: "show", id: id)
        }
    }
}

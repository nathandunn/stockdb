package edu.uoregon.stockdb

import org.springframework.dao.DataIntegrityViolationException

class HostGenotypeController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [hostGenotypeInstanceList: HostGenotype.list(params), hostGenotypeInstanceTotal: HostGenotype.count()]
    }

    def create() {
        [hostGenotypeInstance: new HostGenotype(params)]
    }

    def save() {
        def hostGenotypeInstance = new HostGenotype(params)
        if (!hostGenotypeInstance.save(flush: true)) {
            render(view: "create", model: [hostGenotypeInstance: hostGenotypeInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'hostGenotype.label', default: 'HostGenotype'), hostGenotypeInstance.id])
        redirect(action: "show", id: hostGenotypeInstance.id)
    }

    def show(Long id) {
        def hostGenotypeInstance = HostGenotype.get(id)
        if (!hostGenotypeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'hostGenotype.label', default: 'HostGenotype'), id])
            redirect(action: "list")
            return
        }

        [hostGenotypeInstance: hostGenotypeInstance]
    }

    def edit(Long id) {
        def hostGenotypeInstance = HostGenotype.get(id)
        if (!hostGenotypeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'hostGenotype.label', default: 'HostGenotype'), id])
            redirect(action: "list")
            return
        }

        [hostGenotypeInstance: hostGenotypeInstance]
    }

    def update(Long id, Long version) {
        def hostGenotypeInstance = HostGenotype.get(id)
        if (!hostGenotypeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'hostGenotype.label', default: 'HostGenotype'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (hostGenotypeInstance.version > version) {
                hostGenotypeInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'hostGenotype.label', default: 'HostGenotype')] as Object[],
                          "Another user has updated this HostGenotype while you were editing")
                render(view: "edit", model: [hostGenotypeInstance: hostGenotypeInstance])
                return
            }
        }

        hostGenotypeInstance.properties = params

        if (!hostGenotypeInstance.save(flush: true)) {
            render(view: "edit", model: [hostGenotypeInstance: hostGenotypeInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'hostGenotype.label', default: 'HostGenotype'), hostGenotypeInstance.id])
        redirect(action: "show", id: hostGenotypeInstance.id)
    }

    def delete(Long id) {
        def hostGenotypeInstance = HostGenotype.get(id)
        if (!hostGenotypeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'hostGenotype.label', default: 'HostGenotype'), id])
            redirect(action: "list")
            return
        }

        try {
            hostGenotypeInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'hostGenotype.label', default: 'HostGenotype'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'hostGenotype.label', default: 'HostGenotype'), id])
            redirect(action: "show", id: id)
        }
    }
}

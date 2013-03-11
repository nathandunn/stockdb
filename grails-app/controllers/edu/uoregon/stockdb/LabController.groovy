package edu.uoregon.stockdb

import org.springframework.dao.DataIntegrityViolationException

class LabController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [labInstanceList: Lab.list(params), labInstanceTotal: Lab.count()]
    }

    def create() {
        [labInstance: new Lab(params)]
    }

    def save() {
        def labInstance = new Lab(params)
        if (!labInstance.save(flush: true)) {
            render(view: "create", model: [labInstance: labInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'lab.label', default: 'Lab'), labInstance.id])
        redirect(action: "show", id: labInstance.id)
    }

    def show(Long id) {
        def labInstance = Lab.get(id)
        if (!labInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'lab.label', default: 'Lab'), id])
            redirect(action: "list")
            return
        }

        [labInstance: labInstance]
    }

    def edit(Long id) {
        def labInstance = Lab.get(id)
        if (!labInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'lab.label', default: 'Lab'), id])
            redirect(action: "list")
            return
        }

        [labInstance: labInstance]
    }

    def update(Long id, Long version) {
        def labInstance = Lab.get(id)
        if (!labInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'lab.label', default: 'Lab'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (labInstance.version > version) {
                labInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'lab.label', default: 'Lab')] as Object[],
                          "Another user has updated this Lab while you were editing")
                render(view: "edit", model: [labInstance: labInstance])
                return
            }
        }

        labInstance.properties = params

        if (!labInstance.save(flush: true)) {
            render(view: "edit", model: [labInstance: labInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'lab.label', default: 'Lab'), labInstance.id])
        redirect(action: "show", id: labInstance.id)
    }

    def delete(Long id) {
        def labInstance = Lab.get(id)
        if (!labInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'lab.label', default: 'Lab'), id])
            redirect(action: "list")
            return
        }

        try {
            labInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'lab.label', default: 'Lab'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'lab.label', default: 'Lab'), id])
            redirect(action: "show", id: id)
        }
    }
}

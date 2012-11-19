package edu.uoregon.stockdb

import org.springframework.dao.DataIntegrityViolationException

class OriginController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [originInstanceList: Origin.list(params), originInstanceTotal: Origin.count()]
    }

    def create() {
        [originInstance: new Origin(params)]
    }

    def save() {
        def originInstance = new Origin(params)
        if (!originInstance.save(flush: true)) {
            render(view: "create", model: [originInstance: originInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'origin.label', default: 'Origin'), originInstance.id])
        redirect(action: "show", id: originInstance.id)
    }

    def show(Long id) {
        def originInstance = Origin.get(id)
        if (!originInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'origin.label', default: 'Origin'), id])
            redirect(action: "list")
            return
        }

        [originInstance: originInstance]
    }

    def edit(Long id) {
        def originInstance = Origin.get(id)
        if (!originInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'origin.label', default: 'Origin'), id])
            redirect(action: "list")
            return
        }

        [originInstance: originInstance]
    }

    def update(Long id, Long version) {
        def originInstance = Origin.get(id)
        if (!originInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'origin.label', default: 'Origin'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (originInstance.version > version) {
                originInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'origin.label', default: 'Origin')] as Object[],
                          "Another user has updated this Origin while you were editing")
                render(view: "edit", model: [originInstance: originInstance])
                return
            }
        }

        originInstance.properties = params

        if (!originInstance.save(flush: true)) {
            render(view: "edit", model: [originInstance: originInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'origin.label', default: 'Origin'), originInstance.id])
        redirect(action: "show", id: originInstance.id)
    }

    def delete(Long id) {
        def originInstance = Origin.get(id)
        if (!originInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'origin.label', default: 'Origin'), id])
            redirect(action: "list")
            return
        }

        try {
            originInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'origin.label', default: 'Origin'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'origin.label', default: 'Origin'), id])
            redirect(action: "show", id: id)
        }
    }
}

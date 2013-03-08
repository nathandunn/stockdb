package edu.uoregon.stockdb

import org.springframework.dao.DataIntegrityViolationException

class HostOriginController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [hostOriginInstanceList: Origin.list(params), hostOriginInstanceTotal: Origin.count()]
    }

    def create() {
        [hostOriginInstance: new Origin(params)]
    }

    def save() {
        def hostOriginInstance = new Origin(params)
        if (!hostOriginInstance.save(flush: true)) {
            render(view: "create", model: [hostOriginInstance: hostOriginInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'hostOrigin.label', default: 'HostOrigin'), hostOriginInstance.id])
        redirect(action: "show", id: hostOriginInstance.id)
    }

    def show(Long id) {
        def hostOriginInstance = Origin.get(id)
        if (!hostOriginInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'hostOrigin.label', default: 'HostOrigin'), id])
            redirect(action: "list")
            return
        }

        [hostOriginInstance: hostOriginInstance]
    }

    def edit(Long id) {
        def hostOriginInstance = Origin.get(id)
        if (!hostOriginInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'hostOrigin.label', default: 'HostOrigin'), id])
            redirect(action: "list")
            return
        }

        [hostOriginInstance: hostOriginInstance]
    }

    def update(Long id, Long version) {
        def hostOriginInstance = Origin.get(id)
        if (!hostOriginInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'hostOrigin.label', default: 'HostOrigin'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (hostOriginInstance.version > version) {
                hostOriginInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'hostOrigin.label', default: 'HostOrigin')] as Object[],
                          "Another user has updated this HostOrigin while you were editing")
                render(view: "edit", model: [hostOriginInstance: hostOriginInstance])
                return
            }
        }

        hostOriginInstance.properties = params

        if (!hostOriginInstance.save(flush: true)) {
            render(view: "edit", model: [hostOriginInstance: hostOriginInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'hostOrigin.label', default: 'HostOrigin'), hostOriginInstance.id])
        redirect(action: "show", id: hostOriginInstance.id)
    }

    def delete(Long id) {
        def hostOriginInstance = Origin.get(id)
        if (!hostOriginInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'hostOrigin.label', default: 'HostOrigin'), id])
            redirect(action: "list")
            return
        }

        try {
            hostOriginInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'hostOrigin.label', default: 'HostOrigin'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'hostOrigin.label', default: 'HostOrigin'), id])
            redirect(action: "show", id: id)
        }
    }
}

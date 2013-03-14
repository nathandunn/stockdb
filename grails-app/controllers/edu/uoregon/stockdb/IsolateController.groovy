package edu.uoregon.stockdb

import org.springframework.dao.DataIntegrityViolationException

class IsolateController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [isolateInstanceList: IsolateCondition.list(params), isolateInstanceTotal: IsolateCondition.count()]
    }

    def create() {
        [isolateInstance: new IsolateCondition(params)]
    }

    def save() {
        def isolateInstance = new IsolateCondition(params)
        if (!isolateInstance.save(flush: true)) {
            render(view: "create", model: [isolateInstance: isolateInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'isolate.label', default: 'Isolate'), isolateInstance.id])
        redirect(action: "show", id: isolateInstance.id)
    }

    def show(Long id) {
        def isolateInstance = IsolateCondition.get(id)
        if (!isolateInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'isolate.label', default: 'Isolate'), id])
            redirect(action: "list")
            return
        }

        [isolateInstance: isolateInstance]
    }

    def edit(Long id) {
        def isolateInstance = IsolateCondition.get(id)
        if (!isolateInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'isolate.label', default: 'Isolate'), id])
            redirect(action: "list")
            return
        }

        [isolateInstance: isolateInstance]
    }

    def update(Long id, Long version) {
        def isolateInstance = IsolateCondition.get(id)
        if (!isolateInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'isolate.label', default: 'Isolate'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (isolateInstance.version > version) {
                isolateInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'isolate.label', default: 'Isolate')] as Object[],
                          "Another user has updated this Isolate while you were editing")
                render(view: "edit", model: [isolateInstance: isolateInstance])
                return
            }
        }

        isolateInstance.properties = params

        if (!isolateInstance.save(flush: true)) {
            render(view: "edit", model: [isolateInstance: isolateInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'isolate.label', default: 'Isolate'), isolateInstance.id])
        redirect(action: "show", id: isolateInstance.id)
    }

    def delete(Long id) {
        def isolateInstance = IsolateCondition.get(id)
        if (!isolateInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'isolate.label', default: 'Isolate'), id])
            redirect(action: "list")
            return
        }

        try {
            isolateInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'isolate.label', default: 'Isolate'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'isolate.label', default: 'Isolate'), id])
            redirect(action: "show", id: id)
        }
    }
}

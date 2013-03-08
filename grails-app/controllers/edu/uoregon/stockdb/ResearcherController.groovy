package edu.uoregon.stockdb

import org.springframework.dao.DataIntegrityViolationException

class ResearcherController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [researcherInstanceList: Researcher.list(params), researcherInstanceTotal: Researcher.count()]
    }

    def create() {
        [researcherInstance: new Researcher(params)]
    }

    def save() {
        def researcherInstance = new Researcher(params)
        if (!researcherInstance.save(flush: true)) {
            render(view: "create", model: [researcherInstance: researcherInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'researcher.label', default: 'Researcher'), researcherInstance.id])
        redirect(action: "show", id: researcherInstance.id)
    }

    def show(Long id) {
        def researcherInstance = Researcher.get(id)
        if (!researcherInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'researcher.label', default: 'Researcher'), id])
            redirect(action: "list")
            return
        }

        [researcherInstance: researcherInstance]
    }

    def edit(Long id) {
        def researcherInstance = Researcher.get(id)
        if (!researcherInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'researcher.label', default: 'Researcher'), id])
            redirect(action: "list")
            return
        }

        [researcherInstance: researcherInstance]
    }

    def update(Long id, Long version) {
        def researcherInstance = Researcher.get(id)
        if (!researcherInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'researcher.label', default: 'Researcher'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (researcherInstance.version > version) {
                researcherInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'researcher.label', default: 'Researcher')] as Object[],
                          "Another user has updated this Researcher while you were editing")
                render(view: "edit", model: [researcherInstance: researcherInstance])
                return
            }
        }

        researcherInstance.properties = params

        if (!researcherInstance.save(flush: true)) {
            render(view: "edit", model: [researcherInstance: researcherInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'researcher.label', default: 'Researcher'), researcherInstance.id])
        redirect(action: "show", id: researcherInstance.id)
    }

    def delete(Long id) {
        def researcherInstance = Researcher.get(id)
        if (!researcherInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'researcher.label', default: 'Researcher'), id])
            redirect(action: "list")
            return
        }

        try {
            researcherInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'researcher.label', default: 'Researcher'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'researcher.label', default: 'Researcher'), id])
            redirect(action: "show", id: id)
        }
    }
}

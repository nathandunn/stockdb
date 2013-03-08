package edu.uoregon.stockdb

import org.springframework.dao.DataIntegrityViolationException

class StrainController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [strainInstanceList: Strain.list(params), strainInstanceTotal: Strain.count()]
    }

    /**
     * Default is to search all
     */
    def search(){
        def arbitraryKey = params.arbitraryValue

        def stockName= params.stockName
        def parentStrainName = params.parentStrainName
        def name = params.name
        def motility = params.motility
        def notes = params.notes
        def genus = params.genus
        def phylum= params.phylum
        def formerCloneAlias = params.formerCloneAlias

    }

    def create() {
        [strainInstance: new Strain(params)]
    }

    def save() {
        def strainInstance = new Strain(params)
        if (!strainInstance.save(flush: true)) {
            render(view: "create", model: [strainInstance: strainInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'strain.label', default: 'Strain'), strainInstance.id])
        redirect(action: "show", id: strainInstance.id)
    }

    def show(Long id) {
        def strainInstance = Strain.get(id)
        if (!strainInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'strain.label', default: 'Strain'), id])
            redirect(action: "list")
            return
        }

        [strainInstance: strainInstance]
    }

    def edit(Long id) {
        def strainInstance = Strain.get(id)
        if (!strainInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'strain.label', default: 'Strain'), id])
            redirect(action: "list")
            return
        }

        [strainInstance: strainInstance]
    }

    def update(Long id, Long version) {
        def strainInstance = Strain.get(id)
        if (!strainInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'strain.label', default: 'Strain'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (strainInstance.version > version) {
                strainInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'strain.label', default: 'Strain')] as Object[],
                          "Another user has updated this Strain while you were editing")
                render(view: "edit", model: [strainInstance: strainInstance])
                return
            }
        }

        strainInstance.properties = params

        if (!strainInstance.save(flush: true)) {
            render(view: "edit", model: [strainInstance: strainInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'strain.label', default: 'Strain'), strainInstance.id])
        redirect(action: "show", id: strainInstance.id)
    }

    def delete(Long id) {
        def strainInstance = Strain.get(id)
        if (!strainInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'strain.label', default: 'Strain'), id])
            redirect(action: "list")
            return
        }

        try {
            strainInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'strain.label', default: 'Strain'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'strain.label', default: 'Strain'), id])
            redirect(action: "show", id: id)
        }
    }
}

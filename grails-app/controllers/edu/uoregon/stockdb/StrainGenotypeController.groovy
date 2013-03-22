package edu.uoregon.stockdb

import org.springframework.dao.DataIntegrityViolationException

class StrainGenotypeController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [strainGenotypeInstanceList: StrainGenotype.list(params), strainGenotypeInstanceTotal: StrainGenotype.count()]
    }

    def create() {
        [strainGenotypeInstance: new StrainGenotype(params)]
    }

    def save() {
        def strainGenotypeInstance = new StrainGenotype(params)
        if (!strainGenotypeInstance.save(flush: true)) {
            render(view: "create", model: [strainGenotypeInstance: strainGenotypeInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'strainGenotype.label', default: 'StrainGenotype'), strainGenotypeInstance.name])
        redirect(action: "show", id: strainGenotypeInstance.id)
    }

    def show(Long id) {
        def strainGenotypeInstance = StrainGenotype.get(id)
        if (!strainGenotypeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'strainGenotype.label', default: 'StrainGenotype'), id])
            redirect(action: "list")
            return
        }

        [strainGenotypeInstance: strainGenotypeInstance]
    }

    def edit(Long id) {
        def strainGenotypeInstance = StrainGenotype.get(id)
        if (!strainGenotypeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'strainGenotype.label', default: 'StrainGenotype'), id])
            redirect(action: "list")
            return
        }

        [strainGenotypeInstance: strainGenotypeInstance]
    }

    def update(Long id, Long version) {
        def strainGenotypeInstance = StrainGenotype.get(id)
        if (!strainGenotypeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'strainGenotype.label', default: 'StrainGenotype'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (strainGenotypeInstance.version > version) {
                strainGenotypeInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'strainGenotype.label', default: 'StrainGenotype')] as Object[],
                          "Another user has updated this StrainGenotype while you were editing")
                render(view: "edit", model: [strainGenotypeInstance: strainGenotypeInstance])
                return
            }
        }

        strainGenotypeInstance.properties = params

        if (!strainGenotypeInstance.save(flush: true)) {
            render(view: "edit", model: [strainGenotypeInstance: strainGenotypeInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'strainGenotype.label', default: 'StrainGenotype'), strainGenotypeInstance.name])
        redirect(action: "show", id: strainGenotypeInstance.id)
    }

    def delete(Long id) {
        def strainGenotypeInstance = StrainGenotype.get(id)
        if (!strainGenotypeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'strainGenotype.label', default: 'StrainGenotype'), id])
            redirect(action: "list")
            return
        }

        if(strainGenotypeInstance.strains){
            flash.error = "Must remove ${strainGenotypeInstance.strains.size()} strains before deleting"
            redirect(action: "show", id: id)
            return
        }

        try {
            strainGenotypeInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'strainGenotype.label', default: 'StrainGenotype'), strainGenotypeInstance.name])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'strainGenotype.label', default: 'StrainGenotype'), id])
            redirect(action: "show", id: id)
        }
    }
}

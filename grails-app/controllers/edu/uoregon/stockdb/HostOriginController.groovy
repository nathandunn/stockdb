package edu.uoregon.stockdb

import org.springframework.dao.DataIntegrityViolationException

class HostOriginController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

//    static navigation = [
//            title:'Host Origin',action: 'list',order:5
//    ]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [hostOriginInstanceList: HostOrigin.list(params), hostOriginInstanceTotal: HostOrigin.count()]
    }

    def create() {
        [hostOriginInstance: new HostOrigin(params),strains: Strain.findAllByHostOriginIsNull([sort:'name',order:'asc'])]
    }

    def save() {
        def hostOriginInstance = new HostOrigin(params)

        def strains = Strain.findAllByHostOriginIsNull()

        validateHostOrigin(hostOriginInstance,strains)

        if (hostOriginInstance.hasErrors() || !hostOriginInstance.save(flush: true)) {
            render(view: "create", model: [hostOriginInstance: hostOriginInstance,strains: strains])
            return
        }


        flash.message = message(code: 'default.created.message', args: [message(code: 'hostOrigin.label', default: 'HostOrigin'), hostOriginInstance.display])
        redirect(action: "show", id: hostOriginInstance.id)
    }

    def show(Long id) {
        def hostOriginInstance = HostOrigin.get(id)
        if (!hostOriginInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'hostOrigin.label', default: 'HostOrigin'), id])
            redirect(action: "list")
            return
        }

        [hostOriginInstance: hostOriginInstance]
    }

    def edit(Long id) {
        def hostOriginInstance = HostOrigin.get(id)
        if (!hostOriginInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'hostOrigin.label', default: 'HostOrigin'), id])
            redirect(action: "list")
            return
        }

        def strains = Strain.findAllByHostOriginIsNull()
        strains.addAll(hostOriginInstance.strains)

        [hostOriginInstance: hostOriginInstance,strains:strains]
    }

    def update(Long id, Long version) {
        def hostOriginInstance = HostOrigin.get(id)
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

        hostOriginInstance.strains?.each{ strain ->
            strain.hostOrigin = null
            strain.save(flush: true)
        }
        hostOriginInstance.strains = null

        hostOriginInstance.properties = params

        if(!params.genotypes){
            hostOriginInstance.genotypes = null
        }

        def strains = Strain.findAllByHostOriginIsNull() ?: []
        if(hostOriginInstance.strains){
            strains.addAll(hostOriginInstance.strains)
        }

        validateHostOrigin(hostOriginInstance,strains)


        if (hostOriginInstance.hasErrors() || !hostOriginInstance.save(flush: true)) {
            render(view: "edit", model: [hostOriginInstance: hostOriginInstance,strains: strains])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'hostOrigin.label', default: 'HostOrigin'), hostOriginInstance.display])
        redirect(action: "show", id: hostOriginInstance.id)
    }

    def validateHostOrigin(HostOrigin hostOriginInstance,def strains) {

        if(hostOriginInstance.stage==null || hostOriginInstance.stage=="null" && hostOriginInstance.daysPastFertilization==null ){
            hostOriginInstance.errors.rejectValue("stage", "define.stage","You must define either the stage or the days past fertilization")
            flash.error =  "You must define either the stage of the days past fertilization"
            render(view: "edit", model: [hostOriginInstance: hostOriginInstance,strains: strains])
            return
        }

        if(!hostOriginInstance.anatomy && !hostOriginInstance.anatomyUrl){
            hostOriginInstance.errors.rejectValue("anatomy", "define.anatomy","You must define anatomy")
            flash.error =  "You must define an anatomy term or anatomy URL"
            render(view: "edit", model: [hostOriginInstance: hostOriginInstance,strains: strains])
            return
        }

        if(!hostOriginInstance.hostFacility){
            hostOriginInstance.errors.rejectValue("hostFacility", "define.hostFacility","You must define the host facility")
            render(view: "edit", model: [hostOriginInstance: hostOriginInstance,strains: strains])
            return
        }

        if(hostOriginInstance.genotypes==null  || hostOriginInstance.genotypes.size()==0){
            hostOriginInstance.errors.rejectValue("genotypes", "define.genotypes","You must define at least one genotype including Unknown")
            flash.error =  "You must define at least one genotype including Unknown"
            render(view: "edit", model: [hostOriginInstance: hostOriginInstance,strains: strains])
            return
        }

        if(!hostOriginInstance.species){
            hostOriginInstance.errors.rejectValue("species", "define.species","You must define the species")
            render(view: "edit", model: [hostOriginInstance: hostOriginInstance,strains: strains])
            return
        }
    }

    def delete(Long id) {
        def hostOriginInstance = HostOrigin.get(id)
        if (!hostOriginInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'hostOrigin.label', default: 'HostOrigin'), id])
            redirect(action: "list")
            return
        }

        try {
            hostOriginInstance.strains.each { strain ->
                strain.hostOrigin = null
                strain.save(flush: true)
            }


            hostOriginInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'hostOrigin.label', default: 'HostOrigin'), hostOriginInstance.display])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'hostOrigin.label', default: 'HostOrigin'), id])
            redirect(action: "show", id: id)
        }
        catch(Exception e){
            println "failed for some reason ${e}"
        }
    }

    def removeStrainFromHostOrigin() {
        def hostOriginId = params.hostOriginId
        def strainId = params.strainId
        def hostOriginInstance = HostOrigin.get(hostOriginId)
        if (!hostOriginInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'hostOrigin.label', default: 'Host Origin'), hostOriginId])
            redirect(action: "edit", id: strainId, controller: "strain")
            return
        }

        def strainInstance = Strain.get(strainId)
        if (!hostOriginInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'strain.label', default: 'Strain'), strainId])
            redirect(action: "edit", id: strainId, controller: "strain")
            return
        }

        hostOriginInstance.removeFromStrains(strainInstance)
        strainInstance.hostOrigin = null
        hostOriginInstance.save(flush: true)
        strainInstance.save(flush: true)

        flash.message = "Strain ${strainInstance.name} removed"
        redirect(action: "show", id: hostOriginId, controller: "hostOrigin")
    }

    def removeGenotypeFromHostOrigin() {
        def hostOriginId = params.hostOriginId
        def hostGenotypeId = params.hostGenotypeId
        def hostOriginInstance = HostOrigin.get(hostOriginId)
        if (!hostOriginInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'hostOrigin.label', default: 'Host Origin'), hostOriginId])
            redirect(action: "edit", id: hostGenotypeId, controller: "hostGenotype")
            return
        }

        def hostGenotypeInstance = HostGenotype.get(hostGenotypeId)
        if (!hostOriginInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'hostGenotype.label', default: 'Host Genotype'), hostGenotypeId])
            redirect(action: "edit", id: hostGenotypeId, controller: "hostGenotype")
            return
        }

        hostOriginInstance.removeFromStrains(hostGenotypeInstance)
        hostGenotypeInstance.hostOrigin = null
        hostOriginInstance.save(flush: true)
        hostGenotypeInstance.save(flush: true)

        flash.message = "Host Genotype ${hostGenotypeInstance.name} removed"
        redirect(action: "show", id: hostOriginId, controller: "hostOrigin")
    }
}

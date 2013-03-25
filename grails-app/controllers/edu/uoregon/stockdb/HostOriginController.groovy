package edu.uoregon.stockdb

import org.springframework.dao.DataIntegrityViolationException

class HostOriginController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    static navigation = [
            title:'Host Origin',action: 'list',order:5
    ]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [hostOriginInstanceList: HostOrigin.list(params), hostOriginInstanceTotal: HostOrigin.count()]
    }

    def create() {
        [hostOriginInstance: new HostOrigin(params)]
    }

    def save() {
        def hostOriginInstance = new HostOrigin(params)

        if(params.addstrainid && params.addstrainid!='null'){
            Strain strain = Strain.findById(params.addstrainid)
            hostOriginInstance.addToStrains(strain)
        }

        if (!hostOriginInstance.save(flush: true)) {
            render(view: "create", model: [hostOriginInstance: hostOriginInstance])
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

        [hostOriginInstance: hostOriginInstance]
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


        hostOriginInstance.phenotypes = null

        hostOriginInstance.properties = params


        if(params.addstrainid && params.addstrainid!='null'){
            Strain strain = Strain.findById(params.addstrainid)
            hostOriginInstance.addToStrains(strain)
        }

        if (!hostOriginInstance.save(flush: true)) {
            render(view: "edit", model: [hostOriginInstance: hostOriginInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'hostOrigin.label', default: 'HostOrigin'), hostOriginInstance.display])
        redirect(action: "show", id: hostOriginInstance.id)
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
}

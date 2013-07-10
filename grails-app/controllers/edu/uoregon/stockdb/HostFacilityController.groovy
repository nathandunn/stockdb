package edu.uoregon.stockdb

import org.springframework.dao.DataIntegrityViolationException

class HostFacilityController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

//    static navigation = [
//            title:'Host Facility',action: 'list',order:3
//    ]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [hostFacilityInstanceList: HostFacility.list(params), hostFacilityInstanceTotal: HostFacility.count()]
    }

    def create() {
        [hostFacilityInstance: new HostFacility(params)]
    }

    def save() {
        def hostFacilityInstance = new HostFacility(params)
        if (!hostFacilityInstance.save(flush: true)) {
            render(view: "create", model: [hostFacilityInstance: hostFacilityInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'hostFacility.label', default: 'HostFacility'), hostFacilityInstance.name])
        redirect(action: "show", id: hostFacilityInstance.id)
    }

    def show(Long id) {
        def hostFacilityInstance = HostFacility.get(id)
        if (!hostFacilityInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'hostFacility.label', default: 'HostFacility'), id])
            redirect(action: "list")
            return
        }

        [hostFacilityInstance: hostFacilityInstance]
    }

    def edit(Long id) {
        def hostFacilityInstance = HostFacility.get(id)
        if (!hostFacilityInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'hostFacility.label', default: 'HostFacility'), id])
            redirect(action: "list")
            return
        }

        [hostFacilityInstance: hostFacilityInstance]
    }

    def update(Long id, Long version) {
        def hostFacilityInstance = HostFacility.get(id)
        if (!hostFacilityInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'hostFacility.label', default: 'HostFacility'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (hostFacilityInstance.version > version) {
                hostFacilityInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'hostFacility.label', default: 'HostFacility')] as Object[],
                        "Another user has updated this HostFacility while you were editing")
                render(view: "edit", model: [hostFacilityInstance: hostFacilityInstance])
                return
            }
        }

        hostFacilityInstance.properties = params

        if (!hostFacilityInstance.save(flush: true)) {
            render(view: "edit", model: [hostFacilityInstance: hostFacilityInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'hostFacility.label', default: 'HostFacility'), hostFacilityInstance.name])
        redirect(action: "show", id: hostFacilityInstance.id)
    }

    def delete(Long id) {
        def hostFacilityInstance = HostFacility.get(id)
        if (!hostFacilityInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'hostFacility.label', default: 'HostFacility'), id])
            redirect(action: "list")
            return
        }

        try {
            if(hostFacilityInstance.origins){
                flash.message = "You must re-assign ${hostFacilityInstance.origins.size()} Origins before deleting."
                redirect(action: "show", id: hostFacilityInstance.id)
                return
            }
//            hostFacilityInstance.origins.each { it ->
//                it.hostFacility = null
//                it.save(flush: true)
//            }
//            hostFacilityInstance.origins = null
//            hostFacilityInstance.save(flush: true)


            hostFacilityInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'hostFacility.label', default: 'HostFacility'), hostFacilityInstance.name])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'hostFacility.label', default: 'HostFacility'), id])
            redirect(action: "show", id: id)
        }
    }
}

package edu.uoregon.stockdb

import org.apache.shiro.SecurityUtils
import org.apache.shiro.crypto.hash.Sha256Hash
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

    def resetPassword(){
        String username = params.username
        Researcher researcher = Researcher.findByUsername(username)
        if(researcher){
            flash.message = message(code: 'default.resetemailsent.message', args: [username])
            redirect(view: "login",controllerName:"auth", model: [username: username])
            return
        }
        else{
            flash.message = message(code: 'default.couldnotfindemail.message', args: [username])
            redirect(view: "forgotPassword",controllerName:"auth", model: [username: username])
            return
        }
    }

    def save() {
        if (params.password1) {
            if (params.password1.equals(params.password2)) {
                params.passwordHash = new Sha256Hash(params.password1).toHex()
            } else {
                params.errors.rejectValue("password", "default.password.doesnotmatch", "Passwords do not match")
                render(view: "create", model: [researcherInstance: params])
                return
            }
        }

        println "params: ${params}"
        Researcher researcherInstance = new Researcher(params)
        Role userRole = Role.findByName(ResearcherService.ROLE_USER)
        println "found role: ${userRole}"
        if(researcherInstance){
            researcherInstance?.addToRoles( userRole )
        }

        if (!researcherInstance.save(flush: true)) {
            render(view: "create", model: [researcherInstance: researcherInstance])
            return
        }


        flash.message = message(code: 'default.created.message', args: [message(code: 'researcher.label', default: 'Researcher'), researcherInstance.fullName])
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

        String principalUsername = SecurityUtils.subject.principal
        if(researcherInstance.username==principalUsername || SecurityUtils.subject.hasRole(ResearcherService.ROLE_ADMINISTRATOR)){
            [researcherInstance: researcherInstance]
        }
        else{
//            render "You do not have permission to access this page."
            render(view:"unauthorized")
        }


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

        if (params.password1) {
            if (params.password1.equals(params.password2)) {
                researcherInstance.passwordHash = new Sha256Hash(params.password1).toHex()
            } else {
                researcherInstance.errors.rejectValue("password", "default.password.doesnotmatch", "Passwords do not match")
                render(view: "edit", model: [userInstance: researcherInstance])
                return
            }
        } else {
            params.passwordHash = researcherInstance.passwordHash
        }

        researcherInstance.properties = params

        if (!researcherInstance.save(flush: true)) {
            render(view: "edit", model: [researcherInstance: researcherInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'researcher.label', default: 'Researcher'), researcherInstance.fullName])
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
            if (researcherInstance.isolateConditions) {
                flash.error = "Must remove researcher from ${researcherInstance.isolateConditions.size()} isolate conditions before removing"
                redirect(action: "show", id: id)
                return
            }

            if (researcherInstance.experiments) {
                flash.error = "Must remove researcher from ${researcherInstance.experiments.size()} experiments before removing"
                redirect(action: "show", id: id)
                return
            }

            researcherInstance.lab = null
            researcherInstance.save(flush: true)


            researcherInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'researcher.label', default: 'Researcher'), researcherInstance.fullName])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'researcher.label', default: 'Researcher'), id])
            redirect(action: "show", id: id)
        }
    }
}

package edu.uoregon.stockdb

import org.springframework.dao.DataIntegrityViolationException

class GenomeController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

//    static navigation = [
//            title:'Genome',action: 'list',order:9
//    ]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [genomeInstanceList: Genome.list(params), genomeInstanceTotal: Genome.count()]
    }

    def create() {
        [genomeInstance: new Genome(params)]
    }

    def save() {
        def genomeInstance = new Genome(params)
        if (!genomeInstance.save(flush: true)) {
            println "failed to save "
            render(view: "create", model: [genomeInstance: genomeInstance])
            return
        }

        if (params.addstrainid && params.addstrainid != 'null') {
            Strain strain = Strain.get(params.addstrainid)
            genomeInstance.addToStrains(strain)
            strain.genome = genomeInstance
            if (!strain.save(flush: true)) {
                println "failed to save a strain "
                render(view: "edit", model: [genomeInstance: genomeInstance])
                return
            }
        }


        flash.message = message(code: 'default.created.message', args: [message(code: 'genome.label', default: 'Genome'), genomeInstance.display])

        redirect(action: "show", id: genomeInstance.id,model: [genomeInstance: genomeInstance])
    }

    def show(Long id) {
        def genomeInstance = Genome.get(id)
        if (!genomeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'genome.label', default: 'Genome'), id])
            redirect(action: "list")
            return
        }

        [genomeInstance: genomeInstance]
    }

    def edit(Long id) {
        def genomeInstance = Genome.get(id)
        if (!genomeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'genome.label', default: 'Genome'), id])
            redirect(action: "list")
            return
        }

        [genomeInstance: genomeInstance]
    }

    def update(Long id, Long version) {
        def genomeInstance = Genome.get(id)
        if (!genomeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'genome.label', default: 'Genome'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (genomeInstance.version > version) {
                genomeInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'genome.label', default: 'Genome')] as Object[],
                        "Another user has updated this Genome while you were editing")
                render(view: "edit", model: [genomeInstance: genomeInstance])
                return
            }
        }


        if (params.addstrainid && params.addstrainid != 'null') {
            Strain strain = Strain.get(params.addstrainid)
            genomeInstance.addToStrains(strain)
            strain.genome = genomeInstance
            if (!strain.save(flush: true)) {
                render(view: "edit", model: [genomeInstance: genomeInstance])
                return
            }
        }

        genomeInstance.properties = params

        if (!genomeInstance.save(flush: true)) {
            render(view: "edit", model: [genomeInstance: genomeInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'genome.label', default: 'Genome'), genomeInstance.display])
        redirect(action: "show", id: genomeInstance.id)
    }

    def delete(Long id) {
        def genomeInstance = Genome.get(id)
        if (!genomeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'genome.label', default: 'Genome'), id])
            redirect(action: "list")
            return
        }

        try {
            genomeInstance.strains.each { it ->
                it.genome = null
                it.save(flush: true)
            }
            genomeInstance.strains = null
            genomeInstance.save(flush: true)

            genomeInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'genome.label', default: 'Genome'), genomeInstance.display])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'genome.label', default: 'Genome'), id])
            redirect(action: "show", id: id)
        }
    }

    def removeGenomeFromStrain() {
        def genomeId = params.genomeId
        def strainId = params.strainId
        def genomeInstance = Genome.get(genomeId)
        if (!genomeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'genome.label', default: 'Genome'), genomeId])
            redirect(action: "edit", id: strainId, controller: "strain")
            return
        }

        def strainInstance = Strain.get(strainId)
        if (!genomeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'strain.label', default: 'Strain'), strainId])
            redirect(action: "edit", id: strainId, controller: "strain")
            return
        }

        strainInstance.genome = null

//        genomeInstance.strain = null
//        strainInstance.removeFromGenomes(genomeInstance)
//        genomeInstance.save(flush: true)
        strainInstance.save(flush: true)

        flash.message = message(code: 'default.updated.message', args: [message(code: 'strain.label', default: 'Genome'), genomeInstance.display])
        redirect(action: "show", id: genomeId, controller: "genome")

    }
}

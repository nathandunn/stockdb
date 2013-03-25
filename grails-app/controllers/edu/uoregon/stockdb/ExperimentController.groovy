package edu.uoregon.stockdb

import org.springframework.dao.DataIntegrityViolationException

class ExperimentController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    static navigation = [
            title: 'Experiment', action: 'list', order: 100
    ]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [experimentInstanceList: Experiment.list(params), experimentInstanceTotal: Experiment.count()]
    }

    def create() {
        [experimentInstance: new Experiment(params),availableMeasuredValues: MeasuredValue.findAllByExperimentIsNull()]
    }

    def save() {
        def experimentInstance = new Experiment(params)
        if (!experimentInstance.save(flush: true)) {
            render(view: "create", model: [experimentInstance: experimentInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'experiment.label', default: 'Experiment'), experimentInstance.name])
        redirect(action: "show", id: experimentInstance.id)
    }

    def show(Long id) {
        def experimentInstance = Experiment.get(id)
        if (!experimentInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'experiment.label', default: 'Experiment'), id])
            redirect(action: "list")
            return
        }

//        Map<String,List<String>> values = new TreeMap<String,List<String>>()
        Map<String,List<String>> values  = experimentInstance.createValuesMap()


        [experimentInstance: experimentInstance,valuesMap:values]
    }

    def edit(Long id) {
        def experimentInstance = Experiment.get(id)
        if (!experimentInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'experiment.label', default: 'Experiment'), id])
            redirect(action: "list")
            return
        }

        def measuredValues = edu.uoregon.stockdb.MeasuredValue.findAllByExperimentIsNull() + experimentInstance?.measuredValues

        [experimentInstance: experimentInstance,availableMeasuredValues:measuredValues]
    }

    def update(Long id, Long version) {
        def experimentInstance = Experiment.get(id)
        if (!experimentInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'experiment.label', default: 'Experiment'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (experimentInstance.version > version) {
                experimentInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'experiment.label', default: 'Experiment')] as Object[],
                        "Another user has updated this Experiment while you were editing")
                render(view: "edit", model: [experimentInstance: experimentInstance])
                return
            }
        }


        experimentInstance.measuredValues.each { measuredValue ->
            measuredValue.experiment = null
        }
        experimentInstance.measuredValues = null

        experimentInstance.strains.each { strain ->
            strain.experiments.remove(experimentInstance)
        }
        experimentInstance.strains = null


        experimentInstance.properties = params

        if (!experimentInstance.save(flush: true)) {
            render(view: "edit", model: [experimentInstance: experimentInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'experiment.label', default: 'Experiment'), experimentInstance.name])
        redirect(action: "show", id: experimentInstance.id)
    }

    def delete(Long id) {
        def experimentInstance = Experiment.get(id)
        if (!experimentInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'experiment.label', default: 'Experiment'), id])
            redirect(action: "list")
            return
        }

        if (experimentInstance.strains) {
            flash.error = "Must remove ${experimentInstance.strains.size()} strains before removing"
            redirect(action: "edit", id: id)
            return
        }

        if (experimentInstance.measuredValues) {
            flash.error = "Must remove ${experimentInstance.strains.size()} measured values before removing"
            redirect(action: "edit", id: id)
            return
        }

        try {
            experimentInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'experiment.label', default: 'Experiment'), experimentInstance.name])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'experiment.label', default: 'Experiment'), id])
            redirect(action: "show", id: id)
        }
    }
}

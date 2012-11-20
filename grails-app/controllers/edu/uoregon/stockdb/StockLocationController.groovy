package edu.uoregon.stockdb

import org.springframework.dao.DataIntegrityViolationException

class StockLocationController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [stockLocationInstanceList: Stock.list(params), stockLocationInstanceTotal: Stock.count()]
    }

    def create() {
        [stockLocationInstance: new Stock(params)]
    }

    def save() {
        def stockLocationInstance = new Stock(params)
        if (!stockLocationInstance.save(flush: true)) {
            render(view: "create", model: [stockLocationInstance: stockLocationInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'stockLocation.label', default: 'StockLocation'), stockLocationInstance.id])
        redirect(action: "show", id: stockLocationInstance.id)
    }

    def show(Long id) {
        def stockLocationInstance = StockLocation.get(id)
        if (!stockLocationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'stockLocation.label', default: 'StockLocation'), id])
            redirect(action: "list")
            return
        }

        [stockLocationInstance: stockLocationInstance]
    }

    def edit(Long id) {
        def stockLocationInstance = StockLocation.get(id)
        if (!stockLocationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'stockLocation.label', default: 'StockLocation'), id])
            redirect(action: "list")
            return
        }

        [stockLocationInstance: stockLocationInstance]
    }

    def update(Long id, Long version) {
        def stockLocationInstance = Stock.get(id)
        if (!stockLocationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'stockLocation.label', default: 'StockLocation'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (stockLocationInstance.version > version) {
                stockLocationInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'stockLocation.label', default: 'StockLocation')] as Object[],
                          "Another user has updated this StockLocation while you were editing")
                render(view: "edit", model: [stockLocationInstance: stockLocationInstance])
                return
            }
        }

        stockLocationInstance.properties = params

        if (!stockLocationInstance.save(flush: true)) {
            render(view: "edit", model: [stockLocationInstance: stockLocationInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'stockLocation.label', default: 'StockLocation'), stockLocationInstance.id])
        redirect(action: "show", id: stockLocationInstance.id)
    }

    def delete(Long id) {
        def stockLocationInstance = StockLocation.get(id)
        if (!stockLocationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'stockLocation.label', default: 'StockLocation'), id])
            redirect(action: "list")
            return
        }

        try {
            stockLocationInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'stockLocation.label', default: 'StockLocation'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'stockLocation.label', default: 'StockLocation'), id])
            redirect(action: "show", id: id)
        }
    }
}

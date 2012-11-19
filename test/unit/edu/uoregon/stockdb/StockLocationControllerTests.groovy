package edu.uoregon.stockdb



import org.junit.*
import grails.test.mixin.*

@TestFor(StockLocationController)
@Mock(StockLocation)
class StockLocationControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/stockLocation/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.stockLocationInstanceList.size() == 0
        assert model.stockLocationInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.stockLocationInstance != null
    }

    void testSave() {
        controller.save()

        assert model.stockLocationInstance != null
        assert view == '/stockLocation/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/stockLocation/show/1'
        assert controller.flash.message != null
        assert StockLocation.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/stockLocation/list'

        populateValidParams(params)
        def stockLocation = new StockLocation(params)

        assert stockLocation.save() != null

        params.id = stockLocation.id

        def model = controller.show()

        assert model.stockLocationInstance == stockLocation
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/stockLocation/list'

        populateValidParams(params)
        def stockLocation = new StockLocation(params)

        assert stockLocation.save() != null

        params.id = stockLocation.id

        def model = controller.edit()

        assert model.stockLocationInstance == stockLocation
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/stockLocation/list'

        response.reset()

        populateValidParams(params)
        def stockLocation = new StockLocation(params)

        assert stockLocation.save() != null

        // test invalid parameters in update
        params.id = stockLocation.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/stockLocation/edit"
        assert model.stockLocationInstance != null

        stockLocation.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/stockLocation/show/$stockLocation.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        stockLocation.clearErrors()

        populateValidParams(params)
        params.id = stockLocation.id
        params.version = -1
        controller.update()

        assert view == "/stockLocation/edit"
        assert model.stockLocationInstance != null
        assert model.stockLocationInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/stockLocation/list'

        response.reset()

        populateValidParams(params)
        def stockLocation = new StockLocation(params)

        assert stockLocation.save() != null
        assert StockLocation.count() == 1

        params.id = stockLocation.id

        controller.delete()

        assert StockLocation.count() == 0
        assert StockLocation.get(stockLocation.id) == null
        assert response.redirectedUrl == '/stockLocation/list'
    }
}

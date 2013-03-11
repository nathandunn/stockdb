package edu.uoregon.stockdb



import org.junit.*
import grails.test.mixin.*

@TestFor(HostOriginController)
@Mock(HostOrigin)
class HostOriginControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/hostOrigin/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.hostOriginInstanceList.size() == 0
        assert model.hostOriginInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.hostOriginInstance != null
    }

    void testSave() {
        controller.save()

        assert model.hostOriginInstance != null
        assert view == '/hostOrigin/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/hostOrigin/show/1'
        assert controller.flash.message != null
        assert HostOrigin.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/hostOrigin/list'

        populateValidParams(params)
        def hostOrigin = new HostOrigin(params)

        assert hostOrigin.save() != null

        params.id = hostOrigin.id

        def model = controller.show()

        assert model.hostOriginInstance == hostOrigin
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/hostOrigin/list'

        populateValidParams(params)
        def hostOrigin = new HostOrigin(params)

        assert hostOrigin.save() != null

        params.id = hostOrigin.id

        def model = controller.edit()

        assert model.hostOriginInstance == hostOrigin
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/hostOrigin/list'

        response.reset()

        populateValidParams(params)
        def hostOrigin = new HostOrigin(params)

        assert hostOrigin.save() != null

        // test invalid parameters in update
        params.id = hostOrigin.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/hostOrigin/edit"
        assert model.hostOriginInstance != null

        hostOrigin.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/hostOrigin/show/$hostOrigin.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        hostOrigin.clearErrors()

        populateValidParams(params)
        params.id = hostOrigin.id
        params.version = -1
        controller.update()

        assert view == "/hostOrigin/edit"
        assert model.hostOriginInstance != null
        assert model.hostOriginInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/hostOrigin/list'

        response.reset()

        populateValidParams(params)
        def hostOrigin = new HostOrigin(params)

        assert hostOrigin.save() != null
        assert HostOrigin.count() == 1

        params.id = hostOrigin.id

        controller.delete()

        assert HostOrigin.count() == 0
        assert HostOrigin.get(hostOrigin.id) == null
        assert response.redirectedUrl == '/hostOrigin/list'
    }
}

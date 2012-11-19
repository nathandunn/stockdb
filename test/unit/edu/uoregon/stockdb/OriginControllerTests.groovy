package edu.uoregon.stockdb



import org.junit.*
import grails.test.mixin.*

@TestFor(OriginController)
@Mock(Origin)
class OriginControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/origin/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.originInstanceList.size() == 0
        assert model.originInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.originInstance != null
    }

    void testSave() {
        controller.save()

        assert model.originInstance != null
        assert view == '/origin/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/origin/show/1'
        assert controller.flash.message != null
        assert Origin.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/origin/list'

        populateValidParams(params)
        def origin = new Origin(params)

        assert origin.save() != null

        params.id = origin.id

        def model = controller.show()

        assert model.originInstance == origin
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/origin/list'

        populateValidParams(params)
        def origin = new Origin(params)

        assert origin.save() != null

        params.id = origin.id

        def model = controller.edit()

        assert model.originInstance == origin
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/origin/list'

        response.reset()

        populateValidParams(params)
        def origin = new Origin(params)

        assert origin.save() != null

        // test invalid parameters in update
        params.id = origin.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/origin/edit"
        assert model.originInstance != null

        origin.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/origin/show/$origin.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        origin.clearErrors()

        populateValidParams(params)
        params.id = origin.id
        params.version = -1
        controller.update()

        assert view == "/origin/edit"
        assert model.originInstance != null
        assert model.originInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/origin/list'

        response.reset()

        populateValidParams(params)
        def origin = new Origin(params)

        assert origin.save() != null
        assert Origin.count() == 1

        params.id = origin.id

        controller.delete()

        assert Origin.count() == 0
        assert Origin.get(origin.id) == null
        assert response.redirectedUrl == '/origin/list'
    }
}

package edu.uoregon.stockdb



import org.junit.*
import grails.test.mixin.*

@TestFor(HostFacilityController)
@Mock(HostFacility)
class HostFacilityControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/hostFacility/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.hostFacilityInstanceList.size() == 0
        assert model.hostFacilityInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.hostFacilityInstance != null
    }

    void testSave() {
        controller.save()

        assert model.hostFacilityInstance != null
        assert view == '/hostFacility/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/hostFacility/show/1'
        assert controller.flash.message != null
        assert HostFacility.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/hostFacility/list'

        populateValidParams(params)
        def hostFacility = new HostFacility(params)

        assert hostFacility.save() != null

        params.id = hostFacility.id

        def model = controller.show()

        assert model.hostFacilityInstance == hostFacility
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/hostFacility/list'

        populateValidParams(params)
        def hostFacility = new HostFacility(params)

        assert hostFacility.save() != null

        params.id = hostFacility.id

        def model = controller.edit()

        assert model.hostFacilityInstance == hostFacility
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/hostFacility/list'

        response.reset()

        populateValidParams(params)
        def hostFacility = new HostFacility(params)

        assert hostFacility.save() != null

        // test invalid parameters in update
        params.id = hostFacility.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/hostFacility/edit"
        assert model.hostFacilityInstance != null

        hostFacility.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/hostFacility/show/$hostFacility.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        hostFacility.clearErrors()

        populateValidParams(params)
        params.id = hostFacility.id
        params.version = -1
        controller.update()

        assert view == "/hostFacility/edit"
        assert model.hostFacilityInstance != null
        assert model.hostFacilityInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/hostFacility/list'

        response.reset()

        populateValidParams(params)
        def hostFacility = new HostFacility(params)

        assert hostFacility.save() != null
        assert HostFacility.count() == 1

        params.id = hostFacility.id

        controller.delete()

        assert HostFacility.count() == 0
        assert HostFacility.get(hostFacility.id) == null
        assert response.redirectedUrl == '/hostFacility/list'
    }
}

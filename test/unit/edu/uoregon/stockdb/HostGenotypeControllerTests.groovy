package edu.uoregon.stockdb

import grails.test.mixin.*

@TestFor(HostGenotypeController)
@Mock(HostGenotype)
class HostGenotypeControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/hostGenotype/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.hostGenotypeInstanceList.size() == 0
        assert model.hostGenotypeInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.hostGenotypeInstance != null
    }

    void testSave() {
        controller.save()

        assert model.hostGenotypeInstance != null
        assert view == '/hostGenotype/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/hostGenotype/show/1'
        assert controller.flash.message != null
        assert HostGenotype.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/hostGenotype/list'

        populateValidParams(params)
        def hostGenotype = new HostGenotype(params)

        assert hostGenotype.save() != null

        params.id = hostGenotype.id

        def model = controller.show()

        assert model.hostGenotypeInstance == hostGenotype
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/hostGenotype/list'

        populateValidParams(params)
        def hostGenotype = new HostGenotype(params)

        assert hostGenotype.save() != null

        params.id = hostGenotype.id

        def model = controller.edit()

        assert model.hostGenotypeInstance == hostGenotype
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/hostGenotype/list'

        response.reset()

        populateValidParams(params)
        def hostGenotype = new HostGenotype(params)

        assert hostGenotype.save() != null

        // test invalid parameters in update
        params.id = hostGenotype.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/hostGenotype/edit"
        assert model.hostGenotypeInstance != null

        hostGenotype.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/hostGenotype/show/$hostGenotype.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        hostGenotype.clearErrors()

        populateValidParams(params)
        params.id = hostGenotype.id
        params.version = -1
        controller.update()

        assert view == "/hostGenotype/edit"
        assert model.hostGenotypeInstance != null
        assert model.hostGenotypeInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/hostGenotype/list'

        response.reset()

        populateValidParams(params)
        def hostGenotype = new HostGenotype(params)

        assert hostGenotype.save() != null
        assert HostGenotype.count() == 1

        params.id = hostGenotype.id

        controller.delete()

        assert HostGenotype.count() == 0
        assert HostGenotype.get(hostGenotype.id) == null
        assert response.redirectedUrl == '/hostGenotype/list'
    }
}

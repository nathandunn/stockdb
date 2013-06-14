package edu.uoregon.stockdb

import grails.test.mixin.Mock
import grails.test.mixin.TestFor

@TestFor(GenomeTypeController)
@Mock(GenomeType)
class GenomeTypeControllerTests {

    def populateValidParams(params) {
        assert params != null
        params["organizationName"] = 'someValidName'
        params["baseUrl"] = 'http://rast.com'
    }

    void testIndex() {
        controller.index()
        assert "/genomeType/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.genomeTypeInstanceList.size() == 0
        assert model.genomeTypeInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.genomeTypeInstance != null
    }

    void testSave() {
        controller.save()

        assert model.genomeTypeInstance != null
        assert view == '/genomeType/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/genomeType/show/1'
        assert controller.flash.message != null
        assert GenomeType.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/genomeType/list'

        populateValidParams(params)
        def genomeType = new GenomeType(params)

        assert genomeType.save() != null

        params.id = genomeType.id

        def model = controller.show()

        assert model.genomeTypeInstance == genomeType
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/genomeType/list'

        populateValidParams(params)
        def genomeType = new GenomeType(params)

        assert genomeType.save() != null

        params.id = genomeType.id

        def model = controller.edit()

        assert model.genomeTypeInstance == genomeType
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/genomeType/list'

        response.reset()

        populateValidParams(params)
        def genomeType = new GenomeType(params)

        assert genomeType.save() != null

        // test invalid parameters in update
        params.id = genomeType.id
        params.baseUrl = "notavalidurl"

        controller.update()

        assert view == "/genomeType/edit"
        assert model.genomeTypeInstance != null

        genomeType.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/genomeType/show/$genomeType.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        genomeType.clearErrors()

        populateValidParams(params)
        params.id = genomeType.id
        params.version = -1
        controller.update()

        assert view == "/genomeType/edit"
        assert model.genomeTypeInstance != null
        assert model.genomeTypeInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/genomeType/list'

        response.reset()

        populateValidParams(params)
        def genomeType = new GenomeType(params)

        assert genomeType.save() != null
        assert GenomeType.count() == 1

        params.id = genomeType.id

        controller.delete()

        assert GenomeType.count() == 0
        assert GenomeType.get(genomeType.id) == null
        assert response.redirectedUrl == '/genomeType/list'
    }
}

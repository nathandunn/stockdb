package edu.uoregon.stockdb
import grails.test.mixin.Mock
import grails.test.mixin.TestFor

@TestFor(LabController)
@Mock(Lab)
class LabControllerTests {

    def populateValidParams(params) {
        assert params != null
        params["name"] = 'My Lab'
    }

    void testIndex() {
        controller.index()
        assert "/lab/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.labInstanceList.size() == 0
        assert model.labInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.labInstance != null
    }

    void testSave() {
        controller.save()

        assert model.labInstance != null
        assert view == '/lab/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/lab/show/1'
        assert controller.flash.message != null
        assert Lab.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/lab/list'

        populateValidParams(params)
        def lab = new Lab(params)

        assert lab.save() != null

        params.id = lab.id

        def model = controller.show()

        assert model.labInstance == lab
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/lab/list'

        populateValidParams(params)
        def lab = new Lab(params)

        assert lab.save() != null

        params.id = lab.id

        def model = controller.edit()

        assert model.labInstance == lab
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/lab/list'

        response.reset()

        populateValidParams(params)
        def lab = new Lab(params)

        assert lab.save() != null

        // test invalid parameters in update
        params.id = lab.id
        params.name = null

        controller.update()

        assert view == "/lab/edit"
        assert model.labInstance != null

        lab.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/lab/show/$lab.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        lab.clearErrors()

        populateValidParams(params)
        params.id = lab.id
        params.version = -1
        controller.update()

        assert view == "/lab/edit"
        assert model.labInstance != null
        assert model.labInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/lab/list'

        response.reset()

        populateValidParams(params)
        def lab = new Lab(params)

        assert lab.save() != null
        assert Lab.count() == 1

        params.id = lab.id

        controller.delete()

        assert Lab.count() == 0
        assert Lab.get(lab.id) == null
        assert response.redirectedUrl == '/lab/list'
    }
}

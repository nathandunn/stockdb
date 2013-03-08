package edu.uoregon.stockdb



import org.junit.*
import grails.test.mixin.*

@TestFor(ResearcherController)
@Mock(Researcher)
class ResearcherControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/researcher/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.researcherInstanceList.size() == 0
        assert model.researcherInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.researcherInstance != null
    }

    void testSave() {
        controller.save()

        assert model.researcherInstance != null
        assert view == '/researcher/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/researcher/show/1'
        assert controller.flash.message != null
        assert Researcher.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/researcher/list'

        populateValidParams(params)
        def researcher = new Researcher(params)

        assert researcher.save() != null

        params.id = researcher.id

        def model = controller.show()

        assert model.researcherInstance == researcher
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/researcher/list'

        populateValidParams(params)
        def researcher = new Researcher(params)

        assert researcher.save() != null

        params.id = researcher.id

        def model = controller.edit()

        assert model.researcherInstance == researcher
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/researcher/list'

        response.reset()

        populateValidParams(params)
        def researcher = new Researcher(params)

        assert researcher.save() != null

        // test invalid parameters in update
        params.id = researcher.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/researcher/edit"
        assert model.researcherInstance != null

        researcher.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/researcher/show/$researcher.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        researcher.clearErrors()

        populateValidParams(params)
        params.id = researcher.id
        params.version = -1
        controller.update()

        assert view == "/researcher/edit"
        assert model.researcherInstance != null
        assert model.researcherInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/researcher/list'

        response.reset()

        populateValidParams(params)
        def researcher = new Researcher(params)

        assert researcher.save() != null
        assert Researcher.count() == 1

        params.id = researcher.id

        controller.delete()

        assert Researcher.count() == 0
        assert Researcher.get(researcher.id) == null
        assert response.redirectedUrl == '/researcher/list'
    }
}

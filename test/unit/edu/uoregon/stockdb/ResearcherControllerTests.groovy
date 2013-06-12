package edu.uoregon.stockdb
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import org.junit.After
import org.junit.Before

@TestFor(ResearcherController)
@Mock([Researcher,Role,ResearcherService])
class ResearcherControllerTests {

    @Before
    public void before(){
        Role role = Role.findOrSaveByName(ResearcherService.ROLE_USER).save(failOnError: true)
    }

    @After
    public void after(){
        Role role = Role.findByName(ResearcherService.ROLE_USER)
        Role.deleteAll(role)
    }

    def populateValidParams(params) {
        assert params != null
        params["username"] = 'test@test.com'
        params["firstName"] = 'MyFirst'
        params["lastName"] = 'MyLast'

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
        assert Researcher.count() == 0
        controller.save()
        assert Researcher.count() == 0

        assert model.researcherInstance != null
        assert view == '/researcher/create'

        response.reset()

        assert Researcher.count() == 0
        populateValidParams(params)
        controller.save()
        assert Researcher.count() == 1

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
        params.username = "asdfadsf"

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

package edu.uoregon.stockdb
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import org.junit.After
import org.junit.Before

@TestFor(StrainController)
@Mock([Strain,MeasuredValue])
class StrainControllerTests {


    @Before
    void setUp() {
        Strain strain = Strain.findOrSaveByName("ZOR1234")
    }

    @After
    void tearDown() {
        Strain strain = Strain.findByName("ZOR1234")
        Strain.deleteAll(strain)
    }


    def populateValidParams(params) {
        assert params != null
        params["name"] = 'Z1234'
    }

    void testIndex() {
        controller.index()
        assert "/strain/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.strainInstanceList.size() == 1
        assert model.strainInstanceTotal == 1
    }

    void testCreate() {
        def model = controller.create()

        assert model.strainInstance != null
    }

    void testSave() {
        controller.save()

        assert model.strainInstance != null
        assert view == '/strain/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/strain/show/2'
        assert controller.flash.message != null
        assert Strain.count() == 2
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/strain/list'

        populateValidParams(params)
        def strain = new Strain(params)

        assert strain.save() != null

        params.id = strain.id

        def model = controller.show()

        assert model.strainInstance == strain
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/strain/list'

        populateValidParams(params)
        def strain = new Strain(params)

        assert strain.save() != null

        params.id = strain.id

        def model = controller.edit()

        assert model.strainInstance == strain
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/strain/list'

        response.reset()

        populateValidParams(params)
        def strain = new Strain(params)

        assert strain.save() != null

        // test invalid parameters in update
        params.id = strain.id
        params.name = null

        controller.update()

        assert view == "/strain/edit"
        assert model.strainInstance != null

        strain.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/strain/show/$strain.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        strain.clearErrors()

        populateValidParams(params)
        params.id = strain.id
        params.version = -1
        controller.update()

        assert view == "/strain/edit"
        assert model.strainInstance != null
        assert model.strainInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/strain/list'

        response.reset()

        populateValidParams(params)
        def strain = new Strain(params)

        assert strain.save() != null
        assert Strain.count() == 2

        params.id = strain.id

        controller.delete()

        assert Strain.count() == 1
        assert Strain.get(strain.id) == null
        assert response.redirectedUrl == '/strain/list'
    }
}

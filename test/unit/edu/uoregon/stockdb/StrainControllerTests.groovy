package edu.uoregon.stockdb



import org.junit.*
import grails.test.mixin.*

@TestFor(StrainController)
@Mock(Strain)
class StrainControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/strain/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.strainInstanceList.size() == 0
        assert model.strainInstanceTotal == 0
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

        assert response.redirectedUrl == '/strain/show/1'
        assert controller.flash.message != null
        assert Strain.count() == 1
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
        //TODO: add invalid values to params object

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
        assert Strain.count() == 1

        params.id = strain.id

        controller.delete()

        assert Strain.count() == 0
        assert Strain.get(strain.id) == null
        assert response.redirectedUrl == '/strain/list'
    }
}

package edu.uoregon.stockdb



import org.junit.*
import grails.test.mixin.*

@TestFor(StrainGenotypeController)
@Mock(StrainGenotype)
class StrainGenotypeControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/strainGenotype/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.strainGenotypeInstanceList.size() == 0
        assert model.strainGenotypeInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.strainGenotypeInstance != null
    }

    void testSave() {
        controller.save()

        assert model.strainGenotypeInstance != null
        assert view == '/strainGenotype/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/strainGenotype/show/1'
        assert controller.flash.message != null
        assert StrainGenotype.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/strainGenotype/list'

        populateValidParams(params)
        def strainGenotype = new StrainGenotype(params)

        assert strainGenotype.save() != null

        params.id = strainGenotype.id

        def model = controller.show()

        assert model.strainGenotypeInstance == strainGenotype
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/strainGenotype/list'

        populateValidParams(params)
        def strainGenotype = new StrainGenotype(params)

        assert strainGenotype.save() != null

        params.id = strainGenotype.id

        def model = controller.edit()

        assert model.strainGenotypeInstance == strainGenotype
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/strainGenotype/list'

        response.reset()

        populateValidParams(params)
        def strainGenotype = new StrainGenotype(params)

        assert strainGenotype.save() != null

        // test invalid parameters in update
        params.id = strainGenotype.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/strainGenotype/edit"
        assert model.strainGenotypeInstance != null

        strainGenotype.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/strainGenotype/show/$strainGenotype.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        strainGenotype.clearErrors()

        populateValidParams(params)
        params.id = strainGenotype.id
        params.version = -1
        controller.update()

        assert view == "/strainGenotype/edit"
        assert model.strainGenotypeInstance != null
        assert model.strainGenotypeInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/strainGenotype/list'

        response.reset()

        populateValidParams(params)
        def strainGenotype = new StrainGenotype(params)

        assert strainGenotype.save() != null
        assert StrainGenotype.count() == 1

        params.id = strainGenotype.id

        controller.delete()

        assert StrainGenotype.count() == 0
        assert StrainGenotype.get(strainGenotype.id) == null
        assert response.redirectedUrl == '/strainGenotype/list'
    }
}

package edu.uoregon.stockdb



import org.junit.*
import grails.test.mixin.*

@TestFor(PhenotypeController)
@Mock(Phenotype)
class PhenotypeControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/phenotype/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.phenotypeInstanceList.size() == 0
        assert model.phenotypeInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.phenotypeInstance != null
    }

    void testSave() {
        controller.save()

        assert model.phenotypeInstance != null
        assert view == '/phenotype/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/phenotype/show/1'
        assert controller.flash.message != null
        assert Phenotype.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/phenotype/list'

        populateValidParams(params)
        def phenotype = new Phenotype(params)

        assert phenotype.save() != null

        params.id = phenotype.id

        def model = controller.show()

        assert model.phenotypeInstance == phenotype
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/phenotype/list'

        populateValidParams(params)
        def phenotype = new Phenotype(params)

        assert phenotype.save() != null

        params.id = phenotype.id

        def model = controller.edit()

        assert model.phenotypeInstance == phenotype
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/phenotype/list'

        response.reset()

        populateValidParams(params)
        def phenotype = new Phenotype(params)

        assert phenotype.save() != null

        // test invalid parameters in update
        params.id = phenotype.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/phenotype/edit"
        assert model.phenotypeInstance != null

        phenotype.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/phenotype/show/$phenotype.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        phenotype.clearErrors()

        populateValidParams(params)
        params.id = phenotype.id
        params.version = -1
        controller.update()

        assert view == "/phenotype/edit"
        assert model.phenotypeInstance != null
        assert model.phenotypeInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/phenotype/list'

        response.reset()

        populateValidParams(params)
        def phenotype = new Phenotype(params)

        assert phenotype.save() != null
        assert Phenotype.count() == 1

        params.id = phenotype.id

        controller.delete()

        assert Phenotype.count() == 0
        assert Phenotype.get(phenotype.id) == null
        assert response.redirectedUrl == '/phenotype/list'
    }
}

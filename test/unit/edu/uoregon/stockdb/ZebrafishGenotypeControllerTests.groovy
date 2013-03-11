package edu.uoregon.stockdb



import org.junit.*
import grails.test.mixin.*

@TestFor(ZebrafishGenotypeController)
@Mock(ZebrafishGenotype)
class ZebrafishGenotypeControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/zebrafishGenotype/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.zebrafishGenotypeInstanceList.size() == 0
        assert model.zebrafishGenotypeInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.zebrafishGenotypeInstance != null
    }

    void testSave() {
        controller.save()

        assert model.zebrafishGenotypeInstance != null
        assert view == '/zebrafishGenotype/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/zebrafishGenotype/show/1'
        assert controller.flash.message != null
        assert ZebrafishGenotype.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/zebrafishGenotype/list'

        populateValidParams(params)
        def zebrafishGenotype = new ZebrafishGenotype(params)

        assert zebrafishGenotype.save() != null

        params.id = zebrafishGenotype.id

        def model = controller.show()

        assert model.zebrafishGenotypeInstance == zebrafishGenotype
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/zebrafishGenotype/list'

        populateValidParams(params)
        def zebrafishGenotype = new ZebrafishGenotype(params)

        assert zebrafishGenotype.save() != null

        params.id = zebrafishGenotype.id

        def model = controller.edit()

        assert model.zebrafishGenotypeInstance == zebrafishGenotype
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/zebrafishGenotype/list'

        response.reset()

        populateValidParams(params)
        def zebrafishGenotype = new ZebrafishGenotype(params)

        assert zebrafishGenotype.save() != null

        // test invalid parameters in update
        params.id = zebrafishGenotype.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/zebrafishGenotype/edit"
        assert model.zebrafishGenotypeInstance != null

        zebrafishGenotype.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/zebrafishGenotype/show/$zebrafishGenotype.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        zebrafishGenotype.clearErrors()

        populateValidParams(params)
        params.id = zebrafishGenotype.id
        params.version = -1
        controller.update()

        assert view == "/zebrafishGenotype/edit"
        assert model.zebrafishGenotypeInstance != null
        assert model.zebrafishGenotypeInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/zebrafishGenotype/list'

        response.reset()

        populateValidParams(params)
        def zebrafishGenotype = new ZebrafishGenotype(params)

        assert zebrafishGenotype.save() != null
        assert ZebrafishGenotype.count() == 1

        params.id = zebrafishGenotype.id

        controller.delete()

        assert ZebrafishGenotype.count() == 0
        assert ZebrafishGenotype.get(zebrafishGenotype.id) == null
        assert response.redirectedUrl == '/zebrafishGenotype/list'
    }
}

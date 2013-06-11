package edu.uoregon.stockdb
import grails.test.mixin.Mock
import grails.test.mixin.TestFor

@TestFor(GenusController)
@Mock([Genus,Phylum,Strain])
class GenusControllerTests {

    def populateValidParams(params) {
        assert params != null
        params["name"] = "Thingy"
        params["phylum"] = new Phylum(
                name: "Annelida"
        )
    }

    void testIndex() {
        controller.index()
        assert "/genus/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.genusInstanceList.size() == 0
        assert model.genusInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.genusInstance != null
    }

    void testSave() {
        controller.save()

        assert model.genusInstance != null
        assert view == '/genus/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/genus/show/1'
        assert controller.flash.message != null
        assert Genus.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/genus/list'

        populateValidParams(params)
        def genus = new Genus(params)

        assert genus.save() != null

        params.id = genus.id

        def model = controller.show()

        assert model.genusInstance == genus
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/genus/list'

        populateValidParams(params)
        def genus = new Genus(params)

        assert genus.save() != null

        params.id = genus.id

        def model = controller.edit()

        assert model.genusInstance == genus
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/genus/list'

        response.reset()

        populateValidParams(params)
        def genus = new Genus(params)

        assert genus.save() != null

        // test invalid parameters in update
        params.id = genus.id
        params.name = null

        controller.update()

        assert view == "/genus/edit"
        assert model.genusInstance != null

        genus.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/genus/show/$genus.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        genus.clearErrors()

        populateValidParams(params)
        params.id = genus.id
        params.version = -1
        controller.update()

        assert view == "/genus/edit"
        assert model.genusInstance != null
        assert model.genusInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/genus/list'

        response.reset()

        populateValidParams(params)
        def genus = new Genus(params)

        assert genus.save() != null
        assert Genus.count() == 1

        params.id = genus.id

        controller.delete()

        assert Genus.count() == 0
        assert Genus.get(genus.id) == null
        assert response.redirectedUrl == '/genus/list'
    }
}

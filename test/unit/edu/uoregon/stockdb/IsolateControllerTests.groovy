package edu.uoregon.stockdb

import grails.test.mixin.*

@TestFor(IsolateController)
@Mock([IsolateCondition,Strain,Researcher])
class IsolateControllerTests {

    def populateValidParams(params) {
        assert params != null
        params["isolatedWhen"] = new Date()
        params["oxygenCondition"] = "Lots of air"
        params["temperature"] = 97.4
        params["media"] = "Agar"
        params["notes"] = "Some notes "
    }

    void testIndex() {
        controller.index()
        assert "/isolate/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.isolateInstanceList.size() == 0
        assert model.isolateInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.isolateInstance != null
    }

    void testSave() {
        controller.save()

        assert model.isolateInstance != null
        assert view == '/isolate/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/isolate/show/1'
        assert controller.flash.message != null
        assert IsolateCondition.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/isolate/list'

        populateValidParams(params)
        def isolate = new IsolateCondition(params)

        assert isolate.save() != null

        params.id = isolate.id

        def model = controller.show()

        assert model.isolateInstance == isolate
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/isolate/list'

        populateValidParams(params)
        def isolate = new IsolateCondition(params)

        assert isolate.save() != null

        params.id = isolate.id

        def model = controller.edit()

        assert model.isolateInstance == isolate
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/isolate/list'

        response.reset()

        populateValidParams(params)
        def isolate = new IsolateCondition(params)

        assert isolate.save() != null

        // test invalid parameters in update
        params.id = isolate.id
        params.isolatedWhen = null

        controller.update()

        assert view == "/isolate/edit"
        assert model.isolateInstance != null

        isolate.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/isolate/show/$isolate.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        isolate.clearErrors()

        populateValidParams(params)
        params.id = isolate.id
        params.version = -1
        controller.update()

        assert view == "/isolate/edit"
        assert model.isolateInstance != null
        assert model.isolateInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/isolate/list'

        response.reset()

        populateValidParams(params)
        def isolate = new IsolateCondition(params)

        assert isolate.save() != null
        assert IsolateCondition.count() == 1

        params.id = isolate.id

        controller.delete()

        assert IsolateCondition.count() == 0
        assert IsolateCondition.get(isolate.id) == null
        assert response.redirectedUrl == '/isolate/list'
    }
}

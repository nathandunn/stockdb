<%@ page import="edu.uoregon.stockdb.IsolateCondition" %>


<div class="fieldcontain">
    <label for="isolated">
        <g:message code="isolate.id.label" default="ID" />

    </label>
    ${isolateInstance.id}
</div>

<div class="fieldcontain ${hasErrors(bean: isolateInstance, field: 'isolatedBy', 'error')} ">
	<label for="isolatedBy">
		<g:message code="isolate.isolatedBy.label" default="Isolated By" />
		
	</label>
	<g:select id="isolatedBy" name="isolatedBy.id" from="${edu.uoregon.stockdb.Researcher.list()}"
        optionValue="fullName"
              optionKey="id" value="${isolateInstance?.isolatedBy?.id}" class="many-to-one" noSelection="['null': '']"/>

    <g:link action="create" controller="researcher">Create Researcher</g:link>
</div>

<div class="fieldcontain ${hasErrors(bean: isolateInstance, field: 'isolatedWhen', 'error')} ">
	<label for="isolatedWhen">
		<g:message code="isolate.isolatedWhen.label" default="Isolated When" />
		
	</label>
	<g:datePicker name="isolatedWhen" precision="day"  value="${isolateInstance?.isolatedWhen}" default="${new Date()}" relativeYears="[0..-8]" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: isolateInstance, field: 'media', 'error')} ">
	<label for="media">
		<g:message code="isolate.media.label" default="Media" />
		
	</label>
	<g:textField name="media" value="${isolateInstance?.media}" size="40"/>
</div>


<div class="fieldcontain ${hasErrors(bean: isolateInstance, field: 'oxygenCondition', 'error')} ">
	<label for="oxygenCondition">
		<g:message code="isolate.oxygenCondition.label" default="Oxygen Condition" />
		
	</label>
	<g:textField name="oxygenCondition" value="${isolateInstance?.oxygenCondition}"/>
</div>


<div class="fieldcontain ${hasErrors(bean: isolateInstance, field: 'temperature', 'error')} ">
	<label for="temperature">
		<g:message code="isolate.temperature.label" default="Temperature (C)" />
		
	</label>
	<g:field name="temperature" value="${fieldValue(bean: isolateInstance, field: 'temperature')}" type="number"/>
</div>

<div class="fieldcontain ${hasErrors(bean: isolateInstance, field: 'notes', 'error')} ">
    <label for="notes">
        <g:message code="isolate.notes.label" default="Notes" />

    </label>
    <g:textField name="notes" value="${isolateInstance?.notes}"/>
</div>


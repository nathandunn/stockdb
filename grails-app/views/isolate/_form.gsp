<%@ page import="edu.uoregon.stockdb.Isolate" %>



<div class="fieldcontain ${hasErrors(bean: isolateInstance, field: 'isolatedBy', 'error')} ">
	<label for="isolatedBy">
		<g:message code="isolate.isolatedBy.label" default="Isolated By" />
		
	</label>
	<g:select id="isolatedBy" name="isolatedBy.id" from="${edu.uoregon.stockdb.Researcher.list()}" optionKey="id" value="${isolateInstance?.isolatedBy?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: isolateInstance, field: 'isolatedWhen', 'error')} ">
	<label for="isolatedWhen">
		<g:message code="isolate.isolatedWhen.label" default="Isolated When" />
		
	</label>
	<g:datePicker name="isolatedWhen" precision="day"  value="${isolateInstance?.isolatedWhen}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: isolateInstance, field: 'media', 'error')} ">
	<label for="media">
		<g:message code="isolate.media.label" default="Media" />
		
	</label>
	<g:textField name="media" value="${isolateInstance?.media}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: isolateInstance, field: 'notes', 'error')} ">
	<label for="notes">
		<g:message code="isolate.notes.label" default="Notes" />
		
	</label>
	<g:textField name="notes" value="${isolateInstance?.notes}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: isolateInstance, field: 'oxygenCondition', 'error')} ">
	<label for="oxygenCondition">
		<g:message code="isolate.oxygenCondition.label" default="Oxygen Condition" />
		
	</label>
	<g:textField name="oxygenCondition" value="${isolateInstance?.oxygenCondition}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: isolateInstance, field: 'researcher', 'error')} ">
	<label for="researcher">
		<g:message code="isolate.researcher.label" default="Researcher" />
		
	</label>
	<g:select id="researcher" name="researcher.id" from="${edu.uoregon.stockdb.Researcher.list()}" optionKey="id" value="${isolateInstance?.researcher?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: isolateInstance, field: 'temperature', 'error')} ">
	<label for="temperature">
		<g:message code="isolate.temperature.label" default="Temperature" />
		
	</label>
	<g:field name="temperature" value="${fieldValue(bean: isolateInstance, field: 'temperature')}"/>
</div>


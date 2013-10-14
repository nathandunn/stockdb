<%@ page import="edu.uoregon.stockdb.Population" %>



<div class="fieldcontain ${hasErrors(bean: populationInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="population.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${populationInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: populationInstance, field: 'captureDate', 'error')} ">
	<label for="captureDate">
		<g:message code="population.captureDate.label" default="Capture Date" />
		
	</label>
	<g:datePicker name="captureDate" precision="day"  value="${populationInstance?.captureDate}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: populationInstance, field: 'externalId', 'error')} ">
	<label for="externalId">
		<g:message code="population.externalId.label" default="External Id" />
		
	</label>
	<g:textField name="externalId" value="${populationInstance?.externalId}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: populationInstance, field: 'hostOrigins', 'error')} ">
	<label for="hostOrigins">
		<g:message code="population.hostOrigins.label" default="Host Origins" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${populationInstance?.hostOrigins?}" var="h">
    <li><g:link controller="hostOrigin" action="show" id="${h.id}">${h?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="hostOrigin" action="create" params="['population.id': populationInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'hostOrigin.label', default: 'HostOrigin')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: populationInstance, field: 'latitude', 'error')} ">
	<label for="latitude">
		<g:message code="population.latitude.label" default="Latitude" />
		
	</label>
	<g:field name="latitude" value="${fieldValue(bean: populationInstance, field: 'latitude')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: populationInstance, field: 'longitude', 'error')} ">
	<label for="longitude">
		<g:message code="population.longitude.label" default="Longitude" />
		
	</label>
	<g:field name="longitude" value="${fieldValue(bean: populationInstance, field: 'longitude')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: populationInstance, field: 'notes', 'error')} ">
	<label for="notes">
		<g:message code="population.notes.label" default="Notes" />
		
	</label>
	<g:textArea name="notes" value="${populationInstance?.notes}"/>
</div>


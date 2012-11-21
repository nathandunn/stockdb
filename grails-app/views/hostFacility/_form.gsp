<%@ page import="edu.uoregon.stockdb.HostFacility" %>



<div class="fieldcontain ${hasErrors(bean: hostFacilityInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="hostFacility.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${hostFacilityInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: hostFacilityInstance, field: 'origins', 'error')} ">
	<label for="origins">
		<g:message code="hostFacility.origins.label" default="Origins" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${hostFacilityInstance?.origins?}" var="o">
    <li><g:link controller="hostOrigin" action="show" id="${o.id}">${o?.name}</g:link></li>
</g:each>
<li class="add">
<g:link controller="hostOrigin" action="create" params="['hostFacility.id': hostFacilityInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'hostOrigin.label', default: 'HostOrigin')])}</g:link>
</li>
</ul>

</div>


<%@ page import="edu.uoregon.stockdb.HostGenotype" %>



<div class="fieldcontain ${hasErrors(bean: hostGenotypeInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="hostGenotype.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${hostGenotypeInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: hostGenotypeInstance, field: 'zfinId', 'error')} ">
	<label for="zfinId">
		<g:message code="hostGenotype.zfinId.label" default="Zfin Id" />
		
	</label>
	<g:textField name="zfinId" value="${hostGenotypeInstance?.zfinId}"/>
</div>


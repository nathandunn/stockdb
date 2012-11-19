<%@ page import="edu.uoregon.stockdb.Phenotype" %>



<div class="fieldcontain ${hasErrors(bean: phenotypeInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="phenotype.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${phenotypeInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: phenotypeInstance, field: 'url', 'error')} ">
	<label for="url">
		<g:message code="phenotype.url.label" default="Url" />
		
	</label>
	<g:textField name="url" value="${phenotypeInstance?.url}"/>
</div>


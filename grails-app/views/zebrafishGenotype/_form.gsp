<%@ page import="edu.uoregon.stockdb.ZebrafishGenotype" %>



<div class="fieldcontain ${hasErrors(bean: zebrafishGenotypeInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="zebrafishGenotype.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${zebrafishGenotypeInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: zebrafishGenotypeInstance, field: 'zfinId', 'error')} ">
	<label for="zfinId">
		<g:message code="zebrafishGenotype.zfinId.label" default="Zfin Id" />
		
	</label>
	<g:textField name="zfinId" value="${zebrafishGenotypeInstance?.zfinId}"/>
</div>


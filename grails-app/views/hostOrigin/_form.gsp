<%@ page import="edu.uoregon.stockdb.HostOrigin" %>



<div class="fieldcontain ${hasErrors(bean: hostOriginInstance, field: 'stage', 'error')} ">
	<label for="stage">
		<g:message code="hostOrigin.stage.label" default="Stage" />
		
	</label>
	<g:textField name="stage" value="${hostOriginInstance?.stage}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: hostOriginInstance, field: 'days', 'error')} ">
	<label for="days">
		<g:message code="hostOrigin.days.label" default="Days" />
		
	</label>
	<g:field name="days" type="number" value="${hostOriginInstance.days}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: hostOriginInstance, field: 'anatomy', 'error')} ">
	<label for="anatomy">
		<g:message code="hostOrigin.anatomy.label" default="Anatomy" />
		
	</label>
	<g:textField name="anatomy" value="${hostOriginInstance?.anatomy}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: hostOriginInstance, field: 'hostFacility', 'error')} ">
	<label for="hostFacility">
		<g:message code="hostOrigin.hostFacility.label" default="Host Facility" />
		
	</label>
	<g:select id="hostFacility" name="hostFacility.id" from="${edu.uoregon.stockdb.HostFacility.list()}" optionKey="id" value="${hostOriginInstance?.hostFacility?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: hostOriginInstance, field: 'genotype', 'error')} ">
	<label for="genotype">
		<g:message code="hostOrigin.genotype.label" default="Genotype" />
		
	</label>
	<g:select id="genotype" name="genotype.id" from="${edu.uoregon.stockdb.ZebrafishGenotype.list()}" optionKey="id" value="${hostOriginInstance?.genotype?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: hostOriginInstance, field: 'genus', 'error')} ">
	<label for="genus">
		<g:message code="hostOrigin.genus.label" default="Genus" />
		
	</label>
	<g:select id="genus" name="genus.id" from="${edu.uoregon.stockdb.Genus.list()}" optionKey="id" value="${hostOriginInstance?.genus?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: hostOriginInstance, field: 'notes', 'error')} ">
	<label for="notes">
		<g:message code="hostOrigin.notes.label" default="Notes" />
		
	</label>
	<g:textField name="notes" value="${hostOriginInstance?.notes}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: hostOriginInstance, field: 'phenotypes', 'error')} ">
	<label for="phenotypes">
		<g:message code="hostOrigin.phenotypes.label" default="Phenotypes" />
		
	</label>
	<g:select name="phenotypes" from="${edu.uoregon.stockdb.Phenotype.list()}" multiple="multiple" optionKey="id" size="5" value="${hostOriginInstance?.phenotypes*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: hostOriginInstance, field: 'phylum', 'error')} ">
	<label for="phylum">
		<g:message code="hostOrigin.phylum.label" default="Phylum" />
		
	</label>
	<g:select id="phylum" name="phylum.id" from="${edu.uoregon.stockdb.Phylum.list()}" optionKey="id" value="${hostOriginInstance?.phylum?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: hostOriginInstance, field: 'strains', 'error')} ">
	<label for="strains">
		<g:message code="hostOrigin.strains.label" default="Strains" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${hostOriginInstance?.strains?}" var="s">
    <li><g:link controller="strain" action="show" id="${s.id}">${s?.name}</g:link></li>
</g:each>
<li class="add">
<g:link controller="strain" action="create" params="['hostOrigin.id': hostOriginInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'strain.label', default: 'Strain')])}</g:link>
</li>
</ul>

</div>


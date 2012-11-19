<%@ page import="edu.uoregon.stockdb.Origin" %>



<div class="fieldcontain ${hasErrors(bean: originInstance, field: 'age', 'error')} required">
	<label for="age">
		<g:message code="origin.age.label" default="Age" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="age" type="number" value="${originInstance.age}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: originInstance, field: 'arbitraryData', 'error')} required">
	<label for="arbitraryData">
		<g:message code="origin.arbitraryData.label" default="Arbitrary Data" />
		<span class="required-indicator">*</span>
	</label>
	
</div>

<div class="fieldcontain ${hasErrors(bean: originInstance, field: 'genus', 'error')} required">
	<label for="genus">
		<g:message code="origin.genus.label" default="Genus" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="genus" name="genus.id" from="${edu.uoregon.stockdb.Genus.list()}" optionKey="id" required="" value="${originInstance?.genus?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: originInstance, field: 'hostFacility', 'error')} required">
	<label for="hostFacility">
		<g:message code="origin.hostFacility.label" default="Host Facility" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="hostFacility" name="hostFacility.id" from="${edu.uoregon.stockdb.HostFacility.list()}" optionKey="id" required="" value="${originInstance?.hostFacility?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: originInstance, field: 'partOfFish', 'error')} ">
	<label for="partOfFish">
		<g:message code="origin.partOfFish.label" default="Part Of Fish" />
		
	</label>
	<g:textField name="partOfFish" value="${originInstance?.partOfFish}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: originInstance, field: 'phenotypes', 'error')} ">
	<label for="phenotypes">
		<g:message code="origin.phenotypes.label" default="Phenotypes" />
		
	</label>
	<g:select name="phenotypes" from="${edu.uoregon.stockdb.Phenotype.list()}" multiple="multiple" optionKey="id" size="5" value="${originInstance?.phenotypes*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: originInstance, field: 'phylum', 'error')} required">
	<label for="phylum">
		<g:message code="origin.phylum.label" default="Phylum" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="phylum" name="phylum.id" from="${edu.uoregon.stockdb.Phylum.list()}" optionKey="id" required="" value="${originInstance?.phylum?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: originInstance, field: 'strains', 'error')} ">
	<label for="strains">
		<g:message code="origin.strains.label" default="Strains" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${originInstance?.strains?}" var="s">
    <li><g:link controller="strain" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="strain" action="create" params="['origin.id': originInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'strain.label', default: 'Strain')])}</g:link>
</li>
</ul>

</div>


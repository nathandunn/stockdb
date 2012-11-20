<%@ page import="edu.uoregon.stockdb.Strain" %>



<div class="fieldcontain ${hasErrors(bean: strainInstance, field: 'sequence', 'error')} ">
	<label for="sequence">
		<g:message code="strain.sequence.label" default="Sequence" />
		
	</label>
	<g:textField name="sequence" value="${strainInstance?.sequence}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: strainInstance, field: 'values', 'error')} ">
	<label for="values">
		<g:message code="strain.values.label" default="Values" />
		
	</label>
	<g:textField name="values" value="${strainInstance?.values}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: strainInstance, field: 'formAlias', 'error')} ">
	<label for="formAlias">
		<g:message code="strain.formAlias.label" default="Form Alias" />
		
	</label>
	<g:textField name="formAlias" value="${strainInstance?.formAlias}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: strainInstance, field: 'genus', 'error')} required">
	<label for="genus">
		<g:message code="strain.genus.label" default="Genus" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="genus" name="genus.id" from="${edu.uoregon.stockdb.Genus.list()}" optionKey="id" required="" value="${strainInstance?.genus?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: strainInstance, field: 'isolatedBy', 'error')} required">
	<label for="isolatedBy">
		<g:message code="strain.isolatedBy.label" default="Isolated By" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="isolatedBy" name="isolatedBy.id" from="${edu.uoregon.stockdb.User.list()}" optionKey="id" required="" value="${strainInstance?.isolatedBy?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: strainInstance, field: 'isolatedWhen', 'error')} required">
	<label for="isolatedWhen">
		<g:message code="strain.isolatedWhen.label" default="Isolated When" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="isolatedWhen" precision="day"  value="${strainInstance?.isolatedWhen}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: strainInstance, field: 'motility', 'error')} required">
	<label for="motility">
		<g:message code="strain.motility.label" default="Motility" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="motility" value="${fieldValue(bean: strainInstance, field: 'motility')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: strainInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="strain.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${strainInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: strainInstance, field: 'origin', 'error')} required">
	<label for="origin">
		<g:message code="strain.origin.label" default="Origin" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="origin" name="origin.id" from="${edu.uoregon.stockdb.HostOrigin.list()}" optionKey="id" required="" value="${strainInstance?.origin?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: strainInstance, field: 'parentStrain', 'error')} required">
	<label for="parentStrain">
		<g:message code="strain.parentStrain.label" default="Parent Strain" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="parentStrain" name="parentStrain.id" from="${edu.uoregon.stockdb.Strain.list()}" optionKey="id" required="" value="${strainInstance?.parentStrain?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: strainInstance, field: 'phylum', 'error')} required">
	<label for="phylum">
		<g:message code="strain.phylum.label" default="Phylum" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="phylum" name="phylum.id" from="${edu.uoregon.stockdb.Phylum.list()}" optionKey="id" required="" value="${strainInstance?.phylum?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: strainInstance, field: 'stocks', 'error')} ">
	<label for="stocks">
		<g:message code="strain.stocks.label" default="Stocks" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${strainInstance?.stocks?}" var="s">
    <li><g:link controller="stock" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="stock" action="create" params="['strain.id': strainInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'stock.label', default: 'Stock')])}</g:link>
</li>
</ul>

</div>


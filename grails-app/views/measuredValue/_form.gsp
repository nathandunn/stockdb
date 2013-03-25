<%@ page import="edu.uoregon.stockdb.MeasuredValue" %>



<div class="fieldcontain ${hasErrors(bean: measuredValueInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="measuredValue.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${measuredValueInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: measuredValueInstance, field: 'value', 'error')} ">
	<label for="value">
		<g:message code="measuredValue.value.label" default="Value" />
		
	</label>
	<g:textField name="value" value="${measuredValueInstance?.value}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: measuredValueInstance, field: 'experiment', 'error')} ">
	<label for="experiment">
		<g:message code="measuredValue.experiment.label" default="Experiment" />
		
	</label>
	<g:select id="experiment" name="experiment.id" optionValue="name" from="${edu.uoregon.stockdb.Experiment.list()}" optionKey="id" value="${measuredValueInstance?.experiment?.id}" class="many-to-one" noSelection="['null': '']"/>
    <g:link action="create" controller="experiment">Create Experiment</g:link>
</div>

<div class="fieldcontain ${hasErrors(bean: measuredValueInstance, field: 'phenotype', 'error')} ">
	<label for="phenotype">
		<g:message code="measuredValue.phenotype.label" default="Phenotype" />
		
	</label>
	<g:select id="phenotype" name="phenotype.id" optionValue="name" from="${edu.uoregon.stockdb.Phenotype.list()}" optionKey="id" value="${measuredValueInstance?.phenotype?.id}" class="many-to-one" noSelection="['null': '']"/>
    <g:link action="create" controller="phenotype">Create Phenotype</g:link>
</div>

<div class="fieldcontain ${hasErrors(bean: measuredValueInstance, field: 'type', 'error')} ">
	<label for="type">
		<g:message code="measuredValue.type.label" default="Type" />
		
	</label>
	<g:select name="type" from="${edu.uoregon.stockdb.MeasuredValueTypeEnum?.values()}" keys="${edu.uoregon.stockdb.MeasuredValueTypeEnum.values()*.name()}" value="${measuredValueInstance?.type?.name()}" noSelection="['': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: measuredValueInstance, field: 'units', 'error')} ">
	<label for="units">
		<g:message code="measuredValue.units.label" default="Units" />
		
	</label>
	<g:textField name="units" value="${measuredValueInstance?.units}"/>
</div>


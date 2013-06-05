<%@ page import="edu.uoregon.stockdb.MeasuredValue" %>




<div class="fieldcontain ${hasErrors(bean: measuredValueInstance, field: 'value', 'error')} ">
	<label for="value">
		<g:message code="measuredValue.value.label" default="Value" />
		
	</label>
	<g:textField name="value" value="${measuredValueInstance?.value}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: measuredValueInstance, field: 'type', 'error')} ">
	<label for="type">
		<g:message code="measuredValue.type.label" default="Type" />
		
	</label>
	%{--<g:select name="type" from="${edu.uoregon.stockdb.MeasuredValueTypeEnum?.values()}" keys="${edu.uoregon.stockdb.MeasuredValueTypeEnum.values()*.name()}" value="${measuredValueInstance?.type?.name()}" noSelection="['': '']"/>--}%
    <g:select name="type" from="${edu.uoregon.stockdb.MeasuredValueTypeEnum?.values()}"
              noSelection="['':'- no selection -']"/>
</div>

%{--<div class="fieldcontain ${hasErrors(bean: measuredValueInstance, field: 'units', 'error')} ">--}%
	%{--<label for="units">--}%
		%{--<g:message code="measuredValue.units.label" default="Units" />--}%
		%{----}%
	%{--</label>--}%
	%{--<g:textField name="units" value="${measuredValueInstance?.units}"/>--}%
%{--</div>--}%


<div class="fieldcontain ${hasErrors(bean: measuredValueInstance, field: 'experiment', 'error')} ">
    <label for="experiment">
        <g:message code="measuredValue.experiment.label" default="Experiment" />

    </label>
    <g:select id="experiment" name="experiment.id" optionValue="name" from="${edu.uoregon.stockdb.Experiment.listOrderByName()}" optionKey="id" value="${measuredValueInstance?.experiment?.id}" class="many-to-one" noSelection="['null': '- NONE -']"/>
    <g:link action="create" controller="experiment">Create Experiment</g:link>
</div>


<div class="fieldcontain ${hasErrors(bean: measuredValueInstance, field: 'strain', 'error')} ">
    <label for="strain">
        <g:message code="measuredValue.strain.label" default="Experiment" />

    </label>
    <g:select id="strain" name="strain.id" optionValue="name" from="${edu.uoregon.stockdb.Strain.listOrderByName()}" optionKey="id" value="${measuredValueInstance?.strain?.id}" class="many-to-one" noSelection="['null': '- NONE -']"/>
    <g:link action="create" controller="strain">Create Experiment</g:link>
</div>

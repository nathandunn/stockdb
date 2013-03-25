<%@ page import="edu.uoregon.stockdb.Experiment" %>



<div class="fieldcontain ${hasErrors(bean: experimentInstance, field: 'measuredValues', 'error')} ">
	<label for="measuredValues">
		<g:message code="experiment.measuredValues.label" default="Measured Values" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${experimentInstance?.measuredValues?}" var="m">
    <li><g:link controller="measuredValue" action="show" id="${m.id}">${m?.name}</g:link></li>
</g:each>
<li class="add">
<g:link controller="measuredValue" action="create" params="['experiment.id': experimentInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'measuredValue.label', default: 'MeasuredValue')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: experimentInstance, field: 'note', 'error')} ">
	<label for="note">
		<g:message code="experiment.note.label" default="Note" />
		
	</label>
	<g:textField name="note" value="${experimentInstance?.note}"/>
</div>

%{--<div class="fieldcontain ${hasErrors(bean: experimentInstance, field: 'phenotypes', 'error')} ">--}%
	%{--<label for="phenotypes">--}%
		%{--<g:message code="experiment.phenotypes.label" default="Phenotypes" />--}%
		%{----}%
	%{--</label>--}%
	%{--<g:select name="phenotypes" from="${edu.uoregon.stockdb.Phenotype.list()}" multiple="multiple" optionKey="id" size="5" value="${experimentInstance?.phenotypes*.id}" class="many-to-many"/>--}%
%{--</div>--}%

<div class="fieldcontain ${hasErrors(bean: experimentInstance, field: 'researcher', 'error')} ">
	<label for="researcher">
		<g:message code="experiment.researcher.label" default="Researcher" />
		
	</label>
	<g:select id="researcher" name="researcher.id" from="${edu.uoregon.stockdb.Researcher.list()}" optionKey="id" value="${experimentInstance?.researcher?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: experimentInstance, field: 'strains', 'error')} ">
	<label for="strains">
		<g:message code="experiment.strains.label" default="Strains" />
		
	</label>
	<g:select name="strains" from="${edu.uoregon.stockdb.Strain.list()}" multiple="multiple" optionKey="id" size="5" value="${experimentInstance?.strains*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: experimentInstance, field: 'whenPerformed', 'error')} ">
	<label for="whenPerformed">
		<g:message code="experiment.whenPerformed.label" default="When Performed" />
		
	</label>
	<g:datePicker name="whenPerformed" precision="day"  value="${experimentInstance?.whenPerformed}" default="none" noSelection="['': '']" />
</div>


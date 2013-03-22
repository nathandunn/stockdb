<%@ page import="edu.uoregon.stockdb.StrainGenotype" %>



<div class="fieldcontain ${hasErrors(bean: strainGenotypeInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="strainGenotype.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${strainGenotypeInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: strainGenotypeInstance, field: 'note', 'error')} ">
	<label for="note">
		<g:message code="strainGenotype.note.label" default="Note" />
		
	</label>
	<g:textField name="note" value="${strainGenotypeInstance?.note}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: strainGenotypeInstance, field: 'strains', 'error')} ">
	<label for="strains">
		<g:message code="strainGenotype.strains.label" default="Strains" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${strainGenotypeInstance?.strains?}" var="s">
    <li><g:link controller="strain" action="show" id="${s.id}">${s?.name}</g:link></li>
</g:each>
%{--<li class="add">--}%
%{--<g:link controller="strain" action="create" params="['strainGenotype.id': strainGenotypeInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'strain.label', default: 'Strain')])}</g:link>--}%
%{--</li>--}%
</ul>

</div>


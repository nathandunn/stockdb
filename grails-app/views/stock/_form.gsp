<%@ page import="edu.uoregon.stockdb.Stock" %>



<div class="fieldcontain ${hasErrors(bean: stockInstance, field: 'strain', 'error')} ">
	<label for="strain">
		<g:message code="stock.strain.label" default="Strain" />
		
	</label>
	<g:select id="strain" name="strain.id" from="${edu.uoregon.stockdb.Strain.list()}" optionKey="id" value="${stockInstance?.strain?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: stockInstance, field: 'generalLocation', 'error')} ">
	<label for="generalLocation">
		<g:message code="stock.generalLocation.label" default="General Location" />
		
	</label>
	<g:select id="generalLocation" name="generalLocation.id" from="${edu.uoregon.stockdb.Location.list()}" optionKey="id" value="${stockInstance?.generalLocation?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: stockInstance, field: 'physicalLocation', 'error')} ">
	<label for="physicalLocation">
		<g:message code="stock.physicalLocation.label" default="Physical Location" />
		
	</label>
	<g:textField name="physicalLocation" value="${stockInstance?.physicalLocation}"/>
</div>


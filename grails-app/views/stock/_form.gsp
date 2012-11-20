<%@ page import="edu.uoregon.stockdb.Stock" %>



<div class="fieldcontain ${hasErrors(bean: stockInstance, field: 'location', 'error')} required">
	<label for="location">
		<g:message code="stock.location.label" default="Location" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="location" name="location.id" from="${edu.uoregon.stockdb.Location.list()}" optionKey="id" required="" value="${stockInstance?.location?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: stockInstance, field: 'strain', 'error')} required">
	<label for="strain">
		<g:message code="stock.strain.label" default="Strain" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="strain" name="strain.id" from="${edu.uoregon.stockdb.Strain.list()}" optionKey="id" required="" value="${stockInstance?.strain?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: stockInstance, field: 'value', 'error')} ">
	<label for="value">
		<g:message code="stock.value.label" default="Value" />
		
	</label>
	<g:textField name="value" value="${stockInstance?.value}"/>
</div>


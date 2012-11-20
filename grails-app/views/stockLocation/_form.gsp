<%@ page import="edu.uoregon.stockdb.Stock" %>



<div class="fieldcontain ${hasErrors(bean: stockLocationInstance, field: 'locationDetail', 'error')} ">
	<label for="locationDetail">
		<g:message code="stockLocation.locationDetail.label" default="Location Detail" />
		
	</label>
	<g:textField name="locationDetail" value="${stockLocationInstance?.locationDetail}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: stockLocationInstance, field: 'locationGeneral', 'error')} ">
	<label for="locationGeneral">
		<g:message code="stockLocation.locationGeneral.label" default="Location General" />
		
	</label>
	<g:textField name="locationGeneral" value="${stockLocationInstance?.locationGeneral}"/>
</div>


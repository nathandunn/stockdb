<%@ page import="edu.uoregon.stockdb.Location" %>



<div class="fieldcontain ${hasErrors(bean: locationInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="location.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${locationInstance?.name}" size="60"/>
</div>

<div class="fieldcontain ${hasErrors(bean: locationInstance, field: 'stocks', 'error')} ">
	<label for="stocks">
		<g:message code="location.stocks.label" default="Stocks" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${locationInstance?.stocks?}" var="s">
    <li><g:link controller="stock" action="show" id="${s.id}">${s?.display}</g:link></li>
</g:each>
%{--<li class="add">--}%
%{--<g:link controller="stock" action="create" params="['location.id': locationInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'stock.label', default: 'Stock')])}</g:link>--}%
%{--</li>--}%
</ul>

</div>


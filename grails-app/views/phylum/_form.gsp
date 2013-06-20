<%@ page import="edu.uoregon.stockdb.Phylum" %>


<div class="fieldcontain ${hasErrors(bean: phylumInstance, field: 'name', 'error')} ">
    <label for="name">
        <g:message code="phylum.name.label" default="Name"/>

    </label>
    <g:textField name="name" value="${phylumInstance?.name}" size="60"/>
</div>


<div class="fieldcontain ${hasErrors(bean: phylumInstance, field: 'genuses', 'error')} ">
    <label for="genuses">
        <g:message code="phylum.genuses.label" default="Genuses"/>

    </label>

    <ul class="one-to-many">
        <g:each in="${phylumInstance?.genuses ?}" var="g">
            <li>
                <g:link controller="genus" action="show" id="${g.id}">${g?.name}</g:link>
            </li>
        </g:each>
        <br/>
        %{--<g:select id="phylum" name="addgenusid" from="${edu.uoregon.stockdb.Genus.listOrderByName() - phylumInstance?.genuses }" optionKey="id"--}%
                   %{--optionValue="name" class="many-to-one"--}%
                  %{--noSelection="['null': '- Add Existing -']"/>--}%
        %{--<g:link controller="strain" action="create">Create Genus</g:link>--}%
    </ul>

</div>


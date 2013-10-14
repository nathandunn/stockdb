<%@ page import="edu.uoregon.stockdb.GenomeType" %>



<div class="fieldcontain ${hasErrors(bean: genomeTypeInstance, field: 'baseUrl', 'error')} ">
    <label for="baseUrl">
        <g:message code="genomeType.baseUrl.label" default="Base Url"/>

    </label>
    <g:field type="url" name="baseUrl" value="${genomeTypeInstance?.baseUrl}" size="60"/>
</div>

<div class="fieldcontain ${hasErrors(bean: genomeTypeInstance, field: 'organizationName', 'error')} ">
    <label for="organizationName">
        <g:message code="genomeType.organizationName.label" default="Organization Name"/>

    </label>
    <g:textField name="organizationName" value="${genomeTypeInstance?.organizationName}" size="60"/>
</div>

<div class="fieldcontain ${hasErrors(bean: genomeTypeInstance, field: 'genomes', 'error')} ">
    <label for="genomes">
        <g:message code="genomeType.genomes.label" default="Genomes"/>

    </label>

    <ul class="one-to-many">
            <g:each in="${genomeTypeInstance?.genomes?.sort { a, b -> a.externalId.compareTo(b.externalId) }}" var="g">
                <li><g:link controller="genome" action="show" id="${g.id}">${g?.externalId}</g:link></li>
            </g:each>
        <li class="add">
            <g:link controller="genome" action="create"
                    params="['genomeType.id': genomeTypeInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'genome.label', default: 'Genome')])}</g:link>
        </li>
    </ul>

</div>


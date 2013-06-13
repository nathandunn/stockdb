<%@ page import="edu.uoregon.stockdb.Genome" %>



<div class="fieldcontain ${hasErrors(bean: genomeInstance, field: 'url', 'error')} ">
    <label for="url">
        <g:message code="genome.url.label" default="Url"/>

    </label>
    <g:field type="url" name="url" value="${genomeInstance?.url}" size="80"/>
</div>

<div class="fieldcontain ${hasErrors(bean: genomeInstance, field: 'quality', 'error')} ">
    <label for="quality">
        <g:message code="genome.quality.label" default="Quality"/>

    </label>
    <g:field type="number" name="quality" value="${fieldValue(bean: genomeInstance, field: 'quality')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: genomeInstance, field: 'size', 'error')} ">
    <label for="size">
        <g:message code="genome.size.label" default="Size"/>

    </label>
    <g:field name="size" value="${fieldValue(bean: genomeInstance, field: 'size')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: genomeInstance, field: 'strains', 'error')} ">
    <label for="strains">
        <g:message code="genome.strains.label" default="Strains"/>

    </label>


    <g:select id="strain" name="addstrainid" from="${edu.uoregon.stockdb.Strain.list()}" optionKey="id"
              value="${strainInstance?.genus?.id}" optionValue="name" class="many-to-one"
              noSelection="['null': '- Add Existing -']"/>
    <g:link controller="strain" action="create">Create Strain</g:link>
</div>


<div class="fieldcontain ${hasErrors(bean: genomeInstance, field: 'strains', 'error')} ">
    <label for="strains">
        <g:message code="genome.strains.label" default="Strains"/>

    </label>

    <ul class="one-to-many">
        <g:each in="${genomeInstance?.strains ?}" var="s">
            <li>
                <g:link controller="strain" action="show" id="${s.id}">${s?.name}</g:link>
                <g:link controller="genome" action="removeGenomeFromStrain" params="[genomeId:genomeInstance.id,strainId:s.id]">[remove]</g:link>
            </li>
        </g:each>
    </ul>

</div>

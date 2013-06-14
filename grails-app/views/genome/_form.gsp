<%@ page import="edu.uoregon.stockdb.Genome" %>



<div class="fieldcontain ${hasErrors(bean: genomeInstance, field: 'genomeType', 'error')} ">
    <label for="genomeType">
        <g:message code="genome.genomeType.label" default="Genome Type"/>

    </label>
    %{--<g:field type="url" name="url" value="${genomeInstance?.url}" size="80"/>--}%
    <g:select id="genomeType" name="genomeType.id" from="${edu.uoregon.stockdb.GenomeType.list()}" optionKey="id"
              value="${genomeInstance?.genomeType?.id}" optionValue="organizationName" class="many-to-one"
              noSelection="['null': '- Choose Existing -']"/>
    <g:link controller="genomeType" action="create">Create Genome Type</g:link>
</div>

<div class="fieldcontain ${hasErrors(bean: genomeInstance, field: 'externalId', 'error')} ">
    <label for="externalId">
        <g:message code="genome.externalId.label" default="External Id"/>

    </label>
    <g:field type="text" name="externalId" value="${fieldValue(bean: genomeInstance, field: 'externalId')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: genomeInstance, field: 'quality', 'error')} ">
    <label for="quality">
        <g:message code="genome.quality.label" default="Contigs"/>

    </label>
    <g:field type="number" name="quality" value="${fieldValue(bean: genomeInstance, field: 'quality')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: genomeInstance, field: 'size', 'error')} ">
    <label for="size">
        <g:message code="genome.size.label" default="Size"/>

    </label>
    <g:field type="text" name="size" value="${fieldValue(bean: genomeInstance, field: 'size')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: genomeInstance, field: 'genomeVersion', 'error')} ">
    <label for="genomeVersion">
        <g:message code="genome.genomeVersion.label" default="Version"/>

    </label>
    <g:field type="text" name="genomeVersion" value="${fieldValue(bean: genomeInstance, field: 'genomeVersion')}"/>
</div>

%{--<div class="fieldcontain ${hasErrors(bean: genomeInstance, field: 'strain', 'error')} ">--}%
    %{--<label for="strain">--}%
        %{--<g:message code="genome.strain.label" default="Strain"/>--}%

    %{--</label>--}%

    %{--<g:select id="strain" name="addstrainid" from="${edu.uoregon.stockdb.Strain.list()}" optionKey="id"--}%
              %{--value="${strainInstance?.genus?.id}" optionValue="name" class="many-to-one"--}%
              %{--noSelection="['null': '- Add Existing -']"/>--}%
    %{--<g:link controller="strain" action="create">Create Strain</g:link>--}%
%{--</div>--}%


<div class="fieldcontain ${hasErrors(bean: genomeInstance, field: 'strain', 'error')} ">
    <label for="strains">
        <g:message code="genome.strains.label" default="Strain"/>
    </label>

    <g:select id="strain" name="strain.id" from="${edu.uoregon.stockdb.Strain.listOrderByName()}" optionKey="id"
              value="${genomeInstance?.strain?.id}" optionValue="name" class="many-to-one"
              noSelection="['null': '- Choose Existing -']"/>
    %{--<g:each in="${genomeInstance?.strains ?}" var="s">--}%
    %{--<li>--}%
    %{--<g:link controller="strain" action="show" id="${s.id}">${s?.name}</g:link>--}%
    %{--<g:link controller="genome" action="removeGenomeFromStrain"--}%
    %{--params="[genomeId: genomeInstance.id, strainId: s.id]">[remove]</g:link>--}%
    %{--</li>--}%
    %{--</g:each>--}%
    %{--</ul>--}%

</div>

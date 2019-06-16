<#-- @ftlvariable name="" type="sk.fri.uniza.views.PersonsView" -->
<!-- calls getPersons().getName() and sanitizes it -->
<div class="section no-pad-bot" id="index-banner">
    <div class="container">
        <table id="cities" class="striped" style="width:100%">
            <thead>
            <tr>
                <th>Mesto</th>
                <th>Datum</th>
                <th>Teplota</th>
                <th>Vlhkost</th>
                <th>Tlak vzduchu</th>
            </tr>
            </thead>
            <tbody>
            <#list getPersons() as person>
                <tr>
                    <td>
                        Null [HCode]
                    </td>
                    <td>
                        Null [HCode]
                    </td>
                    <td>
                        Null [HCode]
                    </td>
                    <td>
                        Null [HCode]
                    </td>
                    <td>
                        Null [HCode]
                    </td>
                </tr>
            </#list>
            </tbody>
        </table>
        <ul class="pagination">
            <#if paged.prevPage?? >
                <li class="waves-effect"><a href="?page=${paged.prevPage}">
                        <i class="material-icons">chevron_left</i></a></li>
            <#else>
                <li class="disabled"><a href="#!"><i class="material-icons">chevron_left</i></a></li>
            </#if>

            <#list 1..paged.lastPage as pageNum>
                <#if pageNum == paged.page>
                    <li class="active"><a href="?page=${pageNum}">${pageNum}</a></li>
                <#else>
                    <li class="waves-effect"><a href="?page=${pageNum}">${pageNum}</a></li>
                </#if>
            </#list>

            <#if paged.nextPage?? >
                <li class="waves-effect"><a href="?page=${paged.nextPage}">
                        <i class="material-icons">chevron_right</i></a></li>
            <#else>
                <li class="disabled"><a href="#!"><i class="material-icons">chevron_right</i></a></li>
            </#if>
        </ul>
    </div>
    <br><br>
</div>
<!-- Modal Trigger -->
<#--<a class="waves-effect waves-light btn modal-trigger" href="#modal1">Modal</a>-->

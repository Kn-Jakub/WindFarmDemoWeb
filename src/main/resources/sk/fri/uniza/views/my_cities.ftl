<#-- @ftlvariable name="" type="sk.fri.uniza.views.MyCitiesView" -->
<!-- calls getPersons().getName() and sanitizes it -->
<div class="section no-pad-bot" id="index-banner">
    <div class="container">

        <ul class="collection">
            <#list getCities() as city>
                <div class="row">

                        <li class="collection-item avatar">
                            <div class="col s10">
                                 <img src="/assets/img/cityIconRect.jpg" alt="" class="circle">

                                <a href="/home/city/?id=${city.getIdString()}" class="collection-item "><h7>${city.getName()}   [${city.getCountry()}]</h7></a>
                            </div>
                            <div class="col s2">
                                <a href="/home/city-delete?cityId=${city.getIdString()}&page=${getPaged().getPage()}" class="secondary-content ">
                                    <i class="material-icons ">delete_forever</i>
                                </a>
                            </div>
                        </li>
                   <br />
                </div>
            </#list>
        </ul>

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

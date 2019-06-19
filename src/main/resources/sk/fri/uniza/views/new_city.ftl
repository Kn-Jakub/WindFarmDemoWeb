<#-- @ftlvariable name="" type="sk.fri.uniza.views.NewCityView" -->
<!-- calls getPersons().getName() and sanitizes it -->

<div class="section no-pad-bot" id="index-banner" xmlns="http://www.w3.org/1999/html">
<#--    <form class="container" action="/home/new-city/from-country" method="post">-->
        <div class="container">
        <br><br>

        <div class="row">
            <div class="col s12">
                <div class="card">
                    <div class="card-title orange lighten-1" style="padding: 10px 10px 0px 10px">
                        <div class="row valign-wrapper">
                            <div class="col s3 m4 l2">
                                <img src="/assets/img/cityIconRect.jpg" alt="" class="circle responsive-img">
                                <!-- notice the "circle" class -->
                            </div>
                            <div class="col s9 m8 l10">
                                <span class="white-text">
                                     <h4>Parametre</h4>
                                </span>
                            </div>
                        </div>
                    </div>

                    <div class="card-content">
                        <div class="row">
                            <form action="/home/new-city/from-country" method="post">
                                <div class="input-field col s4">
                                    <select id="country" name="country" required>

                                        <option value="null" <#if (getSelectedCountry()?? && getSelectedCountry() == "null")>selected</#if>>-Vyber Krajinu-</option>

                                        <#list getCountries() as country>
                                            <option value="${country}" <#if (getSelectedCountry()?? && getSelectedCountry() == country)>selected</#if>>${country}</option>
                                        </#list>

                                    </select>
                                    <label>Krajina</label>
                                </div>

                                    <div class="col s1 ">
                                        <button class="btn waves-effect waves-light orange" type="submit" name="action">
                                            <i class="material-icons center-align">autorenew</i>
                                        </button>
                                    </div>
                            </form>


                            <form action="/home/new-city" method="post">
                                <div class="input-field col s7">
                                    <select id="city" name="city" required>
<#--                                <div class="form-group col s6">-->
<#--                                    <select name="city" id="citye" class="form-control input-lg">-->
                                        <#if getCities()??>

                                            <#list getCities() as city>
                                                <option value="${city.getId()}" >${city.getName()}</option>
                                            </#list>
                                        <#else>
                                            <option value="-1" >-Žiadne Mestá-</option>
                                        </#if>

                                    </select>
                                    <label>Mesto</label>
                                </div>
                        </div>

<#--                        <div class="row">-->
<#--                            <div class="input-field col s12">-->
<#--                            <select name="roles" required>-->

<#--                            <#list getSystemRoles().getAllRoles() as role>-->
<#--                                <option value="${role}" >${role}</option>-->
<#--                            </#list>-->

<#--                            </select>-->
<#--                            <label>Užívateľské oprávnenia</label>-->
<#--                            </div>-->
<#--                        </div>-->
<#--                        <div class="row">-->
<#--                            <div class="input-field col s12">-->
<#--                                <input id="password"  name="password" type="password" class="validate" required>-->
<#--                                <label for="password">Password</label>-->
<#--                            </div>-->
<#--                        </div>-->
<#--                        <div class="row">-->
<#--                            <div class="input-field col s12">-->
<#--                                <input id="username" name="username" type="email" class="validate" required>-->
<#--                                <label for="username">Užívateľské meno</label>-->
<#--                            </div>-->
<#--                        </div>-->
<#--                        <div class="row">-->
<#--                            <div class="input-field col s12">-->
<#--                                <input id="email" name="email" type="email" class="validate" required>-->
<#--                                <label for="email">Email</label>-->
<#--                            </div>-->
<#--                        </div>-->
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col s12 right-align">
                <button class="btn waves-effect waves-light orange" type="submit" name="action2">Uložiť
                    <i class="material-icons right">send</i>
                </button>
            </div>
        </div>
        </div>
    </form>
        <#if toastMsg??>
        <script>
            // A $( document ).ready() block.
            $( document ).ready(function() {
                M.toast({html: '${toastMsg?no_esc}'});
            });

        </script>
        </#if>



        <script>
            $( document ).ready(function() {
                $('select[name="country"]').change(function(){
                    if($(this).val() != 'null')
                    {

                        $("#city").empty();
                        $("#city").append('<option value="nieco3" >nieco3</option>');
                        var value = $(this).val();
                        {{ }}

                    }
                    else
                    {
                        $('#city').val('null').trigger('change');
                    }
                }

                // $('select[name="country"]').change(function() {
                //     if ($(this).val() == "null") {
                //         // $("#city").empty();
                //         $('#city').append('<option value="null" >----</option>');
                //         $('#city').val('null').trigger('change');
                //         $('#city').selectmenu('refresh', true);
                //         // var $newOpt = $("<option>").attr("value","null").text("Lololo");
                //         // $("#city").append($newOpt);
                //     } else {
                //         // $("#city").empty();
                //         $('#city').append('<option value="nieco" >nieco</option>');
                //         $('#city').append('<option value="nieco2" >nieco2</option>');
                //         $("#city").append('<option value="nieco3" >nieco3</option>');
                //         $('#city').val('nieco').trigger('change');
                //     }
                // });
            }
        </script>
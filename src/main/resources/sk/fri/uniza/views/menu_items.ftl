<#--https://freemarker.apache.org/docs/ref_builtins_sequence.html-->

<#-- {"title":"","url":"","icon":"","subheader":"","divider":"","roles":""}-->
<#--String USER_READ_ONLY = "USER_READ_ONLY";-->
<#--String ADMIN = "ADMIN";-->


<#assign menu_items = [
{"subheader":"Mestá","divider":"true"},
{"title":"Domov","url":"graphs","icon":"home"},
{"title":"Pridaj mesto","url":"#", "icon":"add_box"},
{"title":"Zoznam miest","url":"#", "icon":"view_list"},

{"subheader":"Uživateľ","divider":"true"},
{"title":"Osobné údaje","icon":"person","url":"persons/user-info"},
{"title":"Nový užívateľ","icon":"person_add","url":"persons/new-user","roles":["ADMIN"]},
{"title":"Zoznam užívateľov","icon":"group","url":"persons","roles":["ADMIN"]}

<#--{"subheader":"Subheader 2","divider":"true"},
{"title":"ADMIN READ_ONLY","url":"#","roles":["ADMIN","USER_READ_ONLY"]},
{"title":"ADMIN","url":"#","roles":["ADMIN"]},
{"title":"READ_ONLY","url":"#","roles":["USER_READ_ONLY"]}-->
]
>

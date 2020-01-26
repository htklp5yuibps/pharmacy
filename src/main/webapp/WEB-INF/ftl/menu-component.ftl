<#assign spring=JspTaglibs["http://www.springframework.org/tags"]/>
<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]/>
<#assign security=JspTaglibs["http://www.springframework.org/security/tags"]/>

<div class="menu">
  

  <@security.authorize access="isAuthenticated()">
    Signed as: <@security.authentication property="principal.username"/>
    <hr/>
  </@security.authorize>
  
  <@security.authorize access="isAnonymous()">
    <@spring.url var="signinPageLink" value="/signin"/>  
    <a href="${signinPageLink}">Signin</a>
  </@security.authorize>

  <@spring.url var="user_personalPage" value="/user/personalPage"/>
  <a href="${user_personalPage}">Personal page</a>
  <@spring.url var="admin_productEditionPage" value="/admin/productEditionPage"/>
  <a href="${admin_productEditionPage}">Product edition</a>
  <@spring.url var="employee_orderEditionPage" value="/employee/orderEditionPage"/>
  <a href="${employee_orderEditionPage}">Order edition</a>
  <@spring.url var="admin_accessLevelEditionPage" value="/admin/accessLevelEditionPage"/>
  <a href="${admin_accessLevelEditionPage}">Access level edition</a>
</div>
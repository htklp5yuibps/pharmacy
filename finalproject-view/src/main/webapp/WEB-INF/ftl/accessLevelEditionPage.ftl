<#ftl encoding="UTF-8"/>
<#assign spring=JspTaglibs["http://www.springframework.org/tags"]/>
<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]/>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title></title>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />

<#include "styles-component.ftl"/>
<style>
  .uui-radio.large label {
    color: white !important;
  }
  
  .sp {
    font-size: 130%;
    font-family: 'Oswald Regular', Arial, sans-serif;
  }
</style>

</head>
<body>
  <!-- wrapper -->
  <div class="wrapper">
    <#include "header-menu-component.ftl"/>
    
    <div class="uui-main-container">
    
      <!-- main -->
      <main>
        <@spring.url var="admin_accessLevelEditionPage" value="/admin/accessLevelEditionPage"/>
        <@form.form class="myui_search_bar fdr jcsb" action="${admin_accessLevelEditionPage}" method="get">
          <div class="myui_search_sub">
            <input type="text" name="name" placeholder="Name/Email part" class="uui-form-element"/>
          </div>
          <div class="myui_search_sub df aic ml15">
            <p class="myui_search_subtitle">
              Search with role
            </p>
            <div class="uui-input-group horizontal ml15">
              <p class="uui-radio large">
                <input type="radio" name="role" id="a1" value="" checked/>
                <label for="a1">All</label>
              </p>
              <p class="uui-radio large">
                <input type="radio" name="role" id="a2" value="user"/>
                <label for="a2">User</label>
              </p>
              <p class="uui-radio large">
                <input type="radio" name="role" id="a3" value="admin"/>
                <label for="a3">Admin</label>
              </p>
              <p class="uui-radio large">
                <input type="radio" name="role" id="a4" value="employee"/>
                <label for="a4">Employee</label>
              </p>
            </div>
          </div>
          <div class="myui_search_sub df aic ml15">
            <p class="myui_search_subtitle">Search with status</p>
            <div class="uui-input-group horizontal ml15">
              <p class="uui-radio large">
                <input type="radio" name="status" id="a5" value="" checked/>
                <label for="a5">All</label>
              </p>
              <p class="uui-radio large">
                <input type="radio" name="status" id="a6" value="active"/>
                <label for="a6">Active</label>
              </p>
              <p class="uui-radio large">
                <input type="radio" name="status" id="a7" value="inactive"/>
                <label for="a7">Inactive</label>
              </p>
            </div>
          </div>
          <div class="myui_search_sub">
            <input type="submit" value="Search" class="uui-button">
          </div>
        </@form.form>
        
        <!-- content -->
        <div class="content df fdc">
          
          <#if RequestParameters.role_changed??>
            <p class="myui_message-success">
              Role was successfully changed.
            </p>
            <#elseif RequestParameters.error??>
              <p class="myui_message-error">
                There was some error while processing request.
              </p>
          </#if>
          
          <#if users??>
            <#list users>
              <#items as user>
                <!-- user card -->
                <div class="card mb10">
                  
                  <!-- user card header -->
                  <div class="card_header df fdc">
                    <p class="card_title">${user.surname} ${user.name} ${user.patronymic}</p>
                    <div class="card_content_subcontent df jcsb">
                      <p class="card_title">ID: ${user.id}</p>
                      <p class="card_title">USERNAME: ${user.username}</p>
                    </div>
                  </div>
                  <!-- end of: user card header -->
                  
                  <!-- user card content -->
                  <div class="card_content">
                    <div class="card_content_subcontent">
                      <span class="sp">ROLE:</span> ${user.role}
                    </div>
                    
                    <div class="card_content_subcontent">
                      <#if user.status = 1>
                        <span class="sp">STATUS:</span> ACTIVE
                        <#else>    
                        <span class="sp">STATUS:</span> INACTIVE
                      </#if>
                    </div>
                  </div>
                  <!-- end of: user card content -->
                  
                  <!-- user card footer -->
                  <div class="card_footer">
                    <div class="card_content_subcontent">
                      <#if user.status = 1>
                        <@spring.url var="deleteUserCommandLink" value="/admin/users/delete"/>
                        <a class="uui-button df fdc" href="${deleteUserCommandLink}"><span>Delete</span></a>
                        <#else>
                        <@spring.url var="restoreUserCommandLink" value="/admin/users/restore"/>
                        <a class="uui-button df fdc" href="${restoreUserCommandLink}"><span>Restore</span></a>
                      </#if>
                    </div>
                    <div class="card_content_subcontent">
                      <@spring.url var="updateRoleCommandLink" value="/admin/users/updateRole"/>
                      <#if user.role = "admin">
                        <a class="uui-button small" href="${updateRoleCommandLink}?userId=${user.id}&role=employee"><span>Set as employee</span></a>
                        <a class="uui-button small" href="${updateRoleCommandLink}?userId=${user.id}&role=user"><span>Set as user</span></a>
                        <#elseif user.role = "employee">
                        <a class="uui-button small" href="${updateRoleCommandLink}?userId=${user.id}&role=admin"><span>Set as admin</span></a>
                        <a class="uui-button small" href="${updateRoleCommandLink}?userId=${user.id}&role=user"><span>Set as user</span></a>
                        <#elseif user.role = "user">
                        <a class="uui-button small" href="${updateRoleCommandLink}?userId=${user.id}&role=employee"><span>Set as employee</span></a>
                        <a class="uui-button small" href="${updateRoleCommandLink}?userId=${user.id}&role=admin"><span>Set as admin</span></a>
                      </#if>
                    </div>
                  </div>
                  <!-- end of: user card content -->
                
                </div>
                <!-- end of: user card -->
              </#items>
            </#list>
          </#if>
          
        </div>
        <!-- end of: content -->
        
      </main>
      <!-- end of: main -->

    </div>
    <#include "footer-component.ftl"/>
  </div>
  <!-- end of: wrapper -->
  <#include "js-component.ftl">
</body>
</html>

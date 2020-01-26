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
</head>
<body>
  <!-- wrapper -->
  <div class="wrapper">
    <#include "header-menu-component.ftl"/>
    
    <div class="uui-main-container">
    
      <!-- main -->
      <main>
        
        <!-- content -->
        <div class="content">
          
          <!-- user info card -->
          <@spring.url var="user_updatePersonalDataCommandLink" value="/user/updatePersonalData"/>
          <@form.form class="card" action="${user_updatePersonalDataCommandLink}" method="post">
            <div class="card_header">
              <p class="card_title">
                PERSONAL DATA EDITING
              </p>
            </div>
            
            <div class="card_content">
              <div class="card_content_subcontent df fdc">
                <input type="text" value="${user.name}" name="name" class="uui-form-element">
              </div>
              <div class="card_content_subcontent df fdc">
                <input type="text" value="${user.surname}" name="surname" class="uui-form-element">
              </div>
              <div class="card_content_subcontent df fdc">
                <input type="text" value="${user.patronymic}" name="patronymic" class="uui-form-element">
              </div>
            </div>
            
            <div class="card_footer">
              <div class="card_content_subcontent">
                <input type="submit" value="Update data" class="uui-button"/>
              </div>
            </div>
            
          </@form.form>
          <!-- end of: user info card -->
          
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
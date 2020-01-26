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
  .content {
    display: grid;
    grid-template-columns: repeat(6, auto);
    grid-auto-rows: auto;
    grid-gap: 10px;
  }
  
  .messages {
    grid-row-start: 1;
    grid-row-end: 2;
    grid-column-start: 1;
    grid-column-end: 7;
  }
  
  .editing_form {
    grid-row-start: 2;
    grid-column-start: 1;
    grid-column-end: 2;
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
        
        <!-- content -->
        <div class="content">
          <div class="messages">
            <p class="myui_message-tip">
              IN ORDER OF SUCCESS, YOU WILL BEW REDIRECTED TO LOGIN PAGE FOR RESIGNING.
            </p>
  
            <#if RequestParameters.error??>
              <p class="myui_message-error">
                THERE WAS SOME ERROR WHILE PROCESSING REQUEST. PLEASE, TRY AGAIN WITH CORRECT DATA.
              </p>
            </#if>          
          </div>
        
          <@spring.url var="user_updatePasswordCommandLink" value="/user/updatePassword"/>
          <@form.form class="card editing_form" action="${user_updatePasswordCommandLink}" method="post">
            <div class="card_header">
              <p class="card_title">
                PASSWORD UPDATE FORM
              </p>
            </div>
            
            <div class="card_content">
              <div class="card_content_subcontent df aic">
                <span class="uui-label">OLD PASSWORD</span>
                <input type="password" name="oldPass" class="uui-form-element ml5 fg1">
              </div>
              <div class="card_content_subcontent df aic">
                <span class="uui-label">NEW PASSWORD</span>
                <input type="password" name="newPass" class="uui-form-element ml5 fg1">
              </div>
            </div>
            
            <div class="card_footer">
              <div class="card_content_subcontent">
                <input type="submit" value="Change password" class="uui-button"/>
              </div>
            </div>
          </@form.form>
          
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
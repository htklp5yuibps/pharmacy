<#assign spring=JspTaglibs["http://www.springframework.org/tags"]/>
<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]/>
<#assign security=JspTaglibs["http://www.springframework.org/security/tags"]/>

<!-- header -->
    <header>
      <div class="uui-header sidebar-header">
        <nav>
          <div class="uui-responsive-header">
            <div class="responsive-header">
              <div class="responsive-toggle-box">
                <span></span> <span></span> <span></span>
              </div>
              <div class="responsive-hide-menu">
                <span></span> <span></span>
              </div>
              <a href="#" class="responsive-brand-logo"> <span
                class="arrow fa fa-angle-left"></span> <span class="logo">
                  <@spring.url var="ic_logo_UUi"
                  value="/images/ic_logo_UUi.svg"/> <img src="${ic_logo_UUi}"
                  alt="" />
              </span> <span class="title">Unified UI</span>
              </a>
            </div>
          </div>
          <!---->
          <div class="uui-toggle-box">
            <span></span> <span></span> <span></span>
          </div>
          <a href="#" class="brand-logo"> <span class="logo">
              <@spring.url var="ic_logo_UUi" value="/images/ic_logo_UUi.svg"/> <img
              src="${ic_logo_UUi}" alt="" />
          </span> Pharma.by
          </a>
        </nav>
      </div>
    </header>
    <aside>
      <div class="uui-side-bar">
        <ul class="sidebar-menu">
        	<@security.authorize access="isAuthenticated()">
        		<div class="myui_userbar">
                    <div class="df aic">
                      <@spring.url var="avatar" value="/images/default/default_avatar.jpg"/>
                      <div class="avatar-menu" style="background-image: url('${avatar}')"></div>
                      <div class="ml5">
    	        		<p style="margin-bottom: 0;">
    						    Signed as: <@security.authentication property="principal.username"/>        		
    	        		</p>
    	        		<p  style="margin-bottom: 0;">
    						    Role: <@security.authentication property="principal.authorities"/>        		
    	        		</p>
                      </div>
                    </div>
	        		<li>
						    <@spring.url var="user_personalPage" value="/user/personalPage"/>
						    <a href="${user_personalPage}" class="myui_link">Personal page</a>
	        		</li>
	        		<li>
						    <@spring.url var="personalCartPageLink" value="/user/personalCartPage"/>
						    <a href="${personalCartPageLink}" class="myui_link">Personal cart: [<#if Session.cart??>${Session.cart.getPartsCount()}<#else>0</#if>]</a>        		
	        		</li>
	        		<li>
						    <@spring.url var="logoutLink" value="/logout"/>
						    <@form.form action="${logoutLink}" method="post">
						      <input type="submit" value="Logout" class="uui-button mt10 w100"/>
						    </@form.form>
	        		</li>
        		</div>
					  <div class="myui_search_separator"></div>
				  </@security.authorize>
				  
        
          <li>
          	<@spring.url var="indexPageLink" value="/index"/>
            <a href="${indexPageLink}">
              <span>Main page</span>
            </a>
          </li>
          
        	<@security.authorize access="isAnonymous()">
  					<@spring.url var="signinPageLink" value="/signin"/>
  					<li>
	  					<a href="${signinPageLink}">
	  						<span>Sign in</span>
	  					</a>
  					</li>
					</@security.authorize>
					
          <li class="sub-menu">
  					<@security.authorize access="hasAuthority('admin')">
	            <a href="#">
	              <span>administrative tools</span>
	            </a>
	            <ul class="sub">
	            	<@spring.url var="adminPanelLink" value="/admin"/>
	            	<li><a href="${adminPanelLink}"><span>Administrative page</span></a></li>
	            	<@spring.url var="admin_productEditionPage" value="/admin/productEditionPage"/>
	              <li><a href="${admin_productEditionPage}"><span>Product editing page</span></a></li>
	              <@spring.url var="employee_orderEditionPage" value="/employee/orderEditionPage"/>
	              <li><a href="${employee_orderEditionPage}"><span>Order edititng tools</span></a></li>
	              <@spring.url var="admin_accessLevelEditionPage" value="/admin/accessLevelEditionPage"/>
	              <li><a href="${admin_accessLevelEditionPage}"><span>Access level editing page</span></a></li>
	            </ul></li>
  					</@security.authorize>
        </ul>
      </div>
    </aside>
 <!-- end of: header -->
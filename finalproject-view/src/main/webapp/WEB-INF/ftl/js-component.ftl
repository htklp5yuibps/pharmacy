<#assign spring=JspTaglibs[ "http://www.springframework.org/tags"]>
<#assign form=JspTaglibs[ "http://www.springframework.org/tags/form"]>

  <!-- uui scripts -->
  <@spring.url var="js_jquery" value="/js/lib/jquery-1.12.0.min.js"/>
  <script src="${js_jquery}"></script>
  <@spring.url var="bootstrap_min_js" value="/bootstrap/js/bootstrap.min.js"/>
  <script src="${bootstrap_min_js}"></script>
  <@spring.url var="js_uui_core_min" value="/js/uui-core.min.js"/>
  <script src="${js_uui_core_min}" type="text/javascript"></script>
  <@spring.url var="js_lib_jquery"
  value="/js/lib/components/jquery.mCustomScrollbar.concat.min.js"/>
  <script src="${js_lib_jquery}"></script>
  <!-- end of: uui scripts -->
  <script>
			UUI.Sidebar.init({
				open : true
			});
			UUI.Navigation.init();
	</script>
<#assign spring=JspTaglibs["http://www.springframework.org/tags"]/>
<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]/>

<!-- uui links -->
<@spring.url var="bootstrap" value="/bootstrap/css/bootstrap.min.css"/>
<link rel="stylesheet" href="${bootstrap}" />
<@spring.url var="css_uui_css_all" value="/css/uui-all.css"/>
<link rel="stylesheet" href="${css_uui_css_all}" />
<@spring.url var="css_uui_css_jquery"
value="/css/lib/components/jquery.mCustomScrollbar.min.css"/>
<link rel="stylesheet" href="${css_uui_css_jquery}" />
<@spring.url var="myui_card_css"
value="/css/myui/card.css"/>
<link rel="stylesheet" href="${myui_card_css}" />
<@spring.url var="myui_main_css"
value="/css/myui/main.css"/>
<link rel="stylesheet" href="${myui_main_css}" />
<@spring.url var="myui_search_bar_css"
value="/css/myui/search-bar.css"/>
<link rel="stylesheet" href="${myui_search_bar_css}" />
<@spring.url var="myui_messages_css" value="/css/myui/messages.css"/>
<link rel="stylesheet" href="${myui_messages_css}" />
<@spring.url var="myui_userbar_css" value="/css/myui/userbar.css"/>
<link rel="stylesheet" href="${myui_userbar_css}" />
<@spring.url var="font_awesome_min" value="/fonts/font-awesome/css/font-awesome.min.css"/>
<link rel="stylesheet" href="${font_awesome_min}" />
<style>
header .uui-header {
	background-color: #113666;
}

header .uui-header.sidebar-header nav .brand-logo {
	background-color: #113666;
}

header .uui-header .uui-toggle-box {
	background-color: #294a75;
}

aside .uui-side-bar {
	background-color: #294a75;
}

footer .uui-footer {
	background-color: #113666;
}

footer .copyright {
	color: #fff;
}

footer .uui-footer .footer-build .build {
	color: #fff;
}

aside .uui-side-bar ul>li {
	box-shadow: none;
}

aside .uui-side-bar ul>li.active>a i, aside .uui-side-bar ul>li.active>a span
	{
	color: #b0c2ff;
}

a:hover {
	background-color: #243263;
}

aside .uui-side-bar ul>li>a:hover {
	background-color: #243263;
}

aside .uui-side-bar ul>li.sub-menu.open>a {
	background-color: #243263;
}
</style>
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
	.card .card_content .card_content_subcontent {
		display: flex;
		flex-direction: column;
	}
	
	.images_block {
		display: grid;
		grid-gap: 10px;
		grid-template-columns: repeat(4, 0.25fr);
		grid-auto-rows: auto;
		justify-content: center;
		padding-top: 10px;
		padding-bottom: 10px;
	}
	
	.images_block img {
		max-height: 150px;
	}
	
	.image {
		display: flex;
		flex-direction: column;
	}
	
	.imageblock_message {
		grid-column-start: span 4;
	}
	
	.info_card {
		grid-column: 1 / span 2;
		align-self: start;
	}
	
	.images_card {
		grid-column: 3 / span 2;
		align-self: start;
	}
	
	.content {
		grid-template-columns: repeat(4, 0.25fr);
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
          
          <!-- card -->
	      <@spring.url var="updateProductCommandLink" value="/admin/product/update"/>
          <@form.form action="${updateProductCommandLink}" method="post" class="card info_card">
          	<!-- card header -->
          	<div class="card_header">
          		<p class="card_title">
          			EDITING PRODUCT: ${product.name}
          		</p>
          	</div>
          	<!-- end of: card header -->
          	
          	<!-- card content -->
          	<div class="card_content">
      			<input type="hidden" name="productId" value="${product.id}"/>
          		<div class="card_content_subcontent">
          			<input type="text" value="${product.name}" name="name" class="uui-form-element"/>
          		</div>
          		<div class="card_content_subcontent">
          			<textarea name="info" class="uui-form-element" rows="4">${product.info}</textarea>
          		</div>
          		<div class="card_content_subcontent">
          			<input type="text" value="${product.cost}" name="cost" class="uui-form-element"/>
          		</div>
          	</div>
          	<!-- end of: card content -->
          
          	<!-- card footer -->
          	<div class="card_footer">
          		<div class="card_content_subcontent">
	          		<input type="submit" value="Save..." class="uui-button">
          		</div>
          	</div>
          	<!-- end of: card footer -->
          
          </@form.form>
          <!-- end of: card -->
          
          <!-- images card -->
          <div class="card images_card">
          	<!-- card header -->
          	<div class="card_header">
          		<p class="card_title">
          			IMAGES OF PRODUCT: ${product.name}
          		</p>
          	</div>
          	<!-- end of: card header -->
          
          	<!-- content -->
          	<div class="card_content images_block">
          		<#if RequestParameters.image_deleted??>
          			<p class="myui_message-success imageblock_message">IMAGE WAS SUCCESSFULLY DELETED.</p>
          			<#elseif RequestParameters.image_deletion_error??>
          				<p class="myui_message-error imageblock_message">
          					ERROR WHILE DELETING PROCESS.
          				</p>
          		</#if>
          		<#if product.images??>
          			<#list product.images>
          				<#items as image>
          					<@spring.url var="deleteImageCommandLink" value="/admin/image/product_image/delete?imageId=${image}&productId=${product.id}"/>
          					<@form.form action="${deleteImageCommandLink}" method="post" class="image">
	          					<@spring.url var="getImageCommandLink" value="/images/getimage?imageId=${image}"/>
	          					<img src="${getImageCommandLink}">
				          		<input type="submit" class="uui-button small" value="DELETE"/>
          					</@form.form>
          				</#items>
          			</#list>
          		</#if>
          	</div>
          	<!-- end of: content -->
          
          </div>
          <!-- end of: images card -->
          
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
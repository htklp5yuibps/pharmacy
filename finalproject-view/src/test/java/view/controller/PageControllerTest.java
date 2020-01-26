package view.controller;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import no.comp.finalproject.controller.PageController;
import no.comp.finalproject.entity.constant.ProductStatus;
import no.comp.finalproject.entity.constant.ProductType;
import view.configuration.StandaloneMvcTestViewResolver;
import view.configuration.TestContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Collection;

@SpringJUnitWebConfig(TestContext.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PageControllerTest {
	
	@Autowired
	PageController pageController;
	
	private MockMvc mockMvc;
	
	@BeforeAll
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(pageController)
				.setViewResolvers(new StandaloneMvcTestViewResolver()).build();
	}
	
	@Test
	public void indexPageTest()
			throws Exception {
		mockMvc.perform(get("/index"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("products"));
	}
	
	@Test
	public void indexPageWithNameParamTest()
			throws Exception {
		mockMvc.perform(get("/index").param("part", "прод"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("products"));
	}
	
	@Test
	public void viewProductPageTest()
			throws Exception {
		mockMvc.perform(get("/product/viewProductPage").param("productId", "1"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("product"));
	}
	
	@Test
	public void productEditingPageTest()
			throws Exception {
		mockMvc.perform(get("/admin/productEditionPage"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("products"));
	}
	
	@Test
	public void productEditingPageWithStatusParameterTest()
			throws Exception {
		mockMvc.perform(get("/admin/productEditionPage")
				.param("status", ProductStatus.AVAILABLE.toString().toLowerCase()))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("products"));
	}
	
	@Test
	public void productEditingPageWithTypeParameterTest()
			throws Exception {
		mockMvc.perform(get("/admin/productEditionPage")
				.param("type", ProductType.PILLS.toString().toLowerCase()))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("products"));
	}
	
	@Test
	public void productEditingPageWithNameParameterTest()
			throws Exception {
		mockMvc.perform(get("/admin/productEditionPage")
				.param("name", "прод"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("products"));
	}
	
	@Test
	public void productEditingPageWithAllParametersTest()
			throws Exception {
		mockMvc.perform(get("/admin/productEditionPage")
				.param("type", ProductType.PILLS.toString().toLowerCase())
				.param("name", "прод")
				.param("status", ProductStatus.AVAILABLE.toString().toLowerCase()))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("products"));
	}
	
	@Test
	public void productAddFormTest()
			throws Exception {
		mockMvc.perform(get("/admin/product/add/form"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("types"));
	}
	
	@Test
	public void productEditorPageTest()
			throws Exception {
		mockMvc.perform(get("/admin/product/editor").param("productId", "1"))
			.andExpect(status().is2xxSuccessful())
			.andExpect(model().attributeExists("product"));
	}
	
	@Test
	public void orderEditingPage()
			throws Exception {
		
		mockMvc.perform(get("/employee/orderEditionPage"))
			.andExpect(status().is2xxSuccessful())
			.andExpect(model().attributeExists("orders"));
	}
	
	@Test
	public void viewOrderTest()
			throws Exception {
		mockMvc.perform(get("/employee/order/viewOrder").param("id", "1"))
			.andExpect(status().is2xxSuccessful())
			.andExpect(model().attributeExists("order"));
	}
	
	@Test
	public void accessLevelEditingPage()
			throws Exception {
		mockMvc.perform(get("/admin/accessLevelEditionPage"))
			.andExpect(status().is2xxSuccessful())
			.andExpect(model().attributeExists("users"));
	}
	
	@Test
	public void personalPage()
			throws Exception {
		mockMvc.perform(get("/user/personalPage").principal(new MockAuthentication()))
				.andExpect(status().is2xxSuccessful())
				.andExpect(model().attributeExists("user"))
				.andExpect(model().attributeExists("orders"));
	}
}

class MockAuthentication implements Authentication {

	@Override
	public String getName() {
		return "admin01@mail.ru";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public Object getCredentials() {
		return null;
	}

	@Override
	public Object getDetails() {
		return null;
	}

	@Override
	public Object getPrincipal() {
		return null;
	}

	@Override
	public boolean isAuthenticated() {
		return false;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
	}
	
}
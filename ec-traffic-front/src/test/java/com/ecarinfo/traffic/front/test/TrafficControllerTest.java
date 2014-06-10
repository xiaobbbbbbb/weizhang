package com.ecarinfo.traffic.front.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ecarinfo.traffic.controller.AdminController;
import com.ecarinfo.traffic.controller.TrafficController;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:applicationContext.xml" })
public class TrafficControllerTest {
	private MockMvc mockMvc;

	@InjectMocks
	private TrafficController trafficController;

	// @Resource
	// private WebApplicationContext webApplicationContext;

	@Before
	public void before() throws Exception {
		MockitoAnnotations.initMocks(this); // 初始化mock对象
		// Mockito.reset(mockCategoryService); // 重置mock对象
		/*
		 * 如果要使用完全默认Spring Web Context, 例如不需要对Controller注入,则使用
		 * WebApplicationContext mockMvc =
		 * MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		 */
		// mockMvc =
		// MockMvcBuilders.standaloneSetup(categoryController).build();
		mockMvc = MockMvcBuilders.standaloneSetup(trafficController).build();
	}
	
	@Test
	public void test() {
		// 构造http请求及期待响应行为
        try {
			mockMvc.perform(get("/traffic/getCity?id=20"))
			        .andDo(print()) // 输出请求和响应信息
			        .andExpect(status().isOk());
//			        .andExpect(view().name("admin/index"));
//			        // .andExpect(forwardedUrl("/WEB-INF/jsp/category/categoryList.jsp"))
//			        .andExpect(model().attribute("categoryList", hasSize(2)))
//			        .andExpect(
//			                model().attribute("categoryList",
//			                        hasItem(allOf(hasProperty("id", is(1)), hasProperty("name", is("cat1"))))))
//			        .andExpect(
//			                model().attribute("categoryList",
//			                        hasItem(allOf(hasProperty("id", is(2)), hasProperty("name", is("cat2"))))));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

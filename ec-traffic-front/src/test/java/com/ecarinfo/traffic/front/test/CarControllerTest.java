package com.ecarinfo.traffic.front.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder.*;

import com.ecarinfo.common.utils.JSONUtil;
import com.ecarinfo.traffic.controller.AdminController;
import com.ecarinfo.traffic.controller.CarController;
import com.ecarinfo.traffic.persist.po.OrgCarInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:applicationContext.xml" })
public class CarControllerTest {
//	private MockMvc mockMvc;

	// @Resource
	// private WebApplicationContext webApplicationContext;
//
//	@Before
//	public void before() throws Exception {
//		MockitoAnnotations.initMocks(this); // 初始化mock对象
//		// Mockito.reset(mockCategoryService); // 重置mock对象
//		/*
//		 * 如果要使用完全默认Spring Web Context, 例如不需要对Controller注入,则使用
//		 * WebApplicationContext mockMvc =
//		 * MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//		 */
//		// mockMvc =
//		// MockMvcBuilders.standaloneSetup(categoryController).build();
//		mockMvc = MockMvcBuilders.standaloneSetup(carController).build();
//	}
	
	@Test
	public void testSaveUpdate() {
		// 构造http请求及期待响应行为
        try {
        	 MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new CarController()).build();
        	 OrgCarInfo orgCarInfo = new OrgCarInfo();
        	 orgCarInfo.setBatNo("");
        	 orgCarInfo.setId(1l);
        	 orgCarInfo.setCarType(1);
             mockMvc.perform(post("/car/saveUpdate").content(JSONUtil.toJson(orgCarInfo))
            		 .contentType(MediaType.APPLICATION_JSON)
            		 .accept(MediaType.APPLICATION_JSON)
            		 )
            		 .andDo(print())
                     .andExpect(status().isOk())
                     .andExpect(content().contentType("application/json"));
//                     .andExpect(jsonPath("$.name").value("Lee")).andExpect(jsonPath("$.title").value("你好"));
//             mockMvc.perform(post("/p2/haha").param("title", "你好吧")).andExpect(status().is(302))
//                     .andExpect(view().name("redirect:/hello")).andExpect(model().attribute("title", "你好吧"));
//             mockMvc.perform(get("/p2/haha2").param("title", "你好吧").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
//                     .andExpect(jsonPath("$.data[0].fullName").value("宝马-进口-x6"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

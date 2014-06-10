package com.ecarinfo.traffic.api.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ecarinfo.traffic.api.job.MergerRuleJob;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:applicationContext.xml" })
public class RuleMergerTest {
	// ① 对接口进行模拟
	@Resource
	private MergerRuleJob mergerRuleJob;// = mock(TrafficService.class);


	@Test
	public void testMerger() {
		
		mergerRuleJob.execute();
		
	}

}

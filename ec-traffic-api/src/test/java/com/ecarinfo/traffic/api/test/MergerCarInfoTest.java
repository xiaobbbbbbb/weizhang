package com.ecarinfo.traffic.api.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ecarinfo.traffic.api.job.MergerCarInfoJob;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:applicationContext.xml" })
public class MergerCarInfoTest {
	// ① 对接口进行模拟
	@Resource
	private MergerCarInfoJob mergerCarInfoJob;// = mock(TrafficService.class);


	@Test
	public void testMerger() {
		
		mergerCarInfoJob.execute();
		
	}

}

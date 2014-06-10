package com.ecarinfo.traffic;

import static com.ecarinfo.traffic.persist.tables.StaticImport.TOrgCarInfo;
import static com.ecarinfo.traffic.persist.tables.StaticImport.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import com.ecarinfo.common.utils.BeanUtils;
import com.ecarinfo.common.utils.MD5Utils;
import com.ecarinfo.traffic.persist.po.OrgCarInfo;
import com.ecarinfo.traffic.service.GenService;
import com.ecarinfo.traffic.vo.customer.OrgCarInfoVO;

public class GenCode extends SimpleTest {

	@Resource
	protected GenService genService;

	@Test
	public void Gen() {
		List<OrgCarInfoVO> infos = db.select(TOrgCarInfo.all).from(TOrgCarInfo).queryObjectsForList(OrgCarInfoVO.class);
		// List<Map<String, Object>> lists = genService.dictDate("org_info", "code", "name");
		List<Map<String, Object>> dictDates = genService.dictDate(TOrgInfo.getTableName(), TOrgInfo.code.getName(), TOrgInfo.name.getName());

		for (OrgCarInfo i : infos) {
			System.err.println(BeanUtils.toString(i));
		}

		// fields.put(key, value)

		genService.dictDateReplace(infos, dictDates, "orgCode", "orgName");

		System.out.println("----------------------------");
		for (OrgCarInfo i : infos) {
			System.err.println(BeanUtils.toString(i));
		}
		
		System.err.println(MD5Utils.md5("123456"));

	}
}

package com.ecarinfo.traffic.service;

import java.util.List;

import com.ecarinfo.traffic.persist.po.RalUser;
import com.ecarinfo.traffic.vo.security.RalRoleVO;
import com.ecarinfo.traffic.vo.security.RalUserVO;
import com.ecarinfo.traffic.vo.security.ZtreeVO;

public interface RalService {
	List<ZtreeVO> getZtree(boolean showUrl, Integer roleId, int id, String target, String url, String icon);

	void save(RalRoleVO vo);

	void update(RalRoleVO vo);
	
	void save(RalUserVO vo);

	void update(RalUserVO vo);

	RalUserVO findUserInfo(String loginName);
	
	RalUserVO findUserInfo(Integer userId);

	boolean loginIn(String loginName, String password);

	boolean loginOut();
		
	public List<RalUser> findUserByResources(String urls);
}

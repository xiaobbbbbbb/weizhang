package com.ecarinfo.traffic.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecarinfo.db4j.table.Tables;
import com.ecarinfo.traffic.persist.logic.OperatorType;
import com.ecarinfo.traffic.persist.po.OrgInfo;
import com.ecarinfo.traffic.persist.po.OrgUserInfo;
import com.ecarinfo.traffic.persist.tables.TOrgInfo;
import com.ecarinfo.traffic.utils.DtoUtil;
import com.ecarinfo.traffic.vo.AjaxJson;

@Controller
@RequestMapping("/org")
public class OrgController extends BaseController {
	protected transient static Logger logger = Logger
			.getLogger(OrgController.class);


	@RequestMapping(value = "/updateUI")
	public String saveUpdateUI(HttpServletRequest request, ModelMap model) {
		OrgUserInfo userInfo = getUser(request);
		TOrgInfo toi= Tables.get(TOrgInfo.class);
		OrgInfo orgInfo = db.findObject(OrgInfo.class,toi.code.eq(userInfo.getOrgCode()) );
		model.put("dto", orgInfo);
		return "org/update";
	}

	@RequestMapping(value = "/update")
	@ResponseBody
	public AjaxJson saveUpdateUI(HttpServletRequest request, Integer id,String name,String address,String tel,String salesman,String contacts) {
		AjaxJson json = new AjaxJson();
		try {
			if (id != null && id > 0) {
				OrgInfo po = db.findByPK(OrgInfo.class, id);
				po.setUpdateTime(new Date());
				po.setName(DtoUtil.incode(name));
				po.setAddress(DtoUtil.incode(address));
				po.setTel(tel);
				po.setSalesman(DtoUtil.incode(salesman));
				po.setContacts(DtoUtil.incode(contacts));
				db.update(po);
				json.setMsg("修改成功!");
				json.setSuccess(true);
				logUtil.log(OperatorType.FRONT,getUser(request).getId(),getUser(request).getUserName(), "修改机构信息",null);//日志记录
			} 
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg("修改失败！");
			logger.error(json.getMsg(), e);
		}
		return json;
	}

}

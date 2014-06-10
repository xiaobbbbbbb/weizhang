package com.ecarinfo.traffic.controller.customer;

import static com.ecarinfo.traffic.persist.tables.StaticImport.TOrgInfo;
import static com.ecarinfo.traffic.persist.tables.StaticImport.TOrgUserInfo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecarinfo.common.utils.MD5Utils;
import com.ecarinfo.db4j.condition.Condition;
import com.ecarinfo.db4j.paginate.ECPage;
import com.ecarinfo.traffic.controller.BaseController;
import com.ecarinfo.traffic.filter.SystemContext;
import com.ecarinfo.traffic.persist.po.OrgInfo;
import com.ecarinfo.traffic.persist.po.OrgUserInfo;
import com.ecarinfo.traffic.protocol.meta.StaticType;
import com.ecarinfo.traffic.vo.AjaxJson;
import com.ecarinfo.traffic.vo.customer.OrgUserInfoVO;

/**
 * Description:客户系统管理员
 */

@Controller
@RequestMapping("/orgUserInfo")
public class OrgUserInfoController extends BaseController {

	protected transient static Logger logger = Logger.getLogger(OrgUserInfoController.class);

	@RequestMapping(value = "/")
	public String index(String orgCode, String nickName, String userName, Integer status, ModelMap map) {
		int pagerOffset = SystemContext.getPageOffset();

		Condition cond = Condition.getInstance();

		if (status == null)
			status = StaticType.Status.VALID;
		
		if (StringUtils.isNotEmpty(orgCode))
			cond.and(TOrgUserInfo.orgCode.like("%" + orgCode.trim() + "%"));

		if (StringUtils.isNotEmpty(nickName))
			cond.and(TOrgUserInfo.nickName.like("%" + nickName.trim() + "%"));

		if (StringUtils.isNotEmpty(userName))
			cond.and(TOrgUserInfo.userName.like("%" + userName.trim() + "%"));

		// 状态
		if (status != null)
			cond.and(TOrgUserInfo.status.eq(status));

		ECPage<OrgUserInfo> ECPage = db.findPage(OrgUserInfo.class, db.where(cond).orderBy(TOrgUserInfo.createTime).desc(), pagerOffset, PAGE_SIZE);

		List<OrgUserInfoVO> vos = new ArrayList<OrgUserInfoVO>();
		for (OrgUserInfo po : ECPage.getList()) {
			OrgUserInfoVO vo = new OrgUserInfoVO();
			BeanUtils.copyProperties(po, vo);
			vos.add(vo);
		}

		List<Map<String, Object>> dictDates = genService.dictDate(TOrgInfo.getTableName(), TOrgInfo.code.getName(), TOrgInfo.name.getName());
		genService.dictDateReplace(vos, dictDates, "orgCode", "orgName");

		ECPage<OrgUserInfoVO> page = new ECPage<OrgUserInfoVO>();
		page.setList(vos);
		page.setCurrentPage(ECPage.getCurrentPage());
		page.setRowsPerPage(ECPage.getRowsPerPage());
		page.setTotalPage(ECPage.getTotalPage());
		page.setTotalRows(ECPage.getTotalRows());

		map.put("ECPage", page);
		return "customer/org_user_info/list";
	}

	@RequestMapping(value = "/saveUpdateUI")
	public String saveUpdateUI(Integer id, ModelMap model) {
		OrgUserInfoVO vo = null;
		if (id != null && id > 0) {
			vo = new OrgUserInfoVO();
			OrgUserInfo dto = db.findByPK(OrgUserInfo.class, id);
			BeanUtils.copyProperties(dto, vo);
			OrgInfo org = db.findObject(OrgInfo.class, db.where(TOrgInfo.code.eq(dto.getOrgCode())));
			if (org != null)
				vo.setOrgName(org.getName());
		}
		model.put("dto", vo);
		return "customer/org_user_info/save_update";
	}

	@RequestMapping(value = "/saveUpdate")
	@ResponseBody
	public AjaxJson saveUpdateUI(@RequestBody OrgUserInfoVO vo) {
		AjaxJson json = new AjaxJson();
		try {
			if (vo.getId() != null && vo.getId() > 0) {
				OrgUserInfo po = db.findByPK(OrgUserInfo.class, vo.getId());
				boolean isExist = false;
				if (!vo.getUserName().equals(po.getUserName()) && !isExist) {
					isExist = isExist(json, vo);
				}
				if (!isExist) {
					po.setOrgCode(vo.getOrgCode());// 所属客户
					po.setNickName(vo.getNickName());// 用户名
					po.setUserName(vo.getUserName());// 登陆名
					if (!po.getPassword().equals(MD5Utils.md5(vo.getPassword())))
						po.setPassword(MD5Utils.md5(vo.getPassword()));// 密码
					po.setJob(vo.getJob());// 岗位
					po.setEmail(vo.getEmail());// 邮箱
					po.setTel(vo.getTel());// 联系电话
					po.setUpdateTime(new Date());// 更新时间
					db.update(po);
				}
			} else {
				OrgUserInfo po = new OrgUserInfo();
				BeanUtils.copyProperties(vo, po);
				po.setCreateTime(new Date());// 注册时间
				po.setUpdateTime(new Date());// 更新时间
				po.setPassword(MD5Utils.md5(vo.getPassword()));// 密码
				if (!isExist(json, vo))
					db.save(po);
			}
		} catch (Exception e) {
			json.setSuccess(false);
			logger.error(json.getMsg(), e);
		}
		return json;
	}

	private boolean isExist(AjaxJson json, OrgUserInfoVO vo) {
		OrgUserInfo old = db.findObject(OrgUserInfo.class,
				db.where(TOrgUserInfo.userName.eq(vo.getUserName()).and(TOrgUserInfo.status.eq(StaticType.Status.VALID))));
		if (old != null) {
			json.setSuccess(false);
			json.setMsg("登陆名不能重复！");
			return true;
		}
		return false;
	}

	// 删除
	@RequestMapping(value = "/delete")
	@ResponseBody
	public AjaxJson deleteModel(Integer[] ids) {
		AjaxJson json = new AjaxJson();
		try {
			if (ids != null && ids.length > 0) {
				db.updateTable(TOrgUserInfo).set(TOrgUserInfo.status, StaticType.Status.IN_VALID).where(TOrgUserInfo.id.in(ids)).executeUpdate();
			} else {
				json.setSuccess(false);
				json.setMsg("客户系统管理员删除失败!");
			}
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg("客户系统管理员删除失败!");
			logger.error("客户系统管理员删除失败!", e);
		}
		return json;
	}
}

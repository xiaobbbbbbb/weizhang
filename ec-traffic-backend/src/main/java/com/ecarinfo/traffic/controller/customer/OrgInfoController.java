package com.ecarinfo.traffic.controller.customer;

import static com.ecarinfo.traffic.persist.tables.StaticImport.*;

import static com.ecarinfo.traffic.persist.tables.StaticImport.TProvince;


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

import com.ecarinfo.db4j.condition.Condition;
import com.ecarinfo.db4j.paginate.ECPage;
import com.ecarinfo.traffic.controller.BaseController;
import com.ecarinfo.traffic.filter.SystemContext;
import com.ecarinfo.traffic.persist.po.City;
import com.ecarinfo.traffic.persist.po.OrgInfo;

import com.ecarinfo.traffic.persist.tables.TOrgInfo;

import com.ecarinfo.traffic.persist.po.Province;

import com.ecarinfo.traffic.persist.po.TackInfo;

import com.ecarinfo.traffic.protocol.meta.StaticType;

import com.ecarinfo.traffic.vo.AjaxJson;
import com.ecarinfo.traffic.vo.customer.OrgInfoVO;

/**
 * Description:客户管理
 */

@Controller
@RequestMapping("/orgInfo")
public class OrgInfoController extends BaseController {

	protected transient static Logger logger = Logger.getLogger(OrgInfoController.class);

	@RequestMapping(value = "/")
	public String index(String code, String name, ModelMap map) {
		int pagerOffset = SystemContext.getPageOffset();

		Condition cond = Condition.getInstance();

		if (StringUtils.isNotEmpty(name))
			cond.and(TOrgInfo.name.like("%" + name.trim() + "%"));

		if (StringUtils.isNotEmpty(code))
			cond.and(TOrgInfo.code.like("%" + code.trim() + "%"));

		ECPage<OrgInfo> ECPage = db.findPage(OrgInfo.class, db.where(cond), pagerOffset, PAGE_SIZE);

		List<OrgInfoVO> vos = new ArrayList<OrgInfoVO>();
		for (OrgInfo po : ECPage.getList()) {
			OrgInfoVO vo = new OrgInfoVO();
			BeanUtils.copyProperties(po, vo);
			vos.add(vo);
		}

		List<Map<String, Object>> dictDateProvinces = genService.dictDate(TProvince.getTableName(), TProvince.id.getName(), TProvince.name.getName());
		genService.dictDateReplace(vos, dictDateProvinces, "provinceId", "provinceName");

		List<Map<String, Object>> dictDateCitys = genService.dictDate(TCity.getTableName(), TCity.id.getName(), TCity.name.getName());
		genService.dictDateReplace(vos, dictDateCitys, "cityId", "cityName");

		List<Map<String, Object>> dictDates = genService.dictDate(TTackInfo.getTableName(), TTackInfo.id.getName(), TTackInfo.name.getName());
		genService.dictDateReplace(vos, dictDates, "tackId", "tackName");

		ECPage<OrgInfoVO> page = new ECPage<OrgInfoVO>();
		page.setList(vos);
		page.setCurrentPage(ECPage.getCurrentPage());
		page.setRowsPerPage(ECPage.getRowsPerPage());
		page.setTotalPage(ECPage.getTotalPage());
		page.setTotalRows(ECPage.getTotalRows());

		map.put("ECPage", page);

		return "customer/org_info/list";
	}

	@RequestMapping(value = "/saveUpdateUI")
	public String saveUpdateUI(Integer id, ModelMap model) {
		OrgInfoVO vo = new OrgInfoVO();
		if (id != null && id > 0) {
			OrgInfo dto = db.findByPK(OrgInfo.class, id);
			BeanUtils.copyProperties(dto, vo);
			Province p = db.findByPK(Province.class, vo.getProvinceId());
			if (p != null)
				vo.setProvinceName(p.getName());
			City c = db.findByPK(City.class, vo.getCityId());
			if (c != null)
				vo.setCityName(c.getName());
			TackInfo tack = db.findObject(TackInfo.class, db.where(TTackInfo.id.eq(dto.getTackId())));
			if (tack != null)
				vo.setTackName(tack.getName());
		}
		model.put("dto", vo);
		return "customer/org_info/save_update";
	}

	@RequestMapping(value = "/saveUpdate")
	@ResponseBody
	public AjaxJson saveUpdateUI(@RequestBody OrgInfoVO vo) {
		AjaxJson json = new AjaxJson();
		try {
			if (vo.getId() != null && vo.getId() > 0) {
				OrgInfo po = db.findByPK(OrgInfo.class, vo.getId());
				boolean isExist = false;
				if (!vo.getCode().equals(po.getCode()) && !isExist) {
					isExist = isExistCode(json, vo);
				}
				if (!vo.getName().equals(po.getName()) && !isExist) {
					isExist = isExistName(json, vo);
				}
				if (!isExist) {
					po.setBelongTo(vo.getBelongTo());// 所属集团
					po.setJoinTime(vo.getJoinTime());// 发展时间
					po.setCode(vo.getCode());// 机构编码
					po.setName(vo.getName());// 机构名称
					po.setProvinceId(vo.getProvinceId());// 省份
					po.setCityId(vo.getCityId());// 城市
					po.setQueryType(vo.getQueryTypeBatch() + vo.getQueryTypeReal());// 查询类型
					po.setMaxCarCounts(vo.getMaxCarCounts());// 授权车辆数
					po.setMaxQueryCounts(vo.getMaxQueryCounts());// 最大查询次数
					po.setLeftCarCounts(vo.getLeftCarCounts());// 剩余车辆数
					po.setLeftQueryCounts(vo.getLeftQueryCounts());// 剩余查询次数
					po.setTel(vo.getTel());// 电话
					po.setAppKey(vo.getAppKey());// 密钥
					po.setContacts(vo.getContacts());// 联系人
					po.setSalesman(vo.getSalesman());// 业务员名称
					po.setTackId(vo.getTackId());// 策略id
					po.setUpdateTime(new Date());// 更新时间
					po.setAddress(vo.getAddress());// 地址
					po.setStatus(vo.getStatus());// 状态
					po.setExpiredTime(vo.getExpiredTime());// 到期时间
					db.update(po);
				}
			} else {
				OrgInfo po = new OrgInfo();
				BeanUtils.copyProperties(vo, po);
				po.setQueryType(vo.getQueryTypeBatch() + vo.getQueryTypeReal());// 查询类型
				po.setUpdateTime(new Date());// 更新时间
				po.setCreateTime(new Date());// 创建时间
				if (!isExistCode(json, vo) && !isExistName(json, vo))
					db.save(po);
			}
		} catch (Exception e) {
			json.setSuccess(false);
			logger.error(json.getMsg(), e);
		}
		return json;
	}

	private boolean isExistCode(AjaxJson json, OrgInfoVO vo) {
		OrgInfo old = db.findObject(OrgInfo.class, db.where(TOrgInfo.code.eq(vo.getCode()).and(TOrgInfo.status.eq(StaticType.Status.VALID))));
		if (old != null) {
			json.setSuccess(false);
			json.setMsg("机构编码不能重复！");
			return true;
		}
		return false;
	}

	private boolean isExistName(AjaxJson json, OrgInfoVO vo) {
		OrgInfo old = db.findObject(OrgInfo.class, db.where(TOrgInfo.name.eq(vo.getName()).and(TOrgInfo.status.eq(StaticType.Status.VALID))));
		if (old != null) {
			json.setSuccess(false);
			json.setMsg("机构名称不能重复！");
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
				db.updateTable(TOrgInfo).set(TOrgInfo.status, StaticType.Status.IN_VALID).where(TOrgInfo.id.in(ids)).executeUpdate();
			} else {
				json.setSuccess(false);
				json.setMsg("客户管理删除失败!");
			}
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg("客户管理删除失败!");
			logger.error("客户管理删除失败!", e);
		}
		return json;
	}
}

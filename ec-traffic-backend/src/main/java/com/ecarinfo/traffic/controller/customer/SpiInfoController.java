package com.ecarinfo.traffic.controller.customer;

import static com.ecarinfo.traffic.persist.tables.StaticImport.TSpiInfo;

import java.util.Date;

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
import com.ecarinfo.traffic.persist.po.SpiInfo;
import com.ecarinfo.traffic.protocol.meta.StaticType;
import com.ecarinfo.traffic.vo.AjaxJson;
import com.ecarinfo.traffic.vo.customer.SpiInfoVO;

/**
 * Description:接口配置
 */

@Controller
@RequestMapping("/spiInfo")
public class SpiInfoController extends BaseController {

	protected transient static Logger logger = Logger.getLogger(SpiInfoController.class);

	@RequestMapping(value = "/")
	public String index(String name, Integer status, ModelMap map) {
		int pagerOffset = SystemContext.getPageOffset();

		Condition cond = Condition.getInstance();

		if (status == null)
			status = StaticType.Status.VALID;

		if (StringUtils.isNotEmpty(name))
			cond.and(TSpiInfo.name.like("%" + name.trim() + "%"));

		// 状态
		if (status != null)
			cond.and(TSpiInfo.status.eq(status));

		ECPage<SpiInfo> ECPage = db.findPage(SpiInfo.class, db.where(cond), pagerOffset, PAGE_SIZE);
		map.put("ECPage", ECPage);
		return "customer/spi_info/list";
	}

	@RequestMapping(value = "/saveUpdateUI")
	public String saveUpdateUI(Integer id, ModelMap model) {
		SpiInfo dto = null;
		if (id != null && id > 0) {
			dto = db.findByPK(SpiInfo.class, id);
		}
		model.put("dto", dto);
		return "customer/spi_info/save_update";
	}

	@RequestMapping(value = "/saveUpdate")
	@ResponseBody
	public AjaxJson saveUpdateUI(@RequestBody SpiInfoVO vo) {
		AjaxJson json = new AjaxJson();
		try {
			if (vo.getId() != null && vo.getId() > 0) {
				SpiInfo po = db.findByPK(SpiInfo.class, vo.getId());
				boolean isExist = false;
				if (!vo.getName().equals(po.getName()) && !isExist) {
					isExist = isExist(json, vo);
				}
				if (!isExist) {
					po.setName(vo.getName());// 接口名称
					po.setNodeUrl(vo.getNodeUrl());// 接口节点url
					po.setEnName(vo.getEnName());// 接口描述
					po.setRuleUrl(vo.getRuleUrl());// 查询规则url
					po.setTrafficUrl(vo.getTrafficUrl());// 获取违章数据url
					po.setUserName(vo.getUserName());// 登录名
					po.setPassword(vo.getPassword());// 密码
					po.setSpiKey(vo.getSpiKey());// SpiKEY
					po.setUpdateTime(new Date());// 修改时间
					po.setStatus(vo.getStatus());// 接口状态
					db.update(po);
				}
			} else {
				SpiInfo po = new SpiInfo();
				BeanUtils.copyProperties(vo, po);
				po.setCreateTime(new Date());// 创建时间
				po.setUpdateTime(new Date());// 修改时间
				if (!isExist(json, vo))
					db.save(po);
			}
		} catch (Exception e) {
			json.setSuccess(false);
			logger.error(json.getMsg(), e);
		}
		return json;
	}

	private boolean isExist(AjaxJson json, SpiInfoVO vo) {
		SpiInfo old = db.findObject(SpiInfo.class, db.where(TSpiInfo.name.eq(vo.getName()).and(TSpiInfo.status.eq(StaticType.Status.VALID))));
		if (old != null) {
			json.setSuccess(false);
			json.setMsg("接口名称不能重复！");
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
				db.updateTable(TSpiInfo).set(TSpiInfo.status, StaticType.Status.IN_VALID).where(TSpiInfo.id.in(ids)).executeUpdate();
			} else {
				json.setSuccess(false);
				json.setMsg("接口配置删除失败!");
			}
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg("接口配置删除失败!");
			logger.error("接口配置删除失败!", e);
		}
		return json;
	}

}

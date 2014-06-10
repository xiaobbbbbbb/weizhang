package com.ecarinfo.traffic.controller.customer;

import static com.ecarinfo.traffic.persist.tables.StaticImport.TApiMoney;
import static com.ecarinfo.traffic.persist.tables.StaticImport.TOrgInfo;

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
import com.ecarinfo.traffic.persist.po.ApiMoney;
import com.ecarinfo.traffic.persist.po.OrgInfo;
import com.ecarinfo.traffic.protocol.meta.StaticType;
import com.ecarinfo.traffic.vo.AjaxJson;
import com.ecarinfo.traffic.vo.customer.ApiMoneyVO;

/**
 * Description:API价格管理
 */

@Controller
@RequestMapping("/apiMoney")
public class ApiMoneyController extends BaseController {

	protected transient static Logger logger = Logger.getLogger(ApiMoneyController.class);

	@RequestMapping(value = "/")
	public String index(String name, String orgCode, Integer type, Integer status, ModelMap map) {
		int pagerOffset = SystemContext.getPageOffset();

		Condition cond = Condition.getInstance();

		if (StringUtils.isNotEmpty(name))
			cond.and(TApiMoney.name.like("%" + name.trim() + "%"));

		if (StringUtils.isNotEmpty(orgCode))
			cond.and(TApiMoney.orgCode.like("%" + orgCode.trim() + "%"));

		// 收费类型
		if (type != null)
			cond.and(TApiMoney.type.eq(type));

		// 状态
		if (status != null)
			cond.and(TApiMoney.status.eq(status));

		ECPage<ApiMoney> ECPage = db.findPage(ApiMoney.class, db.where(cond), pagerOffset, PAGE_SIZE);

		List<ApiMoneyVO> vos = new ArrayList<ApiMoneyVO>();
		for (ApiMoney po : ECPage.getList()) {
			ApiMoneyVO vo = new ApiMoneyVO();
			BeanUtils.copyProperties(po, vo);
			vos.add(vo);
		}

		List<Map<String, Object>> dictDates = genService.dictDate(TOrgInfo.getTableName(), TOrgInfo.code.getName(), TOrgInfo.name.getName());
		genService.dictDateReplace(vos, dictDates, "orgCode", "orgName");

		ECPage<ApiMoneyVO> page = new ECPage<ApiMoneyVO>();
		page.setList(vos);
		page.setCurrentPage(ECPage.getCurrentPage());
		page.setRowsPerPage(ECPage.getRowsPerPage());
		page.setTotalPage(ECPage.getTotalPage());
		page.setTotalRows(ECPage.getTotalRows());

		map.put("ECPage", page);

		return "customer/api_money/list";
	}

	@RequestMapping(value = "/saveUpdateUI")
	public String saveUpdateUI(Integer id, ModelMap model) {
		ApiMoneyVO vo = null;
		if (id != null && id > 0) {
			vo = new ApiMoneyVO();
			ApiMoney dto = db.findByPK(ApiMoney.class, id);
			BeanUtils.copyProperties(dto, vo);
			OrgInfo org = db.findObject(OrgInfo.class, db.where(TOrgInfo.code.eq(dto.getOrgCode())));
			if (org != null)
				vo.setOrgName(org.getName());
		}
		model.put("dto", vo);
		return "customer/api_money/save_update";
	}

	@RequestMapping(value = "/saveUpdate")
	@ResponseBody
	public AjaxJson saveUpdateUI(@RequestBody ApiMoneyVO dto) {
		AjaxJson json = new AjaxJson();
		try {
			if (dto.getId() != null && dto.getId() > 0) {
				ApiMoney po = db.findByPK(ApiMoney.class, dto.getId());
				po.setName(dto.getName());// 收费描述
				po.setMoney(dto.getMoney());// 单价(元)
				po.setOrgCode(dto.getOrgCode());// 客户名称
				po.setType(dto.getType());// 收费类型
				po.setStatus(dto.getStatus());// 状态
				po.setUpdateTime(new Date());// 更新时间
				db.update(po);
			} else {
				ApiMoney po = new ApiMoney();
				BeanUtils.copyProperties(dto, po);
				po.setCreateTime(new Date());// 创建时间
				po.setUpdateTime(new Date());// 更新时间
				db.save(po);
			}
		} catch (Exception e) {
			json.setSuccess(false);
			logger.error(json.getMsg(), e);
		}
		return json;
	}

	// 删除
	@RequestMapping(value = "/delete")
	@ResponseBody
	public AjaxJson deleteModel(Integer[] ids) {
		AjaxJson json = new AjaxJson();
		try {
			if (ids != null && ids.length > 0) {
				db.updateTable(TApiMoney).set(TApiMoney.status, StaticType.Status.IN_VALID).where(TApiMoney.id.in(ids)).executeUpdate();
			} else {
				json.setSuccess(false);
				json.setMsg("API价格管理删除失败!");
			}
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg("API价格管理删除失败!");
			logger.error(json.getMsg(), e);
		}
		return json;
	}
}

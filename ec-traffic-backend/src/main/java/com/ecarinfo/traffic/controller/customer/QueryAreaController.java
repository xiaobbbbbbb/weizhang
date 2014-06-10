package com.ecarinfo.traffic.controller.customer;

import static com.ecarinfo.traffic.persist.tables.StaticImport.TOrgInfo;
import static com.ecarinfo.traffic.persist.tables.StaticImport.TQueryArea;

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

import com.ecarinfo.common.utils.DateUtils;
import com.ecarinfo.db4j.condition.Condition;
import com.ecarinfo.db4j.paginate.ECPage;
import com.ecarinfo.traffic.controller.BaseController;
import com.ecarinfo.traffic.filter.SystemContext;
import com.ecarinfo.traffic.persist.po.OrgInfo;
import com.ecarinfo.traffic.persist.po.QueryArea;
import com.ecarinfo.traffic.vo.AjaxJson;
import com.ecarinfo.traffic.vo.customer.QueryAreaVO;

/**
 * Description:违章查询区域
 */

@Controller
@RequestMapping("/queryArea")
public class QueryAreaController extends BaseController {

	protected transient static Logger logger = Logger.getLogger(QueryAreaController.class);

	@RequestMapping(value = "/")
	public String index(String orgCode, String beginCreateTime, String endCreateTime, ModelMap map) {
		int pagerOffset = SystemContext.getPageOffset();

		Condition cond = Condition.getInstance();

		if (StringUtils.isNotEmpty(orgCode))
			cond.and(TQueryArea.orgCode.like("%" + orgCode.trim() + "%"));

		// 创建时间
		if (StringUtils.isNotEmpty(beginCreateTime) && StringUtils.isNotEmpty(endCreateTime))
			cond.and(TQueryArea.createTime.between(DateUtils.stringToDate(beginCreateTime.trim() + " 00:00:00"),
					DateUtils.stringToDate(endCreateTime.trim() + " 23:59:59")));

		ECPage<QueryArea> ECPage = db.findPage(QueryArea.class, db.where(cond), pagerOffset, PAGE_SIZE);

		List<QueryAreaVO> vos = new ArrayList<QueryAreaVO>();
		for (QueryArea po : ECPage.getList()) {
			QueryAreaVO vo = new QueryAreaVO();
			BeanUtils.copyProperties(po, vo);
			vos.add(vo);
		}

		List<Map<String, Object>> dictDates = genService.dictDate(TOrgInfo.getTableName(), TOrgInfo.code.getName(), TOrgInfo.name.getName());
		genService.dictDateReplace(vos, dictDates, "orgCode", "orgName");

		ECPage<QueryAreaVO> page = new ECPage<QueryAreaVO>();
		page.setList(vos);
		page.setCurrentPage(ECPage.getCurrentPage());
		page.setRowsPerPage(ECPage.getRowsPerPage());
		page.setTotalPage(ECPage.getTotalPage());
		page.setTotalRows(ECPage.getTotalRows());

		map.put("ECPage", page);

		return "customer/query_area/list";
	}

	@RequestMapping(value = "/saveUpdateUI")
	public String saveUpdateUI(Integer id, ModelMap model) {
		QueryAreaVO vo = null;
		if (id != null && id > 0) {
			vo = new QueryAreaVO();
			QueryArea dto = db.findByPK(QueryArea.class, id);
			BeanUtils.copyProperties(dto, vo);
			OrgInfo org = db.findObject(OrgInfo.class, db.where(TOrgInfo.code.eq(dto.getOrgCode())));
			if (org != null)
				vo.setOrgName(org.getName());
		}
		model.put("dto", vo);
		return "customer/query_area/save_update";
	}

	@RequestMapping(value = "/saveUpdate")
	@ResponseBody
	public AjaxJson saveUpdateUI(@RequestBody QueryAreaVO vo) {
		AjaxJson json = new AjaxJson();
		try {
			if (vo.getId() != null && vo.getId() > 0) {
				QueryArea po = db.findByPK(QueryArea.class, vo.getId());
				boolean isExist = false;
				if (!vo.getOrgCode().equals(po.getOrgCode()) && !vo.getCityId().equals(po.getCityId()) && !isExist) {
					isExist = isExist(json, vo);
				}
				if (!isExist) {
					po.setOrgCode(vo.getOrgCode());// 机构编码
					po.setProvinceId(vo.getProvinceId());// 省份id
					po.setProvinceName(vo.getProvinceName());// 省份名称
					po.setCityId(vo.getCityId());// 城市id
					po.setCityName(vo.getCityName());// 城市名称
					po.setUpdateTime(new Date());// 修改时间
					db.update(po);
					merger4Query.mergerRule4Org(po.getOrgCode());
				}
			} else {
				QueryArea po = new QueryArea();
				BeanUtils.copyProperties(vo, po);
				po.setUpdateTime(new Date());// 修改时间
				po.setCreateTime(new Date());// 创建时间
				if (!isExist(json, vo)) {
					db.save(po);
					merger4Query.mergerRule4Org(po.getOrgCode());
				}
			}
		} catch (Exception e) {
			json.setSuccess(false);
			logger.error(json.getMsg(), e);
		}
		return json;
	}

	private boolean isExist(AjaxJson json, QueryAreaVO vo) {
		QueryArea old = db.findObject(QueryArea.class, db.where(TQueryArea.orgCode.eq(vo.getOrgCode()).and((TOrgInfo.cityId.eq(vo.getCityId())))));
		if (old != null) {
			json.setSuccess(false);
			json.setMsg("客户查询区域不能重复！");
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
				db.delete(QueryArea.class, db.where(TQueryArea.id.in(ids)));
			} else {
				json.setSuccess(false);
				json.setMsg("违章查询区域删除失败!");
			}
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg("违章查询区域删除失败!");
			logger.error("违章查询区域删除失败!", e);
		}
		return json;
	}
}

package com.ecarinfo.traffic.controller.customer;

import static com.ecarinfo.traffic.persist.tables.StaticImport.TOrgInfo;
import static com.ecarinfo.traffic.persist.tables.StaticImport.TTackInfo;

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
import com.ecarinfo.traffic.persist.po.TackInfo;
import com.ecarinfo.traffic.vo.AjaxJson;
import com.ecarinfo.traffic.vo.customer.TackInfoVO;

/**
 * Description:策略管理
 */

@Controller
@RequestMapping("/tackInfo")
public class TackInfoController extends BaseController {

	protected transient static Logger logger = Logger.getLogger(TackInfoController.class);

	@RequestMapping(value = "/")
	public String index(String name, Integer queryType, String beginCreateTime, String endCreateTime, ModelMap map) {
		int pagerOffset = SystemContext.getPageOffset();

		Condition cond = Condition.getInstance();

		if (StringUtils.isNotEmpty(name))
			cond.and(TTackInfo.name.like("%" + name.trim() + "%"));

		// 查询类型
		if (queryType != null)
			cond.and(TTackInfo.queryType.eq(queryType));

		// 创建时间
		if (StringUtils.isNotEmpty(beginCreateTime) && StringUtils.isNotEmpty(endCreateTime))
			cond.and(TTackInfo.createTime.between(DateUtils.stringToDate(beginCreateTime.trim() + " 00:00:00"),
					DateUtils.stringToDate(endCreateTime.trim() + " 23:59:59")));

		ECPage<TackInfo> ECPage = db.findPage(TackInfo.class, db.where(cond), pagerOffset, PAGE_SIZE);

		List<TackInfoVO> vos = new ArrayList<TackInfoVO>();
		for (TackInfo po : ECPage.getList()) {
			TackInfoVO vo = new TackInfoVO();
			BeanUtils.copyProperties(po, vo);
			vos.add(vo);
		}

		List<Map<String, Object>> dictDates = genService.dictDate(TOrgInfo.getTableName(), TOrgInfo.code.getName(), TOrgInfo.name.getName());
		genService.dictDateReplace(vos, dictDates, "orgCode", "orgName");

		ECPage<TackInfoVO> page = new ECPage<TackInfoVO>();
		page.setList(vos);
		page.setCurrentPage(ECPage.getCurrentPage());
		page.setRowsPerPage(ECPage.getRowsPerPage());
		page.setTotalPage(ECPage.getTotalPage());
		page.setTotalRows(ECPage.getTotalRows());

		map.put("ECPage", page);

		return "customer/tack_info/list";
	}

	@RequestMapping(value = "/saveUpdateUI")
	public String saveUpdateUI(Integer id, ModelMap model) {
		TackInfoVO vo = null;
		if (id != null && id > 0) {
			vo = new TackInfoVO();
			TackInfo dto = db.findByPK(TackInfo.class, id);
			BeanUtils.copyProperties(dto, vo);
		}
		model.put("dto", vo);
		return "customer/tack_info/save_update";
	}

	@RequestMapping(value = "/saveUpdate")
	@ResponseBody
	public AjaxJson saveUpdateUI(@RequestBody TackInfoVO vo) {
		AjaxJson json = new AjaxJson();
		try {
			if (vo.getId() != null && vo.getId() > 0) {
				TackInfo po = db.findByPK(TackInfo.class, vo.getId());
				po.setName(vo.getName());// 接口名称
				po.setQueryType(vo.getQueryType());// 查询类型
				po.setOperatorId(vo.getOperatorId());// 操作员
				po.setCreateTime(vo.getCreateTime());// 创建时间
				po.setUpdateTime(new Date());// 更新时间
				po.setCacheTime(vo.getCacheTime());// 缓存时间(小时)
				db.update(po);
			} else {
				TackInfo po = new TackInfo();
				BeanUtils.copyProperties(vo, po);
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
				db.delete(TackInfo.class, db.where(TTackInfo.id.in(ids)));
			} else {
				json.setSuccess(false);
				json.setMsg("策略管理删除失败!");
			}
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg("策略管理删除失败!");
			logger.error("策略管理删除失败!", e);
		}
		return json;
	}
}

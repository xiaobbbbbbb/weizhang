package com.ecarinfo.traffic.controller.customer;

import static com.ecarinfo.traffic.persist.tables.StaticImport.TSpiInfo;
import static com.ecarinfo.traffic.persist.tables.StaticImport.TSpiMoney;

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
import com.ecarinfo.traffic.persist.po.SpiInfo;
import com.ecarinfo.traffic.persist.po.SpiMoney;
import com.ecarinfo.traffic.protocol.meta.StaticType;
import com.ecarinfo.traffic.vo.AjaxJson;
import com.ecarinfo.traffic.vo.customer.SpiMoneyVO;

/**
 * Description:SPI价格管理
 */

@Controller
@RequestMapping("/spiMoney")
public class SpiMoneyController extends BaseController {

	protected transient static Logger logger = Logger.getLogger(SpiMoneyController.class);

	@RequestMapping(value = "/")
	public String index(Integer spiId, String name, Integer type, Integer status, ModelMap map) {
		int pagerOffset = SystemContext.getPageOffset();

		Condition cond = Condition.getInstance();

		if (status == null)
			status = StaticType.Status.VALID;
		
		// 接口名称
		if (spiId != null)
			cond.and(TSpiMoney.spiId.eq(spiId));

		if (StringUtils.isNotEmpty(name))
			cond.and(TSpiMoney.name.like("%" + name.trim() + "%"));

		// 收费类型
		if (type != null)
			cond.and(TSpiMoney.type.eq(type));

		// 状态
		if (status != null)
			cond.and(TSpiMoney.status.eq(status));

		ECPage<SpiMoney> ECPage = db.findPage(SpiMoney.class, db.where(cond), pagerOffset, PAGE_SIZE);

		List<SpiMoneyVO> vos = new ArrayList<SpiMoneyVO>();
		for (SpiMoney po : ECPage.getList()) {
			SpiMoneyVO vo = new SpiMoneyVO();
			BeanUtils.copyProperties(po, vo);
			vos.add(vo);
		}

		List<Map<String, Object>> dictDateSpiInfos = genService.dictDate(TSpiInfo.getTableName(), TSpiInfo.id.getName(), TSpiInfo.name.getName());
		genService.dictDateReplace(vos, dictDateSpiInfos, "spiId", "spiInfoName");

		ECPage<SpiMoneyVO> page = new ECPage<SpiMoneyVO>();
		page.setList(vos);
		page.setCurrentPage(ECPage.getCurrentPage());
		page.setRowsPerPage(ECPage.getRowsPerPage());
		page.setTotalPage(ECPage.getTotalPage());
		page.setTotalRows(ECPage.getTotalRows());

		map.put("ECPage", page);

		return "customer/spi_money/list";
	}

	@RequestMapping(value = "/saveUpdateUI")
	public String saveUpdateUI(Integer id, ModelMap model) {
		SpiMoneyVO vo = null;
		if (id != null && id > 0) {
			vo = new SpiMoneyVO();
			SpiMoney dto = db.findByPK(SpiMoney.class, id);
			BeanUtils.copyProperties(dto, vo);
			SpiInfo spiInfo = db.findObject(SpiInfo.class, db.where(TSpiInfo.id.eq(dto.getSpiId())));
			if (spiInfo != null)
				vo.setSpiInfoName(spiInfo.getName());
		}
		model.put("dto", vo);
		return "customer/spi_money/save_update";
	}

	@RequestMapping(value = "/saveUpdate")
	@ResponseBody
	public AjaxJson saveUpdateUI(@RequestBody SpiMoneyVO vo) {
		AjaxJson json = new AjaxJson();
		try {
			if (vo.getId() != null && vo.getId() > 0) {
				SpiMoney po = db.findByPK(SpiMoney.class, vo.getId());
				po.setSpiId(vo.getSpiId());// 接口名称
				po.setName(vo.getName());// 收费描述
				po.setMoney(vo.getMoney());// 收费(元)
				po.setType(vo.getType());// 收费类型
				po.setStatus(vo.getStatus());// 状态
				po.setUpdateTime(new Date());// 更新时间
				db.update(po);
			} else {
				SpiMoney po = new SpiMoney();
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
				db.updateTable(TSpiMoney).set(TSpiMoney.status, StaticType.Status.IN_VALID).where(TSpiMoney.id.in(ids)).executeUpdate();
			} else {
				json.setSuccess(false);
				json.setMsg("SPI价格管理删除失败!");
			}
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg("SPI价格管理删除失败!");
			logger.error(json.getMsg(), e);
		}
		return json;
	}
}

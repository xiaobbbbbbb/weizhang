package com.ecarinfo.traffic.controller.customer;

import static com.ecarinfo.traffic.persist.tables.StaticImport.TSpiInfo;
import static com.ecarinfo.traffic.persist.tables.StaticImport.TSpiTack;
import static com.ecarinfo.traffic.persist.tables.StaticImport.TTackInfo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import com.ecarinfo.traffic.persist.po.SpiTack;
import com.ecarinfo.traffic.persist.po.TackInfo;
import com.ecarinfo.traffic.vo.AjaxJson;
import com.ecarinfo.traffic.vo.customer.SpiTackVO;

/**
 * Description:Spi策略配置
 */

@Controller
@RequestMapping("/spiTack")
public class SpiTackController extends BaseController {

	protected transient static Logger logger = Logger.getLogger(SpiTackController.class);

	@RequestMapping(value = "/")
	public String index(Integer tackId, Integer spiId, ModelMap map) {
		int pagerOffset = SystemContext.getPageOffset();

		Condition cond = Condition.getInstance();

		// 策略名称
		if (tackId != null)
			cond.and(TSpiTack.tackId.eq(tackId));

		// 第三方配置名称
		if (spiId != null)
			cond.and(TSpiTack.spiId.eq(spiId));

		ECPage<SpiTack> ECPage = db.findPage(SpiTack.class, db.where(cond), pagerOffset, PAGE_SIZE);

		List<SpiTackVO> vos = new ArrayList<SpiTackVO>();
		for (SpiTack po : ECPage.getList()) {
			SpiTackVO vo = new SpiTackVO();
			BeanUtils.copyProperties(po, vo);
			vos.add(vo);
		}

		List<Map<String, Object>> dictDates = genService.dictDate(TTackInfo.getTableName(), TTackInfo.id.getName(), TTackInfo.name.getName());
		genService.dictDateReplace(vos, dictDates, "tackId", "tackName");

		List<Map<String, Object>> dictDateSpis = genService.dictDate(TSpiInfo.getTableName(), TSpiInfo.id.getName(), TSpiInfo.name.getName());
		genService.dictDateReplace(vos, dictDateSpis, "spiId", "spiName");

		ECPage<SpiTackVO> page = new ECPage<SpiTackVO>();
		page.setList(vos);
		page.setCurrentPage(ECPage.getCurrentPage());
		page.setRowsPerPage(ECPage.getRowsPerPage());
		page.setTotalPage(ECPage.getTotalPage());
		page.setTotalRows(ECPage.getTotalRows());

		map.put("ECPage", page);

		return "customer/spi_tack/list";
	}

	@RequestMapping(value = "/saveUpdateUI")
	public String saveUpdateUI(Integer id, ModelMap model) {
		SpiTackVO vo = null;
		if (id != null && id > 0) {
			vo = new SpiTackVO();
			SpiTack dto = db.findByPK(SpiTack.class, id);
			BeanUtils.copyProperties(dto, vo);
			TackInfo tack = db.findObject(TackInfo.class, db.where(TTackInfo.id.eq(dto.getTackId())));
			if (tack != null)
				vo.setTackName(tack.getName());

			SpiInfo spi = db.findObject(SpiInfo.class, db.where(TSpiInfo.id.eq(dto.getSpiId())));
			if (spi != null)
				vo.setSpiName(spi.getName());
		}
		model.put("dto", vo);
		return "customer/spi_tack/save_update";
	}

	@RequestMapping(value = "/saveUpdate")
	@ResponseBody
	public AjaxJson saveUpdateUI(@RequestBody SpiTackVO dto) {
		AjaxJson json = new AjaxJson();
		try {
			if (dto.getId() != null && dto.getId() > 0) {
				SpiTack po = db.findByPK(SpiTack.class, dto.getId());
				po.setTackId(dto.getTackId());// 策略名称
				po.setSpiId(dto.getSpiId());// 第三方配置名称
				po.setCreateTime(dto.getCreateTime());// 创建时间
				db.update(po);
			} else {
				SpiTack po = new SpiTack();
				BeanUtils.copyProperties(dto, po);
				po.setCreateTime(new Date());// 创建时间
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
				db.delete(SpiTack.class, db.where(TSpiTack.id.in(ids)));
			} else {
				json.setSuccess(false);
				json.setMsg("Spi策略配置删除失败!");
			}
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg("Spi策略配置删除失败!");
			logger.error("Spi策略配置删除失败!", e);
		}
		return json;
	}
}

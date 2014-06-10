package com.ecarinfo.traffic.controller.customer;

import static com.ecarinfo.traffic.persist.tables.StaticImport.TTrafficInfo;

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
import com.ecarinfo.traffic.persist.po.TrafficInfo;
import com.ecarinfo.traffic.vo.AjaxJson;
import com.ecarinfo.traffic.vo.customer.TrafficInfoVO;

/**
 * Description:违章明细
 */

@Controller
@RequestMapping("/trafficInfo")
public class TrafficInfoController extends BaseController {

	protected transient static Logger logger = Logger.getLogger(TrafficInfoController.class);

	@RequestMapping(value = "/")
	public String index(String carNo, String code, String archive, ModelMap map) {
		int pagerOffset = SystemContext.getPageOffset();

		Condition cond = Condition.getInstance();

		if (StringUtils.isNotEmpty(carNo))
			cond.and(TTrafficInfo.carNo.like("%" + carNo.trim() + "%"));

		if (StringUtils.isNotEmpty(code))
			cond.and(TTrafficInfo.code.like("%" + code.trim() + "%"));

		if (StringUtils.isNotEmpty(archive))
			cond.and(TTrafficInfo.archive.like("%" + archive.trim() + "%"));

		ECPage<TrafficInfo> ECPage = db.findPage(TrafficInfo.class, db.where(cond), pagerOffset, PAGE_SIZE);
		map.put("ECPage", ECPage);
		return "customer/traffic_info/list";
	}

	@RequestMapping(value = "/saveUpdateUI")
	public String saveUpdateUI(Integer id, ModelMap model) {
		TrafficInfo dto = null;
		if (id != null && id > 0) {
			dto = db.findByPK(TrafficInfo.class, id);
		}
		model.put("dto", dto);
		return "customer/traffic_info/save_update";
	}

	@RequestMapping(value = "/saveUpdate")
	@ResponseBody
	public AjaxJson saveUpdateUI(@RequestBody TrafficInfoVO dto) {
		AjaxJson json = new AjaxJson();
		try {
			if (dto.getId() != null && dto.getId() > 0) {
				TrafficInfo po = db.findByPK(TrafficInfo.class, dto.getId());
				po.setCarNo(dto.getCarNo());// 车牌号
				po.setCarType(dto.getCarType());// 车型
				po.setTrafficTime(dto.getTrafficTime());// 违章时间
				po.setTrafficLocation(dto.getTrafficLocation());// 违章地点
				po.setTrafficDetail(dto.getTrafficDetail());// 违章描述
				po.setScores(dto.getScores());// 违章扣分
				po.setCode(dto.getCode());// 违章代号
				po.setCreateTime(dto.getCreateTime());// 创建时间
				po.setArchive(dto.getArchive());// 文书号
				po.setMoney(dto.getMoney());// 违章罚款
				db.update(po);
			} else {
				TrafficInfo po = new TrafficInfo();
				BeanUtils.copyProperties(dto, po);
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
	public AjaxJson deleteModel(Long[] ids) {
		AjaxJson json = new AjaxJson();
		try {
			if (ids != null && ids.length > 0) {
				db.delete(TrafficInfo.class, db.where(TTrafficInfo.id.in(ids)));
			} else {
				json.setSuccess(false);
				json.setMsg("违章明细删除失败!");
			}
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg("违章明细删除失败!");
			logger.error("违章明细删除失败!", e);
		}
		return json;
	}
}

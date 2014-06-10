package com.ecarinfo.traffic.controller.customer;

import static com.ecarinfo.traffic.persist.tables.StaticImport.TCarInfo;

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
import com.ecarinfo.traffic.persist.po.CarInfo;
import com.ecarinfo.traffic.vo.AjaxJson;
import com.ecarinfo.traffic.vo.customer.CarInfoVO;

/**
 * Description:车辆管理
 */

@Controller
@RequestMapping("/carInfo")
public class CarInfoController extends BaseController {

	protected transient static Logger logger = Logger.getLogger(CarInfoController.class);

	@RequestMapping(value = "/")
	public String index(String carNo, String carFrameNo, String carCertificate, Integer carType, Integer status, ModelMap map) {
		int pagerOffset = SystemContext.getPageOffset();

		Condition cond = Condition.getInstance();

		if (StringUtils.isNotEmpty(carNo))
			cond.and(TCarInfo.carNo.like("%" + carNo.trim() + "%"));

		if (StringUtils.isNotEmpty(carFrameNo))
			cond.and(TCarInfo.carFrameNo.like("%" + carFrameNo.trim() + "%"));

		if (StringUtils.isNotEmpty(carCertificate))
			cond.and(TCarInfo.carCertificate.like("%" + carCertificate.trim() + "%"));

		// 车辆类型
		if (carType != null)
			cond.and(TCarInfo.carType.eq(carType));

		// 状态
		if (status != null)
			cond.and(TCarInfo.status.eq(status));

		ECPage<CarInfo> ECPage = db.findPage(CarInfo.class, db.where(cond), pagerOffset, PAGE_SIZE);
		map.put("ECPage", ECPage);
		return "customer/car_info/list";
	}

	@RequestMapping(value = "/saveUpdateUI")
	public String saveUpdateUI(Integer id, ModelMap model) {
		CarInfo dto = null;
		if (id != null && id > 0) {
			dto = db.findByPK(CarInfo.class, id);
		}
		model.put("dto", dto);
		return "customer/car_info/save_update";
	}

	@RequestMapping(value = "/saveUpdate")
	@ResponseBody
	public AjaxJson saveUpdateUI(@RequestBody CarInfoVO dto) {
		AjaxJson json = new AjaxJson();
		try {
			if (dto.getId() != null && dto.getId() > 0) {
				CarInfo po = db.findByPK(CarInfo.class, dto.getId());
				po.setCarNo(dto.getCarNo());// 车牌号
				po.setCarFrameNo(dto.getCarFrameNo());// 车架号
				po.setCarEngineNo(dto.getCarEngineNo());// 发动机号
				po.setUpdateTime(dto.getUpdateTime());// 更新时间
				po.setUpdateTime(new Date());// 更新时间
				po.setCarCertificate(dto.getCarCertificate());// 车辆证书编号
				po.setOwnerName(dto.getOwnerName());// 车主姓名
				po.setCarType(dto.getCarType());// 车辆类型
				po.setStatus(dto.getStatus());// 状态
				po.setCreateTime(dto.getCreateTime());// 创建时间
				po.setIsDelete(dto.getIsDelete());// 是否删除
				db.update(po);
			} else {
				CarInfo po = new CarInfo();
				BeanUtils.copyProperties(dto, po);
				po.setUpdateTime(new Date());// 更新时间
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
	public AjaxJson deleteModel(Long[] ids) {
		AjaxJson json = new AjaxJson();
		try {
			if (ids != null && ids.length > 0) {
				db.delete(CarInfo.class, db.where(TCarInfo.id.in(ids)));
			} else {
				json.setSuccess(false);
				json.setMsg("车辆管理删除失败!");
			}
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg("车辆管理删除失败!");
			logger.error("车辆管理删除失败!", e);
		}
		return json;
	}
}

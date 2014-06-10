package com.ecarinfo.traffic.controller.customer;

import static com.ecarinfo.traffic.persist.tables.StaticImport.TOrgCarInfo;
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
import com.ecarinfo.traffic.persist.po.OrgCarInfo;
import com.ecarinfo.traffic.protocol.meta.StaticType;
import com.ecarinfo.traffic.vo.AjaxJson;
import com.ecarinfo.traffic.vo.customer.OrgCarInfoVO;

/**
 * Description:客户车辆信息
 */

@Controller
@RequestMapping("/orgCarInfo")
public class OrgCarInfoController extends BaseController {

	protected transient static Logger logger = Logger.getLogger(OrgCarInfoController.class);

	@RequestMapping(value = "/")
	public String index(String orgCode, String carNo, Integer carType, Integer status, ModelMap map) {
		int pagerOffset = SystemContext.getPageOffset();

		Condition cond = Condition.getInstance();

		if (status == null)
			status = StaticType.Status.VALID;

		if (StringUtils.isNotEmpty(orgCode))
			cond.and(TOrgCarInfo.orgCode.like("%" + orgCode.trim() + "%"));

		if (StringUtils.isNotEmpty(carNo))
			cond.and(TOrgCarInfo.carNo.like("%" + carNo.trim() + "%"));

		// 车辆类型
		if (carType != null)
			cond.and(TOrgCarInfo.carType.eq(carType));

		// 状态
		if (status != null)
			cond.and(TOrgCarInfo.status.eq(status));

		ECPage<OrgCarInfo> ECPage = db.findPage(OrgCarInfo.class, db.where(cond), pagerOffset, PAGE_SIZE);

		List<OrgCarInfoVO> vos = new ArrayList<OrgCarInfoVO>();
		for (OrgCarInfo po : ECPage.getList()) {
			OrgCarInfoVO vo = new OrgCarInfoVO();
			BeanUtils.copyProperties(po, vo);
			vos.add(vo);
		}

		List<Map<String, Object>> dictDates = genService.dictDate(TOrgInfo.getTableName(), TOrgInfo.code.getName(), TOrgInfo.name.getName());
		genService.dictDateReplace(vos, dictDates, "orgCode", "orgName");

		ECPage<OrgCarInfoVO> page = new ECPage<OrgCarInfoVO>();
		page.setList(vos);
		page.setCurrentPage(ECPage.getCurrentPage());
		page.setRowsPerPage(ECPage.getRowsPerPage());
		page.setTotalPage(ECPage.getTotalPage());
		page.setTotalRows(ECPage.getTotalRows());

		map.put("ECPage", page);

		return "customer/org_car_info/list";
	}

	@RequestMapping(value = "/saveUpdateUI")
	public String saveUpdateUI(Integer id, ModelMap model) {
		OrgCarInfo dto = null;
		if (id != null && id > 0) {
			dto = db.findByPK(OrgCarInfo.class, id);
		}
		model.put("dto", dto);
		return "customer/org_car_info/save_update";
	}

	@RequestMapping(value = "/saveUpdate")
	@ResponseBody
	public AjaxJson saveUpdateUI(@RequestBody OrgCarInfoVO dto) {
		AjaxJson json = new AjaxJson();
		try {
			if (dto.getId() != null && dto.getId() > 0) {
				OrgCarInfo po = db.findByPK(OrgCarInfo.class, dto.getId());
				po.setOrgCode(dto.getOrgCode());// 机构编号
				po.setCarNo(dto.getCarNo());// 车牌号
				po.setCarFrameNo(dto.getCarFrameNo());// 车架号
				po.setCarEngineNo(dto.getCarEngineNo());// 发动机号
				po.setCertificate(dto.getCertificate());// 车辆证书编号
				po.setCarType(dto.getCarType());// 车辆类型
				po.setStatus(dto.getStatus());// 状态
				po.setUpdateTime(dto.getUpdateTime());// 更新时间
				po.setUpdateTime(new Date());// 更新时间
				po.setBatNo(dto.getBatNo());// 批次
				po.setCreateTime(dto.getCreateTime());// 创建时间
				po.setIsDelete(dto.getIsDelete());// 是否删除
				db.update(po);
			} else {
				OrgCarInfo po = new OrgCarInfo();
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
				db.delete(OrgCarInfo.class, db.where(TOrgCarInfo.id.in(ids)));
			} else {
				json.setSuccess(false);
				json.setMsg("客户车辆信息删除失败!");
			}
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg("客户车辆信息删除失败!");
			logger.error("客户车辆信息删除失败!", e);
		}
		return json;
	}
}

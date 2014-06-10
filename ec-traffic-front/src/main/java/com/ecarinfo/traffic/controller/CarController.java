package com.ecarinfo.traffic.controller;

import static com.ecarinfo.traffic.persist.tables.StaticImport.TOrgCarInfo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecarinfo.common.utils.BeanUtils;
import com.ecarinfo.common.utils.JSONUtil;
import com.ecarinfo.db4j.condition.Condition;
import com.ecarinfo.db4j.paginate.ECPage;
import com.ecarinfo.db4j.table.Tables;
import com.ecarinfo.db4j.transaction.EcTransaction;
import com.ecarinfo.traffic.dto.Constant;
import com.ecarinfo.traffic.dto.FileDto;
import com.ecarinfo.traffic.dto.QueryDto;
import com.ecarinfo.traffic.filter.SystemContext;
import com.ecarinfo.traffic.persist.logic.LogUtil;
import com.ecarinfo.traffic.persist.logic.OperatorType;
import com.ecarinfo.traffic.persist.po.OrgCarInfo;
import com.ecarinfo.traffic.persist.po.OrgInfo;
import com.ecarinfo.traffic.persist.po.OrgUserInfo;
import com.ecarinfo.traffic.persist.tables.TOrgCarInfo;
import com.ecarinfo.traffic.persist.tables.TOrgInfo;
import com.ecarinfo.traffic.utils.DtoUtil;
import com.ecarinfo.traffic.utils.ExcelReader;
import com.ecarinfo.traffic.utils.FileStorageUtil;
import com.ecarinfo.traffic.utils.FileUtil;
import com.ecarinfo.traffic.utils.HSSFUtil;
import com.ecarinfo.traffic.vo.AjaxJson;
import com.ecarinfo.traffic.vo.UploadView;

@Controller
@RequestMapping("/car")
public class CarController extends BaseController {
	protected transient static Logger logger = Logger
			.getLogger(CarController.class);

	@RequestMapping(value = "/")
	public String index(HttpServletRequest request,Integer queryType ,String carNo,String batNo,Integer carType,String startTime,String endTime,ModelMap map) {
		int pagerOffset = SystemContext.getPageOffset();
		try{
			Condition cd = Condition.getInstance();
			TOrgCarInfo tq = Tables.get(TOrgCarInfo.class);
			QueryDto dto = new QueryDto();
			cd.and(TOrgCarInfo.orgCode.eq(getUser(request).getOrgCode()));
			if(StringUtils.isNotBlank(carNo)){
				dto.setCarNo(carNo);
				 cd.and(tq.carNo.like("%"+carNo+"%"));
			}if(StringUtils.isNotBlank(batNo)){
				dto.setBatNo(batNo);
				cd.and(tq.batNo.eq(batNo));
			}if(carType!=null&&carType>0){
				dto.setCarType(carType);
				cd.and(tq.carType.eq(carType));
			}if(StringUtils.isNotBlank(startTime)){
				dto.setStartTime(startTime);
				cd.and(tq.createTime.greaterOrEqual(startTime));
			}if(StringUtils.isNotBlank(endTime)){
				dto.setEndTime(endTime);
				cd.and(tq.createTime.lessOrEqual(endTime));
			}if(queryType!=null&&queryType>0){
				cd.and(tq.queryType.eq(queryType));
				
			}
			map.put("queryType", queryType);
			ECPage<OrgCarInfo> page = db.findPage(OrgCarInfo.class, db.where(cd.and(tq.isDelete.eq(false))),pagerOffset, PAGE_SIZE);//一车辆只能绑定一个任务
			map.put("ECPage", page);
			map.put("dto", dto);
		}
		catch (Exception e) {
				logger.error("",e);
		}
		return "car/list";
	}

	@RequestMapping(value = "/saveUpdateUI")
	public String saveUpdateUI(Integer id, ModelMap model) {
		OrgCarInfo dto = null;
		if (id != null && id > 0) {
			dto = db.findByPK(OrgCarInfo.class, id);
		}
		model.put("dto", dto);
		return "car/save_update";
	}

	@RequestMapping(value = "/saveUpdate")
	@ResponseBody
	public AjaxJson saveUpdateUI(HttpServletRequest request,@RequestBody OrgCarInfo dto) {
		logger.debug(BeanUtils.toString(dto));
		AjaxJson json = new AjaxJson();
		try {
			if (dto.getId() != null && dto.getId() > 0) {
				OrgCarInfo po = db.findByPK(OrgCarInfo.class, dto.getId());
				po.setUpdateTime(new Date());
				po.setCarNo(dto.getCarNo());
				po.setCarEngineNo(dto.getCarEngineNo());
				po.setCarFrameNo(dto.getCarFrameNo());
				po.setCertificate(dto.getCertificate());
				po.setStatus(dto.getStatus());
				po.setCarType(dto.getCarType());
				po.setBatNo(dto.getBatNo());
				po.setQueryType(dto.getQueryType());
				db.update(po);
				json.setSuccess(true);
				logUtil.log(OperatorType.FRONT, getUser(request).getId(),getUser(request).getUserName(), "更新车辆"+dto.getCarNo(), null);//日志记录
			} else {
				TOrgCarInfo oi=Tables.get(TOrgCarInfo.class);
				OrgCarInfo car= db.findObject(OrgCarInfo.class, oi.orgCode.eq(getUser(request).getOrgCode()).and(oi.carNo.eq(dto.getCarNo()).and(oi.isDelete.eq(false))));
				if(car!=null){
					json.setSuccess(false);
					json.setMsg("车辆已存在！");
					return json;
				}else{
					dto.setCreateTime(new Date());
					dto.setUpdateTime(new Date());
					dto.setIsDelete(false);
					dto.setOrgCode(getUser(request).getOrgCode());
					dto.setStatus(0);
					db.save(dto);
					json.setSuccess(true);
					logUtil.log(OperatorType.FRONT, getUser(request).getId(),getUser(request).getUserName(),"新增车辆"+dto.getCarNo(), null);//日志记录
				}
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
	public AjaxJson deleteModel(HttpServletRequest request,Long[] ids) {
		AjaxJson json = new AjaxJson();
		try {
			if (ids != null && ids.length > 0) {
				db.updateTable(TOrgCarInfo).set(TOrgCarInfo.isDelete, true)
						.where(TOrgCarInfo.id.in(ids)).executeUpdate();
				//TODO 当前操作用户从session中获取
				logUtil.log(OperatorType.FRONT, getUser(request).getId(),getUser(request).getUserName(),"删除车辆","车辆id"+ids.toString());//日志记录
			} else {
				json.setSuccess(false);
				json.setMsg("车型删除失败!");
			}
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg("车型删除失败!");
			logger.error("车型删除失败!", e);
		}
		return json;
	}

	// 批量导入
	@RequestMapping(value = "/upload")
	public String upload(ModelMap map) {
		return "car/upload";
	}

	// 上传文件
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public void upload(HttpServletRequest request,
			HttpServletResponse response, String desc) {
		String descName = (StringUtils.isNotEmpty(desc) && desc.length() > 0) ? desc
				: Constant.DEFAULT_FILE;
		uploadFile(request, response, descName);
	}

	// 上传文件
	private void uploadFile(HttpServletRequest request,
			HttpServletResponse response, String desc) {
		AjaxJson json = new AjaxJson();
		try {

			FileDto dto = FileUtil.uploadFileService(request, desc);
			String fileName = dto.getFileName();
			Set<UploadView> set = new HashSet<UploadView>();// 解析数据封装

			/*
			 * 模板上传格式判断，暂时支持.txt和.xls两种格式
			 */
			if (dto.getFileSuffix().equals(".txt")) {
				set = analysisTxt(dto);
			}
			/*
			 * 解析xls模板
			 */
			else if (dto.getFileSuffix().equals(".xls")) {
				set = analysisXls(dto);
			} else {
				json.setMsg("请使用txt,excel模板上传！");
				super.printAjax(response, JSONUtil.toJson(json));
				return;
			}
			logger.info("有效数据有：" + set.size() + "条");
			dto.setRepeatNums(dto.getTotalNums()-set.size());
			//过滤重复车辆
			Set<OrgCarInfo> vaCarInfos = validateCarinfo(set,dto,getUser(request).getOrgCode());
			int nums =saveWithBatch(vaCarInfos,request);
			dto.setSuccessNums(nums-1);
			json.setObj(dto);
			System.out.println(json);
		} catch (IOException e) {
			json.setSuccess(false);
			logger.error("文件解析异常!", e);
			super.printAjax(response, JSONUtil.toJson(json));
			return;
		} catch (Exception e) {
			json.setSuccess(false);
			logger.error("文件上传异常!", e);
			super.printAjax(response, JSONUtil.toJson(json));
			return;
		}
		super.printAjax(response, JSONUtil.toJson(json));
	}

	// 文件下载
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public void fileDawnload(String fileName, String filePath,
			HttpServletResponse response, HttpServletRequest request) {
		try {
			fileName = DtoUtil.incode(fileName);
			String path = FileStorageUtil.getBaseFileStorage(request) + "/"
					+ filePath;
			// EcUtil.fileDownload(fileName, path, response);
		} catch (Exception e) {
			logger.error("文件下载异常!", e);
		}
	}

	// 自定义模板导出
	@RequestMapping(value = "/template", method = RequestMethod.GET)
	public void template(HttpServletResponse response,
			HttpServletRequest request) {
		try {
			TOrgInfo tq = Tables.get(TOrgInfo.class);
			OrgInfo orgInfo = db.findObject(OrgInfo.class,db.where(tq.code.eq(getUser(request).getOrgCode())));
			if(orgInfo!=null){
				HSSFUtil.createTemplate(response, orgInfo);
			}

		} catch (Exception e) {
			logger.error("模板输出异常！", e);
		}
	}


	/*
	 * @Description 过滤carinfo库中已存在的车辆
	 * 
	 * @Param set
	 * 
	 * @Return set
	 */
	private Set<OrgCarInfo> validateCarinfo(Set<UploadView> set,FileDto dto,String orgCode) {
		Iterator<UploadView> it = set.iterator();
		Set<OrgCarInfo> carSet = new HashSet<OrgCarInfo>();
		int idx = 0;
		Long batNo = System.currentTimeMillis();
		while (it.hasNext()) {
			UploadView view = it.next();
			OrgCarInfo carInfo = db.findObject(OrgCarInfo.class,
					db.where(TOrgCarInfo.carNo.eq(view.getCarNo()).and(TOrgCarInfo.carFrameNo.eq(view.getCarFrameNo())).and(TOrgCarInfo.orgCode.eq(orgCode))).limit(1));
			if (carInfo == null) {
				OrgCarInfo car = new OrgCarInfo();
				car.setCarNo(view.getCarNo());
				car.setCarType(view.getType());
				car.setCarEngineNo(view.getCarEngineNo());
				car.setCarFrameNo(view.getCarFrameNo());
				car.setCertificate(view.getCertificate());
				car.setQueryType(view.getQueryType());
				car.setOrgCode(orgCode);
				Date date = new Date();
				car.setUpdateTime(date);
				car.setCreateTime(date);
				car.setIsDelete(false);
				car.setStatus(0);
				car.setBatNo(batNo.toString());
				carSet.add(car);
			}else{
				idx++;
			}
		}
		dto.setExitsNums(idx);
		logger.info("carlistsize==" + carSet.size());
		return carSet;
	}

	// 批量保存
	private int saveWithBatch(Set<OrgCarInfo> set,HttpServletRequest request) {
		int idx = 1;
		OrgUserInfo userInfo=getUser(request);
		OrgInfo orgInfo = db.findObject(OrgInfo.class, TOrgInfo.getInstance().code.eq(userInfo.getOrgCode()).and(TOrgInfo.getInstance().isDelete.eq(false)));
		if(set==null||set.size()<1){
			return idx;
		}
		EcTransaction trans = new EcTransaction(db);
		trans.beginTrans();
		try {
			for (OrgCarInfo car : set) {
				if(StringUtils.isBlank(car.getCarNo())){//车牌为空
					continue;
				}if(car.getCarEngineNo()!=null&&car.getCarEngineNo().length()<orgInfo.getCarEngineLen()){//长度不够
					continue;
				}if(car.getCarFrameNo()!=null&&car.getCarFrameNo().length()<orgInfo.getCarFrameLen()){//长度不够
					continue;
				}if(car.getCertificate()!=null&&car.getCertificate().length()<orgInfo.getCarCertificateLen()){//长度不够
					continue;
				}
				db.save(car);
				if (idx % 100 == 0) {
					trans.commit();
				}
				idx++;
			}
			trans.commit();
			logUtil.log(OperatorType.FRONT, userInfo.getId(),userInfo.getUserName(),"批量上传车辆", "批次号："+set.iterator().next().getBatNo());//日志记录
		} catch (Exception e) {
			logger.error("批量保存出错！", e);
			trans.rollback();
		}
		return idx;
	}

	// 解析txt
	public Set<UploadView> analysisTxt(FileDto dto) throws Exception {
		Set<UploadView> set = new HashSet<UploadView> ();
		InputStreamReader read = null;

		InputStream is = new FileInputStream(dto.getFilePath());

		read = new InputStreamReader(is, "GBK");
		BufferedReader bufferedReader = new BufferedReader(read);
		String lineTXT = null;
		while ((lineTXT = bufferedReader.readLine()) != null) { // 换行
			String[] stuAttr = lineTXT.toString().split(",");
			UploadView view = new UploadView();
			view.setCarNo(stuAttr[0] != null ? stuAttr[0] : "");
			view.setUserName(stuAttr[1] != null ? stuAttr[1] : "");
			view.setCarFrameNo(stuAttr[2] != null ? stuAttr[2] : "");
			view.setCarEngineNo(stuAttr[3] != null ? stuAttr[3] : "");
			// view.setCarRegistNo(stuAttr[4]!=null?stuAttr[4]:"");
			set.add(view);
		}
		read.close();

		return set;
	}

	private Set<UploadView> analysisXls(FileDto dto) throws IOException {
		Set<UploadView> set = new HashSet<UploadView> ();
		InputStream is = new FileInputStream(dto.getFilePath());
		POIFSFileSystem fs = new POIFSFileSystem(is);
		HSSFWorkbook wb = new HSSFWorkbook(fs);

		HSSFSheet sheet = wb.getSheetAt(0);

		// 得到总行数
		int rowNum = sheet.getLastRowNum();
		dto.setTotalNums(rowNum);
		HSSFRow row0 = sheet.getRow(0);
		int colNum = row0.getPhysicalNumberOfCells();
		ExcelReader excelReader = new ExcelReader();

		logger.info("总行数:" + rowNum);
		logger.info("总列数:" + colNum);
		String[] regexStrings = {"车牌","车辆类型","查询类型","车架","发动机","证书"};//根据表头来匹配字段
		String[] titleStrings = new String[colNum];
		//读取表头
		for(int k=0;k<colNum;k++){
			titleStrings[k]=  excelReader.getStringCellValue(row0.getCell(k));
		}
		// 正文内容应该从第二行开始,第一行为表头的标题
		for (int i = 1; i <= rowNum; i++) {

			UploadView view = new UploadView();
			HSSFRow row = sheet.getRow(i);
			for(int k=0;k<colNum;k++){
				if(excelReader.getStringCellValue(row0.getCell(k)).trim().contains("车牌")){
					view.setCarNo(excelReader.getStringCellValue(row.getCell(k)).trim());
				}
				if(excelReader.getStringCellValue(row0.getCell(k)).trim().contains("车辆类型")){
					view.setType(excelReader.getIntegerCellValue(row.getCell(k)));
				}
				if(excelReader.getStringCellValue(row0.getCell(k)).trim().contains("查询类型")){
					view.setQueryType(excelReader.getIntegerCellValue(row.getCell(k)));
				}
				if(excelReader.getStringCellValue(row0.getCell(k)).trim().contains("车架")){
					view.setCarFrameNo(excelReader.getStringCellValue(row.getCell(k)).trim());
				}
				if(excelReader.getStringCellValue(row0.getCell(k)).trim().contains("发动机")){
					view.setCarEngineNo(excelReader.getStringCellValue(row.getCell(k)).trim());
				}
				if(excelReader.getStringCellValue(row0.getCell(k)).trim().contains("证书")){
					view.setCertificate(excelReader.getStringCellValue(row.getCell(k)).trim());
				}
			}
//			// 也可以将每个单元格的数据设置到一个javabean的属性中，此时需要新建一个javabean
//			view.setCarNo(excelReader.getStringCellValue(row.getCell(0)).trim());
//			view.setType(excelReader.getIntegerCellValue(row.getCell(1)));
//			view.setCarFrameNo(excelReader.getStringCellValue(row.getCell(2))
//					.trim());
//			view.setCarEngineNo(excelReader.getStringCellValue(row.getCell(3))
//					.trim());
//			view.setCertificate(excelReader.getStringCellValue(row.getCell(4))
//					.trim());

			set.add(view);
		}
		return set;
	}
}

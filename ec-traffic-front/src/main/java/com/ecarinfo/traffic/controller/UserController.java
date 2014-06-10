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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ecarinfo.common.utils.JSONUtil;
import com.ecarinfo.common.utils.MD5Utils;
import com.ecarinfo.db4j.table.Tables;
import com.ecarinfo.db4j.transaction.EcTransaction;
import com.ecarinfo.traffic.dto.Constant;
import com.ecarinfo.traffic.dto.FileDto;
import com.ecarinfo.traffic.persist.logic.OperatorType;
import com.ecarinfo.traffic.persist.po.OrgCarInfo;
import com.ecarinfo.traffic.persist.po.OrgInfo;
import com.ecarinfo.traffic.persist.po.OrgUserInfo;
import com.ecarinfo.traffic.persist.tables.TOrgInfo;
import com.ecarinfo.traffic.utils.DtoUtil;
import com.ecarinfo.traffic.utils.ExcelReader;
import com.ecarinfo.traffic.utils.FileStorageUtil;
import com.ecarinfo.traffic.utils.FileUtil;
import com.ecarinfo.traffic.utils.HSSFUtil;
import com.ecarinfo.traffic.vo.AjaxJson;
import com.ecarinfo.traffic.vo.UploadView;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
	protected transient static Logger logger = Logger
			.getLogger(UserController.class);

	/**
	 * 修改密码界面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/updatePwdUI")
	public String saveUpdateUI( ModelMap model) {
		return "user/update";
	}

	@RequestMapping(value = "/update")
	@ResponseBody
	public AjaxJson saveUpdateUI(HttpServletRequest request, String  oldPassword,String passwd,String password ) {
		AjaxJson json = new AjaxJson();
		try {
			
				OrgUserInfo user = getUser(request);
				if(!MD5Utils.md5(oldPassword).equals(user.getPassword())){
					json.setMsg("旧密码输入错误！");
					json.setSuccess(false);
					return json;
				}if(!passwd.equals(password)){
					json.setMsg("两次密码输入不一致!");
					json.setSuccess(false);
					return json;
				}
				user.setPassword(MD5Utils.md5(password));
				db.update(user);
				logUtil.log(OperatorType.FRONT,getUser(request).getId(),getUser(request).getUserName(), "修改密码",null);//日志记录
				json.setMsg("密码修改成功!");
				json.setSuccess(true);
				return json;
			
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg("密码修改失败！");
			logger.error(json.getMsg(), e);
		}
		return json;
	}

}

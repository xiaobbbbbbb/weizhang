package com.ecarinfo.traffic.service.impl;

import static com.ecarinfo.traffic.persist.tables.StaticImport.TRalResource;
import static com.ecarinfo.traffic.persist.tables.StaticImport.TRalRole;
import static com.ecarinfo.traffic.persist.tables.StaticImport.TRalRoleResources;
import static com.ecarinfo.traffic.persist.tables.StaticImport.TRalUser;
import static com.ecarinfo.traffic.persist.tables.StaticImport.TRalUserRole;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ecarinfo.db4j.table.Tables;
import com.ecarinfo.traffic.persist.po.RalResource;
import com.ecarinfo.traffic.persist.po.RalRole;
import com.ecarinfo.traffic.persist.po.RalRoleResources;
import com.ecarinfo.traffic.persist.po.RalUser;
import com.ecarinfo.traffic.persist.po.RalUserRole;
import com.ecarinfo.traffic.persist.tables.TRalResource;
import com.ecarinfo.traffic.persist.tables.TRalRole;
import com.ecarinfo.traffic.persist.tables.TRalRoleResources;
import com.ecarinfo.traffic.persist.tables.TRalUser;
import com.ecarinfo.traffic.persist.tables.TRalUserRole;
import com.ecarinfo.traffic.service.RalService;
import com.ecarinfo.traffic.vo.security.RalRoleVO;
import com.ecarinfo.traffic.vo.security.RalUserVO;
import com.ecarinfo.traffic.vo.security.ZtreeVO;

@Service
public class RalServiceImpl extends BaseServiceImpl implements RalService {

	private static final Logger logger = Logger.getLogger(RalServiceImpl.class);

	public List<ZtreeVO> getZtree(boolean showUrl, Integer roleId, int id, String target, String url, String icon) {
		List<RalRoleResources> rrr = null;
		if (roleId != null && roleId > 0) {
			rrr = db.findObjectsForList(RalRoleResources.class, db.where(TRalRoleResources.roleId.eq(roleId)));
		}

		List<ZtreeVO> dtos = new ArrayList<ZtreeVO>();

		List<RalResource> list = db.findObjectsForList(RalResource.class, db.where(TRalResource.parentId.eq(id == 0 ? 0 : id)).orderBy(TRalResource.level)
				.asc());

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String webpath = request.getContextPath();

		for (RalResource r : list) {
			ZtreeVO dto = new ZtreeVO();
			dto.setId(r.getResourceId());
			dto.setName(r.getName());
			if (r.getIsLeaf() > 0) {
				dto.setIsParent(true);
				dto.setOpen(true);
			}
			if (showUrl) {
				dto.setUrl(webpath + url + "?id=" + r.getResourceId());
				dto.setTarget(target != null ? target : "_self");
			}
			dto.setIconSkin(icon != null ? icon : "pIcon1");
			dto.setpId(r.getParentId());

			if (rrr != null) {
				for (RalRoleResources rr : rrr) {
					if (rr.getResourcesId() == r.getResourceId())
						dto.setChecked(true);
				}
			}
			dtos.add(dto);
		}
		return dtos;
	}

	public void save(RalRoleVO vo) {
		vo.setCreateTime(new Date());
		vo.setUpdateTime(new Date());
		RalRole role = new RalRole();
		BeanUtils.copyProperties(vo, role);
		db.save(role);
		vo.setRoleId(role.getRoleId());
		saveRoleResources(vo);
	}

	// 保存角色与资源对应对象
	public void saveRoleResources(RalRoleVO vo) {
		if (vo.getIds() != null) {
			String[] ids = vo.getIds().split(",");
			for (String id : ids) {
				RalRoleResources rr = new RalRoleResources();
				rr.setRoleId(vo.getRoleId());
				rr.setResourcesId(Integer.parseInt(id));
				db.save(rr);
			}
		}
	}

	public void update(RalRoleVO dto) {
		db.delete(RalRoleResources.class, db.where(TRalRoleResources.roleId.eq(dto.getRoleId())));
		saveRoleResources(dto);
		dto.setUpdateTime(new Date());
		RalRole role = new RalRole();
		RalRole r = db.findByPK(RalRole.class, dto.getRoleId());
		BeanUtils.copyProperties(dto, role);
		role.setCreateTime(r.getCreateTime());
		db.update(role);
	}

	public void save(RalUserVO vo) {
		RalUser user = new RalUser();
		BeanUtils.copyProperties(vo, user);
		vo.setUpdateTime(new Date());// 更新时间
		vo.setCreateTime(new Date());// 创建日期
		db.save(user);// 保存用户
		if (StringUtils.isNotEmpty(vo.getRoleIds())) {
			String[] ids = vo.getRoleIds().split(",");
			saveRalUserRole(ids, user);// 保存用户角色
		}
	}

	public void update(RalUserVO vo) {
		RalUser user = new RalUser();
		BeanUtils.copyProperties(vo, user);
		user.setUpdateTime(new Date());// 更新时间
		db.update(user);// 修改用户

		// 删除用户之前的角色
		db.delete(RalUserRole.class, db.where(TRalUserRole.userId.eq(user.getUserId())));
		if (StringUtils.isNotEmpty(vo.getRoleIds())) {
			String[] ids = vo.getRoleIds().split(",");
			saveRalUserRole(ids, user);// 保存用户角色
		}
	}

	/**
	 * 保存用户角色
	 */
	private void saveRalUserRole(String[] ids, RalUser po) {
		RalUserRole rur = null;
		for (String id : ids) {
			rur = new RalUserRole();
			rur.setUserId(po.getUserId());
			rur.setRoleId(Integer.valueOf(id));
			db.save(rur);// 保存用户角色
		}
	}

	public RalUserVO findUserInfo(String loginName) {
		RalUser user = db.findObject(RalUser.class, db.where(TRalUser.loginName.eq(loginName).and(TRalUser.status.eq(1).or(TRalUser.status.eq(-1)))));
		RalUserVO po = new RalUserVO();
		if (user != null) {

			BeanUtils.copyProperties(user, po);
			// 添加拥有的角色
			Set<RalRole> roles = new HashSet<RalRole>();

			TRalRole r = Tables.get(TRalRole.class, "r");
			TRalUserRole ru = Tables.get(TRalUserRole.class, "ru");
			TRalUser u = Tables.get(TRalUser.class, "u");
			TRalResource rr = Tables.get(TRalResource.class, "rr");
			TRalRoleResources rrr = Tables.get(TRalRoleResources.class, "rrr");

			List<RalRole> dtos = db.select(r.all).from(r, ru, u).where(u.userId.eq(ru.userId).and(ru.roleId.eq(r.roleId)).and(u.userId.eq(user.getUserId())))
					.queryObjectsForList(RalRole.class);

			roles.addAll(dtos);
			po.setRoleSet(roles);

			// 添加拥有的资源
			Set<RalResource> ress = new HashSet<RalResource>();
			List<RalResource> rdtos = db
					.select(rr.all)
					.from(u, ru, r, rrr, rr)
					.where(u.userId.eq(ru.userId).and(ru.roleId.eq(r.roleId)).and(r.roleId.eq(rrr.roleId))
							.and(rrr.resourcesId.eq(rr.resourceId).and(u.userId.eq(user.getUserId())))).queryObjectsForList(RalResource.class);
			ress.addAll(rdtos);
			po.setResourceSet(ress);
		}
		return po;
	}

	public RalUserVO findUserInfo(Integer userId) {
		RalUser dto = db.findByPK(RalUser.class, userId);
		List<RalUserRole> userRoles = this.db.findObjectsForList(RalUserRole.class, db.where(TRalUserRole.userId.eq(userId)));
		Set<Integer> roleIds = new HashSet<Integer>();
		for (RalUserRole userRole : userRoles) {
			roleIds.add(userRole.getRoleId());
		}
		List<RalRole> roles = this.db.findObjectsForList(RalRole.class, db.where(TRalRole.roleId.in(roleIds.toArray(new Integer[roleIds.size()]))));
		StringBuffer roleNames = new StringBuffer();
		StringBuffer rIds = new StringBuffer();
		for (RalRole role : roles) {
			roleNames.append(role.getName() + ",");
			rIds.append(role.getRoleId() + ",");
		}
		if ((roleNames.toString()).length() > 0) {
			roleNames.deleteCharAt((roleNames.toString()).length() - 1);
			rIds.deleteCharAt((rIds.toString()).length() - 1);
		}
		RalUserVO vo = new RalUserVO();
		BeanUtils.copyProperties(dto, vo);
		vo.setRoleNames(roleNames.toString());
		vo.setRoleIds(rIds.toString());
		return vo;
	}

	// 根据资源获取用户
	public List<RalUser> findUserByResources(String urls) {
		TRalRole r = Tables.get(TRalRole.class, "r");
		TRalUserRole ru = Tables.get(TRalUserRole.class, "ru");
		TRalUser u = Tables.get(TRalUser.class, "u");
		TRalResource rr = Tables.get(TRalResource.class, "rr");
		TRalRoleResources rrr = Tables.get(TRalRoleResources.class, "rrr");
		List<RalUser> dtos = db
				.select(u.all)
				.from(u, ru, r, rrr, rr)
				.where(u.userId.eq(ru.userId).and(ru.roleId.eq(r.roleId)).and(r.roleId.eq(rrr.roleId)).and(rrr.resourcesId.eq(rr.resourceId))
						.and(u.status.eq(1)).and(rr.url.in(urls))).queryObjectsForList(RalUser.class);
		return dtos;
	}

	public boolean loginIn(String username, String password) {
		// Subject理解成权限对象。类似user
		Subject subject = SecurityUtils.getSubject();
		// Session session = subject.getSession();

		// 创建用户名和密码的令牌
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		// 记录该令牌，如果不记录则类似购物车功能不能使用。
		token.setRememberMe(true);
		try {
			subject.login(token);
		} catch (Exception ex) {
			logger.error(username + "用户名或密码不正确！", ex);
		}
		// 验证是否成功登录的方法
		if (subject.isAuthenticated()) {
			return true;
		}
		return false;
	}

	public boolean loginOut() {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return true;
	}
}

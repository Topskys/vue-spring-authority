package com.topsky.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.topsky.entity.Permission;
import com.topsky.vo.RolePermissionVo;
import com.topsky.vo.query.PermissionQueryVo;

import java.util.List;

public interface PermissionService extends IService<Permission> {
    /**
     * 根据用户ID查询权限菜单列表
     * @param userId
     * @return
     */
    List<Permission> findPermissionListByUserId(Long userId);

    /**
     * 查询菜单列表
     * @param permissionQueryVo
     * @return
     */
    List<Permission> findPermissionList(PermissionQueryVo permissionQueryVo);

    /**
     * 查询上级菜单列表
     * @return
     */
    List<Permission> findParentPermissionList();

    /**
     * 检查菜单是否有子菜单
     * @param id
     * @return
     */
    boolean hasChildrenOfPermission(Long id);

    /**
     * 查询权限菜单树
     * @param userId
     * @param roleId
     * @return
     */
    RolePermissionVo findPermissionTree(Long userId,Long roleId);
}

package com.sample.dao;

import com.sample.mbg.model.Permission;
import com.sample.mbg.model.Role;

import java.util.List;

public interface UserRolePermissionRelationDao {
    /**
     * 联表查询用户角色
     *
     * @param userId
     * @return
     */
    List<Role> getRoleByUserId(long userId);


    /**
     * 联表查询用户权限
     *
     * @param userId
     * @return
     */
    List<Permission> getPermissionByUserId(long userId);

}

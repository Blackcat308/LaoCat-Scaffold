package org.laocat.entity.req;

import lombok.Data;

import java.util.Date;

/**
 * @description: 角色请求类
 * @author: LaoCat
 * @date: 2022/6/23
 */
@Data
public class UserRoleReq {

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色code
     */
    private String roleCode;

    /**
     * 创建时间  开始 and 结束
     */
    private Date startTime;
    private Date endTime;

}

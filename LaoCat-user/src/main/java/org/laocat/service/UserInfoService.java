package org.laocat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.laocat.entity.UserInfo;
import org.laocat.entity.vo.UserInfoDetailVO;

import javax.servlet.http.HttpServletRequest;

/**
 * @author LaoCat
 * @date 2022/6/15
 */
public interface UserInfoService extends IService<UserInfo> {

    /**
     * @param username
     * @return UserInfo
     */
    UserInfo loadUserInfoByUserName(String username);


    /**
     * @param userInfo
     * @return Boolean
     */
    Boolean register(UserInfo userInfo);


    /**
     * @return UserInfoDetailVO
     */
    UserInfoDetailVO selectUserInfoByToken();


    /**
     * @param phone
     * @return UserInfo
     */
    UserInfo loadUserInfoByUserPhone(String phone);
}

package org.laocat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.laocat.entity.UserInfo;

/**
 * @author LaoCat
 * @date 2022/6/15
 */
public interface UserInfoService extends IService<UserInfo> {

    /**
     * @param username
     */
    UserInfo loadUserInfoByUserName(String username);
}

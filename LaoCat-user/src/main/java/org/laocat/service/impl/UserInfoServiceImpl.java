package org.laocat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.laocat.auth.MD5Util;
import org.laocat.entity.UserInfo;
import org.laocat.mapper.UserInfoMapper;
import org.laocat.service.UserInfoService;
import org.springframework.stereotype.Service;

/**
 * @author LaoCat
 * @date 2022/6/15
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    /**
     * @return void
     * @author LaoCat
     * @date 2022/6/15
     * @description 根据用户名查询用户
     */
    @Override
    public UserInfo loadUserInfoByUserName(String username) {
        return this.getOne(new LambdaQueryWrapper<UserInfo>().eq(UserInfo::getUsername, username));
    }

    /**
     * @return org.laocat.entity.UserInfo
     * @author LaoCat
     * @date 2022/6/16
     * @description 注册
     */
    @Override
    public Boolean register(UserInfo userInfo) {
        // 需要加密入库
        userInfo.setPassword(MD5Util.encode(userInfo.getPassword()));
        return this.save(userInfo);
    }
}

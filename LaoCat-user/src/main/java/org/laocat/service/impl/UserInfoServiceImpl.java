package org.laocat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
     * @description TODO
     */
    @Override
    public UserInfo loadUserInfoByUserName(String username) {
        return this.getOne(new LambdaQueryWrapper<UserInfo>().eq(UserInfo::getUsername, username));
    }
}

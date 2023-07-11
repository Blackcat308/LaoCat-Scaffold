package org.laocat.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.laocat.auth.JwtUtil;
import org.laocat.auth.MD5Util;
import org.laocat.constant.AuthConstant;
import org.laocat.core.utils.VoUtils;
import org.laocat.entity.UserInfo;
import org.laocat.entity.UserRole;
import org.laocat.entity.vo.UserInfoDetailVO;
import org.laocat.entity.vo.UserRoleDetailVO;
import org.laocat.mapper.UserInfoMapper;
import org.laocat.service.UserInfoService;
import org.laocat.service.UserRoleAssociationService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static org.laocat.constant.AuthConstant.AUTHORIZATION;
import static org.laocat.constant.AuthConstant.BEARER;

/**
 * @author LaoCat
 * @date 2022/6/15
 */
@Service
@AllArgsConstructor
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {
    private final JwtUtil jwtUtil;
    private final UserRoleAssociationService userRoleAssociationService;

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

    /**
     * @description: 查询用户详细信息
     * @author: LaoCat
     * @date: 2022/6/29
     * @returnType: org.laocat.entity.vo.UserInfoDetailVO
     */
    @Override
    public UserInfoDetailVO selectUserInfoByToken() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        UserInfo userInfo = this.loadUserInfoByUserName(userDetails.getUsername());
        List<UserRole> userRoles = userRoleAssociationService.selectUserRolesByUserId(userInfo.getId());

        UserInfoDetailVO userInfoDetail = VoUtils.copy(userInfo, UserInfoDetailVO.class);
        List<UserRoleDetailVO> userRoleDetails = VoUtils.copy(userRoles, UserRoleDetailVO.class);
        userInfoDetail.setUserRoleDetails(userRoleDetails);

        return userInfoDetail;
    }

    /**
     * @description: 根据用户名查询用户
     * @author: LaoCat
     * @date: 2023/7/11
     * @returnType: org.laocat.entity.UserInfo
     */
    @Override
    public UserInfo loadUserInfoByUserPhone(String phone) {
        return this.getOne(new LambdaQueryWrapper<UserInfo>().eq(UserInfo::getPhone, phone));
    }


}

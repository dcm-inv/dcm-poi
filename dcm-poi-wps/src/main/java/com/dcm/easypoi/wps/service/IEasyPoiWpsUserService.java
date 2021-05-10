package com.dcm.easypoi.wps.service;

import com.dcm.easypoi.wps.entity.WpsUserEntity;

/**
 * 用户服务
 */
public interface IEasyPoiWpsUserService {

    /**
     * 获取用户信息
     *
     * @param userId
     * @return
     */
    WpsUserEntity getUser(String userId);
}

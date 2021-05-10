package com.dcm.easypoi.wps.entity.resreq;

import com.dcm.easypoi.wps.entity.WpsFileEntity;
import com.dcm.easypoi.wps.entity.WpsUserEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * 获取文件返回对象
 */
@Data
public class WpsFileResponse extends WpsResponse implements Serializable {

    private WpsFileEntity file;

    private WpsUserEntity user = new WpsUserEntity();
}

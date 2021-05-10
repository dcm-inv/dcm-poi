package com.dcm.easypoi.wps.entity;

import com.dcm.easypoi.wps.entity.resreq.WpsResponse;
import lombok.Data;

import java.io.Serializable;

/**
 * 界面的Token数据,默认不使用
 *
 * @Author hourz
 * @since 2018-01-06
 */
@Data
public class WpsToken extends WpsResponse implements Serializable {

    private int expires_in;
    private String token;
    private String wpsUrl;

}

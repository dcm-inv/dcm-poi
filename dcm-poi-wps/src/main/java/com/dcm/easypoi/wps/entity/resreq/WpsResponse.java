package com.dcm.easypoi.wps.entity.resreq;

import lombok.Data;

import java.io.Serializable;

@Data
public class WpsResponse implements Serializable {

    private int code = 200;

    private String msg;
    private String status = "success";

    public static WpsResponse success() {
        return new WpsResponse();
    }
}

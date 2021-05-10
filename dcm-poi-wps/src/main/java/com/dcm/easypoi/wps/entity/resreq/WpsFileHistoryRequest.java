package com.dcm.easypoi.wps.entity.resreq;

import lombok.Data;

import java.io.Serializable;

@Data
public class WpsFileHistoryRequest extends WpsResponse implements Serializable {

    private String id;
    private int offset;
    private int count;
}

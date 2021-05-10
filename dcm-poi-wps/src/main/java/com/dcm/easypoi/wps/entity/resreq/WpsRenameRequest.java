package com.dcm.easypoi.wps.entity.resreq;

import lombok.Data;

import java.io.Serializable;

@Data
public class WpsRenameRequest extends WpsResponse implements Serializable {

    private String name;
}

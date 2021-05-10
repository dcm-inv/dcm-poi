package com.dcm.easypoi.wps.entity.resreq;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class WpsUserRequest implements Serializable {

    List<String> ids;
}

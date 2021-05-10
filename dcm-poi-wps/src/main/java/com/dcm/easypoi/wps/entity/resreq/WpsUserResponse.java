package com.dcm.easypoi.wps.entity.resreq;

import com.dcm.easypoi.wps.entity.WpsUserEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class WpsUserResponse extends WpsResponse implements Serializable {

    private List<WpsUserEntity> users = new ArrayList<>( );
}

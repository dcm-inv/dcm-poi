package com.dcm.easypoi.wps.entity.resreq;

import com.dcm.easypoi.wps.entity.WpsFileHistoryEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.List;


@Data
public class WpsFileHistoryResponse extends WpsResponse implements Serializable {

    private List<WpsFileHistoryEntity> histories;
}

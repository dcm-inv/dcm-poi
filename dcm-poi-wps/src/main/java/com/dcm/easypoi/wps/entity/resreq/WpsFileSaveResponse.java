package com.dcm.easypoi.wps.entity.resreq;

import com.dcm.easypoi.wps.entity.WpsFileEntity;
import lombok.Data;

import java.io.Serializable;


@Data
public class WpsFileSaveResponse extends WpsResponse implements Serializable {

    public WpsFileSaveResponse() {
    }

    public WpsFileSaveResponse(WpsFileEntity file) {
        this.file = file;
    }

    private WpsFileEntity file;
}

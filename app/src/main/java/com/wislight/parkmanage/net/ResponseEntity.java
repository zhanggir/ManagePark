package com.wislight.parkmanage.net;

import com.google.gson.annotations.SerializedName;
import com.wislight.parkmanage.entity.Status;

/**
 * Created by gqq on 2017/6/14.
 * @author zzx
 */

// 响应的实体基类：为了防止直接实例化，所以做成抽象类
public abstract class ResponseEntity {

    @SerializedName("status")
    private Status mStatus;

    public Status getStatus() {
        return mStatus;
    }
}

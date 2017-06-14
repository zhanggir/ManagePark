package com.wislight.parkmanage.net;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by gqq on 2017/6/14.
 * @author zzx
 */
// 将请求的path、请求参数、响应的数据类型做一个整体的管理。
//    Api的接口的抽象化，具体每一个实现类都代表一个服务器的接口
public interface ApiInterface {

    @NonNull String getPath();

    @Nullable RequestParam getRequestParam();

    @NonNull Class<? extends ResponseEntity> getResponseEntity();
}

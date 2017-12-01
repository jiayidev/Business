package com.dev.brian.business.module.recommand;

import com.dev.brian.business.module.BaseModel;

/**
 * Author   :BrianDev
 * Email    :yuni0914@163.com
 * Create at:2017/11/16 0016
 * Description:
 */

public class BaseRecommandModel extends BaseModel {

    // 一定要保证我们的变量名字与我们json中的key完全一样
    public String ecode;
    public String emsg;
    public RecommandModel data;
}

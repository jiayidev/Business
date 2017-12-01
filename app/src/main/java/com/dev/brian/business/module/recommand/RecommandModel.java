package com.dev.brian.business.module.recommand;

import com.dev.brian.business.module.BaseModel;

import java.util.ArrayList;

/**
 * Author   :BrianDev
 * Email    :yuni0914@163.com
 * Create at:2017/11/16 0016
 * Description: 产品实体
 */

public class RecommandModel extends BaseModel {
    /**
     * 分别对应我们json中的两个数据部分
     */
    public ArrayList<RecommandBodyValue> list;
    public RecommandHeadValue head;
}

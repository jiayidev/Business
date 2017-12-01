package com.dev.brian.business.module.recommand;

import com.dev.brian.business.module.BaseModel;

import java.util.ArrayList;

/**
 * Author   :BrianDev
 * Email    :yuni0914@163.com
 * Create at:2017/12/1 0001
 * Description:
 */

public class RecommandHeadValue extends BaseModel {
    public ArrayList<String> ads;
    public ArrayList<String> middle;
    public ArrayList<RecommandFooterValue> footer;
}

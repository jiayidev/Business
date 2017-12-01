package com.dev.brian.business.module.recommand;

import com.dev.brian.business.module.BaseModel;
import com.dev.brian.sdk.module.monitor.Monitor;
import com.dev.brian.sdk.module.monitor.emevent.EMEvent;

import java.util.ArrayList;

/**
 * Author   :BrianDev
 * Email    :yuni0914@163.com
 * Create at:2017/11/16 0016
 * Description: 搜索实体
 */

public class RecommandBodyValue extends BaseModel {

    public int type;
    public String logo;
    public String title;
    public String info;
    public String price;
    public String text;
    public String site;
    public String from;
    public String zan;
    public ArrayList<String> url;

    //视频专用
    public String thumb;
    public String resource;
    public String resourceID;
    public String adid;
    public ArrayList<Monitor> startMonitor;
    public ArrayList<Monitor> middleMonitor;
    public ArrayList<Monitor> endMonitor;
    public String clickUrl;
    public ArrayList<Monitor> clickMonitor;
    public EMEvent event;
}

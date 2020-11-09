package com.el.returnmsg.service;

import com.alibaba.fastjson.JSON;
import com.el.returnmsg.config.Config;
import com.el.returnmsg.dao.F551513ZDao;
import com.el.returnmsg.mapper.F551513Z;
import com.el.returnmsg.model.F551513ZJson;
import com.el.returnmsg.utils.RestfulWithoutPW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Alan on 2020/10/10 17:10 (required = false)
 */

@Service
public class F551513ZService {
    private final Logger log = LoggerFactory.getLogger(getClass());
    @Resource
    F551513ZDao f551513ZDao;
    public String queryF551513ZList(String edsp,String actn){
        List<F551513Z> listF=f551513ZDao.queryF551513ZList(edsp,actn);
        F551513ZJson jsonstr=new F551513ZJson();
        List<Integer> listmp=new ArrayList<Integer>();
        Date now = new Date(); // 创建一个Date对象，获取当前时间
        // 指定格式化格式
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        //json是数字型，做下转换
        for (int i = 0; i < listF.size(); i++) {
            listmp.add(Integer.valueOf(listF.get(i).getAXPID().trim()));
        }
        if(listmp.size()==0){
            log.info("JSON 查询无数据");
            return "没有需要返回的消息的数据";
        }
        jsonstr.setPids(listmp);
        jsonstr.setOperateTime(f.format(now));
        //A新增标识已经收到 D删除标识已经撤销 P已过帐
        if(actn.equals("A")){
            jsonstr.setOperationType(0);
        }else if(actn.equals("D")){
            jsonstr.setOperationType(1);
        }else if(actn.equals("P")){
            jsonstr.setOperationType(2);
        }
        String json=JSON.toJSONString(jsonstr);

        log.info("JSON 输入："+json);
        Config config=new Config();

        String respose = RestfulWithoutPW.getRestfulWithoutPWResult(config.getReturnMsg(),json);
        log.info("JSON 返回："+respose);

        if(respose!=null){
            //发送完成后更新edsp=Y
            for (int i = 0; i < listF.size(); i++) {
                updateF551513Z(listF.get(i).getAXPID());
            }

        }else{
            log.info("接口调用失败");
            return "接口调用失败";
        }
        log.info("=========return end=====");
        return respose;
    }


    public int deleteF551513ZList(F551513ZJson inpuJson){
        int i=f551513ZDao.deleteF551513ZList(inpuJson);
        int j=f551513ZDao.deleteF551511ZList(inpuJson);
        int k=1;
        if(i==0 && j==0){
            k=0;
        }
        return k;
    }

    public int updateF551513Z(String pid){
        return f551513ZDao.updateF551513Z(pid);
    }
}

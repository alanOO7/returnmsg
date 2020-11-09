package com.el.returnmsg.controller;

import com.el.returnmsg.mapper.F551513Z;
import com.el.returnmsg.model.F551513ZJson;
import com.el.returnmsg.service.F551513ZService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Alan on 2020/10/10 17:17
 */
@RestController
@RequestMapping("/find")
public class F551513ZController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    @Resource
    F551513ZService f551513ZService;

    @RequestMapping("/return")
    public String queryF551513ZList(@RequestParam String edsp,@RequestParam String actn){
        log.info("=========return begin=====");
        return f551513ZService.queryF551513ZList(edsp,actn);

    }
    @RequestMapping("/delete")
    public String deleteF551513ZList(@RequestBody F551513ZJson inputJson){
        log.info("=========delete begin=====");
        log.info("=inputJson="+ inputJson);
        System.out.println("inputJson = " + inputJson);
        int i=100;
        String message="Y";
        String rtu="{\n" +
                "    \"code\":"+i+",\n" +
                "    \"message\":\""+message+"\"\n" +
                "}";
        try {
            int row=f551513ZService.deleteF551513ZList(inputJson);
            if(row==0){
                i=200;
                message="撤销行数0,请检查数据是已过账或者异常";
            }
        }catch (Exception e){
            i=200;
            message="撤销失败"+e.getMessage();
         log.info("delete error:{}",e.toString());
        }
        rtu="{\n" +
                "    \"code\":"+i+",\n" +
                "    \"message\":\""+message+"\"\n" +
                "}";
        log.info("delete info:{}",message);
        return rtu;
    }
    @RequestMapping("/updrecord")
    public int updateF551513Z(String pid){
        return f551513ZService.updateF551513Z(pid);
    }
}

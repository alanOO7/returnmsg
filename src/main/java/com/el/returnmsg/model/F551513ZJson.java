package com.el.returnmsg.model;

import java.util.List;

/**
 * Created by Alan on 2020/10/10 16:19
 */
public class F551513ZJson {
    private int operationType;
    private String operateTime;
    private List<Integer> pids;
    private String operator;


    public void setOperationType(int operationType){
        this.operationType = operationType;
    }
    public int getOperationType(){
        return this.operationType;
    }
    public void setPids(List<Integer> pids){
        this.pids = pids;
    }
    public List<Integer> getPids(){
        return this.pids;
    }
    public void setOperator(String operator){
        this.operator = operator;
    }
    public String getOperator(){
        return this.operator;
    }
    public void setOperateTime(String operateTime){
        this.operateTime = operateTime;
    }
    public String getOperateTime(){
        return this.operateTime;
    }

    @Override
    public String toString() {
        return "F551513ZJson{" +
                "operationType=" + operationType +
                ", pids=" + pids +
                ", operator='" + operator + '\'' +
                ", operateTime='" + operateTime + '\'' +
                '}';
    }
}

package com.dcw.app.rating.biz.pojo.tuangou;

import java.util.List;

/**
 * Created by adao12 on 2015/5/20.
 */
public class IdListStatus {

    private String status;

    private String count;

    private List<Integer> id_lists ;

    public void setStatus(String status){
        this.status = status;
    }
    public String getStatus(){
        return this.status;
    }
    public void setCount(String count){
        this.count = count;
    }
    public String getCount(){
        return this.count;
    }
    public void setId_list(List<Integer> id_list){
        this.id_lists = id_list;
    }
    public List<Integer> getId_list(){
        return this.id_lists;
    }
}

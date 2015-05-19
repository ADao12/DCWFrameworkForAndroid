package com.dcw.app.rating.biz.pojo.url;

/**
 * Created by adao12 on 2015/5/20.
 */
public class Root {
    private String status;

    private String search_result_url;

    public void setStatus(String status){
        this.status = status;
    }
    public String getStatus(){
        return this.status;
    }
    public void setSearch_result_url(String search_result_url){
        this.search_result_url = search_result_url;
    }
    public String getSearch_result_url(){
        return this.search_result_url;
    }
}

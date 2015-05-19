package com.dcw.app.rating.biz.pojo.city;

import java.util.List;

/**
 * Created by adao12 on 2015/5/20.
 */
public class City {

    private String city_name;

    private List<String> districtss ;

    public void setCity_name(String city_name){
        this.city_name = city_name;
    }
    public String getCity_name(){
        return this.city_name;
    }
    public void setDistricts(List<String> districts){
        this.districtss = districts;
    }
    public List<String> getDistricts(){
        return this.districtss;
    }
}

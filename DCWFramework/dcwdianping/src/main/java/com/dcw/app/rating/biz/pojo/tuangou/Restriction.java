package com.dcw.app.rating.biz.pojo.tuangou;

/**
 * Created by adao12 on 2015/5/20.
 */
public class Restriction {

    private String is_reservation_required;

    private String is_refundable;

    private String special_tips;

    public void setIs_reservation_required(String is_reservation_required){
        this.is_reservation_required = is_reservation_required;
    }
    public String getIs_reservation_required(){
        return this.is_reservation_required;
    }
    public void setIs_refundable(String is_refundable){
        this.is_refundable = is_refundable;
    }
    public String getIs_refundable(){
        return this.is_refundable;
    }
    public void setSpecial_tips(String special_tips){
        this.special_tips = special_tips;
    }
    public String getSpecial_tips(){
        return this.special_tips;
    }
}

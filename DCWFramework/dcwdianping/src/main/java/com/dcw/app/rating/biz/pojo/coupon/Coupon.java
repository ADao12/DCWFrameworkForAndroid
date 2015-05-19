package com.dcw.app.rating.biz.pojo.coupon;

import com.dcw.app.rating.biz.pojo.business.Business;
import com.dcw.app.rating.biz.pojo.business.Category;
import com.dcw.app.rating.biz.pojo.business.Region;

import java.util.List;

/**
 * Created by adao12 on 2015/5/20.
 */
public class Coupon {

    private String coupon_id;

    private String title;

    private String description;

    private List<Region> regionss ;

    private List<Category> categoriess ;

    private String download_count;

    private String publish_date;

    private String expiration_date;

    private String distance;

    private String logo_img_url;

    private String coupon_url;

    private String coupon_h5_url;

    private List<Business> businessess ;
}

package com.dcw.app.rating.biz.pojo.city;

import java.util.List;

/**
 * Created by adao12 on 2015/5/20.
 */
public class Category {
    private String category_name;

    private List<String> subcategoriess;

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getCategory_name() {
        return this.category_name;
    }

    public void setSubcategories(List<String> subcategories) {
        this.subcategoriess = subcategories;
    }

    public List<String> getSubcategories() {
        return this.subcategoriess;
    }

}

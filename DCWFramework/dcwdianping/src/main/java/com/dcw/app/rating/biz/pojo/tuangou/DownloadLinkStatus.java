package com.dcw.app.rating.biz.pojo.tuangou;

import java.util.List;

/**
 * Created by adao12 on 2015/5/20.
 */
public class DownloadLinkStatus {

    private String status;

    private String count;

    private List<DownloadLink> download_linkss ;

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
    public void setDownload_links(List<DownloadLink> download_links){
        this.download_linkss = download_links;
    }
    public List<DownloadLink> getDownload_links(){
        return this.download_linkss;
    }
}

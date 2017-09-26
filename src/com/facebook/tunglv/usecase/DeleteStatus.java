/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facebook.tunglv.usecase;

import com.facebook.tunglv.config.Config;
import com.facebook.tunglv.dto.Feed;
import com.facebook.tunglv.dto.User;
import com.facebook.tunglv.httprequest.FacebookHttpRequest;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TUNGLV
 */
public class DeleteStatus {

    public static void main(String[] args) throws Exception {
        Config cfg = new Config();
        String token = cfg.USER_ACCESS_TOKEN;
        //lay thong tin user
        String fields = "id,name,first_name,birthday,feed";
        User user = FacebookHttpRequest.getUser(token, fields);
        //lay ra danh sach cac bai da dang tren trang ca nhan
        List<Feed> lstFeed = user.getListFeed();
        List<Feed> lstPostStatus = new ArrayList<>();
        for (Feed f : lstFeed) {
            if (f.getType() != null && f.getType().equals("message")) {
                lstPostStatus.add(f);
            }
        }
        //id cua bai viet da dang
        String idNode = null;
        for (Feed postStatus : lstPostStatus) {
            //tim theo noi dung bai dang
            if (postStatus.getContent().equals("TestLayDanhSachBaiDaDangEdit")) {
                idNode = postStatus.getId();
            }
        }
        //xoa bai dang
        String rs = FacebookHttpRequest.deleteFeed(token, idNode);
        System.out.println("Ket qua xoa bai viet len trang ca nhan: " + rs);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facebook.tunglv.usecase;

import com.facebook.tunglv.config.Config;
import com.facebook.tunglv.dto.User;
import com.facebook.tunglv.httprequest.FacebookHttpRequest;

/**
 *
 * @author TUNGLV
 */
public class PostSatus {

    public static void main(String[] args) throws Exception {
        Config cfg = new Config();
        String token = cfg.USER_ACCESS_TOKEN;
        //lay ra thong tin nguoi dung
        String fields = "id,name,first_name,birthday,feed";
        User user = FacebookHttpRequest.getUser(token, fields);
        //dang bai viet len trang ca nhan
        String msg = "TestLayDanhSachBaiDaDang";
        String rs = FacebookHttpRequest.postFeed(token, user.getId(), msg);
        System.out.println("Ket qua dang bai viet len trang ca nhan: " + rs);
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facebook.tunglv.usecase;

import com.facebook.tunglv.config.Config;
import com.facebook.tunglv.dto.Page;
import com.facebook.tunglv.dto.User;
import com.facebook.tunglv.httprequest.FacebookHttpRequest;
import java.util.List;

/**
 *
 * @author TUNGLV
 */
public class GetPageUserLike {

    public static void main(String[] args) throws Exception {
        Config cfg = new Config();
        String token = cfg.USER_ACCESS_TOKEN;
        //lay thong tin nguoi dung
        String fields = "id,name,first_name,birthday,feed";
        User user = FacebookHttpRequest.getUser(token, fields);
        //lay danh sach cac trang nguoi dung da thich
        List<Page> list = FacebookHttpRequest.getUserLikes(token, user.getId());
        System.out.println("So trang da like: " + list.size());
        for (Page p : list) {
            System.out.println("ID: " + p.getId() + " Name: " + p.getName() + " Created_time: " + p.getCreatTime());
        }
    }
}

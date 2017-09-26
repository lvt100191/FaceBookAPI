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
public class GetUser {

    public static void main(String[] args) throws Exception {
        Config cfg= new Config();
        String token = cfg.USER_ACCESS_TOKEN;
        String fields="id,name,first_name,birthday,feed";
        User user = FacebookHttpRequest.getUser(token,fields);
        System.out.println("Thong tin nguoi dung: id= " + user.getId() + "; Name= " + user.getName());

    }

}

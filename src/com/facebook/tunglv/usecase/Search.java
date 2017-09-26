/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facebook.tunglv.usecase;

import com.facebook.tunglv.config.Config;
import com.facebook.tunglv.httprequest.FacebookHttpRequest;

/**
 *
 * @author TUNGLV
 */
public class Search {

    public static void main(String[] args) throws Exception {
        Config cfg = new Config();
        String token = cfg.USER_ACCESS_TOKEN;
        String q = "Sach";
        //type nay bao gom cac loai:user, page, event, group, place, placetopic, ad_*
        //chi tiet xem tai link sau phan tim kiem: https://developers.facebook.com/docs/graph-api/using-graph-api
        String type = "page";
        String rs = FacebookHttpRequest.search(token, q, type);
        System.out.println("Ket qua tim kiem: " + rs);
    }
}

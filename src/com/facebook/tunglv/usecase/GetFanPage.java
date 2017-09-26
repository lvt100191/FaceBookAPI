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
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * desc: Lay id cua trang ca nhan
 *
 * @author TUNGLV
 */
public class GetFanPage {

    public static void main(String[] args) throws Exception {
        //lay thong tin user
        Config cfg = new Config();
        String token = cfg.USER_ACCESS_TOKEN;
        //neu trang chua tao nguoi dung thi id hien thi tren link url
        //https://www.facebook.com/Khuy%E1%BA%BFn-m%C3%A3i-c%E1%BB%B1c-ch%E1%BA%A5t-1619680474772086/?fref=ts
        //neu nguoi dung da dat ten thi se hien ten tren url https://www.facebook.com/mshoatoeic/?fref=ts
        String username = "mshoatoeic";
        String jsonStr = FacebookHttpRequest.getFanPage(token, username);
        //parse chuoi json tra va
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(jsonStr);
        String idPage = obj.get("id").toString();
        
        System.out.println("Id cua trang: " + idPage);

    }
}
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
import com.facebook.tunglv.httprequest.ResponseUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.json.simple.JSONArray;
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
        JSONParser parser = null;
        //neu trang chua tao nguoi dung thi id hien thi tren link url
        //https://www.facebook.com/Khuy%E1%BA%BFn-m%C3%A3i-c%E1%BB%B1c-ch%E1%BA%A5t-1619680474772086/?fref=ts
        //neu nguoi dung da dat ten thi se hien ten tren url https://www.facebook.com/mshoatoeic/?fref=ts
        String username = "mshoatoeic";
        String jsonStr = FacebookHttpRequest.getFanPage(token, username);
        //parse chuoi json tra va
        parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(jsonStr);
        //lay id cua trang id= 266324126761796
        String idPage = obj.get("id").toString();
         System.out.println("Id cua trang: " + idPage);
        //lay bai dang cua trang trong ngay hom nay/{page-id}/feed
        String urlPageFeed = "https://graph.facebook.com/" + idPage + "/feed?access_token=" + token;
        String rs = ResponseUtil.sendGet(urlPageFeed);
        parser = new JSONParser();
        JSONObject objFeed = (JSONObject) parser.parse(rs);
        JSONArray data = (JSONArray) objFeed.get("data");
        List<Feed> lstFeed = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            JSONObject feed = (JSONObject) data.get(i);
            String creat_time = feed.get("created_time").toString();
            //2017-09-27T08:29:04+0000
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
            Date date = sdf.parse(creat_time);
             SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
            String dateCreateformat = sdf1.format(date);
            Date today = new Date();
            String todayFormat = sdf1.format(today);
            if (dateCreateformat.equals(todayFormat)) {
                Feed f = new Feed();
                Object keyMessage = feed.get("message");
                Object keyStory = feed.get("story");
                f.setId(feed.get("id").toString());
                f.setCreateTime(creat_time);
                if (keyMessage != null) {
                    f.setMessage(feed.get("message").toString());
                }
                if (keyStory != null) {
                    f.setStory(feed.get("story").toString());
                }
                lstFeed.add(f);
            }

        }
        System.out.println("So bai bai dang ngay hom nay: " + lstFeed.size());
        //lay danh sach comment cua bai viet hom nay cua trang
        //GET /v2.10/{object-id}/comments HTTP/1.1
       //Host: https://graph.facebook.com
        for(int i=0; i<lstFeed.size(); i++){
            String urlGetComment = "https://graph.facebook.com/v2.10/"+ lstFeed.get(i).getId()+"/comments?access_token=" + token;
            String rsComment = ResponseUtil.sendGet(urlGetComment);
            System.out.println("Bai viet : " + lstFeed.get(i).getId());
           
        }

    }
}

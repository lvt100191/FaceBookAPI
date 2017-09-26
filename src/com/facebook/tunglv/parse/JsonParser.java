/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facebook.tunglv.parse;

import com.facebook.tunglv.dto.Feed;
import com.facebook.tunglv.dto.Page;
import com.facebook.tunglv.dto.User;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author TUNGLV
 */
public class JsonParser {

    public static void main(String[] args) throws Exception {
        JsonParser parser = new JsonParser();
        System.out.println("JsonParser finish!");
    }

    public static User convertJSONToUser(String jsonStr, String fields) throws Exception {
        User user = new User();
        JSONParser parser = new JSONParser();
        try {
            JSONObject obj = (JSONObject) parser.parse(jsonStr);
            if (fields.contains("id")) {
                user.setId(obj.get("id").toString());
            }
            if (fields.contains("name")) {
                user.setName(obj.get("name").toString());
            }
            if (fields.contains("birthday")) {
                user.setBirthDay(obj.get("birthday").toString());
            }
            if (fields.contains("feed")) {
                List<Feed> listFeed = new ArrayList<Feed>();
                String jsonFeed = obj.get("feed").toString();
                listFeed = convertJSONToListFeed(jsonFeed);
                user.setListFeed(listFeed);
            }

            return user;
        } catch (ParseException pe) {
            throw new Exception("convertJSONToUser error!");
        }
    }

    public static List<Page> convertJSONToListPage(String jsonStr) throws Exception {
        List<Page> pages = new ArrayList<Page>();
        JSONParser parser = new JSONParser();
        try {
            JSONObject obj = (JSONObject) parser.parse(jsonStr);
            JSONArray data = (JSONArray) obj.get("data");
            for (int i = 0; i < data.size(); i++) {
                JSONObject page = (JSONObject) data.get(i);
                Page p = new Page();
                p.setId(page.get("id").toString());
                p.setName(page.get("name").toString());
                p.setCreatTime(page.get("created_time").toString());
                pages.add(p);
            }
            return pages;
        } catch (ParseException pe) {
            throw new Exception("convertJSONToUser error!");
        }
    }

    private static List<Feed> convertJSONToListFeed(String jsonFeed) throws Exception {
        List<Feed> listFeed = new ArrayList<Feed>();
        JSONParser parser = new JSONParser();
        try {
            JSONObject obj = (JSONObject) parser.parse(jsonFeed);
            JSONArray data = (JSONArray) obj.get("data");
            for (int i = 0; i < data.size(); i++) {
                JSONObject feed = (JSONObject) data.get(i);
                Object keyMessage = feed.get("message");
                Object keyStory = feed.get("story");

                Feed f = new Feed();
                f.setId(feed.get("id").toString());
                f.setCreateTime(feed.get("created_time").toString());
                if (keyMessage != null) {
                    f.setType("message");
                    f.setContent(feed.get("message").toString());
                }
                if (keyStory != null) {
                    f.setType("story");
                    f.setContent(feed.get("story").toString());
                }
                listFeed.add(f);

            }
            return listFeed;
        } catch (ParseException pe) {
            throw new Exception("convertJSONToListFeed error!");
        }
    }

}

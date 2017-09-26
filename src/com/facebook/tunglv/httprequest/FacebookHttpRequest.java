/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facebook.tunglv.httprequest;

import com.facebook.tunglv.dto.Feed;
import com.facebook.tunglv.dto.Page;
import com.facebook.tunglv.dto.User;
import com.facebook.tunglv.parse.JsonParser;
import java.util.List;

/**
 *
 * @author TUNGLV
 */
public class FacebookHttpRequest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        System.out.println("FacebookHttpRequest Finish!");

    }

    /**
     * desc: Lay thong tin ca nhan
     *
     * @param token: ma truy cap nguoi dung
     * @param fields: liet ke cac truong can lay thong tin fields="id,name,first_name,birthday,feed";
     * @return
     * @throws Exception
     */
    public static User getUser(String token, String fields) throws Exception {
        String urlMe = "https://graph.facebook.com/me?fields="+fields+"&access_token=" + token;
        String rs = ResponseUtil.sendGet(urlMe);
        User user = JsonParser.convertJSONToUser(rs, fields);
        return user;
    }
    
        /**
     * desc: Lay thong tin mot trang https://www.facebook.com/mshoatoeic
     *
     * @param token: ma truy cap nguoi dung
     * @param fields: liet ke cac truong can lay thong tin fields="id,name,first_name,birthday,feed";
     * @return
     * @throws Exception
     */
    public static String getFanPage(String token, String username) throws Exception {
        String urlMe = "https://graph.facebook.com/"+username+"?access_token=" + token;
        String rs = ResponseUtil.sendGet(urlMe);
        return rs;
    }

    /**
     * desc: Lay danh sach cac trang user like
     *
     * @param token ma truy cap nguoi dung
     * @param userID id cua nguoi dung
     * @return
     * @throws Exception
     */
    public static List<Page> getUserLikes(String token, String userID) throws Exception {
        String urlLikes = "https://graph.facebook.com/v2.10/" + userID + "/likes?access_token=" + token;
        String rs = ResponseUtil.sendGet(urlLikes);
        List<Page> list = JsonParser.convertJSONToListPage(rs);
        return list;
    }

    /**
     * @desc: Dang bai len trang ca nhan
     * @param token ma truy cap nguoi dung
     * @param userID: id cua nguoi dung
     * @param msg: noi dung dang len trang ca nhan
     * @return
     * @throws Exception
     */
    public static String postFeed(String token, String userID, String msg) throws Exception {
        String urlPostFeed = "https://graph.facebook.com/v2.10/" + userID + "/feed?message=" + msg + "&access_token=" + token;
        ResponseUtil responseUtil = new ResponseUtil();
        return responseUtil.sendPost(urlPostFeed);
    }

    /**
     * @desc: Sua bai da dang tren trang ca nhan
     * @param token ma truy cap nguoi dung
     * @param idNode: id cua bai da dang
     * @param updateField: truong update
     * @param valueUpdate: gia tri update
     * @return
     * @throws Exception
     */
    public static String updateFeed(String token, String idNode, String updateField, String valueUpdate) throws Exception {
        String urlEditFeed = "https://graph.facebook.com/v2.10/" + idNode + "?" + updateField + "=" + valueUpdate + "&access_token=" + token;
        ResponseUtil responseUtil = new ResponseUtil();
        return responseUtil.sendPost(urlEditFeed);
    }

    /**
     * desc: Xoa bai da dang tren trang ca nhan
     *
     * @param token ma truy cap nguoi dung
     * @param idNode: id cua bai viet can xoa
     * @return
     * @throws Exception
     */
    public static String deleteFeed(String token, String idNode) throws Exception {
        String urlDeleteFeed = "https://graph.facebook.com/v2.10/" + idNode + "?method=delete&access_token=" + token;
        ResponseUtil responseUtil = new ResponseUtil();
        return responseUtil.sendPost(urlDeleteFeed);
    }
    /**
     * desc: Tìm kiếm: https://developers.facebook.com/docs/graph-api/using-graph-api 
     * @param q : chuoi tim kiem
     * @param type :loai doi tuong tim kiem
     */
    public static String search(String token, String q, String type) throws Exception{
        String urlSearch = "https://graph.facebook.com/search?q="+q+"&access_token=" + token+"&type="+type;
        String rs = ResponseUtil.sendGet(urlSearch);
        return ResponseUtil.sendGet(urlSearch);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facebook.tunglv.usecase;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.types.send.IdMessageRecipient;
import com.restfb.types.send.Message;

/**
 *
 * @author TUNGLV
 */
public class SendMessager {
    public static void main(String[] args) {
        FacebookClient facebookClient = new DefaultFacebookClient(
                "EAACEdEose0cBAPoJjW0ryh75ZCP64n7NvHWoSmBe78zvUTtajLuGdi0ESZBW7DPAZAGbZAsKTFMIvSHnVSOwfMUVbvft6pFsVXyGuLNwLGsawAnYRhDvkhq0W4LZCpzTwcxsIJxukWItaG6s8an5RkifRdK2NuUpU1bxOrKIx24BcvsHvciqNKjqB50qwiF8YJFEB6ifVMwZDZD",
                "d15a6a821df5b91b59f38d79856631f0",
                Version.VERSION_2_6);
        final IdMessageRecipient recipient = new IdMessageRecipient("102860516723111");

        final Message simpleTextMessage = new Message("First Message");
        facebookClient.publish("/me/messages", Message.class,
                Parameter.with("recipient", recipient),
                Parameter.with("message", simpleTextMessage));
    }
}

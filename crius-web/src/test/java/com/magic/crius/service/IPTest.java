package com.magic.crius.service;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.IOException;

/**
 * created by roachjiang ON 2017/9/5
 */
public class IPTest {


    public static void main(String[] args) throws IOException {
        HttpClient httpClient = new HttpClient();
        PostMethod method = new PostMethod();
        method.setURI(new URI("http://ip.taobao.com/service/getIpInfo2.php"));
        method.addParameter("ip","122.53.134.66");
        int s = httpClient.executeMethod(method);
        System.out.println(method.getResponseBodyAsString());
    }
}

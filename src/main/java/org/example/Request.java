package org.example;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

class Request {
    private char[] body;

    public Request(char[] body) {
        if (body == null) {
            throw new IllegalArgumentException("Body cannot be null");
        } else {
            this.body = body;
        }
    }

    public char[] getBody(){
        return body;
    }

    public static ConcurrentHashMap<String,String> getQueryParams(String extras){
        ConcurrentHashMap<String,String> results = new ConcurrentHashMap<>();
        try {
            URI rawExtras = new URI("?" + extras);
            List<NameValuePair> extraList = URLEncodedUtils.parse(rawExtras,"UTF-8");
            for (NameValuePair item: extraList){
                int i = 0;
                String name = item.getName();
                while (results.containsKey(name)){
                    name = item.getName() + ++i;
                }
                results.put(name, item.getValue());
            }
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return results;
    }
}
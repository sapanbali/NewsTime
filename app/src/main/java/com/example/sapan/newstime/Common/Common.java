package com.example.sapan.newstime.Common;

import com.example.sapan.newstime.Interface.NewsApi;
import com.example.sapan.newstime.Remote.RetroFitClient;
import com.example.sapan.newstime.Remote.RetroFitClient;
import com.example.sapan.newstime.Remote.RetroFitClient;

public class Common {
    private static final String BASE_URL = "https://newsapi.org/";
    public static final String  API_KEY= "6e14b881c13f49c6bd9fb310e1d54d8b";

    public static NewsApi getNewsApi()
    {
        return RetroFitClient.getClient(BASE_URL).create(NewsApi.class);
    }

    public static String getAPIUrl(String source,String apiKEY)
    {
        StringBuilder apiUrl =new StringBuilder("https://newsapi.org/v2/top-headlines?sources=");
        return apiUrl.append(source)
                .append("&apiKey=")
                .append(apiKEY)
                .toString();
    }

}

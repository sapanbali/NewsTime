package com.example.sapan.newstime.Interface;

import com.example.sapan.newstime.Common.Common;
import com.example.sapan.newstime.Model.News;
import com.example.sapan.newstime.Model.Website;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface NewsApi {

    @GET("v2/sources?language=en&apiKey="+ Common.API_KEY)
    Call<Website> getSources();

    //https://newsapi.org/v1/articles?source=the-verge&apiKey=6e14b881c13f49c6bd9fb310e1d54d8b
    @GET
    Call<News> getNewestArticles(@Url String Url);
}



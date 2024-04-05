package com.smarteist.FirstMile.data.source.remote;

import com.smarteist.FirstMile.data.models.NewsResponse;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface NewsService {

    @GET("428eee83-3150-4920-98b6-bd4254072c8c")
    Single<NewsResponse> getNews();

}

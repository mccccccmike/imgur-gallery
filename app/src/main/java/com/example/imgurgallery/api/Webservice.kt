package com.example.imgurgallery.api

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface Webservice {
    @Headers("Authorization: Client-ID acd490e4225e438")
    @GET("3/gallery/{section}/{sort}/{window}/{page}")
    fun getGallery(
        @Path("section") section: String = "hot",
        @Path("sort") sort: String = "viral",
        @Path("window") window: String = "day",
        @Path("page") page: Int = 1
    ): Observable<Resource<List<GalleryItem>>>
}
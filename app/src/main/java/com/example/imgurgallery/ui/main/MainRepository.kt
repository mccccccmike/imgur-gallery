package com.example.imgurgallery.ui.main

import com.example.imgurgallery.api.Webservice
import javax.inject.Inject

class MainRepository @Inject constructor(private val webService: Webservice) {
    fun getGallery() = webService.getGallery()
}
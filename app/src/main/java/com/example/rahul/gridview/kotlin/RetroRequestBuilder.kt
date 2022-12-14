package com.example.rahul.gridview.kotlin

import android.provider.ContactsContract.CommonDataKinds.Website.URL
import com.google.gson.GsonBuilder

import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import java.util.concurrent.TimeUnit

open class RetroRequestBuilder {

    internal var restAdapter: Retrofit? = null

   // val url =  "http://elite.interstellar.co.in"
    val url =  "http://elite.rupeeboss.com"
    protected fun build(): Retrofit {
        if (restAdapter == null) {

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val gson = GsonBuilder()
                .serializeNulls()
                .setLenient()
                .create()

            val okHttpClient = okhttp3.OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .addInterceptor(interceptor)

                .build()

            restAdapter = Retrofit.Builder()
                .baseUrl(url)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

        }
        return restAdapter as Retrofit
    }





}
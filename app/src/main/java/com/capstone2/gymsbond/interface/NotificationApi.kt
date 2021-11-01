package com.capstone2.gymsbond.`interface`

import com.android.volley.Response
import com.capstone2.gymsbond.Constants.Constants.Companion.CONTENT_TYPE
import com.capstone2.gymsbond.Constants.Constants.Companion.SERVER_KEY
import com.capstone2.gymsbond.model.PushNotification
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface NotificationApi {

    @Headers("Authorization: key=$SERVER_KEY", "Content-type:$CONTENT_TYPE")
    @POST("fcm/send")
    suspend fun postNotification(@Body notification: PushNotification): Response<ResponseBody>
}
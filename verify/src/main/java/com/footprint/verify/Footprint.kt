package com.footprint.verify

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import okio.IOException

class Footprint(
    private val publicKey: String,
    private val scheme: String,
    private val host: String? = null,
    private val options: FootprintOptions? = null,
    private val userData: FootprintUserData? = null
    ) {
    private val client = OkHttpClient()
    private val customTabsIntent = CustomTabsIntent.Builder().build()
    private val sdkName = "footprint-android 1.0.0"

    private fun getUrl(sdkToken: String): String {
        val baseUrl = "https://id.onefootprint.com"
        var hostVal: String = ""
        if (this.host != null) hostVal = this.host
        val redirectUrl = "${this.scheme}://$hostVal"
        return "$baseUrl/?redirect_url=$redirectUrl#$sdkToken"
    }

    private fun getSdkRequestBody(): String {
        val kind = "verify_v1";
        val requestData = Data(publicKey = this.publicKey, userData = this.userData, options = this.options);
        val requestBody = SdkRequestData(kind =kind, data = requestData)
        val requestBodyString = Json.encodeToString(requestBody);
        return requestBodyString
    }

    private fun getSdkRequest(sdkRequestBody: String): Request {
        val endPoint = "https://api.onefootprint.com/org/sdk_args";
        return Request.Builder()
            .url(endPoint)
            .header("x-fp-client-version", this.sdkName)
            .header("Content-Type", "application/json")
            .post(sdkRequestBody.toRequestBody())
            .build();
    }

    fun startVerification(context: Context) {
        val sdkRequestBody = getSdkRequestBody()
        val sdkRequest = getSdkRequest(sdkRequestBody)

        this.client.newCall(sdkRequest).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) {throw IOException("Unexpected code $response")}
                    val responseBody = response.body!!.string();
                    val responseObject = Json.decodeFromString<SdkTokenResponse>(responseBody)
                    val sdkToken = responseObject.token
                    val url = getUrl(sdkToken)
                    customTabsIntent.launchUrl(context, Uri.parse(url))
                }
            }
        })
    }
}
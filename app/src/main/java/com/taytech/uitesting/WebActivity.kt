package com.taytech.uitesting

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.VisibleForTesting


/**
 * An [Activity] that gets a text string from the user and displays it back when the user
 * clicks on one of the two buttons. The first one shows it in the same activity and the second
 * one opens another activity and displays the message.
 */
class WebViewActivity : Activity() {
    private var mWebView: WebView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)
        mWebView = findViewById<View>(R.id.web_view) as WebView
        mWebView!!.settings.javaScriptEnabled = true
        mWebView!!.loadUrl(urlFromIntent(intent)!!)
        mWebView!!.requestFocus()
        mWebView!!.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                return false
            }
        }
    }

    companion object {
        const val KEY_URL_TO_LOAD = "KEY_URL_TO_LOAD"

        @VisibleForTesting
        val WEB_FORM_URL = "file:///android_asset/web_form.html"
        private fun urlFromIntent(intent: Intent): String? {
            checkNotNull(intent, { "Intent cannot be null!" })
            val url = intent.getStringExtra(KEY_URL_TO_LOAD)
            return if (!TextUtils.isEmpty(url)) url else WEB_FORM_URL
        }
    }
}

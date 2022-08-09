package com.example.webviewtest

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.ConsoleMessage
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.fragment.app.Fragment
import com.example.webviewtest.databinding.FragmentSecondBinding


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

private var _binding: FragmentSecondBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

      _binding = FragmentSecondBinding.inflate(inflater, container, false)
      return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.webview.webChromeClient = object : WebChromeClient() {
            override fun onConsoleMessage(consoleMessage: ConsoleMessage): Boolean {
                Log.e("WebView - Logger", consoleMessage.messageLevel().toString() + " : " + consoleMessage.lineNumber() + " : " + consoleMessage.message())
                return true
            }
        }

        WebView.setWebContentsDebuggingEnabled(true)


        val webSettings: WebSettings = binding.webview.settings
        webSettings.allowContentAccess = true
        webSettings.allowFileAccess = true
        webSettings.databaseEnabled = true
        webSettings.displayZoomControls = true
        webSettings.domStorageEnabled = true
        webSettings.loadsImagesAutomatically = true
        webSettings.mediaPlaybackRequiresUserGesture = true

        webSettings.javaScriptEnabled = true
//        webSettings.useWideViewPort = true
//        webSettings.loadWithOverviewMode = true

        webSettings.cacheMode = WebSettings.LOAD_NO_CACHE

        binding.webview.loadUrl(arguments?.getString("url") ?: "")
    }
override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
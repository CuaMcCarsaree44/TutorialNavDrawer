package com.example.navdrawer.fragment;


import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.navdrawer.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment {
    WebView webViewHome;

    public Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    //TODO 2.1 Connect WebView Datatype with an id
    @Override //WHEN HOME CREATED
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //TODO 2.2 Connect the WebView and load url
        String url = "http://binus.ac.id/"; //What URL you want to visit
        webViewHome = view.findViewById(R.id.home_webview); //Get WebView ID
        webViewHome.loadUrl(url); //Load URL

        //TODO 2.3 Enable JavaScript Support
        webViewHome.getSettings().setJavaScriptEnabled(true); //Enabling JavaScript


        //Loading Screen
        final ProgressDialog dialog = ProgressDialog.show(getActivity() //When the Web is Loading
                                        , "Please Wait. . ."
                                        , "Loading"
                                        , true);

       //To give client an access
        webViewHome.setWebViewClient(new WebViewClient(){
            @Override //Check if the URL working or not
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                Toast.makeText(view.getContext(), "Binus Maya Cacat", Toast.LENGTH_SHORT).show();
            }

            @Override //When you load the page
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                dialog.show();
            }

            @Override //When you finished load the page
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                dialog.hide();
            }
        });
    }
}

package net.hardforkcafe.sinyal.trendbulucu;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.app.NavUtils;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.view.*;



public class aroon extends AppCompatActivity {
    SwipeRefreshLayout mySwipeRefreshLayoutA;


    private CustomWebViewClient webViewClient;
    private String Url = "http://uygulama.hardforkcafe.net/aroon.html";
    ProgressDialog mProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aroon);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mySwipeRefreshLayoutA = (SwipeRefreshLayout)this.findViewById(R.id.swiperefreshA);


        mProgressDialog = new ProgressDialog(this);//ProgressDialog objesi oluşturuyoruz
        mProgressDialog.setMessage("Yükleniyor...");//ProgressDialog Yükleniyor yazısı

        webViewClient = new CustomWebViewClient();//CustomWebViewClient classdan webViewClient objesi oluşturuyoruz

        final WebView  webView = (WebView) findViewById(R.id.aroon);//webview mızı xml anasayfa.xml deki webview bağlıyoruz
        webView.getSettings().setBuiltInZoomControls(true); //zoom yapılmasına izin verir
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(webViewClient); //oluşturduğumuz webViewClient objesini webViewımıza set ediyoruz
        webView.loadUrl(Url);

        mySwipeRefreshLayoutA.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {

                        webView.reload();
                    }
                }
        );


        /*final Button bt6 = (Button) findViewById(R.id.button6);


        bt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(aroon.this, MainActivity.class);
                startActivity(intent);
            }
        });*/


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class MyWebviewClient extends WebViewClient {
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            mySwipeRefreshLayoutA.setRefreshing(false);
        }


    }

    class CustomWebViewClient extends WebViewClient {
        //Alttaki methodların hepsini kullanmak zorunda deilsiniz
        //Hangisi işinize yarıyorsa onu kullanabilirsiniz.
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) { //Sayfa yüklenirken çalışır
            super.onPageStarted(view, url, favicon);

            if (!mProgressDialog.isShowing())//mProgressDialog açık mı kontrol ediliyor
            {
                mProgressDialog.show();//mProgressDialog açık değilse açılıyor yani gösteriliyor ve yükleniyor yazısı çıkıyor
            }

        }

        @Override
        public void onPageFinished(WebView view, String url) {//sayfamız yüklendiğinde çalışıyor.
            super.onPageFinished(view, url);

            if (mProgressDialog.isShowing()) {//mProgressDialog açık mı kontrol
                mProgressDialog.dismiss();//mProgressDialog açıksa kapatılıyor
            }
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // Bu method açılan sayfa içinden başka linklere tıklandığında açılmasına yarıyor.
            //Bu methodu override etmez yada edip içini boş bırakırsanız ilk url den açılan sayfa dışında başka sayfaya geçiş yapamaz

            view.loadUrl(url);//yeni tıklanan url i açıyor
            return true;
        }


    }
}

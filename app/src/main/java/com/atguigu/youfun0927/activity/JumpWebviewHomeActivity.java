package com.atguigu.youfun0927.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.TextView;

import com.atguigu.youfun0927.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class JumpWebviewHomeActivity extends Activity {

    @Bind(R.id.bimg_back_jump)
    ImageButton bimgBackJump;
    @Bind(R.id.tv_title_jump)
    TextView tvTitleJump;
    @Bind(R.id.bimg_share_jump)
    ImageButton bimgShareJump;
    @Bind(R.id.webview_jump)
    WebView webviewJump;

    private WebSettings webSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jump_top_img_home);
        ButterKnife.bind(this);

        setData();

    }


    private void setData() {
        String url = getIntent().getStringExtra("jumpurl");
        final String title = getIntent().getStringExtra("title");
        String jumptitle = getIntent().getStringExtra("jumpname");

        tvTitleJump.setText(jumptitle);
        //设置支持javaScript
        webSettings = webviewJump.getSettings();
        //设置支持javaScript
        webSettings.setJavaScriptEnabled(true);
        //设置双击变大变小
        webSettings.setUseWideViewPort(true);
        //增加缩放按钮
        webSettings.setBuiltInZoomControls(true);
        //设置文字大小
//        webSettings.setTextSize(WebSettings.TextSize.NORMAL);
        webSettings.setTextZoom(100);
        //不让从当前网页跳转到系统的浏览器中
        webviewJump.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                tvTitleJump.setText(title);
            }

        });

        webviewJump.loadUrl(url);

    }


    @OnClick({R.id.bimg_back_jump, R.id.bimg_share_jump})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bimg_back_jump:
                finish();
                break;
            case R.id.bimg_share_jump:
                break;
        }
    }
}

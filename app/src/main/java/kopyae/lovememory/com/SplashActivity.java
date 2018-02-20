package kopyae.lovememory.com;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends BaseActivity {

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_splash;
    }

    @Override
    protected void setUpContents(Bundle savedInstanceState) {
        setupToolbar(false);
        
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                initRun();
            }
        },1500);

    }

    private void initRun() {
        Intent i = new Intent(SplashActivity.this,TestActivity.class);
        startActivity(i);
    }
}

package kopyae.lovememory.com;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener{

    private static final String TAG = MainActivity.class.getSimpleName();

    private static final String TAG_LOVE_MEMORY_FRAGMENT = "love";
    private static final String TAG_MEMORY_FRAGMENT  = "memory";

    private static int NAV_ITEM_INDEX = 0;
    private static String CURRENT_TAG = TAG_LOVE_MEMORY_FRAGMENT;

    @BindView(R.id.appbar)
    AppBarLayout appbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;



    @BindView(R.id.nav_view_left)
    NavigationView leftNavigationView;


    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void setUpContents(Bundle savedInstanceState) {
        setupToolbar(true);



    }

    private void loadFragment(){

    }
    private Fragment getFragment(){

    }

    private void setupNavigationView(NavigationView navigationView){

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}

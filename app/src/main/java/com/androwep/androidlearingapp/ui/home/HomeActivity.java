package com.androwep.androidlearingapp.ui.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.androwep.androidlearingapp.R;
import com.androwep.androidlearingapp.databinding.ActivityHomeBinding;
import com.androwep.androidlearingapp.ui.aboutus.AboutUsFragment;
import com.androwep.androidlearingapp.ui.main.MainFragment;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout mDrawerLayout;
    NavigationView mNabigationView;
    ActionBarDrawerToggle mToogle;
    Toolbar mToolbar;
    FragmentManager manager;
    AboutUsFragment aboutUsFragment;
    MainFragment mainFragment;
    ActivityHomeBinding mBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_home);
        setupNavigationView();
        //fot fragment loder
        aboutUsFragment = new AboutUsFragment();
        mainFragment = new MainFragment();
        manager = getSupportFragmentManager();
        manager.beginTransaction().replace(mBinding.appbarMain.changeLayout.getId(),mainFragment ).commit();

    }

    private void setupNavigationView() {

        mDrawerLayout = mBinding.drawerlayout;
        mNabigationView = mBinding.navigationId;
        mToolbar = mBinding.appbarMain.toolbar;

        mToogle = new ActionBarDrawerToggle(this,mDrawerLayout, mToolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(mToogle);
        mToogle.syncState();

        mNabigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        if(menuItem.getItemId() ==R.id.menu_home){
            Toast.makeText(this, "Home sected", Toast.LENGTH_LONG).show();
            manager.beginTransaction().replace(mBinding.appbarMain.changeLayout.getId(),mainFragment ).commit();
        }
        else if(menuItem.getItemId()==R.id.menu_about_us){
            Toast.makeText(this, "about", Toast.LENGTH_LONG).show();
            manager.beginTransaction().replace(mBinding.appbarMain.changeLayout.getId(),aboutUsFragment ).commit();
        }
        else if(menuItem.getItemId()==R.id.menu_rate_us){
            Toast.makeText(this, "rate", Toast.LENGTH_LONG).show();
            Intent rateIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getBaseContext().getPackageName()));
            startActivity(rateIntent);
        }
        mDrawerLayout.closeDrawers();
        return true;
    }
}

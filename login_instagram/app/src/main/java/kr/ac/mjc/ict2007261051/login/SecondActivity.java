package kr.ac.mjc.ict2007261051.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        ViewPager viewpager = findViewById(R.id.viewpager);
        TabLayout tabLayout = findViewById(R.id.tablayout);

        MainPagerAdapter adapter = new MainPagerAdapter(getSupportFragmentManager());
        viewpager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewpager);

        tabLayout.getTabAt(0).setIcon(getResources().getDrawable(R.drawable.home));
        tabLayout.getTabAt(1).setIcon(getResources().getDrawable(R.drawable.search));
        tabLayout.getTabAt(2).setIcon(getResources().getDrawable(R.drawable.add_box));
        tabLayout.getTabAt(3).setIcon(getResources().getDrawable(R.drawable.favorite));
        tabLayout.getTabAt(4).setIcon(getResources().getDrawable(R.drawable.account_circle));
    }
}

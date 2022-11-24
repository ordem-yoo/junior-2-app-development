package kr.ac.mjc.ict2007261051.login;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MainPagerAdapter extends FragmentPagerAdapter {

    public MainPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            return new HomeFragment();
        }
        else if(position == 1){
            return new SearchFragment();
        }else if(position == 2){
            return new AddFragment();

        }else if(position == 3){
            return new FavoriteFragment();
        }else{
            return new AccountFragment();
        }
    }

    @Override
    public int getCount() {
        return 5;
    }
}

package android.eservices.movies.presentation.moviedisplay;

import android.eservices.movies.R;
import android.eservices.movies.presentation.moviedisplay.list.fragment.ListFragment;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class MovieDisplayActivity extends AppCompatActivity {

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViewPagerAndTabs();
    }


    private void setupViewPagerAndTabs() {
        viewPager = findViewById(R.id.tab_viewpager);

        final ListFragment listFragment = ListFragment.newInstance();

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                    return listFragment;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                    return ListFragment.TAB_NAME;
            }

            @Override
            public int getCount() {
                return 1;
            }
        });
    }

}

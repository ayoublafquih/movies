package android.eservices.movies.presentation.moviedisplay;

import android.eservices.movies.R;
import android.eservices.movies.presentation.moviedisplay.favorite.fragment.FavoriteFragment;
import android.eservices.movies.presentation.moviedisplay.list.fragment.ListFragment;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class MovieDisplayActivity extends AppCompatActivity {

    private ViewPager viewPager;
    public static final String MOST_POPULAR = "popular";
    public static final String TOP_RATED = "top_rated";
    private String sortBy = MOST_POPULAR;
    ListFragment listFragment;
    FavoriteFragment fragmentFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViewPagerAndTabs(sortBy);
    }


    private void setupViewPagerAndTabs(String sortBy) {
        viewPager = findViewById(R.id.tab_viewpager);

        listFragment = ListFragment.newInstance(sortBy);
        fragmentFavorite = FavoriteFragment.newInstance();

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                if (position == 0) {
                    return listFragment;
                }
                return fragmentFavorite;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                if (position == 0) {
                    return ListFragment.SORT_BY;
                }
                return FavoriteFragment.TAB_NAME;
            }

            @Override
            public int getCount() {
                return 2;
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_layout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sort_by_popular:
                sortBy = MOST_POPULAR;
                item.setCheckable(true);
                item.setChecked(true);
                setupViewPagerAndTabs(MOST_POPULAR);
                break;
            case R.id.sort_by_top_rated:
                sortBy = TOP_RATED;
                item.setCheckable(true);
                item.setChecked(true);
                setupViewPagerAndTabs(TOP_RATED);
                break;
            case R.id.sort_by_favorite:
                listFragment.changeLayout();
                fragmentFavorite.changeLayout();
                item.setChecked(true);
                item.setCheckable(true);
                break;
            default:
                ;
        }

        return super.onOptionsItemSelected(item);
    }

}




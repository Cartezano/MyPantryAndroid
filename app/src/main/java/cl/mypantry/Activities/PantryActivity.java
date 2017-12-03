package cl.mypantry.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import cl.mypantry.Fragments.BarcodeScanFragment;
import cl.mypantry.Fragments.CartFragment;
import cl.mypantry.Fragments.HomeFragment;
import cl.mypantry.Fragments.PantryFragment;
import cl.mypantry.Libraries.UtilAndroid;
import cl.mypantry.Libraries.UtilPreference;
import cl.mypantry.R;

public class PantryActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantry);
        setToolbarPantry();

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);

        setFragmentByDefault();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                boolean fragmentTransaction = false;
                Fragment fragment = null;
                Bundle bundle = new Bundle();

                switch (item.getItemId()) {
                    case R.id.menu_home:
                        fragment = new HomeFragment();
                        fragmentTransaction = true;
                        break;
                    case R.id.menu_pantry:
                        fragment = new PantryFragment();
                        fragmentTransaction = true;
                        break;
                    case R.id.menu_add_product:
                        fragment = new BarcodeScanFragment();
                        bundle.putBoolean("option", true);
                        fragment.setArguments(bundle);
                        fragmentTransaction = true;
                        break;
                    case R.id.menu_remove_product:
                        fragment = new BarcodeScanFragment();
                        bundle.putBoolean("option", false);
                        fragment.setArguments(bundle);
                        fragmentTransaction = true;
                        break;
                    case R.id.menu_cart:
                        fragment = new CartFragment();
                        fragmentTransaction = true;
                        break;
                    case R.id.menu_logout:
                        logOut();
                        break;
                }

                if (fragmentTransaction) {
                    changeFragment(fragment, item);
                    drawerLayout.closeDrawers();
                }

                return fragmentTransaction;
            }
        });
    }

    private void setToolbarPantry() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_pantry);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorIcons));
        toolbar.setSubtitleTextColor(getResources().getColor(R.color.colorIcons));
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setFragmentByDefault() {
        changeFragment(new HomeFragment(), navigationView.getMenu().getItem(0));
    }

    private void changeFragment(Fragment fragment, MenuItem item) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();
        item.setChecked(true);
        getSupportActionBar().setTitle(item.getTitle());
    }

    private void logOut() {
        preferences = getSharedPreferences("MyPantry", Context.MODE_PRIVATE);
        UtilPreference.removeActivePreferences(preferences);

        Intent intent = UtilAndroid.redirect(PantryActivity.this, MainActivity.class);
        startActivity(intent);
        finish();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

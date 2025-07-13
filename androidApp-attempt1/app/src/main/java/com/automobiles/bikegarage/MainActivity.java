package com.automobiles.bikegarage;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.automobiles.bikegarage.fragments.ServiceFragment;
import com.automobiles.bikegarage.fragments.SettingsFragment; // Assuming you have this fragment
import com.automobiles.bikegarage.fragments.AboutFragment; // Assuming you have this fragment
import com.automobiles.bikegarage.fragments.ProfileFragment; // Assuming you have this fragment
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.appbar.AppBarLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this::onNavigationItemSelected);

        // Load the ServiceFragment by default
        loadFragment(new ServiceFragment());
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
        drawerLayout.closeDrawers(); // Close the navigation drawer
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_profile:
                loadFragment(new ProfileFragment());
                break;
            case R.id.nav_stats:
                loadFragment(new StatsFragment()); // Assuming you have this fragment
                break;
            case R.id.nav_settings:
                loadFragment(new SettingsFragment());
                break;
            case R.id.nav_about:
                loadFragment(new AboutFragment());
                break;
            default:
                loadFragment(new ServiceFragment());
                break;
        }
        return true;
    }
}

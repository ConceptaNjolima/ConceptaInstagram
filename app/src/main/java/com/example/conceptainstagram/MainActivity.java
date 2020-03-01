package com.example.conceptainstagram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.conceptainstagram.fragments.ComposeFragment;
import com.example.conceptainstagram.fragments.PostsFragment;
import com.example.conceptainstagram.fragments.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;

public class MainActivity<photoFile> extends AppCompatActivity {
    public static final String TAG="MainActivity";
    final FragmentManager fragmentManager=getSupportFragmentManager();
    private BottomNavigationView bottomNavigationView;
    public String photoFileName = "photo.jpg";
    File photoFile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView=findViewById(R.id.bottom_navigation);

        //queryPosts();


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment;
                switch (menuItem.getItemId()) {
                    case R.id.action_home:
                        fragment= new PostsFragment();
                        break;
                    case R.id.action_compose:
                        fragment= new ComposeFragment();
                        break;
                    case R.id.action_profile:
                    default:
                        fragment= new ProfileFragment();
                        break;
                }
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
                return true;
            }


        });
        bottomNavigationView.setSelectedItemId(R.id.action_home);
        

    }


}

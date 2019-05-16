package com.example.keepsake;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class NavigationActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, ImageAdapter.LoadImage{

    private static final String Tag = "tag" ;
    private DrawerLayout drawerLayout;
    private TextView useremail;
    private FirebaseAuth firebaseAuth;
    SignIn signIn = new SignIn();
    FirebaseStorage firebaseStorage;
    private StorageReference storageReference;
    int arrayPosition;
    private Uri imageUri;
    char parentFragment;
    int PICK_IMAGE_REQUEST = 1;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference("photos");

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        useremail = (TextView) navigationView.getHeaderView(0).findViewById(R.id.userEmail);
        useremail.setText(user.getEmail());

        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_intro:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new IntroFragment()).addToBackStack(null).commit();
                break;

            case R.id.nav_arrival:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ArrivalFragment()).addToBackStack(null).commit();
                break;

            case R.id.nav_theDay:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new TheDayFragment()).addToBackStack(null).commit();
                break;

            case R.id.nav_grow:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new GrowFragment()).addToBackStack(null).commit();
                break;

            case R.id.nav_monthlypic:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MonthlyPicFragment()).addToBackStack(null).commit();
                break;

            case R.id.nav_myfirsts:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MyFirstsFragment()).addToBackStack(null).commit();
                break;

            case R.id.nav_familyPic:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new FamilyPicFragment()).addToBackStack(null).commit();
                break;

            case R.id.nav_logout:
                firebaseAuth.signOut();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();

        }

        drawerLayout.closeDrawer(GravityCompat.START);


        return true;
    }

    @Override
    public void loadImageFunction(int position, char parentFragment) {
        arrayPosition = position;
        this.parentFragment = parentFragment;
        openFileChooser();

    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(Tag, "entered activity result");


        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {

            imageUri = data.getData();


            String key = user.getEmail()+ parentFragment+ "_" + String.valueOf(arrayPosition);

            StorageReference filepath = storageReference.child(key);
            filepath.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(NavigationActivity.this, getString(R.string.upload_succ), Toast.LENGTH_LONG).show();

                   if (parentFragment=='m'){
                       Fragment fr = null;
                       getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                               new MonthlyPicFragment()).addToBackStack(null).commit();

                    }else{
                       getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                               new FamilyPicFragment()).addToBackStack(null).commit();
                    }

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(NavigationActivity.this, getString(R.string.upload_fail), Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}

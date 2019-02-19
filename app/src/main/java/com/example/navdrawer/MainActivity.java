package com.example.navdrawer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.navdrawer.activity.Login;
import com.example.navdrawer.fragment.*;

import com.example.navdrawer.fragment.Home;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //TODO 4.5 Declare a variable for name
    String Name;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        //TODO Kill FloatingActionButtons
        fab.setVisibility(View.GONE);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //TODO 4.6
        Login.SessionPreferences pref = new Login.SessionPreferences(MainActivity.this);
        Name = pref.getName();
        if(Name.equals(""))
        {
            startActivity(new Intent(MainActivity.this, Login.class));
            finish();
        }else {
            // TODO 4.8 If This is the first time you open this app
            if(savedInstanceState == null)
            {
                Home fragment = new Home();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .commit();
            }
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }else if(id == R.id.action_logout)  //TODO 4.9 Logout Button
        {
            Login.SessionPreferences mSession = new Login.SessionPreferences(MainActivity.this);
                    mSession.logOut();
            startActivity(new Intent(MainActivity.this, Login.class));
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;

        //TODO 1.1 Declare Fragments
        if (id == R.id.nav_home) {
            fragment = new Home();
        } else if (id == R.id.nav_platform) {
            fragment = new Platform();
        } else if (id == R.id.nav_news) {
            fragment = new News();
        } else if (id == R.id.nav_contact) {
            call();
        } else if (id == R.id.nav_email) {
            //TODO 1.2 Declare Email Function
            email();
        }

        if(fragment!= null) //Commit Change Fragment
        {
            getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.fragment_container, fragment)
                                        .commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void call()
    {
        Intent call = new Intent(Intent.ACTION_DIAL);
        call.setData(Uri.parse("tel:081210315286"));
        startActivity(call);
    }

    //TODO 1.3 Function Declaration
    private void email()
    {
        Intent send = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", "yosuakristianto754@gmail.com", null
        ));

        send.putExtra(Intent.EXTRA_SUBJECT, "This is Title");
        send.putExtra(Intent.EXTRA_TEXT, "This is Content");

        if(send.resolveActivity(getPackageManager()) != null)
            startActivity(Intent.createChooser(send, "Open With..."));
        else Toast.makeText(this, "There's no app for this type of operation"
                , Toast.LENGTH_SHORT).show();
    }
}

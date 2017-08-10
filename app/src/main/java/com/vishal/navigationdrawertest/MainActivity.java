package com.vishal.navigationdrawertest;

import android.content.res.Configuration;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.vishal.navigationdrawertest.fragment.FragmentOne;
import com.vishal.navigationdrawertest.listener.DrawerItemClickListener;

public class MainActivity extends AppCompatActivity implements DrawerItemClickListener.CallbackListener, FragmentOne.OnFragmentInteractionListener {

    ListView listView;
    String[] listItems;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);
        listItems = getResources().getStringArray(R.array.items);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.drawer_listview_item, listItems);
        listView.setAdapter(adapter);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,  R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);


        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//            getSupportActionBar().setHomeButtonEnabled(true);
        }

        DrawerItemClickListener drawerListener = new DrawerItemClickListener(this);
        listView.setOnItemClickListener(drawerListener);
        listView.setOnScrollListener(drawerListener);

        listView.bringToFront();
        drawerLayout.requestLayout();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Log.e("MainActivity"," onOptionsItemSelected - called");
        if(actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onItemClicked(AdapterView<?> adapterView, View view, int i, long l) {

        Toast.makeText(view.getContext(), ((TextView)view).getText().toString() + " selected", Toast.LENGTH_SHORT).show();

        Fragment fragment = FragmentOne.newInstance(((TextView)view).getText().toString(), null);
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.content_frame, fragment).commit();

        if(drawerLayout.isShown()) {
            drawerLayout.closeDrawer(Gravity.LEFT);
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}

package com.vishal.navigationdrawertest.listener;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Vishal Aroor on 08-Aug-17.
 */

public class DrawerItemClickListener implements AdapterView.OnItemClickListener, AbsListView.OnScrollListener {

    CallbackListener listener;
    private DrawerItemClickListener() {}

    public DrawerItemClickListener(DrawerItemClickListener.CallbackListener listener) {
        this.listener = listener;

    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        listener.onItemClicked(adapterView, view, i, l);
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {

        //i is the scroll state
        Log.e("DrawerItemClickListener"," onScrollStateChanged - called");
        if(i == SCROLL_STATE_IDLE) {
            absListView.bringToFront();
//            drawerLayout.requestLayout();
        }
    }

    @Override
    public void onScroll(AbsListView absListView, int i, int i1, int i2) {
        Log.e("DrawerItemClickListener"," onScroll - called");
    }

    public interface CallbackListener {

        public void onItemClicked(AdapterView<?> adapterView, View view, int i, long l);
    }

}

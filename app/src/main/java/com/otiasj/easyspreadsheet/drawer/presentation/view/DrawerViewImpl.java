package com.otiasj.easyspreadsheet.drawer.presentation.view;

import android.content.DialogInterface;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.otiasj.easyspreadsheet.R;
import com.otiasj.easyspreadsheet.drawer.presentation.presenter.DrawerPresenter;
import com.otiasj.easyspreadsheet.drawer.presentation.presenter.DrawerPresenterImpl;

/**
 * Created by julien on 1/10/2017.
 * All rights reserved
 */

public class DrawerViewImpl implements DrawerView, NavigationView.OnNavigationItemSelectedListener  {

    private AppCompatActivity activity;
    private DrawerPresenter drawerPresenter;

    @Override
    public void onCreate(final AppCompatActivity activity) {
        this.activity = activity;
        drawerPresenter = new DrawerPresenterImpl(activity, this);
        Toolbar toolbar = (Toolbar) activity.findViewById(R.id.toolbar);
        activity.setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) activity.findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                activity, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) activity.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_save) {
        } else if (id == R.id.nav_save) {
            drawerPresenter.saveClicked();
        } else if (id == R.id.nav_load) {
            drawerPresenter.loadClicked();
        } else if (id == R.id.nav_share) {
            drawerPresenter.shareClicked();
        } else if (id == R.id.nav_send) {
            drawerPresenter.sendClicked();
        }

        //close the drawer
        DrawerLayout drawer = (DrawerLayout) activity.findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void askFileName() {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(R.string.set_file_name);

        // Set up the input
        final EditText input = new EditText(activity);
        builder.setView(input);
        // Set up the buttons
        builder.setPositiveButton(R.string.OK, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String deviceName = input.getText().toString().trim();
                drawerPresenter.filedFileName(deviceName);
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(activity, R.string.cancelled, Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });
        builder.show();
    }

    @Override
    public void askChooseFile() {
        //FIXME start activity for result to choose a file
    }

    @Override
    public void displayError(final int stringId) {
        Toast.makeText(activity, stringId, Toast.LENGTH_SHORT).show();
    }

}

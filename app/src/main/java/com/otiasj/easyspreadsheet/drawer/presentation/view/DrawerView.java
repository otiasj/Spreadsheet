package com.otiasj.easyspreadsheet.drawer.presentation.view;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by julien on 1/10/2017.
 * All rights reserved
 */

public interface DrawerView {

    void onCreate(AppCompatActivity activity);

    void onStart();

    void onStop();

    void askFileName();

    void askChooseFile();

    void displayError(int stringId);
}

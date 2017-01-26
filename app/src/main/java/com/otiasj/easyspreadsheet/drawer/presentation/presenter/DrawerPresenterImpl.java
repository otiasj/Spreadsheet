package com.otiasj.easyspreadsheet.drawer.presentation.presenter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Toast;

import com.otiasj.easyspreadsheet.R;
import com.otiasj.easyspreadsheet.drawer.presentation.view.DrawerView;
import com.otiasj.easyspreadsheet.login.SignInActivity;
import com.otiasj.easyspreadsheet.utils.StringUtils;

/**
 * Created by julien on 1/10/2017.
 * All rights reserved
 */

public class DrawerPresenterImpl implements DrawerPresenter {

    private final AppCompatActivity activity;
    private final DrawerView view;
    private String currentFilename;

    public DrawerPresenterImpl(final AppCompatActivity activity, final DrawerView view) {
        this.activity = activity;
        this.view = view;
    }

    @Override
    public void saveClicked() {
        if (signedIn()) {
            if (TextUtils.isEmpty(currentFilename)) {
                view.askFileName();
            } else {
                saveFile(currentFilename);
            }
        } else {
            signIn();
        }
    }

    @Override
    public void loadClicked() {
        if (signedIn()) {
            view.askChooseFile();
        } else {
            signIn();
        }
    }

    /**
     * The user as entered a filename
     * @param filename
     */
    @Override
    public void filedFileName(final String filename) {
        if (StringUtils.isAlphanumeric(filename)) {
            currentFilename = filename;
            saveFile(currentFilename);
        } else {
            view.displayError(R.string.invalid_name);
            view.askFileName();
        }
    }

    /**
     * Save the current spreadsheet to a file
     * @param currentFilename
     */
    private void saveFile(final String currentFilename) {
        //FIXME: trigger a save, fileSelector?
    }

    @Override
    public void shareClicked() {
        //FIXME trigger a share file
    }

    @Override
    public void sendClicked() {
        //FIXME trigger a send file
    }

    /**
     * check if the user is signed in,
     * @return true if the user is signed in, false otherwise
     */
    private boolean signedIn() {
        return SignInActivity.isSignedIn();
    }

    /**
     * trigger the sign in activity
     */
    private void signIn() {
        Intent signInActivityIntent = new Intent(activity, SignInActivity.class);
        activity.startActivity(signInActivityIntent);
    }


}

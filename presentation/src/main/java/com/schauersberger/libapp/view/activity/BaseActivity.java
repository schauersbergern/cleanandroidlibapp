package com.schauersberger.libapp.view.activity;
import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.schauersberger.libapp.AndroidApplication;
import com.schauersberger.libapp.internal.di.components.ApplicationComponent;
import com.schauersberger.libapp.internal.di.modules.ActivityModule;
import com.schauersberger.libapp.navigation.Navigator;

import javax.inject.Inject;

public class BaseActivity extends Activity {

    @Inject
    Navigator navigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getApplicationComponent().inject(this);
    }

    /**
     * Adds a {@link Fragment} to this activity's layout.
     *
     * @param containerViewId The container view to where add the fragment.
     * @param fragment The fragment to be added.
     */
    protected void addFragment(int containerViewId, Fragment fragment) {
        final FragmentTransaction fragmentTransaction = this.getFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.commit();
    }

    /**
     * Get the Main Application component for dependency injection.
     *
     * @return {@link ApplicationComponent}
     */
    protected ApplicationComponent getApplicationComponent() {

        Application app = getApplication();

        AndroidApplication aapp = (AndroidApplication) app;

        ApplicationComponent hallo = aapp.getApplicationComponent();

        return hallo;
    }

    /**
     * Get an Activity module for dependency injection.
     *
     * @return {@link ActivityModule}
     */
    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

}

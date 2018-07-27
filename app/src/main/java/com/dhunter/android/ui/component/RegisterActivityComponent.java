package com.dhunter.android.ui.component;

import com.dhunter.android.ui.activities.RegisterActivity;
import com.dhunter.android.ui.module.RegisterPresenterModule;
import com.dhunter.common.AppComponent;
import com.dhunter.common.PerActivity;

import dagger.Component;

/**
 * Created by dhunter on 2018/7/10.
 */

@PerActivity
@Component(dependencies = AppComponent.class , modules = RegisterPresenterModule.class)
public interface RegisterActivityComponent {
    void inject(RegisterActivity activity);
}
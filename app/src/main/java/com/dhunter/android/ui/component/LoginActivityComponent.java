package com.dhunter.android.ui.component;

import com.dhunter.android.ui.activities.LoginActivity;
import com.dhunter.android.ui.module.LoginPresenterModule;
import com.dhunter.common.AppComponent;
import com.dhunter.common.PerActivity;

import dagger.Component;

/**
 * Created by dhunter on 2018/6/27.
 */

@PerActivity
@Component(dependencies = AppComponent.class, modules = LoginPresenterModule.class)
public interface LoginActivityComponent {
    void inject(LoginActivity activity);
}

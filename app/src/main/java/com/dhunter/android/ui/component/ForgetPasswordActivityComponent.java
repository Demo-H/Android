package com.dhunter.android.ui.component;


import com.dhunter.android.ui.activities.ForgetPasswordActivity;
import com.dhunter.android.ui.module.ForgetPasswordPresenterModule;
import com.dhunter.common.AppComponent;
import com.dhunter.common.PerActivity;

import dagger.Component;

/**
 * Created by dhunter on 2018/7/6.
 */

@PerActivity
@Component(dependencies = AppComponent.class , modules = ForgetPasswordPresenterModule.class)
public interface ForgetPasswordActivityComponent {
    void inject(ForgetPasswordActivity activity);
}
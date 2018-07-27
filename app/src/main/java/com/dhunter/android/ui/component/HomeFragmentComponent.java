package com.dhunter.android.ui.component;

import com.dhunter.android.ui.fragment.HomeFragment;
import com.dhunter.android.ui.module.HomePresenterModule;
import com.dhunter.common.AppComponent;
import com.dhunter.common.PerFragment;

import dagger.Component;

/**
 * Created by dhunter on 2018/6/27.
 */

@PerFragment
@Component(dependencies = AppComponent.class , modules = HomePresenterModule.class)
public interface HomeFragmentComponent {
    void inject(HomeFragment fragment);
}
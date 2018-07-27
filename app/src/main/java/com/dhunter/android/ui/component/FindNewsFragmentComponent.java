package com.dhunter.android.ui.component;

import com.dhunter.android.ui.fragment.FindNewsFragment;
import com.dhunter.android.ui.module.FindNewsPresenterModule;
import com.dhunter.common.AppComponent;
import com.dhunter.common.PerFragment;

import dagger.Component;

/**
 * Created by dhunter on 2018/6/27.
 */

@PerFragment
@Component(dependencies = AppComponent.class , modules = FindNewsPresenterModule.class)
public interface FindNewsFragmentComponent {
    void inject(FindNewsFragment fragment);
}
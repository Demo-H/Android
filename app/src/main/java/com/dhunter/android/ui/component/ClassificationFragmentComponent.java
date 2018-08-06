package com.dhunter.android.ui.component;

import com.dhunter.android.ui.fragment.ClassificationFragment;
import com.dhunter.android.ui.module.ClassificationPresenterModule;
import com.dhunter.common.AppComponent;
import com.dhunter.common.PerFragment;

import dagger.Component;

/**
 * Created by dhunter on 2018/8/3.
 */

@PerFragment
@Component(dependencies = AppComponent.class , modules = ClassificationPresenterModule.class)
public interface ClassificationFragmentComponent {
    void inject(ClassificationFragment fragment);
}

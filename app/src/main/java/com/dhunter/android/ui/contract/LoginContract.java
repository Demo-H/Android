package com.dhunter.android.ui.contract;

import com.dhunter.android.entity.BaseResponse;
import com.dhunter.android.entity.login.LoginRequest;

/**
 * Created by dhunter on 2018/6/25.
 */

public class LoginContract {

    public interface View {
        void setLoginResult(BaseResponse response);
        void hideDialog();
    }

    public interface Presenter {
        void login(LoginRequest request);
    }
}

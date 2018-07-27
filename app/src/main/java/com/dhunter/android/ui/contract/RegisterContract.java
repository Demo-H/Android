package com.dhunter.android.ui.contract;


import com.dhunter.android.entity.login.LoginRequest;

/**
 * Created by dhunter on 2018/7/10.
 */

public class RegisterContract {

    public interface View {
        void toast(String msg);
        void hideDialog();
        void setSmsCodeResult();
        void setSMSCodeError();
        void setRegisterResult();
        void setRegisterError();
    }

    public interface Presenter {
        void getSMSCode(String phone);
        void startRegister(LoginRequest request);
    }
}

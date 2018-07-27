package com.dhunter.android.ui.contract;


import com.dhunter.android.entity.login.LoginRequest;

/**
 * Created by dhunter on 2018/7/6.
 */

public class ForgetPasswordContract {
    public interface View {
        void setSMSCodeSuccess();
        void setSMSCodeError();
        void setResetPwdSuccess();
        void hideDialog();
        void toast(String msg);
    }

    public interface Presenter {
        void getSMSCode(String phoneNum);
        void forgetResetPwd(LoginRequest request);
    }
}

package com.dhunter.common.network;

import android.widget.Toast;

import com.dhunter.common.GlobalAppComponent;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by dhunter on 2018/6/25.
 */

public abstract class ErrorDisposableObserver<T> extends DisposableObserver<T> {
    @Override
    public void onError(Throwable e) {
        //此处可按状态码解析或error类型，进行分别处理其他error, getResponseThrowable(e)
        NetWorkCodeException.ResponseThrowable responseThrowable = NetWorkCodeException.getResponseThrowable(e);
        Toast.makeText(GlobalAppComponent.getAppComponent().getContext(), "Code = " + responseThrowable.code + ", " + responseThrowable.message, Toast.LENGTH_SHORT).show();

    }
}
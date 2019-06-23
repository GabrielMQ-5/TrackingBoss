package com.geca.trackingboss.userinterface.login;

import com.geca.trackingboss.model.login.LoginRequest;
import com.geca.trackingboss.model.login.LoginResponse;
import com.geca.trackingboss.userinterface.BasePresenter;
import com.geca.trackingboss.userinterface.BaseView;

public interface LoginContract {
    interface View extends BaseView<Presenter> {

        void setSharedPreferences(LoginResponse response);

    }

    interface Presenter extends BasePresenter {
        void login(LoginRequest request);
    }
}

package com.geca.trackingboss.userinterface.register;

import com.geca.trackingboss.model.register.RegisterRequest;
import com.geca.trackingboss.userinterface.BasePresenter;
import com.geca.trackingboss.userinterface.BaseView;

public interface RegisterContract {
    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter {
        void registerBoss(RegisterRequest request);
    }
}

package com.geca.trackingboss.userinterface.relative;

import com.geca.trackingboss.model.register.RegisterRequest;
import com.geca.trackingboss.userinterface.BasePresenter;
import com.geca.trackingboss.userinterface.BaseView;

public interface AddEditRelativeContract {
    interface View extends BaseView<Presenter> {
        void firebaseRegister();
    }

    interface Presenter extends BasePresenter {
        void saveRelative(RegisterRequest request, String bossId, String token);
    }
}

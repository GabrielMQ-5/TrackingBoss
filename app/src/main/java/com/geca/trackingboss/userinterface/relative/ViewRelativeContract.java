package com.geca.trackingboss.userinterface.relative;

import com.geca.trackingboss.userinterface.BasePresenter;
import com.geca.trackingboss.userinterface.BaseView;

public interface ViewRelativeContract {
    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter {
        void deleteRelative(int relativeId, String token);
    }
}

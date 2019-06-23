package com.geca.trackingboss.userinterface.main;

import com.geca.trackingboss.model.relative.ListRelativeResponse;
import com.geca.trackingboss.userinterface.BasePresenter;
import com.geca.trackingboss.userinterface.BaseView;

import java.util.List;

public interface MainContract {
    interface View extends BaseView<Presenter> {
        void getRelatives();

        void getAllRelatives();

        void setRelatives(ListRelativeResponse relatives);
    }

    interface Presenter extends BasePresenter {
        void listRelatives(String bossId, Integer isOnline, String token);
    }
}

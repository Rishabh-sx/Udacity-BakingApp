package com.rishabh.bakingapp.onboard.splash;


import com.rishabh.bakingapp.base.BasePresenter;

/**
 * Created by appinventiv on 27/3/18.
 */

public class SplashPresenter extends BasePresenter<SplashView> {



    SplashPresenter(SplashView view) {
        super(view);
    }

    @Override
    protected void setModel() {

    }

    @Override
    protected void destroy() {
    }

    @Override
    protected void initView() {
    }

    public void splashTimeOut() {
        if(getView()!=null)
            getView().steerToHome();
    }
}

package com.rishabh.bakingapp.onboard.splash;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rishabh.bakingapp.R;
import com.rishabh.bakingapp.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class SplashFragment extends BaseFragment implements SplashView {


    private Handler handler;
    private Runnable runnable;
    private SplashPresenter mPresenter;
    private ISplashHost host;

    public SplashFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getActivity() instanceof ISplashHost) {
            host = (ISplashHost) getActivity();
        } else throw new IllegalStateException("Host must Implement ISplashHost");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = new SplashPresenter(this);
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                mPresenter.splashTimeOut();
            }
        };
        handler.postDelayed(runnable, 1500);
    }


    @Override
    public void steerToHome() {
        if (host != null)
            host.steerToHomeFromSplash();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        handler.removeCallbacks(runnable);
        mPresenter.detachView();
        mPresenter = null;
    }

    public SplashFragment getInstance() {

        return new SplashFragment();

    }

    public interface ISplashHost {

        void steerToHomeFromSplash();

    }


}

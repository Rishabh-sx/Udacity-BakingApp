package com.rishabh.bakingapp.recipe.stepdescription;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.exoplayer2.ui.PlayerView;
import com.rishabh.bakingapp.R;
import com.rishabh.bakingapp.base.BaseFragment;
import com.rishabh.bakingapp.pojo.Recipe.Step;
import com.rishabh.bakingapp.utils.PlayerManager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by #Rishabh Saxena
 * rishabhsx@gmail.com.
 */

public class StepsDescriptionFragment extends BaseFragment implements StepsDescriptionView, PlayerManager.PlayerCallbacks {


    private static final String STEPS = "steps";
    private static final String POSITION = "position";
    @BindView(R.id.video_view)
    PlayerView videoView;
    Unbinder unbinder;
    @BindView(R.id.tv_short_desc)
    TextView tvShortDesc;
    @BindView(R.id.tv_long_desc)
    TextView tvLongDesc;
    private StepsDescriptionPresenter presenter;
    private ArrayList<Step> stepArrayList;
    private PlayerManager player;
    private Bundle savedInstanceBundle;
    private boolean playerStopped;
    private int position=-1;

    public StepsDescriptionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        savedInstanceBundle = savedInstanceState;
        if (savedInstanceState != null) {
            position = savedInstanceBundle.getInt("position");
        }
        View view = inflater.inflate(R.layout.fragment_steps_description, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e("onViewCreated: ", "call");
        presenter = new StepsDescriptionPresenter(this);
        presenter.init();

    }


    public StepsDescriptionFragment getInstance(ArrayList<Step> stepArrayList, int position) {
        Bundle bundle;
        StepsDescriptionFragment stepsDescriptionFragment = new StepsDescriptionFragment();
        if (stepArrayList != null) {
            bundle = new Bundle();
            bundle.putInt(POSITION, position);
            bundle.putParcelableArrayList(STEPS, stepArrayList);
            stepsDescriptionFragment.setArguments(bundle);
        }
        return stepsDescriptionFragment;
    }

    @Override
    public void getStep() {
        if (getArguments() != null
                && getArguments().containsKey(STEPS)
                && getArguments().getParcelableArrayList(STEPS) != null) {

            stepArrayList = getArguments().getParcelableArrayList(STEPS);
            if (position == -1)
                position = getArguments().getInt(POSITION);

            if (stepArrayList != null)
                presenter.stepFetched(position);

        }
    }


    @Override
    public void setUpViews(int position) {
        player = new PlayerManager(getActivity(), this);
        player.init(getActivity(), videoView, stepArrayList.get(position).getVideoURL(), savedInstanceBundle);
        tvShortDesc.setText(stepArrayList.get(position).getShortDescription());
        tvLongDesc.setText(stepArrayList.get(position).getDescription());
        savedInstanceBundle = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onVideoPlaybackCompleted() {

    }

    @Override
    public void onPlayerErrorLoading() {

    }

    public void loadRecipe(int position) {
        if (player != null) {
            player.release();
        }
        this.position = position;
        presenter.loadStep(position);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.e("onSaveInstanceState: ", "Called");
        super.onSaveInstanceState(outState);
        Bundle bundle = player.getCurrentState();
        outState.putLong("player_position", bundle.getLong("player_position"));
        outState.putBoolean("player_state", bundle.getBoolean("player_state"));
        outState.putInt("position", position);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("onStop: ", "Called");
        if (player != null) {
            player.pause();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (player != null) {
            player.release();
        }
    }
}



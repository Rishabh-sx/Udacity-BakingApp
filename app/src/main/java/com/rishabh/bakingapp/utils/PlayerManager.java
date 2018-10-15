/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.rishabh.bakingapp.utils;

import android.app.Application;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.C.ContentType;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.dash.DashMediaSource;
import com.google.android.exoplayer2.source.dash.DefaultDashChunkSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.rishabh.bakingapp.BakingApp;
import com.rishabh.bakingapp.R;

import static com.google.android.exoplayer2.Player.STATE_ENDED;
import static com.google.android.exoplayer2.Player.STATE_READY;

/**
 * Manages the {@link ExoPlayer}, the IMA plugin and all video playback.
 */
public class PlayerManager implements Player.EventListener /*implements AdsMediaSource.MediaSourceFactory*/ {

    // private final ImaAdsLoader adsLoader;
    private final DataSource.Factory manifestDataSourceFactory;
    private final DataSource.Factory mediaDataSourceFactory;
    private PlayerCallbacks playerCallbacks;
    private SimpleExoPlayer player;
    private long contentPosition;
    private String contentUrl;

    public PlayerManager(Context context, PlayerCallbacks playerCallbacks) {
        this.playerCallbacks = playerCallbacks;
        manifestDataSourceFactory =
                new DefaultDataSourceFactory(
                        BakingApp.getContext(), Util.getUserAgent(BakingApp.getContext(), "Bakersapp"));
        mediaDataSourceFactory =
                new DefaultDataSourceFactory(
                        BakingApp.getContext(),
                        Util.getUserAgent(BakingApp.getContext(), "Bakersapp"),
                        new DefaultBandwidthMeter());
    }

    public void init(Context context, PlayerView playerView, String contentUrl,
                     Bundle savedInstanceBundle) {
        this.contentUrl = contentUrl;
        // Create a default track selector.
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTrackSelectionFactory =
                new AdaptiveTrackSelection.Factory(bandwidthMeter);
        TrackSelector trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);

        // Create a player instance.
        player = ExoPlayerFactory.newSimpleInstance(BakingApp.getContext(), trackSelector);

        // Bind the player to the view.
        playerView.setPlayer(player);

        // This is the MediaSource representing the content media (i.e. not the ad).
        // contentUrl = context.getString(R.string.content_url);
        MediaSource contentMediaSource = buildMediaSource(Uri.parse(contentUrl));


        // Prepare the player with the source.

        player.prepare(contentMediaSource);

        if (savedInstanceBundle != null) {
            Log.e("init: ", String.valueOf(savedInstanceBundle.getLong("player_position")));
            player.seekTo(savedInstanceBundle.getLong("player_position"));
            player.setPlayWhenReady(savedInstanceBundle.getBoolean("player_state"));
        } else {
            player.setPlayWhenReady(true);
        }
        player.addListener(this);
        playerView.hideController();
    }


    public void release() {
        if (player != null) {
            player.setPlayWhenReady(false);
            player.stop();
            player.release();
            player = null;
        }
    }


    private MediaSource buildMediaSource(Uri uri) {
        @ContentType int type = Util.inferContentType(uri);
        switch (type) {
            case C.TYPE_DASH:
                return new DashMediaSource.Factory(
                        new DefaultDashChunkSource.Factory(mediaDataSourceFactory),
                        manifestDataSourceFactory)
                        .createMediaSource(uri);
     /* case C.TYPE_SS:
        return new SsMediaSource.Factory(
                new DefaultSsChunkSource.Factory(mediaDataSourceFactory), manifestDataSourceFactory)
            .createMediaSource(uri);
      case C.TYPE_HLS:
        return new HlsMediaSource.Factory(mediaDataSourceFactory).createMediaSource(uri);
     */
            case C.TYPE_OTHER:
                return new ExtractorMediaSource.Factory(mediaDataSourceFactory).createMediaSource(uri);
            default:
                throw new IllegalStateException("Unsupported type: " + type);
        }
    }


    @Override
    public void onTimelineChanged(Timeline timeline, Object manifest, int reason) {

    }

    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

    }

    @Override
    public void onLoadingChanged(boolean isLoading) {

    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
        switch (playbackState) {
            case STATE_ENDED:
                playerCallbacks.onVideoPlaybackCompleted();
                break;
            case STATE_READY:
                //       playerCallbacks.setPlayerDurationToProgressBar(player.getDuration());
                break;
        }


    }

    @Override
    public void onRepeatModeChanged(int repeatMode) {

    }

    @Override
    public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {

    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {
        playerCallbacks.onPlayerErrorLoading();
    }

    @Override
    public void onPositionDiscontinuity(int reason) {

    }

    @Override
    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

    }

    @Override
    public void onSeekProcessed() {

    }

    public Bundle getCurrentState() {
        Bundle bundle;
        bundle = new Bundle();
        bundle.putLong("player_position", player.getCurrentPosition());
        bundle.putBoolean("player_state", player.getPlayWhenReady());
        return bundle;
    }

    public void pause() {
        player.setPlayWhenReady(false);
    }



    public interface PlayerCallbacks {

        void onVideoPlaybackCompleted();

        //void setPlayerDurationToProgressBar(long duration);

        void onPlayerErrorLoading();
    }


}

package com.rishabh.bakingapp.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

import com.rishabh.bakingapp.R;
import com.rishabh.bakingapp.data.prefrence.PreferenceKeys;
import com.rishabh.bakingapp.onboard.OnBoardActivity;

public class BakingWidget extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
     //   super.onUpdate(context, appWidgetManager, appWidgetIds);
        for (int appWidgetId : appWidgetIds) {

            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.layout_widget);
            appWidgetManager.updateAppWidget(appWidgetId, views);

            PendingIntent pendingIntent = PendingIntent.getActivity(
                    context,
                    0,
                    new Intent(context, OnBoardActivity.class), 0);

            views.setOnClickPendingIntent(R.id.ll_layout, pendingIntent);
        }

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);


        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.layout_widget);

        SharedPreferences sharedPref = android.preference.PreferenceManager.getDefaultSharedPreferences(context);

        String recipe = sharedPref.getString(PreferenceKeys.RECIPE_NAME, "");
        String ingredients = sharedPref.getString(PreferenceKeys.INGREDIENTS, "");

        views.setTextViewText(R.id.tv_recipe_name, recipe);
        views.setTextViewText(R.id.tv_ingredients, ingredients);


        PendingIntent pendingIntent = PendingIntent.
                getActivity(context,
                        0,
                        new Intent(context, OnBoardActivity.class),
                        0);

        views.setOnClickPendingIntent(R.id.ll_layout, pendingIntent);


        AppWidgetManager.getInstance(context).updateAppWidget(
                new ComponentName(context, BakingWidget.class), views);

    }
}

package com.meowplex.weather_app

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.meowplex.weather_app.ext.getResId
import com.meowplex.weather_app.mapper.*
import com.meowplex.weather_app.model.TemperatureUnits
import com.meowplex.weather_app.model.WeatherPreviewModel
import com.meowplex.weather_app.repository.ApiRepository
import com.meowplex.weather_app.repository.SharedPrefsRepository
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.*
import javax.inject.Inject


@AndroidEntryPoint
class AppWidget : AppWidgetProvider() {

    @Inject
    lateinit var sharedPrefs: SharedPrefsRepository

    @Inject
    lateinit var apiRepository: ApiRepository

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(
                context,
                appWidgetManager,
                appWidgetId,
            )
        }
    }

    private fun updateAppWidget(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetId: Int,
    ) {

        val views = RemoteViews(context.packageName, R.layout.app_widget)

        views.setTextViewText(R.id.location_text, context.getString(R.string.loading))
        views.setTextViewText(R.id.temp_text, "?")
        views.setTextViewText(R.id.comment_text, context.getString(R.string.loading))
        views.setImageViewResource(R.id.image_view, R.drawable.ic_loading)
        appWidgetManager.updateAppWidget(appWidgetId, views)

        val intentUpdate = Intent(context, AppWidget::class.java)
        intentUpdate.action = AppWidgetManager.ACTION_APPWIDGET_UPDATE

        val idArray = intArrayOf(appWidgetId)
        intentUpdate.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, idArray)

        val pendingUpdate = PendingIntent.getBroadcast(
            context,
            appWidgetId,
            intentUpdate,
            PendingIntent.FLAG_IMMUTABLE
        )
        views.setOnClickPendingIntent(R.id.widget_layout, pendingUpdate)

        fetchData(
            appWidgetManager,
            appWidgetId,
            views,
            context,
        )

    }

    @SuppressLint("MissingPermission")
    private fun fetchData(
        appWidgetManager: AppWidgetManager,
        appWidgetId: Int,
        views: RemoteViews,
        context: Context
    ) {


        val location = sharedPrefs.getLocation()
            ?: Constants.defaultLocation

        val single = apiRepository.getWeatherPreview(location)

        val observer = object : SingleObserver<WeatherPreviewModel> {

            override fun onSuccess(weather: WeatherPreviewModel) {
                views.setTextViewText(R.id.location_text, weather.city)
                views.setTextViewText(
                    R.id.temp_text,
                    weather.temp.getValue(
                        tempUnit = sharedPrefs.getTempUnit() ?: TemperatureUnits.Celsius
                    )
                )
                views.setTextViewText(R.id.comment_text, weather.comment)
                views.setImageViewResource(
                    R.id.image_view,
                    R.drawable::class.java.getResId(weather.iconRes)
                )
                appWidgetManager.updateAppWidget(appWidgetId, views)
            }

            override fun onSubscribe(d: Disposable) {
            }


            override fun onError(e: Throwable) {
                views.setTextViewText(R.id.location_text, context.getString(R.string.error))
                views.setTextViewText(R.id.temp_text, "X")
                views.setTextViewText(R.id.comment_text, context.getString(R.string.error))
                views.setImageViewResource(R.id.image_view, R.drawable.ic_error)
                appWidgetManager.updateAppWidget(appWidgetId, views)
            }
        }

        single.subscribeOn(Schedulers.io()).subscribe(observer)
    }

}





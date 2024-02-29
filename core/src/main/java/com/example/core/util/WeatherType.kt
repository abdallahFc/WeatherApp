package com.example.core.util

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
import com.example.core.R

sealed class WeatherType(
    @DrawableRes val iconRes: Int
) {
    object Sunny : WeatherType(
        iconRes = R.drawable.ic_sunny
    )
    object PartlySunny : WeatherType(
        iconRes = R.drawable.ic_sunnycloudy
    )
    object MostlySunny : WeatherType(
        iconRes = R.drawable.ic_sunnycloudy
    )
    object HazySunshine : WeatherType(
        iconRes = R.drawable.ic_sunny
    )
    object ClearSky : WeatherType(
        iconRes = R.drawable.ic_sunny
    )
    object PartlyCloudy : WeatherType(
        iconRes = R.drawable.ic_sunnycloudy
    )
    object Cold : WeatherType(
        iconRes = R.drawable.ic_very_cloudy
    )
    object Hot : WeatherType(
        iconRes = R.drawable.ic_sunny
    )
    object MostlyCloudy : WeatherType(
        iconRes = R.drawable.ic_very_cloudy
    )
    object Cloudy : WeatherType(
        iconRes = R.drawable.ic_cloudy
    )
    object Overcast : WeatherType(
        iconRes = R.drawable.ic_very_cloudy
    )
    object Foggy : WeatherType(
        iconRes = R.drawable.ic_very_cloudy
    )
    object Windy : WeatherType(
        iconRes = R.drawable.ic_windy
    )
    object HeavyFreezingRain: WeatherType(
        iconRes = R.drawable.ic_snowyrainy
    )
    object SlightSnowFall: WeatherType(
        iconRes = R.drawable.ic_snowy
    )
    object ModerateSnowFall: WeatherType(
        iconRes = R.drawable.ic_heavysnow
    )
    object HeavySnowFall: WeatherType(
        iconRes = R.drawable.ic_heavysnow
    )
    object SnowGrains: WeatherType(
        iconRes = R.drawable.ic_heavysnow
    )
    object Rain: WeatherType(
        iconRes = R.drawable.ic_rainshower
    )
    object MostlyCloudyWithShowers: WeatherType(
        iconRes = R.drawable.ic_rainshower
    )
    object PartlyCloudyWithShowers: WeatherType(
        iconRes = R.drawable.ic_rainshower
    )
    object PartlySunnyWithShowers: WeatherType(
        iconRes = R.drawable.ic_sunnyrainy
    )
    object SlightRainShowers: WeatherType(
        iconRes = R.drawable.ic_rainshower
    )
    object MostlyCloudyWithSnow: WeatherType(
        iconRes = R.drawable.ic_snowy
    )
    object SlightHailThunderstorm: WeatherType(
        iconRes = R.drawable.ic_rainythunder
    )
    object HeavyHailThunderstorm: WeatherType(
        iconRes = R.drawable.ic_rainythunder
    )



    companion object {
        fun fromWMO(code: Int): WeatherType {
            return when(code) {
                1 -> Sunny
                2 -> MostlySunny
                3 -> PartlySunny
                4 -> PartlyCloudy
                5 -> HazySunshine
                6 -> MostlyCloudy
                7 -> Cloudy
                8 -> Overcast
                11 -> Foggy
                12 -> SlightRainShowers
                13 -> MostlyCloudyWithShowers
                14 -> PartlySunnyWithShowers
                15 -> SlightHailThunderstorm
                16 -> HeavyHailThunderstorm
                17 -> SlightHailThunderstorm
                18 -> Rain
                19 -> SnowGrains
                20 -> HeavySnowFall
                21 -> ModerateSnowFall
                22 -> SlightSnowFall
                23 -> MostlyCloudyWithSnow
                24 -> HeavyFreezingRain
                25 -> SlightSnowFall
                26 -> HeavyFreezingRain
                29 -> HeavyFreezingRain
                30 -> Hot
                31 -> Cold
                32 -> Windy
                33 -> ClearSky
                34 -> ClearSky
                35 -> PartlyCloudy
                36 -> PartlyCloudy
                37 -> PartlyCloudy
                38 -> MostlyCloudy
                39 -> PartlyCloudyWithShowers
                40 -> MostlyCloudyWithShowers
                41 -> PartlyCloudy
                42 -> MostlyCloudy
                43 -> MostlyCloudyWithSnow
                44 -> MostlyCloudyWithSnow
                else -> ClearSky
            }
        }
    }
}
fun ImageView.setWeatherImageState(code: Int) {
    this.setImageDrawable(
        AppCompatResources.getDrawable(
            this.context,
            WeatherType.fromWMO(code).iconRes
        )
    )
}
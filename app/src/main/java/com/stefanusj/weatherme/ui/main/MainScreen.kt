package com.stefanusj.weatherme.ui.main

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDownward
import androidx.compose.material.icons.rounded.ArrowUpward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.CoilImage
import com.stefanusj.weatherme.R
import com.stefanusj.weatherme.helper.toCurrentDate
import com.stefanusj.weatherme.repository.Result
import com.stefanusj.weatherme.repository.model.Main.Companion.CELCIUS
import com.stefanusj.weatherme.repository.model.Main.Companion.HUMIDITY
import com.stefanusj.weatherme.repository.model.OpenWeatherDatabase
import com.stefanusj.weatherme.repository.model.Wind.Companion.WIND_SPEED
import com.stefanusj.weatherme.theme.AppTheme

@Composable
fun MainFragmentScreen(viewModel: MainViewModel) {
    val currentWeather by viewModel.currentWeather.observeAsState(Result.Loading())
    AppTheme {
        Surface {
            MainScreen(
                currentWeatherResult = currentWeather
            )
        }
    }
}

@Composable
fun MainScreen(
    currentWeatherResult: Result<OpenWeatherDatabase>
) {
    Column {
        when (currentWeatherResult) {
            is Result.Loading -> {
            }
            is Result.Success -> {
                val currentWeather = currentWeatherResult.data
                WeatherLocationAndTime(weather = currentWeather)
                WeatherData(weather = currentWeather)
            }
            is Result.Error -> {
                val message = currentWeatherResult.t.message.toString()
                Text(text = message)
            }
        }
    }
}

@Composable
private fun WeatherLocationAndTime(
    weather: OpenWeatherDatabase,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(all = 16.dp)) {
        Text(
            text = weather.name,
            style = MaterialTheme.typography.h5
        )
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Text(
                text = weather.timestampMs.toCurrentDate("EEE, dd MMM yyyy HH:mm"),
                style = MaterialTheme.typography.body2
            )
        }
    }
}

@Composable
private fun WeatherData(
    weather: OpenWeatherDatabase,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        WeatherNameAndImage(weather, Modifier)
        Row {
            WeatherAttributeSimple(
                imageVector = Icons.Rounded.ArrowUpward,
                text = weather.main.temperatureMax.toString() + CELCIUS
            )
            WeatherAttributeSimple(
                imageVector = Icons.Rounded.ArrowDownward,
                text = weather.main.temperatureMin.toString() + CELCIUS,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
        Spacer(modifier = Modifier.height(height = 64.dp))
        Row {
            WeatherAttribute(
                title = stringResource(R.string.weather_temperature),
                text = weather.main.temperature.toString() + CELCIUS,
                modifier = Modifier.weight(weight = 1f)
            )
            WeatherAttribute(
                title = stringResource(R.string.weather_feels_like),
                text = weather.main.feelsLike.toString() + CELCIUS,
                modifier = Modifier.weight(weight = 1f)
            )
        }
        Row {
            WeatherAttribute(
                title = stringResource(R.string.weather_wind),
                text = weather.wind.speed.toString() + WIND_SPEED,
                modifier = Modifier.weight(weight = 1f)
            )
            WeatherAttribute(
                title = stringResource(R.string.weather_humidity),
                text = weather.main.humidity.toString() + HUMIDITY,
                modifier = Modifier.weight(weight = 1f)
            )
        }
    }
}

@Composable
private fun WeatherNameAndImage(
    weather: OpenWeatherDatabase,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = weather.weather.description.toUpperCase(),
            style = MaterialTheme.typography.subtitle1
        )
        CoilImage(
            data = weather.weather.iconUrl,
            contentDescription = null,
            modifier = Modifier.size(144.dp)
        )
    }
}

@Composable
private fun WeatherAttributeSimple(
    imageVector: ImageVector,
    text: String,
    modifier: Modifier = Modifier
) {
    Row(modifier) {
        Icon(
            imageVector = imageVector,
            contentDescription = null
        )
        Text(
            text = text,
            style = MaterialTheme.typography.body1
        )
    }
}

@Composable
private fun WeatherAttribute(
    title: String,
    text: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(all = 16.dp)
    ) {
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Text(
                text = title,
                style = MaterialTheme.typography.body2,
            )
        }
        Text(
            text = text,
            style = MaterialTheme.typography.subtitle1,
        )
    }
}
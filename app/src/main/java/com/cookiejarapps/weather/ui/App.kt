package com.cookiejarapps.weather.ui

import android.content.res.ColorStateList
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cookiejarapps.weather.R


@ExperimentalFoundationApi
@Preview
@Composable
fun App() {
    Scaffold(
        scaffoldState = rememberScaffoldState(),
        topBar = { TopBar() },
        bottomBar = { BottomBar() },
        content = {
            Column {
                TodaySummary()
                TodayDetails()
                Spacer(Modifier.height(16.dp))
                HourlyBreakdown()
            }
        }
    )
}

@Composable
fun BottomBar() {
    BottomAppBar(backgroundColor = Color.White) {
        BottomNavigationItem(
            onClick = { /*TODO*/ },
            icon = { Icon(Icons.Default.Home, null) },
            selected = true
        )
        BottomNavigationItem(
            onClick = { /*TODO*/ },
            icon = { Icon(Icons.Default.CalendarViewWeek, null) },
            selected = false
        )
        BottomNavigationItem(
            onClick = { /*TODO*/ },
            icon = { Icon(Icons.Default.Radar, null) },
            selected = false
        )
        BottomNavigationItem(
            onClick = { /*TODO*/ },
            icon = { Icon(Icons.Default.Person, null) },
            selected = false
        )

    }
}

@Composable
fun TopBar(){
    TopAppBar(
        title = {Text("Weather - Geneva")},
        backgroundColor = Color.White,
    actions = {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(Icons.Default.LocationCity, null)
        }
        IconButton(onClick = { /*TODO*/ }) {
            Icon(Icons.Default.Settings, null)
        }
    }
  )
}

@Composable
fun TodaySummary(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.Center,
    ){
        Card(shape = RoundedCornerShape(8.dp),
            backgroundColor = Color(0xff87ceeb),) {
            Row{
                Column(modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)){
                    Icon(imageVector = Icons.Default.Cloud, contentDescription = null, modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .height(42.dp)
                        .width(42.dp),
                        tint = Color.White,)
                    Text(text = "Cloudy",
                        style = MaterialTheme.typography.h6,
                        color = Color.White,)
                }
                Column(modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)){
                    Text(text = "21??",
                        style = MaterialTheme.typography.h4,
                        fontWeight = FontWeight.Black,
                        color = Color.White,)
                    Text(text = "Feels like 21??",
                        style = MaterialTheme.typography.body2,
                        fontWeight = FontWeight.Light,
                        color = Color.White,)
                }
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun TodayDetails(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize()
    ) {
        val featureArray =
            arrayListOf(Icons.Default.Thermostat, Icons.Default.Face, Icons.Default.Air, Icons.Default.Umbrella)
        val valueArray =
            arrayListOf("21??", "21??", "0m/h", "0%")

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.Center,
        ){
            Card{
                Row{
                    featureArray.forEachIndexed { index, feature ->
                       WeatherItem(imageVector = feature, value = valueArray[index])
                   }
                }
            }
        }
    }
}

@Composable
fun WeatherItem(imageVector: ImageVector, value: String){
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = null,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = value,
            style = MaterialTheme.typography.body1,
        )
    }
}

@Composable
fun HourlyBreakdown(){
    Text(
        text = "Today",
        style = MaterialTheme.typography.h6,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
    Row(modifier = Modifier
        .horizontalScroll(rememberScrollState())
        .fillMaxWidth()){
        for(i in listOf("Temperature", "Wind", "Precipitation", "UV Index")){
            Button(onClick = { /*TODO*/ }, modifier = Modifier.padding(16.dp)) {
                Text(i)
            }
        }
    }

    Row(modifier = Modifier
        .horizontalScroll(rememberScrollState())
        .width(512.dp)){
        LineChartScreen()
    }
}
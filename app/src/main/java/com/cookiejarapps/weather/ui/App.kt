package com.cookiejarapps.weather.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
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
    Column {
        TodaySummary()
        TodayDetails()
    }
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
            backgroundColor = MaterialTheme.colors.surface,) {
            Row{
                Column(modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)){
                    Icon(imageVector = Icons.Default.Cloud, contentDescription = null, modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .height(42.dp)
                        .width(42.dp))
                    Text(text = "Cloudy",
                        style = MaterialTheme.typography.h6)
                }
                Column(modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)){
                    Text(text = "21°",
                        style = MaterialTheme.typography.h4,
                        fontWeight = FontWeight.Black)
                    Text(text = "Feels like 21°",
                        style = MaterialTheme.typography.body2,
                        fontWeight = FontWeight.Light)
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
            arrayListOf("21°", "21°", "0m/h", "0%")


        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
        ) {
            items(featureArray.size) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    WeatherItem(imageVector = featureArray[it], value = valueArray[it])
                }
            }
        }
    }
}

@Composable
fun WeatherItem(imageVector: ImageVector, value: String){
    Column(
        modifier = Modifier.padding(top = 16.dp)
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
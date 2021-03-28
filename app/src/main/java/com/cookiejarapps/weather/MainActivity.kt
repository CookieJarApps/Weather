package com.cookiejarapps.weather

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            todayItem()
        }
    }

    @Composable
    @Preview
    fun todayItem(){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize()
        ) {
            Text("Today")
            Spacer(Modifier.height(8.dp))

            val featureArray =
                arrayListOf("Temperature", "Feels like", "Wind speed", "Chance of rain")
            val valueArray =
                arrayListOf("21°", "21°", "0m/h", "0%")


            val itemsPerRow = 2
            featureArray.chunked(itemsPerRow)
                .run { this }
                .forEach { factsPerRow ->
                    Row(modifier = Modifier.fillMaxWidth()) {
                        factsPerRow.forEachIndexed { index, item ->
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(1f / (itemsPerRow - index))
                            ) {
                                weatherItem(item, valueArray[index])
                            }
                        }
                    }
                }
        }
    }

    @Composable
    fun weatherItem(text: String, value: String){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            Spacer(Modifier.width(16.dp))
            Column {
                Text(
                    text = text,
                    style = MaterialTheme.typography.h4,
                )
                Spacer(Modifier.height(4.dp))
                Text(
                        text = value,
                        style = MaterialTheme.typography.body1,
                    )
            }
        }
    }

}
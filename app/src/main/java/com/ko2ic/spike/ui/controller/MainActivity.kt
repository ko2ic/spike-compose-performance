package com.ko2ic.spike.ui.controller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ko2ic.spike.ui.theme.SpikecomposeperformanceTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      SpikecomposeperformanceTheme {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
          MyContent()
        }
      }
    }
  }

  @Composable
  fun MyContent() {
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(64.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.SpaceEvenly,
    ) {
      Button(onClick = {
        startActivity(RecyclerViewActivity.intent(this@MainActivity))
      }) {
        Text(text = "RecyclerView")
      }
      Button(onClick = {
        startActivity(RecyclerViewWithComposeActivity.intent(this@MainActivity))
      }) {
        Text(text = "RecyclerView with ComposeView", fontSize = 12.sp)
      }
      Button(onClick = {
        startActivity(GroupieActivity.intent(this@MainActivity))
      }) {
        Text(text = "Groupie")
      }
      Button(onClick = {
        startActivity(GroupieWithComposeActivity.intent(this@MainActivity))
      }) {
        Text(text = "Groupie with ComposeView", fontSize = 12.sp)
      }
      Button(onClick = {
        startActivity(MyAdapterActivity.intent(this@MainActivity))
      }) {
        Text(text = "My Custom Adapter")
      }
      Button(onClick = {
        startActivity(MyAdapterWithComposeActivity.intent(this@MainActivity))
      }) {
        Text(text = "My Custom Adapter with ComposeView")
      }
      Button(onClick = {
        startActivity(ComposeActivity.intent(this@MainActivity))
      }) {
        Text(text = "Compose LazyColumn")
      }
    }
  }

  @Preview(showBackground = true)
  @Composable
  fun DefaultPreview() {
    SpikecomposeperformanceTheme {
      MyContent()
    }
  }
}


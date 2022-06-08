package com.ko2ic.spike.ui.controller

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ko2ic.spike.ComposeListItemView
import com.ko2ic.spike.Item
import com.ko2ic.spike.ui.viewmodel.MyViewModel

class ComposeActivity : AppCompatActivity() {

  companion object {
    fun intent(context: Context) = Intent(context, ComposeActivity::class.java).apply {
      flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      val viewModel: MyViewModel = viewModel()
      MyLazyColumn(
        list = viewModel.list
      )
    }
  }
}

@Composable
fun MyLazyColumn(
  modifier: Modifier = Modifier,
  list: List<Item>,
) {
  LazyColumn(
    modifier = modifier,
  ) {
    items(list) {
      ComposeListItemView(item = it)
      Spacer(
        modifier = Modifier
          .fillMaxWidth()
      )
    }
  }
}
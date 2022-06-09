package com.ko2ic.spike

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ko2ic.spike.ui.theme.Purple200
import com.ko2ic.spike.ui.viewmodel.MyItemViewModel

@Composable
fun ComposeListItemView(item: Item) {
  Column(Modifier.padding(8.dp)) {
    Text(
      text = item.title,
      style = TextStyle(fontSize = 24.sp, color = Purple200),
      textAlign = TextAlign.Center
    )
    Text(
      text = item.description,
      style = TextStyle(fontSize = 16.sp, color = Color.Black),
      textAlign = TextAlign.Center
    )
  }
}

@Composable
fun ComposeListItemViewModel(itemViewModel: MyItemViewModel) {
  ComposeListItemView(Item(itemViewModel.title, itemViewModel.description))
}

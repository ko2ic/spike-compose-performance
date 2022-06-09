package com.ko2ic.spike.ui.view

import android.content.Context
import android.util.AttributeSet
import androidx.compose.runtime.*
import androidx.compose.ui.platform.AbstractComposeView
import com.ko2ic.spike.ComposeListItemView
import com.ko2ic.spike.Item

class ComposeViewListItemView @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null,
  defStyle: Int = 0
) : AbstractComposeView(context, attrs, defStyle) {
  var item by mutableStateOf(Item("",""))

  @Composable
  override fun Content() {
    key(item) {
      ComposeListItemView(item)
    }
  }
}
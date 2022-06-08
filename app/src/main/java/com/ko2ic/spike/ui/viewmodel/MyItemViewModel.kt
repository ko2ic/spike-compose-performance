package com.ko2ic.spike.ui.viewmodel

import com.ko2ic.spike.Item
import com.ko2ic.spike.ui.controller.myadapter.CollectionItemViewModel

class MyItemViewModel(item: Item) : CollectionItemViewModel {
  val title = item.title
  val description = item.description
}
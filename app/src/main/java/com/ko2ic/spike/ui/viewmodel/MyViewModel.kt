package com.ko2ic.spike.ui.viewmodel

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ko2ic.spike.Item
import com.ko2ic.spike.MyRepository
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MyViewModel : ViewModel() {

  val list = ObservableArrayList<Item>()

  // 自作アダプター用
  val viewModels = ObservableArrayList<MyItemViewModel>()

  init {
    MyRepository().fetch().onEach {
      list.addAll(it)
      val viewModels = it.map { MyItemViewModel(it) }
      this.viewModels.addAll(viewModels)
    }.launchIn(viewModelScope)
  }
}
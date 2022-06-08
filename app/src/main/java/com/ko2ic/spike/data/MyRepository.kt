package com.ko2ic.spike

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.Serializable

class MyRepository {

  fun fetch(): Flow<List<Item>> {
    val list = (0..1000).map { Item("タイトル$it", "本文$it") }
    return flow { emit(list) }
  }
}

data class Item(val title: String, val description: String) : Serializable
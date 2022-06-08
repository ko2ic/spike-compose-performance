package com.ko2ic.spike.ui.controller

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ko2ic.spike.R

class GroupieActivity : AppCompatActivity() {

  companion object {
    fun intent(context: Context) = Intent(context, GroupieActivity::class.java).apply {
      flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_recycler_view)
    if (savedInstanceState == null) {
      supportFragmentManager.beginTransaction()
        .replace(R.id.container, GroupieFragment.newInstance())
        .commitNow()
    }
  }
}
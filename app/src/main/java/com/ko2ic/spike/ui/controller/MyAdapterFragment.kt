package com.ko2ic.spike.ui.controller

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ko2ic.spike.R
import com.ko2ic.spike.databinding.FragmentRecyclerViewBinding
import com.ko2ic.spike.ui.controller.myadapter.CollectionItemViewModel
import com.ko2ic.spike.ui.controller.myadapter.ItemViewTypeProvider
import com.ko2ic.spike.ui.viewmodel.MyItemViewModel
import com.ko2ic.spike.ui.viewmodel.MyViewModel

class MyAdapterFragment : Fragment(R.layout.fragment_recycler_view) {

  companion object {
    fun newInstance() = MyAdapterFragment()
  }

  private lateinit var viewModel: MyViewModel

  private var _binding: FragmentRecyclerViewBinding? = null

  private val binding get() = _binding!!

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    viewModel = ViewModelProvider(this)[MyViewModel::class.java]
    _binding = DataBindingUtil.bind(view)
    binding.viewModel = viewModel
    binding.lifecycleOwner = viewLifecycleOwner

    val itemViewTypeProvider = object : ItemViewTypeProvider {
      override fun getLayoutRes(modelCollectionItem: CollectionItemViewModel): Int {
        return when (modelCollectionItem) {
          is MyItemViewModel -> R.layout.list_item_view_my_adapter
          else -> throw IllegalArgumentException("Unexpected layout")
        }
      }
    }
    binding.recyclerView.adapter = com.ko2ic.spike.ui.controller.myadapter.RecyclerViewAdapter(
      viewModel.viewModels,
      itemViewTypeProvider
    )
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}




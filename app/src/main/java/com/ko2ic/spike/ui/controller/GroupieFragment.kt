package com.ko2ic.spike.ui.controller

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ko2ic.spike.Item
import com.ko2ic.spike.R
import com.ko2ic.spike.databinding.FragmentRecyclerViewBinding
import com.ko2ic.spike.databinding.ListItemViewBinding
import com.ko2ic.spike.ui.viewmodel.MyViewModel
import com.xwray.groupie.GroupieAdapter
import com.xwray.groupie.viewbinding.BindableItem

class GroupieFragment : Fragment(R.layout.fragment_recycler_view) {

  companion object {
    fun newInstance() = GroupieFragment()
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

    binding.recyclerView.apply {
      layoutManager = LinearLayoutManager(this@GroupieFragment.context)
      val adapter = GroupieAdapter()
      // 本当はobserveで設定すべき
      val items = viewModel.list.map {
        GroupieItem(it)
      }
      adapter.addAll(items)
      this.adapter = adapter
    }
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}

class GroupieItem(private val item: Item) : BindableItem<ListItemViewBinding>() {

  override fun bind(viewBinding: ListItemViewBinding, position: Int) {
    viewBinding.title.text = item.title
    viewBinding.description.text = item.description
  }

  override fun getLayout(): Int = R.layout.list_item_view

  override fun initializeViewBinding(view: View): ListItemViewBinding {
    return ListItemViewBinding.bind(view)
  }
}




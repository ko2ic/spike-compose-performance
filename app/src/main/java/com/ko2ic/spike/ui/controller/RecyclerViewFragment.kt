package com.ko2ic.spike.ui.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ko2ic.spike.Item
import com.ko2ic.spike.R
import com.ko2ic.spike.databinding.FragmentRecyclerViewBinding
import com.ko2ic.spike.databinding.ListItemViewBinding
import com.ko2ic.spike.ui.viewmodel.MyViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class RecyclerViewFragment : Fragment(R.layout.fragment_recycler_view) {

  companion object {
    fun newInstance() = RecyclerViewFragment()
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
      layoutManager = LinearLayoutManager(this@RecyclerViewFragment.context)
      adapter = RecyclerViewAdapter(viewModel.list)
    }
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}

class RecyclerViewAdapter(private val listItems: List<Item>) :
  RecyclerView.Adapter<ItemViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
    val binding = ListItemViewBinding.inflate(
      LayoutInflater.from(parent.context), parent, false
    )

    return ItemViewHolder(binding)
  }

  override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
    holder.bindView(listItems[position])
  }

  override fun getItemCount(): Int {
    return listItems.size
  }
}

class ItemViewHolder(private val viewBinding: ListItemViewBinding) :
  RecyclerView.ViewHolder(viewBinding.root) {
  fun bindView(content: Item) {
    viewBinding.title.text = content.title
    viewBinding.description.text = content.description
  }
}



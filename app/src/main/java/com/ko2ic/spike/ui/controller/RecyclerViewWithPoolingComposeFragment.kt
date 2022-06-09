package com.ko2ic.spike.ui.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ko2ic.spike.ComposeListItemView
import com.ko2ic.spike.Item
import com.ko2ic.spike.R
import com.ko2ic.spike.databinding.FragmentRecyclerViewBinding
import com.ko2ic.spike.databinding.ListItemViewBinding
import com.ko2ic.spike.databinding.ListItemViewComposeBinding
import com.ko2ic.spike.databinding.ListItemViewComposePoolingBinding
import com.ko2ic.spike.ui.view.ComposeViewListItemView
import com.ko2ic.spike.ui.viewmodel.MyViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class RecyclerViewWithPoolingComposeFragment : Fragment(R.layout.fragment_recycler_view) {

  companion object {
    fun newInstance() = RecyclerViewWithPoolingComposeFragment()
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
      layoutManager = LinearLayoutManager(this@RecyclerViewWithPoolingComposeFragment.context)
      adapter = RecyclerViewWithPoolingComposeAdapter(viewModel.list)
    }
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}

class RecyclerViewWithPoolingComposeAdapter(private val listItems: List<Item>) :
  RecyclerView.Adapter<PoolingComposeItemViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PoolingComposeItemViewHolder {
    val binding = ListItemViewComposePoolingBinding.inflate(
      LayoutInflater.from(parent.context), parent, false
    )
    return PoolingComposeItemViewHolder(binding)
  }

  override fun onBindViewHolder(holder: PoolingComposeItemViewHolder, position: Int) {
    holder.bindView(listItems[position])
  }

  override fun getItemCount(): Int {
    return listItems.size
  }
}

class PoolingComposeItemViewHolder(
  viewBinding: ListItemViewComposePoolingBinding
) : RecyclerView.ViewHolder(viewBinding.root) {
  private val listItemView: ComposeViewListItemView = viewBinding.composeViewPooling

  fun bindView(content: Item) {
    listItemView.item = content
  }
}



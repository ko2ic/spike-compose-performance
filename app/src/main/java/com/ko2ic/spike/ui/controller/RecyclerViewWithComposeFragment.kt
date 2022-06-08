package com.ko2ic.spike.ui.controller

import android.os.Bundle
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
import com.ko2ic.spike.ui.viewmodel.MyViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class RecyclerViewWithComposeFragment : Fragment(R.layout.fragment_recycler_view) {

  companion object {
    fun newInstance() = RecyclerViewWithComposeFragment()
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
      layoutManager = LinearLayoutManager(this@RecyclerViewWithComposeFragment.context)
      adapter = RecyclerViewWithComposeAdapter(viewModel.list)
    }
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}

class RecyclerViewWithComposeAdapter(private val listItems: List<Item>) :
  RecyclerView.Adapter<ComposeItemViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComposeItemViewHolder {
    return ComposeItemViewHolder(ComposeView(parent.context))
  }

  override fun onBindViewHolder(holder: ComposeItemViewHolder, position: Int) {
    holder.bindView(listItems[position])
  }

  override fun onViewRecycled(holder: ComposeItemViewHolder) {
    super.onViewRecycled(holder)
    holder.composeView.disposeComposition()
  }

  override fun getItemCount(): Int {
    return listItems.size
  }
}


class ComposeItemViewHolder(
  val composeView: ComposeView
) : RecyclerView.ViewHolder(composeView) {

  init {
    composeView.setViewCompositionStrategy(
      ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed
    )
  }

  fun bindView(content: Item) {
    composeView.setContent {
      ComposeListItemView(content)
    }
  }
}



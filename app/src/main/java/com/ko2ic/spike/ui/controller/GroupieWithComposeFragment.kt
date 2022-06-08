package com.ko2ic.spike.ui.controller

import android.os.Bundle
import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ko2ic.spike.ComposeListItemView
import com.ko2ic.spike.R
import com.ko2ic.spike.databinding.FragmentRecyclerViewBinding
import com.ko2ic.spike.databinding.ListItemViewComposeBinding
import com.ko2ic.spike.ui.viewmodel.MyViewModel
import com.xwray.groupie.GroupieAdapter
import com.xwray.groupie.viewbinding.BindableItem

class GroupieWithComposeFragment : Fragment(R.layout.fragment_recycler_view) {

  companion object {
    fun newInstance() = GroupieWithComposeFragment()
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
      layoutManager = LinearLayoutManager(this@GroupieWithComposeFragment.context)
      val adapter = GroupieAdapter()

      // 本当はobserveで設定すべき
      val items = viewModel.list.map {
        GroupieItemWithCompose { ComposeListItemView(it) }
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

class GroupieItemWithCompose(
  private val composable: @Composable () -> Unit
) : BindableItem<ListItemViewComposeBinding>() {

  override fun bind(viewBinding: ListItemViewComposeBinding, position: Int) {
    viewBinding.composeView.setContent(composable)
  }

  override fun getLayout(): Int = R.layout.list_item_view_compose

  override fun initializeViewBinding(view: View): ListItemViewComposeBinding {
    return ListItemViewComposeBinding.bind(view).also {
      it.composeView.setViewCompositionStrategy(
        ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed
      )
    }
  }

  override fun unbind(viewHolder: com.xwray.groupie.viewbinding.GroupieViewHolder<ListItemViewComposeBinding>) {
    viewHolder.binding.composeView.disposeComposition()
    super.unbind(viewHolder)
  }
}




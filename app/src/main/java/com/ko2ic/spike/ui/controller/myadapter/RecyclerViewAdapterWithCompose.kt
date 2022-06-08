package com.ko2ic.spike.ui.controller.myadapter

import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.recyclerview.widget.RecyclerView

interface ItemViewTypeProviderWithCompose {
  fun getComposable(modelCollectionItem: CollectionItemViewModel): @Composable () -> Unit
}

class RecyclerViewAdapterWithCompose<T>(
  private val list: ObservableArrayList<T>,
  private val viewTypeProvider: ItemViewTypeProviderWithCompose,
  private val onPostBindViewListener: ((T) -> Unit)? = null
) : RecyclerView.Adapter<RecyclerViewAdapterWithCompose.ItemViewHolder>()
    where T : CollectionItemViewModel {

  init {
    list.addOnListChangedCallback(object :
      ObservableList.OnListChangedCallback<ObservableList<T>>() {
      override fun onChanged(viewModels: ObservableList<T>) {
        notifyDataSetChanged()
      }

      override fun onItemRangeChanged(
        viewModels: ObservableList<T>,
        positionStart: Int,
        itemCount: Int
      ) {
        notifyItemRangeChanged(positionStart, itemCount)
      }

      override fun onItemRangeInserted(
        viewModels: ObservableList<T>,
        positionStart: Int,
        itemCount: Int
      ) {
        notifyItemRangeInserted(positionStart, itemCount)
      }

      override fun onItemRangeMoved(
        viewModels: ObservableList<T>,
        fromPosition: Int,
        toPosition: Int,
        itemCount: Int
      ) {
        notifyItemMoved(fromPosition, toPosition)
      }

      override fun onItemRangeRemoved(
        viewModels: ObservableList<T>,
        positionStart: Int,
        itemCount: Int
      ) {
        notifyItemRangeRemoved(positionStart, itemCount)
      }
    })
  }

  fun getItem(position: Int) = list.elementAtOrNull(position)

  override fun getItemCount() = list.count()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
    return ItemViewHolder(ComposeView(parent.context))
  }

  override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
    val item = list[position]

    val composable = viewTypeProvider.getComposable(item)
    holder.bind(composable)

    val onPostBindViewListener = this.onPostBindViewListener
    if (onPostBindViewListener != null) {
      onPostBindViewListener(item)
    }
  }

  override fun onViewRecycled(holder: ItemViewHolder) {
    super.onViewRecycled(holder)
    holder.unbind()
  }


  class ItemViewHolder(
    private val composeView: ComposeView
  ) : RecyclerView.ViewHolder(composeView) {

    init {
      composeView.setViewCompositionStrategy(
        ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed
      )
    }

    fun bind(composable: @Composable () -> Unit) {
      composeView.setContent {
        composable()
      }
    }

    fun unbind() {
      composeView.disposeComposition()
    }
  }
}

package com.ko2ic.spike.ui.controller.myadapter

interface ItemViewTypeProvider {
  fun getLayoutRes(modelCollectionItem: CollectionItemViewModel): Int
  fun getBindingVariableId(modelCollectionItem: CollectionItemViewModel) =
    com.ko2ic.spike.BR.viewModel
}
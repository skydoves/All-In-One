package com.skydoves.allinone.view.viewholder

import android.view.View
import com.skydoves.allinone.extension.gone
import com.skydoves.allinone.extension.visible
import com.skydoves.allinone.models.IconItem
import com.skydoves.baserecyclerviewadapter.BaseViewHolder
import kotlinx.android.synthetic.main.item_icon.view.*

class TodoIconViewHolder(
  val delegate: Delegate,
  val view: View): BaseViewHolder(view) {

  interface Delegate {
    fun onIconItemClick(iconItem: IconItem)
  }

  private lateinit var iconItem: IconItem

  override fun bindData(data: Any) {
    if (data is IconItem) {
      this.iconItem = data
      drawItemUI()
    }
  }

  private fun drawItemUI() {
    itemView.apply {
      item_icon.setImageDrawable(iconItem.resource)
      if (iconItem.isChecked) {
        item_check.visible()
      } else {
        item_check.gone()
      }
    }
  }

  override fun onClick(v: View?) {
    delegate.onIconItemClick(iconItem)
  }

  override fun onLongClick(v: View?) = false
}
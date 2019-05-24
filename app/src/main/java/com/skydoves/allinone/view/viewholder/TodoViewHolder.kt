/*
 * Copyright (C) 2019 skydoves
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.skydoves.allinone.view.viewholder

import android.view.View
import com.skydoves.allinone.models.entities.Todo
import com.skydoves.baserecyclerviewadapter.BaseViewHolder
import kotlinx.android.synthetic.main.item_todo.view.*

class TodoViewHolder(
  private val delegate: Delegate,
  view: View
)
  : BaseViewHolder(view) {

  interface Delegate {
    fun onItemClick(todo: Todo)
  }

  private lateinit var todo: Todo

  override fun bindData(data: Any) {
    if (data is Todo) {
      this.todo = data
      drawItemUI()
    }
  }

  private fun drawItemUI() {
    itemView.run {
      title.text = todo.title
      content.text = todo.contents
    }
  }

  override fun onClick(p0: View?) {
    delegate.onItemClick(todo)
  }

  override fun onLongClick(p0: View?) = false
}

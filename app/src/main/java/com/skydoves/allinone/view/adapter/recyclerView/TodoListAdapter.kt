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

package com.skydoves.allinone.view.adapter.recyclerView

import android.content.Context
import android.view.View
import com.skydoves.allinone.R
import com.skydoves.allinone.models.entities.Todo
import com.skydoves.allinone.view.viewholder.TodoTitleViewHolder
import com.skydoves.allinone.view.viewholder.TodoViewHolder
import com.skydoves.baserecyclerviewadapter.BaseAdapter
import com.skydoves.baserecyclerviewadapter.BaseViewHolder
import com.skydoves.baserecyclerviewadapter.SectionRow
import org.threeten.bp.OffsetDateTime

class TodoListAdapter(
  context: Context?,
  private val delegate: TodoViewHolder.Delegate
)
  : BaseAdapter() {

  init {
    addSection(ArrayList<String>())
    addSection(ArrayList<Todo>())
    addSection(ArrayList<String>())
    addSection(ArrayList<Todo>())

    context?.let {
      addItemOnSection(0, it.getString(R.string.label_todo))
      addItemOnSection(1, Todo(OffsetDateTime.now(), "test1", "summary", "content", 0, 0))
      addItemOnSection(1, Todo(OffsetDateTime.now(), "test1", "summary", "content", 0, 0))
      addItemOnSection(1, Todo(OffsetDateTime.now(), "test1", "summary", "content", 0, 0))
      addItemOnSection(2, it.getString(R.string.label_completed))
      addItemOnSection(3, Todo(OffsetDateTime.now(), "test3", "summary", "content", 0, 0))
      addItemOnSection(3, Todo(OffsetDateTime.now(), "test3", "summary", "content", 0, 0))
    }
    notifyDataSetChanged()
  }

  override fun layout(sectionRow: SectionRow): Int {
    return when (sectionRow.section) {
      0, 2 -> R.layout.item_todo_title
      else -> R.layout.item_todo
    }
  }

  override fun viewHolder(layout: Int, view: View): BaseViewHolder {
    return when (layout) {
      R.layout.item_todo_title -> TodoTitleViewHolder(view)
      else -> TodoViewHolder(delegate, view)
    }
  }
}

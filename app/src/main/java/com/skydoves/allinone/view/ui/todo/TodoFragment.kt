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

package com.skydoves.allinone.view.ui.todo

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.skydoves.allinone.R
import com.skydoves.allinone.extension.observeLiveData
import com.skydoves.allinone.extension.slideFromRightAnimation
import com.skydoves.allinone.extension.vm
import com.skydoves.allinone.models.entities.Todo
import com.skydoves.allinone.utils.TodoUtils
import com.skydoves.allinone.view.adapter.recyclerView.TodoListAdapter
import com.skydoves.allinone.view.viewholder.TodoViewHolder
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.layout_todo.*
import javax.inject.Inject

class TodoFragment : Fragment(), TodoViewHolder.Delegate {

  @Inject
  lateinit var viewModelFactory: ViewModelProvider.Factory
  private val viewModel by lazy { vm(viewModelFactory, TodoViewModel::class) }
  private val adapter by lazy { TodoListAdapter(context, this) }

  override fun onAttach(context: Context) {
    AndroidSupportInjection.inject(this)
    super.onAttach(context)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.layout_todo, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initializeUI()
    observeLiveData()
  }

  private fun initializeUI() {
    recyclerView.layoutManager = LinearLayoutManager(context)
    recyclerView.adapter = adapter
  }

  private fun observeLiveData() {
    observeLiveData(viewModel.getTodoList()) {
      task_todo.text = TodoUtils.getTodoSize(it).toString()
      task_complete.text = TodoUtils.getCompleteSize(it).toString()
      recyclerView.slideFromRightAnimation()
    }
  }

  override fun onItemClick(todo: Todo) {
  }
}

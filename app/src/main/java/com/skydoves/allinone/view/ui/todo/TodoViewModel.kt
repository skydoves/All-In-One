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
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.skydoves.allinone.models.entities.Todo
import com.skydoves.allinone.persistence.preference.PreferenceComponent_PreferenceComponent
import com.skydoves.allinone.persistence.room.dao.TodoDao
import com.skydoves.allinone.utils.AbsentLiveData
import com.skydoves.allinone.utils.DateUtils
import com.skydoves.allinone.utils.TodoUtils.getDummyTodoItem
import timber.log.Timber
import javax.inject.Inject

class TodoViewModel @Inject
constructor(private val todoDao: TodoDao) : ViewModel() {

  private val trigger: MutableLiveData<Int> = MutableLiveData()
  private val todoList: LiveData<List<Todo>>
  private val settings = PreferenceComponent_PreferenceComponent.getInstance().Settings()

  init {
    Timber.d("injection TodoViewModel")

    trigger.value = 0
    todoList = trigger.switchMap {
      trigger.value?.let { todoDao.getTodoFromDay(DateUtils.getSinceToday()) }
        ?: AbsentLiveData.create()
    }
  }

  fun getTodoList() = todoList

  fun addDummyTodo(context: Context?) {
    if (context != null) {
        if (!settings.initTodo) {
          settings.putIntro(true)
          todoDao.insertTodo(getDummyTodoItem(context))
        }
      }
  }
}

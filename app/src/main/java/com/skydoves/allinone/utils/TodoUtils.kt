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

package com.skydoves.allinone.utils

import android.content.Context
import androidx.core.content.ContextCompat
import com.skydoves.allinone.R
import com.skydoves.allinone.models.entities.Todo
import org.threeten.bp.OffsetDateTime

object TodoUtils {

  fun getTodoSize(todoList: List<Todo>): Int {
    var size = 0
    for (todo in todoList) {
      if (todo.progress < 100) {
        size++
      }
    }
    return size
  }

  fun getCompleteSize(todoList: List<Todo>): Int {
    var size = 0
    for (todo in todoList) {
      if (todo.progress >= 100) {
        size++
      }
    }
    return size
  }

  fun getDummyTodoItem(context: Context): Todo {
    return Todo(
      id = 0,
      timeStamp = OffsetDateTime.now(),
      title = "인트로",
      contents = "앱 설치 후 인트로 및 권한 팝업 확인 완료!",
      color = ContextCompat.getColor(context, R.color.yellow),
      icon = R.drawable.ic_new,
      progress = 100,
      alarmStamp = null)
  }
}

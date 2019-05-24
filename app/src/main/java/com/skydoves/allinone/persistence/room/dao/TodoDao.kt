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

package com.skydoves.allinone.persistence.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.skydoves.allinone.models.entities.Todo

@Dao
interface TodoDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertTodo(todo: Todo)

  @Update
  fun updateTodo(todo: Todo)

  @Query("SELECT * FROM Todo WHERE timeStamp = :timeStamp_")
  fun getTodo(timeStamp_: String): LiveData<Todo>

  @Query("SELECT * FROM TODO WHERE timeStamp >= :timeStamp_")
  fun getTodoFromDay(timeStamp_: String): LiveData<List<Todo>>
}

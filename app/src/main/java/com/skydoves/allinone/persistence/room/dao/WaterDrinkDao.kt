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
import com.skydoves.allinone.models.entities.WaterDrink

@Dao
interface WaterDrinkDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertWaterDrink(waterDrink: WaterDrink)

  @Update
  fun updateWaterDrink(waterDrink: WaterDrink)

  @Query("SELECT * FROM WaterDrink ORDER BY datetime(timeStamp) DESC")
  fun getWaterDrinks(): LiveData<List<WaterDrink>>

  @Query("SELECT * FROM WaterDrink WHERE date = :date_")
  fun getWaterDrinksByDate(date_: String): LiveData<List<WaterDrink>>
}

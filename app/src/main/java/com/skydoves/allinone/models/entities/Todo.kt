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

package com.skydoves.allinone.models.entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import org.threeten.bp.OffsetDateTime

@Suppress("unused")
@Entity(primaryKeys = [("timeStamp")])
data class Todo(
  val timeStamp: OffsetDateTime,
  val title: String?,
  val contents: String?,
  val color: Int,
  val icon: Int,
  val progress: Int
) : Parcelable {
  constructor(source: Parcel) : this(
      source.readSerializable() as OffsetDateTime,
      source.readString(),
      source.readString(),
      source.readInt(),
      source.readInt(),
      source.readInt()
  )

  override fun describeContents() = 0

  override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
    writeSerializable(timeStamp)
    writeString(title)
    writeString(contents)
    writeInt(color)
    writeInt(icon)
    writeInt(progress)
  }

  companion object {
    @JvmField
    val CREATOR: Parcelable.Creator<Todo> = object : Parcelable.Creator<Todo> {
      override fun createFromParcel(source: Parcel): Todo = Todo(source)
      override fun newArray(size: Int): Array<Todo?> = arrayOfNulls(size)
    }
  }
}

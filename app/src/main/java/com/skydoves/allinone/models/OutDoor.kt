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

package com.skydoves.allinone.models

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.DrawableRes

data class OutDoor(
  @DrawableRes val background: Int,
  val title: String?
) : Parcelable {
  constructor(source: Parcel) : this(
    source.readInt(),
    source.readString()
  )

  override fun describeContents() = 0

  override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
    writeInt(background)
    writeString(title)
  }

  companion object {
    @JvmField
    val CREATOR: Parcelable.Creator<OutDoor> = object : Parcelable.Creator<OutDoor> {
      override fun createFromParcel(source: Parcel): OutDoor = OutDoor(source)
      override fun newArray(size: Int): Array<OutDoor?> = arrayOfNulls(size)
    }
  }
}

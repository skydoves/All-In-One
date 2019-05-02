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

package com.skydoves.allinone.bus

import android.util.SparseArray
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

object LiveDataBus {

  private val sEventMap = SparseArray<EventLiveData>()

  const val EVENT_CHANGED_WATER_DRINK = 0

  private fun getLiveData(eventId: Int): EventLiveData {
    var liveData: EventLiveData? = sEventMap.get(eventId)
    if (liveData == null) {
      liveData = EventLiveData(eventId)
      sEventMap.put(eventId, liveData)
    }

    return liveData
  }

  fun observe(eventId: Int, lifecycle: LifecycleOwner, action: Observer<Any>) {
    getLiveData(eventId).observe(lifecycle, action)
  }

  fun unregister(eventId: Int) {
    sEventMap.remove(eventId)
  }

  fun sendEvent(eventId: Int, message: Any) {
    getLiveData(eventId).updateValue(message)
  }
}

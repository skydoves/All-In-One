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

package com.skydoves.allinone.api.client

import com.skydoves.allinone.api.ApiResponse
import com.skydoves.allinone.api.service.GoKrService
import com.skydoves.allinone.extension.async
import com.skydoves.allinone.models.api.response.AirResponse

@Suppress("SpellCheckingInspection")
class GoKrClient(private val service: GoKrService) {

  fun fetchAir(
    numOfRows: Int,
    sidoName: String,
    onResult: (response: ApiResponse<AirResponse>) -> Unit
  ) {
    service.fetchFinedust(numOfRows, sidoName).async(onResult)
  }
}

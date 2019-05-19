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

package com.skydoves.allinone.persistence.preference

import com.skydoves.allinone.models.Air
import com.skydoves.allinone.models.Weather
import com.skydoves.allinone.persistence.preference.converter.BaseGsonConverter
import com.skydoves.preferenceroom.EncryptEntity
import com.skydoves.preferenceroom.KeyName
import com.skydoves.preferenceroom.PreferenceEntity
import com.skydoves.preferenceroom.TypeConverter

@Suppress("unused")
@EncryptEntity("skydoves01234567")
@PreferenceEntity("Weathers")
open class WeatherEntity {

  @KeyName("weather")
  @TypeConverter(BaseGsonConverter::class)
  @JvmField val weather: Weather? = null

  @KeyName("air")
  @TypeConverter(BaseGsonConverter::class)
  @JvmField val air: Air? = null
}

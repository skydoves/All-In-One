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

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "data", strict = false)
data class Weather constructor(
  @field:Element(name = "hour")
  @param:Element(name = "hour")
  val hour: Int,

  @field:Element(name = "temp")
  @param:Element(name = "temp")
  val temp: Float,

  @field:Element(name = "wfKor")
  @param:Element(name = "wfKor")
  val wfKor: String,

  @field:Element(name = "reh")
  @param:Element(name = "reh")
  val reh: Int
)

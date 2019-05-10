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

object LocalUtils {

  val locals = arrayOf(
    "서울특별시", "경기도", "강원도",
    "경상남도", "경상북도", "광주광역시",
    "대구광역시", "대전광역시", "부산광역시",
    "울산광역시", "인천광역시", "전라남도",
    "전라북도", "충청북도", "충청남도",
    "제주특별자치도")

  fun getLocalName(index: Int): String {
    return locals[index]
  }

  fun getLocalUrl(index: Int): String {
    when (index) {
      1 -> return "http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=4182025000"
      2 -> return "http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=4282025000"
      3 -> return "http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=4817074000"
      4 -> return "http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=4729053000"
      5 -> return "http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=2920054000"
      6 -> return "http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=2720065000"
      7 -> return "http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=3023052000"
      8 -> return "http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=2644058000"
      9 -> return "http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=3114056000"
      10 -> return "http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=2871025000"
      11 -> return "http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=4681025000"
      12 -> return "http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=4579031000"
      13 -> return "http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=4376031000"
      14 -> return "http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=4376031000"
      15 -> return "http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=5013025300"
      else -> return "http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1159068000"
    }
  }
}

package com.skydoves.allinone.extension

import org.threeten.bp.OffsetDateTime

fun OffsetDateTime.toDateString(): String {
  return "${hour}시 ${minute}분"
}
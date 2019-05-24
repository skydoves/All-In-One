package com.skydoves.allinone.utils

import com.skydoves.allinone.models.entities.Todo

object TodoUtils {

  fun getTodoSize(todoList: List<Todo>): Int {
    var size = 0
    for (todo in todoList) {
      if (todo.progress >= 100) {
        size++
      }
    }
    return size
  }

  fun getCompleteSize(todoList: List<Todo>): Int {
    var size = 0
    for (todo in todoList) {
      if (todo.progress < 100) {
        size++
      }
    }
    return size
  }
}
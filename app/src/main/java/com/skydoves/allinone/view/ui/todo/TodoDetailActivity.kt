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

package com.skydoves.allinone.view.ui.todo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.transition.Explode
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.content.ContextCompat
import androidx.core.util.Pair
import androidx.core.view.ViewCompat
import androidx.lifecycle.ViewModelProvider
import com.skydoves.allinone.R
import com.skydoves.allinone.extension.checkIsMaterialVersion
import com.skydoves.allinone.extension.gone
import com.skydoves.allinone.extension.setImageTint
import com.skydoves.allinone.extension.toDateString
import com.skydoves.allinone.extension.vm
import com.skydoves.allinone.models.entities.Todo
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_todo_detail.*
import org.jetbrains.anko.image
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import javax.inject.Inject

class TodoDetailActivity : AppCompatActivity() {

  @Inject
  lateinit var viewModelFactory: ViewModelProvider.Factory
  private val viewModel by lazy { vm(viewModelFactory, TodoDetailViewModel::class) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    if (checkIsMaterialVersion()) {
      with(window) {
        requestFeature(android.view.Window.FEATURE_CONTENT_TRANSITIONS)
        enterTransition = Explode()
        exitTransition = Explode()
      }
    }

    setContentView(R.layout.activity_todo_detail)
    AndroidInjection.inject(this)
    initializeUI()
  }

  private fun initializeUI() {
    val todo = getTodoFromIntent()
    todo?.let {
      circle.setImageTint(todo.color)
      circle_icon.setImageDrawable(ContextCompat.getDrawable(this, todo.icon))
      created_time.text = todo.timeStamp.toDateString()
      todo.alarmStamp?.let { alarm_time.text = it.toDateString() } ?: label_alarm_time.gone()
      detail_title.text = todo.title
      detail_content.text = todo.contents
      if (todo.isComplete()) {
        check.image = ContextCompat.getDrawable(this, R.drawable.ic_retry)
      }
      close.setOnClickListener { onBackPressed() }
      check.setOnClickListener {
        if (todo.isComplete()) {
          todo.progress = 0
          toast(getString(R.string.label_rollback))
        } else {
          todo.progress = 100
          toast(getString(R.string.label_complete))
        }
        viewModel.updateTodo(todo)
        finish()
      }
      bin.setOnClickListener {
        viewModel.removeTodo(todo)
        toast(getString(R.string.label_delete_todo))
        finish()
      }
    }
  }

  private fun getTodoFromIntent(): Todo? {
    return viewModel.getTodo(intent.getIntExtra(id, 0))
  }

  companion object {
    private const val id = "id"
    private const val intent_requestCode = 1000
    fun startActivity(activity: Activity, todo: Todo, circle: View, icon: View) {
      if (activity.checkIsMaterialVersion()) {
        val intent = Intent(activity, TodoDetailActivity::class.java)
        intent.putExtra(id, todo.id)
        val p1 = Pair.create(circle, ViewCompat.getTransitionName(circle))
        val p2 = Pair.create(icon, ViewCompat.getTransitionName(icon))
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, p1, p2)
        activity.startActivityForResult(intent, intent_requestCode, options.toBundle())
      } else {
        activity.startActivity<TodoDetailActivity>(id to todo.id)
      }
    }
  }
}

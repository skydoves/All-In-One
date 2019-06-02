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
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.TimePicker
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.skydoves.allinone.R
import com.skydoves.allinone.extension.overridePendingDown
import com.skydoves.allinone.extension.setImageTint
import com.skydoves.allinone.extension.textWatcher
import com.skydoves.allinone.extension.toTodayFormat
import com.skydoves.allinone.extension.vm
import com.skydoves.allinone.models.ColorItem
import com.skydoves.allinone.models.IconItem
import com.skydoves.allinone.models.entities.Todo
import com.skydoves.allinone.view.adapter.recyclerView.TodoColorAdapter
import com.skydoves.allinone.view.adapter.recyclerView.TodoIconAdapter
import com.skydoves.allinone.view.viewholder.TodoColorViewHolder
import com.skydoves.allinone.view.viewholder.TodoIconViewHolder
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_add_todo.*
import kotlinx.android.synthetic.main.toolbar_default.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.threeten.bp.OffsetDateTime
import java.util.Calendar
import javax.inject.Inject

class AddTodoActivity : AppCompatActivity(),
  TodoColorViewHolder.Delegate, TodoIconViewHolder.Delegate,
  TimePickerDialog.OnTimeSetListener {

  @Inject
  lateinit var viewModelFactory: ViewModelProvider.Factory
  private val viewModel by lazy { vm(viewModelFactory, AddTodoViewModel::class) }

  private val colorAdapter by lazy { TodoColorAdapter(this, this) }
  private val iconAdapter by lazy { TodoIconAdapter(this) }

  private lateinit var colorItem: ColorItem
  private lateinit var iconItem: IconItem
  private var alarmOffset: OffsetDateTime? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_add_todo)

    initializeUI()
    initializeAdapter()
    initializeEditTodoUI()
  }

  private fun initializeUI() {
    toolbar_home.setOnClickListener { onBackPressed() }
    toolbar_title.text = getString(R.string.label_add_item)
    recyclerView_color.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    recyclerView_color.adapter = colorAdapter
    recyclerView_icon.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    recyclerView_icon.adapter = iconAdapter

    time.setOnClickListener {
      val calendar = Calendar.getInstance()
      val dialog = TimePickerDialog(this,
        R.style.DialogTheme,
        this,
        calendar.get(Calendar.HOUR_OF_DAY),
        calendar.get(Calendar.MINUTE),
        false)
      dialog.show()
    }

    val watcher = textWatcher { checkValidate() }
    input_title.addTextChangedListener(watcher)
    input_content.addTextChangedListener(watcher)

    save.setOnClickListener {
      if (it.alpha == 1.0f) {
        viewModel.insertTodo(Todo(
          id = 0,
          timeStamp = OffsetDateTime.now(),
          title = input_title.text.toString(),
          contents = input_content.text.toString(),
          color = colorItem.color,
          icon = iconItem.resource,
          progress = 0,
          alarmStamp = alarmOffset))
        finishEdit()
      } else {
        toast(getString(R.string.label_require_inputs))
      }
    }
  }

  private fun initializeAdapter() {
    colorAdapter.getFirstItem().isChecked = true
    colorItem = colorAdapter.getFirstItem()
    iconAdapter.getFirstItem().isChecked = true
    iconItem = iconAdapter.getFirstItem()
  }

  private fun initializeEditTodoUI() {
    val todo = intent.getParcelableExtra<Todo>(todoName)
    todo?.let {
      toolbar_title.text = getString(R.string.label_edit_item)
      save.text = getString(R.string.label_edit)
      todo.title?.let { input_title.setText(it) }
      todo.contents?.let { input_content.setText(it) }
      circle.setImageTint(todo.color)
      circle_icon.setImageDrawable(ContextCompat.getDrawable(this, todo.icon))
      save.setOnClickListener {
        viewModel.updateTodo(Todo(
          id = todo.id,
          timeStamp = todo.timeStamp,
          title = input_title.text.toString(),
          contents = input_content.text.toString(),
          color = colorItem.color,
          icon = iconItem.resource,
          progress = 0,
          alarmStamp = alarmOffset))
        finishEdit()
      }
    }
  }

  private fun finishEdit() {
    toast(getString(R.string.label_added_todo))
    finish()
  }

  override fun onColorItemClick(colorItem: ColorItem) {
    colorAdapter.checkColorItem(colorItem)
    this.colorItem = colorItem
    circle.setImageTint(colorItem.color)
  }

  override fun onIconItemClick(iconItem: IconItem) {
    iconAdapter.checkIconItem(iconItem)
    this.iconItem = iconItem
    circle_icon.setImageDrawable(ContextCompat.getDrawable(this, iconItem.resource))
  }

  override fun onTimeSet(p0: TimePicker?, hour: Int, minute: Int) {
    time.text = toTodayFormat(hour, minute)
    this.alarmOffset = OffsetDateTime.now().withHour(hour).withMinute(minute)
  }

  private fun checkValidate() {
    if (input_title.text.toString().isEmpty() ||
      input_content.text.toString().isEmpty()) {
      save.isEnabled = false
      save.alpha = 0.7f
    } else {
      save.isEnabled = true
      save.alpha = 1.0f
    }
  }

  override fun onBackPressed() {
    super.onBackPressed()
    overridePendingDown()
  }

  companion object {
    const val todoName = "todo"
    fun startActivity(activity: Activity, todo: Todo) {
      activity.startActivity<AddTodoActivity>(todoName to todo)
    }
  }
}

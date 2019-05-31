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

import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.TimePicker
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.skydoves.allinone.R
import com.skydoves.allinone.extension.overridePendingDown
import com.skydoves.allinone.extension.setImageTint
import com.skydoves.allinone.extension.toTodayFormat
import com.skydoves.allinone.extension.vm
import com.skydoves.allinone.models.ColorItem
import com.skydoves.allinone.models.IconItem
import com.skydoves.allinone.view.adapter.recyclerView.TodoColorAdapter
import com.skydoves.allinone.view.adapter.recyclerView.TodoIconAdapter
import com.skydoves.allinone.view.viewholder.TodoColorViewHolder
import com.skydoves.allinone.view.viewholder.TodoIconViewHolder
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_add_todo.*
import kotlinx.android.synthetic.main.toolbar_default.*
import java.util.Calendar
import javax.inject.Inject

class AddTodoActivity : AppCompatActivity(),
    TodoColorViewHolder.Delegate, TodoIconViewHolder.Delegate,
    TimePickerDialog.OnTimeSetListener {

  @Inject
  lateinit var viewModelFactory: ViewModelProvider.Factory
  private val viewModel by lazy { vm(viewModelFactory, AddTodoViewModel::class) }

  private val colorAdapter by lazy { TodoColorAdapter(this, this) }
  private val iconAdapter by lazy { TodoIconAdapter(this, this) }

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_add_todo)

    initializeUI()
    initializeAdapter()
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

    save.setOnClickListener {

    }
  }

  private fun initializeAdapter() {
    colorAdapter.getFirstItem().isChecked = true
    iconAdapter.getFirstItem().isChecked = true
  }

  override fun onColorItemClick(colorItem: ColorItem) {
    colorAdapter.checkColorItem(colorItem)
    circle.setImageTint(colorItem.color)
  }

  override fun onIconItemClick(iconItem: IconItem) {
    iconAdapter.checkIconItem(iconItem)
    circle_icon.setImageDrawable(iconItem.resource)
  }

  override fun onTimeSet(p0: TimePicker?, hour: Int, minute: Int) {
    time.text = toTodayFormat(hour, minute)
  }

  override fun onBackPressed() {
    super.onBackPressed()
    overridePendingDown()
  }
}

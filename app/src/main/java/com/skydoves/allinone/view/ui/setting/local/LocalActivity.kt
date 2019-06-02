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

package com.skydoves.allinone.view.ui.setting.local

import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.skydoves.allinone.R
import com.skydoves.allinone.bus.LiveDataBus
import com.skydoves.allinone.extension.vm
import com.skydoves.allinone.utils.LocalUtils
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_local.*
import org.jetbrains.anko.toast
import javax.inject.Inject

class LocalActivity : AppCompatActivity() {

  @Inject
  lateinit var viewModelFactory: ViewModelProvider.Factory
  private val viewModel by lazy { vm(viewModelFactory, LocalViewModel::class) }

  private var local: Int = -1

  override fun onCreate(savedInstanceState: Bundle?) {
    AndroidInjection.inject(this)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_local)

    val prefLocal = viewModel.getLocal()
    if (prefLocal != -1) {
      location.text = LocalUtils.getLocalName(prefLocal)
      this.local = prefLocal
    }
    select_local.setOnClickListener { showSelectLocalDialog() }
    change_local.setOnClickListener { setLocal() }
  }

  private fun showSelectLocalDialog() {
    val lastIndex = this.local
    this.local = 0
    val ab = AlertDialog.Builder(this)
    ab.setSingleChoiceItems(LocalUtils.locals, 0
    ) { _: DialogInterface, whichButton: Int -> this.local = whichButton }.setPositiveButton(getString(R.string.label_select)
    ) { _: DialogInterface, _: Int -> location.text = LocalUtils.getLocalName(this.local) }.setNegativeButton(getString(R.string.cancel)
    ) { _: DialogInterface, _: Int -> this.local = lastIndex }.show()
  }

  private fun setLocal() {
    if (this.local != -1) {
      viewModel.setLocal(this.local)
      LiveDataBus.sendEvent(LiveDataBus.EVENT_CHANGED_WEATHER_LOCAL, 0)
      toast(getString(R.string.toast_change_local))
      finish()
    } else {
      toast(getString(R.string.toast_change_local_error))
    }
  }
}

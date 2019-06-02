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

package com.skydoves.allinone.view.ui.outdoor

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.skydoves.allinone.R
import com.skydoves.allinone.extension.vm
import com.skydoves.allinone.models.OutDoor
import com.skydoves.allinone.view.adapter.recyclerView.OutdoorAdapter
import com.skydoves.allinone.view.viewholder.OutdoorViewHolder
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.layout_todo.*
import javax.inject.Inject

class OutdoorFragment : Fragment(), OutdoorViewHolder.Delegate {

  @Inject
  lateinit var viewModelFactory: ViewModelProvider.Factory
  private val viewModel by lazy { vm(viewModelFactory, OutDoorViewModel::class) }
  private val adapter by lazy { OutdoorAdapter(this) }

  override fun onAttach(context: Context) {
    AndroidSupportInjection.inject(this)
    super.onAttach(context)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.layout_outdoor, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initializeUI()
  }

  private fun initializeUI() {
    recyclerView.layoutManager = LinearLayoutManager(context)
    recyclerView.adapter = adapter
  }

  override fun onItemClick(outDoor: OutDoor) {
  }
}

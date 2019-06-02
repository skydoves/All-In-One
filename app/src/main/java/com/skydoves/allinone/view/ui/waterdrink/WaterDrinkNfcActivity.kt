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

package com.skydoves.allinone.view.ui.waterdrink

import android.app.PendingIntent
import android.content.Intent
import android.content.IntentFilter
import android.nfc.NdefMessage
import android.nfc.NdefRecord
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.nfc.tech.Ndef
import android.nfc.tech.NdefFormatable
import android.os.Bundle
import android.provider.Settings
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.skydoves.allinone.R
import kotlinx.android.synthetic.main.activity_nfc.*
import kotlinx.android.synthetic.main.toolbar_default.*
import org.jetbrains.anko.toast
import java.io.IOException

@Suppress("SpellCheckingInspection")
class WaterDrinkNfcActivity : AppCompatActivity() {

  private var mWriteMode = false
  private var mNfcAdapter: NfcAdapter? = null
  private var mNfcPendingIntent: PendingIntent? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_nfc)

    nfc_sticker.startRippleAnimation()

    write_nfc.setOnClickListener {
      if (checkNfcEnabled()) {
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this)
        mNfcPendingIntent = PendingIntent.getActivity(this,
            0,
            Intent(this, WaterDrinkNfcActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP),
            0)
        enableTagWriteMode()
        nfc_sticker.visibility = View.VISIBLE
      } else {
        val setnfc = Intent(Settings.ACTION_NFC_SETTINGS)
        startActivity(setnfc)
        toast(getString(R.string.msg_require_nfc))
      }
    }

    toolbar_title.text = getString(R.string.label_nfc_tag)
    toolbar_home.setOnClickListener { onBackPressed() }
  }

  private fun checkNfcEnabled(): Boolean {
    val nfcAdpt = NfcAdapter.getDefaultAdapter(this)
    if (nfcAdpt != null) {
      if (!nfcAdpt.isEnabled)
        return false
    }
    return true
  }

  private fun enableTagWriteMode() {
    mWriteMode = true
    val tagDetected = IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED)
    val mWriteTagFilters = arrayOf(tagDetected)
    mNfcAdapter!!.enableForegroundDispatch(this, mNfcPendingIntent, mWriteTagFilters, null)
  }

  private fun disableTagWriteMode() {
    mWriteMode = false
  }

  override fun onNewIntent(intent: Intent) {
    super.onNewIntent(intent)
    if (mWriteMode && NfcAdapter.ACTION_TAG_DISCOVERED == intent.action) {
      val detectedTag = intent.getParcelableExtra<Tag>(NfcAdapter.EXTRA_TAG)
      val record = NdefRecord.createMime("all_in_one/NFC", nfc_value.text.toString().toByteArray())
      val message = NdefMessage(arrayOf(record))

      // detected tag
      if (writeTag(message, detectedTag)) {
        disableTagWriteMode()
        nfc_sticker.visibility = View.INVISIBLE
        toast(getString(R.string.msg_wrote_nfc))
      }
    }
  }

  private fun writeTag(message: NdefMessage, tag: Tag): Boolean {
    val size = message.toByteArray().size
    try {
      val ndef = Ndef.get(tag)
      if (ndef != null) {
        ndef.connect()
        if (!ndef.isWritable) {
          toast(getString(R.string.msg_wrote_nfc_failed))
          return false
        }
        if (ndef.maxSize < size) {
          toast(getString(R.string.msg_wrote_nfc_failed))
          return false
        }
        ndef.writeNdefMessage(message)
        return true
      } else {
        val format = NdefFormatable.get(tag)
        return if (format != null) {
          try {
            format.connect()
            format.format(message)
            true
          } catch (e: IOException) {
            false
          }
        } else {
          false
        }
      }
    } catch (e: Exception) {
      return false
    }
  }
}

/*
 * Copyright 2014-2017 Eduard Ereza Martínez
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package blcs.lwb.kotlin.Common

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import blcs.lwb.kotlin.Common.AppManager.Companion.instance
import blcs.lwb.kotlin.Common.AppManager.Companion.restartApp
import blcs.lwb.kotlin.R

/**
 * 异常显示界面
 */
class DefaultErrorActivity : AppCompatActivity(), View.OnClickListener {
    private var btn_error_details: Button? = null
    private var exception: String? = null

    @SuppressLint("PrivateResource")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        instance.addActivity(this)
        setContentView(R.layout.activity_default_error)
        val restartButton = findViewById<Button>(R.id.btn_restart_app)
        restartButton.setOnClickListener(this)
        btn_error_details = findViewById(R.id.btn_error_details)
        btn_error_details?.setOnClickListener(this)
        exception = intent.getStringExtra(CrashHandler.SP_BUG)
    }

    /**
     * 复制异常信息
     */
    private fun copyErrorToClipboard() {
        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        if (clipboard != null) {
            val clip = ClipData.newPlainText("错误信息", exception)
            clipboard.setPrimaryClip(clip)
            Toast.makeText(this@DefaultErrorActivity, "复制", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onClick(v: View) {
        val i = v.id
        if (i == R.id.btn_restart_app) {
            restartApp(this)
        } else if (i == R.id.btn_error_details) {
            initErrorDetails()
        }
    }

    /**
     * 异常信息
     */
    private fun initErrorDetails() {
        val dialog = AlertDialog.Builder(this)
                .setTitle("错误详情")
                .setMessage(exception)
                .setPositiveButton("重启") { dialog, which -> restartApp(this@DefaultErrorActivity) }
                .setNeutralButton("复制"
                ) { dialog1: DialogInterface?, which: Int -> copyErrorToClipboard() }.show()
        val textView = dialog.findViewById<TextView>(android.R.id.message)
        textView?.setTextSize(TypedValue.COMPLEX_UNIT_PX, resources.getDimension(R.dimen.standard_work))
    }
}
package blcs.lwb.utils

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import blcs.lwb.lwbtool.utils.IntentUtils
import blcs.lwb.lwbtool.utils.XStatusBar
import blcs.lwb.utils.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bind = DataBindingUtil.setContentView<ActivitySplashBinding>(this, R.layout.activity_splash)
        XStatusBar.setTransparent(this)
        bind.root.postDelayed({
            IntentUtils.toActivity(this@SplashActivity, MainActivity::class.java, true)
            finish()
        }, 3000)
    }
}
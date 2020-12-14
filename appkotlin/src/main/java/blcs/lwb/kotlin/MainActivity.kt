package blcs.lwb.kotlin

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import blcs.lwb.kotlin.manage.MrActivity

class MainActivity : AppCompatActivity() {
    private  val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MrActivity.instance.addActivity(this)
        findViewById<TextView>(R.id.tv_main).setOnClickListener {
//            try {
                throw RuntimeException("xxxxxxxxx")
//            } catch (e: Exception) {
//                Log.e(TAG, "onCreate: "+e.message )
//            }
        }

    }


}
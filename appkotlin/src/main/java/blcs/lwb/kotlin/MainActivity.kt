package blcs.lwb.kotlin

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private  val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<TextView>(R.id.tv_main).setOnClickListener {
//            try {
                throw RuntimeException("xxxxxxxxx")
//            } catch (e: Exception) {
//                Log.e(TAG, "onCreate: "+e.message )
//            }
        }
    }


}
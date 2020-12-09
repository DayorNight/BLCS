package blcs.lwb.kotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.lang.Thread.sleep

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sleep(3000)
    }
}
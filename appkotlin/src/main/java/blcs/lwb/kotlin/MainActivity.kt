package blcs.lwb.kotlin

import android.os.Bundle
import com.blcs.comlibs.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

public class MainActivity() : BaseActivity(){
    override fun initUI() {
        super.initUI()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv_main?.text = "xxxxxx"
        tv_main.setOnClickListener { throw RuntimeException("xxxxxxxxxx") }
    }
}
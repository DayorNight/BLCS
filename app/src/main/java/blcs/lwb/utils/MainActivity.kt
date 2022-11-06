package blcs.lwb.utils

import android.content.res.Resources
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import blcs.lwb.lwbtool.utils.LeakCanaryUtils
import blcs.lwb.lwbtool.utils.SPUtils
import blcs.lwb.utils.adapter.ViewPagerHomeAdapter
import blcs.lwb.utils.databinding.ActivityMainBinding
import blcs.lwb.utils.utils.MyUtils

class MainActivity : AppCompatActivity() {
    lateinit var bind :ActivityMainBinding
    var imgMenu = intArrayOf(R.mipmap.img_util, R.mipmap.img_view, R.mipmap.img_other, R.mipmap.img_resources)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = DataBindingUtil.setContentView(this,R.layout.activity_main)
        initBottomMenu()
        initViewPage()
    }

    private fun initBottomMenu() {
        val menus = MyUtils.getArray(this, R.array.bottom_menu)
        val menu = bind.mainBottom.menu
        for (i in menus.indices) {
            menu.add(1, i, i, menus[i])
            val item = menu.findItem(i)
            item.setIcon(imgMenu[i])
        }
        bind.mainBottom.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                0 -> bind.mainViewpage.currentItem = 0
                1 -> bind.mainViewpage.currentItem = 1
                2 -> bind.mainViewpage.currentItem = 2
                3 -> bind.mainViewpage.currentItem = 3
                4 -> bind.mainViewpage.currentItem = 4
            }
            true
        }
    }

    private fun initViewPage() {
        val adapter = ViewPagerHomeAdapter(supportFragmentManager)
        bind.mainViewpage.adapter = adapter
        bind.mainViewpage.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
            override fun onPageSelected(position: Int) {
                bind.mainBottom.selectedItemId = position
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        LeakCanaryUtils.fixFocusedViewLeak(application)
    }

    override fun getResources(): Resources {
        val fontSizeScale = SPUtils.get(this, Constants.SP_FontScale, 0.0f) as Float
        val res = super.getResources()
        val config = res.configuration
        if (fontSizeScale > 0.5) {
            config.fontScale = fontSizeScale //1 设置正常字体大小的倍数
        }
        res.updateConfiguration(config, res.displayMetrics)
        return res
    }
}
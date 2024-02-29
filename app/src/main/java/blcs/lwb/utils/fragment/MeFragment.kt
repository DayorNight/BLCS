package com.lwb.android.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import blcs.lwb.utils.R
import blcs.lwb.utils.databinding.FragmentMeBinding
import blcs.lwb.utils.fragment.MeChildFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener

class MeFragment : Fragment() {
    private val TAG = "MeFragment"
    private var type: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            type = it.getString(ARG_PARAM)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val ui = DataBindingUtil.inflate<FragmentMeBinding>(
            inflater,
            R.layout.fragment_me,
            container,
            false
        )
        return initUI(ui)
    }

    private fun initUI(ui: FragmentMeBinding):View {
        ui.viewToolbar.title = type
        ui.viewTablelayout.addOnTabSelectedListener(object :OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                ui.viewViewpager.currentItem = tab?.position?:0
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}

        })
        ui.viewViewpager.adapter = MeViewPager(this)
        ui.viewViewpager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                ui.viewTablelayout.getTabAt(position)?.select()
            }
        })
        return ui.root
    }

    companion object {
        private const val ARG_PARAM: String = "MeFragment_Param"

        @JvmStatic
        fun newInstance(type: String) =
            MeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM, type)
                }
            }
    }
}

class MeViewPager(fragment: MeFragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount() = 5

    override fun createFragment(position: Int): Fragment = when(position){
        0,1,2,3,4 ->  MeChildFragment.newInstance(position)
        else -> MeChildFragment.newInstance(position)
    }
}
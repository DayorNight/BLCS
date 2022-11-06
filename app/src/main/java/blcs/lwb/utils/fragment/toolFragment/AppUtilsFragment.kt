package blcs.lwb.utils.fragment.toolFragment

import blcs.lwb.lwbtool.base.BaseVMFragment
import blcs.lwb.utils.viewmodel.EmptyViewModel
import java.lang.Class
import blcs.lwb.utils.R
import blcs.lwb.lwbtool.utils.AppUtils
import java.lang.StringBuilder
import android.view.View
import androidx.lifecycle.Observer
import blcs.lwb.lwbtool.utils.StringUtils
import blcs.lwb.utils.Constants
import blcs.lwb.utils.databinding.FragmentAppBinding

class AppUtilsFragment : BaseVMFragment<FragmentAppBinding, EmptyViewModel>(), View.OnClickListener {
    override fun getViewModelClass(): Class<EmptyViewModel> {
        return EmptyViewModel::class.java
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_app
    }

    override fun observerData() {
        super.observerData()
        binding.apkPath = Constants.phonePath + "/app.apk"
        binding.packageName = getString(R.string.getPackage)
        binding.versionName = getString(R.string.getVersionName)
        binding.versionCode = getString(R.string.getVersionCode)
        binding.channel = getString(R.string.getChannel)
        binding.click = this
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btn_getPackage -> binding!!.packageName = getString(R.string.getPackage) + AppUtils.getAppPackageName(activity)
            R.id.btn_getVersionName -> binding!!.versionName = getString(R.string.getVersionName) + AppUtils.getVersionName(activity)
            R.id.btn_getVersionCode -> binding!!.versionCode = getString(R.string.getVersionCode) + AppUtils.getVersionCode(activity)
            R.id.btn_getInstalledPackages -> {
                val installedPackages = AppUtils.getInstalledPackages(activity)
                val stringBuilder = StringBuilder()
                for (packageInfo in installedPackages) {
                    val packageManager = activity?.packageManager
                    val s = packageManager?.getApplicationLabel(packageInfo.applicationInfo).toString()
                    stringBuilder.append("$s/")
                    binding!!.installedPackages = stringBuilder.toString()
                }
            }
            R.id.btn_getApplicationIcon -> binding!!.imgGetApplicationIcon.setImageDrawable(AppUtils.getApplicationIcon(activity))
            R.id.btn_installApk -> if (StringUtils.isNotEmpty(binding!!.etInstallApk, true)) {
                AppUtils.installApk(activity, binding!!.etInstallApk.text.toString())
            }
            R.id.btn_getChannel -> binding!!.channel = getString(R.string.getChannel) + AppUtils.getChannel(activity)
        }
    }
}
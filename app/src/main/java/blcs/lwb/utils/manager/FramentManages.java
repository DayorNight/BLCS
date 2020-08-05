package blcs.lwb.utils.manager;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;

import blcs.lwb.utils.R;
import blcs.lwb.utils.fragment.otherFragment.Jetpack.NavigationFragment;
import blcs.lwb.utils.fragment.otherFragment.Jetpack.PagingFragment;
import blcs.lwb.utils.fragment.otherFragment.Jetpack.RoomFragment;
import blcs.lwb.utils.fragment.otherFragment.Jetpack.DataBindingFragment;
import blcs.lwb.utils.fragment.otherFragment.Jetpack.LifecycleFragment;
import blcs.lwb.utils.fragment.otherFragment.Jetpack.LiveDataFragment;
import blcs.lwb.utils.fragment.otherFragment.Jetpack.ViewModelFragment;
import blcs.lwb.utils.fragment.otherFragment.Jetpack.WorkManagerFragment;
import blcs.lwb.utils.fragment.otherFragment.UnusedFunctionFragment;
import blcs.lwb.utils.fragment.viewFragment.BannerFragment;
import blcs.lwb.utils.fragment.viewFragment.BarQrCodeFragment;
import blcs.lwb.utils.fragment.viewFragment.BaseAdapterFragment.AnimationRecyclerFragment;
import blcs.lwb.utils.fragment.viewFragment.BaseAdapterFragment.DragAndSwipeFragment;
import blcs.lwb.utils.fragment.viewFragment.BaseAdapterFragment.EmptyViewFragment;
import blcs.lwb.utils.fragment.viewFragment.BaseAdapterFragment.ExpandableItemFragment;
import blcs.lwb.utils.fragment.viewFragment.BaseAdapterFragment.Header_FooterFragment;
import blcs.lwb.utils.fragment.viewFragment.BaseAdapterFragment.ItemClickRecyclerFragment;
import blcs.lwb.utils.fragment.viewFragment.BaseAdapterFragment.MultipleItemRecyclerFragment;
import blcs.lwb.utils.fragment.viewFragment.BaseAdapterFragment.PullToRefreshFragment;
import blcs.lwb.utils.fragment.viewFragment.BaseAdapterFragment.SectionFragment;
import blcs.lwb.utils.fragment.viewFragment.BaseAdapterFragment.UpFetchDataFragment;
import blcs.lwb.utils.fragment.BaseFragment;
import blcs.lwb.utils.fragment.toolFragment.BitmapUtilsFragment;
import blcs.lwb.utils.fragment.otherFragment.BlogsFragment;
import blcs.lwb.utils.fragment.viewFragment.BottomNavigationFragment;
import blcs.lwb.utils.fragment.viewFragment.Color_SpiderFragment;
import blcs.lwb.utils.fragment.viewFragment.Common_DialogFragment;
import blcs.lwb.utils.fragment.viewFragment.CoordinatorLayoutFragment;
import blcs.lwb.utils.fragment.viewFragment.DialogBottomFragment;
import blcs.lwb.utils.fragment.viewFragment.DrawerLayoutFragment;
import blcs.lwb.utils.fragment.toolFragment.EditTextUtilsFragment;
import blcs.lwb.utils.fragment.toolFragment.FileUtilsFragment;
import blcs.lwb.utils.fragment.otherFragment.FunciotnIntroFragment;
import blcs.lwb.utils.fragment.toolFragment.IntentUtilsFragment;
import blcs.lwb.utils.fragment.otherFragment.Jetpack.JetpackFragment;
import blcs.lwb.utils.fragment.viewFragment.FloatingWindowFragment;
import blcs.lwb.utils.fragment.viewFragment.LabelListFragment;
import blcs.lwb.utils.fragment.otherFragment.DependConfigFragment;
import blcs.lwb.utils.fragment.LearnWebsiteFragment;
import blcs.lwb.utils.fragment.toolFragment.LinCleanDataFragment;
import blcs.lwb.utils.fragment.toolFragment.LinCommonFragment;
import blcs.lwb.utils.fragment.viewFragment.LinCountyFragment;
import blcs.lwb.utils.fragment.toolFragment.LinNetStatusFragment;
import blcs.lwb.utils.fragment.toolFragment.LinPermissionFragment;
import blcs.lwb.utils.fragment.toolFragment.LinPhoneFragment;
import blcs.lwb.utils.fragment.toolFragment.LinViewFragment;
import blcs.lwb.utils.fragment.ListFragment;
import blcs.lwb.utils.fragment.viewFragment.LoupeViewFragment;
import blcs.lwb.utils.fragment.viewFragment.MPAndroidChart.BarCharts2Fragment;
import blcs.lwb.utils.fragment.viewFragment.MPAndroidChart.BarChartsFragment;
import blcs.lwb.utils.fragment.viewFragment.MPAndroidChart.LineChartsFragment;
import blcs.lwb.utils.fragment.viewFragment.MPAndroidChart.OtherChartsFragment;
import blcs.lwb.utils.fragment.viewFragment.MPAndroidChart.PieChartsFragment;
import blcs.lwb.utils.fragment.viewFragment.MPAndroidChart.ScrollingChartsFragment;
import blcs.lwb.utils.fragment.viewFragment.MPAndroidChartFragment;
import blcs.lwb.utils.fragment.viewFragment.MagicIndicator.BadgeTabFragment;
import blcs.lwb.utils.fragment.viewFragment.MagicIndicator.CustomNavigatorFragment;
import blcs.lwb.utils.fragment.viewFragment.MagicIndicator.DynamicTabFragment;
import blcs.lwb.utils.fragment.viewFragment.MagicIndicator.FixedTabFragment;
import blcs.lwb.utils.fragment.viewFragment.MagicIndicator.FragmentContainerFragment;
import blcs.lwb.utils.fragment.viewFragment.MagicIndicator.LoadCustomLayoutFragment;
import blcs.lwb.utils.fragment.viewFragment.MagicIndicator.MagicIndicatorFragment;
import blcs.lwb.utils.fragment.viewFragment.MagicIndicator.NoTabOnlyIndicatorFragment;
import blcs.lwb.utils.fragment.viewFragment.MagicIndicator.ScrollableTabFragment;
import blcs.lwb.utils.fragment.viewFragment.MarqueeViewFragment;
import blcs.lwb.utils.fragment.MyFragment;
import blcs.lwb.utils.fragment.viewFragment.NotificationCompatFragment;
import blcs.lwb.utils.fragment.otherFragment.OnCrashFragment;
import blcs.lwb.utils.fragment.viewFragment.OpenGlSquareFragment;
import blcs.lwb.utils.fragment.viewFragment.OpenGlTriangleFragment;
import blcs.lwb.utils.fragment.viewFragment.PickerFragment;
import blcs.lwb.utils.fragment.viewFragment.PopupWindowFragment;
import blcs.lwb.utils.fragment.viewFragment.ProgressBarFragment;
import blcs.lwb.utils.fragment.viewFragment.RecyclerViewFragment;
import blcs.lwb.utils.fragment.otherFragment.RxjavaRetrofitFragment;
import blcs.lwb.utils.fragment.otherFragment.SQLiteFragment;
import blcs.lwb.utils.fragment.viewFragment.ScratchCardViewFragment;
import blcs.lwb.utils.fragment.toolFragment.ScreenUtilsFragment;
import blcs.lwb.utils.fragment.viewFragment.SoundAndVibrationFragment;
import blcs.lwb.utils.fragment.toolFragment.StringUtilsFragment;
import blcs.lwb.utils.fragment.otherFragment.SystemFunctionFragment;
import blcs.lwb.utils.fragment.viewFragment.ToolbarFragment;
import blcs.lwb.utils.fragment.viewFragment.TurnTableViewFragment;
import blcs.lwb.utils.fragment.otherFragment.VersionUpdateFragment;
import blcs.lwb.utils.fragment.viewFragment.Viewpage.SwipeCardFragment;
import blcs.lwb.utils.fragment.viewFragment.Viewpage.ViewpageFragment;
import blcs.lwb.utils.fragment.viewFragment.Viewpage.jellyViewPagerFragment;
import blcs.lwb.utils.fragment.viewFragment.WeChat.MultiLanguageFragment;
import blcs.lwb.utils.fragment.viewFragment.WeChat.WeChatFunctionFragment;
import blcs.lwb.utils.fragment.toolFragment.AppUtilsFragment;
import blcs.lwb.utils.mvp.BaseFragmentActivity;

/**
 * 对所有片段操作的管理
 *
 * @author WESTAKE
 */
public class FramentManages {
    private ArrayList<BaseFragment> list_Frament;// 保存当前Activity的Frament
    public FragmentManager fm;// 片段管理器
    /**
     * 片段名
     */
    public final static String Demo = "我的片段";
    public final static String ListDemo = "ListFragment";
    public final static String LearnLanguage = "学习语言";
    public final static String LearnWebsite = "学习网站";
    public final static String GitHubSources = "GitHub资源归类";
    /**
     * 工具
     */
    public final static String App_Utils = "AppUtils";
    public final static String Bitmap_Utils = "BitmapUtils";
    public final static String EditText_Utils = "EditTextUtils";
    public final static String Intent_Utils = "IntentUtils";
    public final static String String_Utils = "StringUtils";
    public final static String Screen_Utils = "ScreenUtils";
    public final static String LinView = "LinView";
    public final static String FileUtils = "FileUtils";
    public final static String LinCommon = "LinCommon";
    public final static String LinCleanData = "LinCleanData";
    public final static String LinPermission = "LinPermission";
    public final static String LinNetStatus = "LinNetStatus";
    public final static String LinPhone = "LinPhone";
    /**
     * View
     */
    public final static String WeChatFunction = "仿微信功能及控件";
        public final static String FontSize = "字体大小";
        public final static String MultiLanguage = "多语言";
        public final static String WeChatStorage = "存储空间";
        public final static String LinCounty = "地区选择";
    public final static String BottomNavigation = "BottomNavigationView";
    public final static String RecyclerView = "RecyclerView";
        public final static String AnimationRecycler = "AnimationRecycler";
        public final static String MultipleItemRecycler = "MultipleItemRecycler";
        public final static String Header_FooterRecycler = "Header/FooterRecycler";
        public final static String PullToRefreshRecycler = "PullToRefreshRecycler";
        public final static String SectionRecycler = "SectionRecycler";
        public final static String EmptyViewRecycler = "EmptyViewRecycler";
        public final static String DragAndSwipeRecycler = "DragAndSwipeRecycler";
        public final static String ItemClickRecycler = "ItemClickRecycler";
        public final static String ExpandableItemRecycler = "ExpandableItemRecycler";
        public final static String UpFetchDataRecycler = "UpFetchDataRecycler";
    public final static String DialogFragment = "DialogFragment";
    public final static String Toolbar = "toolbar";
    public final static String RxToast = "RxToast";
    public final static String TurnTableView = "转盘小游戏";
    public final static String MarqueeView = "跑马灯/水波纹/标签";
    public final static String DrawerLayout = "侧滑菜单/悬浮按钮";
    public final static String MagicIndicator = "ViewPager指示器";
    public final static String ScrollableTab = "指示器1";
    public final static String FixedTab = "指示器2";
    public final static String DynamicTab = "指示器3";
    public final static String NoTabOnlyIndicator = "指示器4";
    public final static String BadgeTab = "指示器5";
    public final static String FragmentContainer = "指示器6";
    public final static String LoadCustomLayout = "指示器7";
    public final static String CustomNavigator = "指示器8";
    public final static String Viewpage = "Viewpage";
    public final static String jellyViewPager = "jellyViewPager";
    public final static String SwipeCard = "SwipeCard";
    public final static String OpenGl_Triangle = "OpenGl三角形";
    public final static String OpenGl_Square = "OpenGl矩形";
    public final static String Dialog = "常用Dialog";
    public final static String ProgressBar = "进度条";
    public final static String Color_Spider = "蛛网等级及颜色选取";
    public final static String Banner = "Banner轮播图";
    public final static String NotificationCompat = "通知NotificationCompat";
    public final static String Picker = "选择器Picker";
    public final static String LabelList = "标签列表LabelList";
    public final static String SoundAndVibration = "声音与震动";
    public final static String PopupWindow = "PopupWindow";
    public final static String LoupeView = "放大镜";
    public final static String ScratchCardView = "刮刮卡";
    public final static String QMUI_Android = "腾讯开源UI库《QMUI_Android》";
    public final static String MPAndroidChart = "开源图表库《MPAndroidChart》";
        public final static String LineCharts = "LineCharts";
        public final static String BarCharts = "BarCharts";
        public final static String BarCharts2 = "BarCharts2";
        public final static String PieCharts = "PieCharts";
        public final static String OtherCharts = "OtherCharts";
        public final static String ScrollingCharts = "ScrollingCharts";
    public final static String BarQrCode = "条形码/二维码";
    public final static String CoordinatorLayout = "CoordinatorLayout";
    public final static String FloatingWindow = "悬浮窗口";
    /**
     * 其他
     */
    public final static String BLOGS = "博客";
    public final static String VersionUpdate = "版本更新";
    public final static String UnusedFunction = "不常用功能";
        public final static String FunciotnIntro = "功能介绍";
    public final static String CustomActivityOnCrash = "全局异常捕获";
    public final static String DependConfig = "依赖配置";
    public final static String RxjavaRetrofit = "Rxjava+Retrofit封装";
    public final static String SystemFunction = "调用系统功能";
    public final static String SQLite = "SQLite";
    public final static String Jetpack = "Jetpack架构组件";
        public final static String DataBinding = "DataBinding";
        public final static String Lifecycle = "Lifecycle";
        public final static String LiveData = "LiveData";
        public final static String Navigation = "Navigation";
        public final static String Paging = "Paging";
        public final static String ViewModel = "ViewModel";
        public final static String Room = "Room";
        public final static String WorkManager = "WorkManager";

    /**
     * 这个在Fragment中不能new出来,只能在Activity中new，每个Activity对应一个List_fragment来管理
     */
    public FramentManages(BaseFragmentActivity activity) {
        list_Frament = new ArrayList<>();
        fm = activity.getSupportFragmentManager();
    }

    private BaseFragment getFrament(Activity activity, String alias) {
        switch (alias) {
            default:
                return null;
            case FramentManages.Demo:
            case FramentManages.QMUI_Android:
                return new MyFragment();
            case FramentManages.ListDemo:
                return new ListFragment();
            case FramentManages.String_Utils:
                return new StringUtilsFragment();
            case FramentManages.EditText_Utils:
                return new EditTextUtilsFragment();
            case FramentManages.Intent_Utils:
                return new IntentUtilsFragment();
            case FramentManages.App_Utils:
                return new AppUtilsFragment();
            case FramentManages.Screen_Utils:
                return new ScreenUtilsFragment();
            case FramentManages.Bitmap_Utils:
                return new BitmapUtilsFragment();
            case FramentManages.RxToast:
                return new RxToastFragment();
            case FramentManages.BottomNavigation:
                return new BottomNavigationFragment();
            case FramentManages.RecyclerView:
                return new RecyclerViewFragment();
            case FramentManages.TurnTableView:
                return new TurnTableViewFragment();
            case FramentManages.AnimationRecycler:
                return new AnimationRecyclerFragment();
            case FramentManages.MultipleItemRecycler:
                return new MultipleItemRecyclerFragment();
            case FramentManages.Header_FooterRecycler:
                return new Header_FooterFragment();
            case FramentManages.PullToRefreshRecycler:
                return new PullToRefreshFragment();
            case FramentManages.SectionRecycler:
                return new SectionFragment();
            case FramentManages.EmptyViewRecycler:
                return new EmptyViewFragment();
            case FramentManages.DragAndSwipeRecycler:
                return new DragAndSwipeFragment();
            case FramentManages.ItemClickRecycler:
                return new ItemClickRecyclerFragment();
            case FramentManages.ExpandableItemRecycler:
                return new ExpandableItemFragment();
            case FramentManages.UpFetchDataRecycler:
                return new UpFetchDataFragment();
            case FramentManages.MarqueeView:
                return new MarqueeViewFragment();
            case FramentManages.CustomActivityOnCrash:
                return new OnCrashFragment();
            case FramentManages.DependConfig:
                return new DependConfigFragment();
            case FramentManages.Toolbar:
                return new ToolbarFragment();
            case FramentManages.DrawerLayout:
                return new DrawerLayoutFragment();
            case FramentManages.MagicIndicator:
                return new MagicIndicatorFragment();
            case FramentManages.ScrollableTab:
                return new ScrollableTabFragment();
            case FramentManages.FixedTab:
                return new FixedTabFragment();
            case FramentManages.DynamicTab:
                return new DynamicTabFragment();
            case FramentManages.NoTabOnlyIndicator:
                return new NoTabOnlyIndicatorFragment();
            case FramentManages.BadgeTab:
                return new BadgeTabFragment();
            case FramentManages.FragmentContainer:
                return new FragmentContainerFragment();
            case FramentManages.LoadCustomLayout:
                return new LoadCustomLayoutFragment();
            case FramentManages.CustomNavigator:
                return new CustomNavigatorFragment();
            case FramentManages.Viewpage:
                return new ViewpageFragment();
            case FramentManages.jellyViewPager:
                return new jellyViewPagerFragment();
            case FramentManages.SwipeCard:
                return new SwipeCardFragment();
            case FramentManages.OpenGl_Triangle:
                return new OpenGlTriangleFragment();
            case FramentManages.OpenGl_Square:
                return new OpenGlSquareFragment();
            case FramentManages.Dialog:
                return new Common_DialogFragment();
            case FramentManages.ProgressBar:
                return new ProgressBarFragment();
            case FramentManages.Color_Spider:
                return new Color_SpiderFragment();
            case FramentManages.Banner:
                return new BannerFragment();
            case FramentManages.WeChatFunction:
                return new WeChatFunctionFragment();
            case FramentManages.MultiLanguage:
                return new MultiLanguageFragment();
            case FramentManages.RxjavaRetrofit:
                return new RxjavaRetrofitFragment();
            case FramentManages.DialogFragment:
                return new DialogBottomFragment();
            case FramentManages.BLOGS:
                return new BlogsFragment();
            case FramentManages.NotificationCompat:
                return new NotificationCompatFragment();
            case FramentManages.Picker:
                return new PickerFragment();
            case FramentManages.VersionUpdate:
                return new VersionUpdateFragment();
            case FramentManages.UnusedFunction:
                return new UnusedFunctionFragment();
            case FramentManages.LabelList:
                return new LabelListFragment();
            case FramentManages.SoundAndVibration:
                return new SoundAndVibrationFragment();
            case FramentManages.SystemFunction:
                return new SystemFunctionFragment();
            case FramentManages.LinView:
                return new LinViewFragment();
            case FramentManages.FunciotnIntro:
                return new FunciotnIntroFragment();
            case FramentManages.FileUtils:
                return new FileUtilsFragment();
            case FramentManages.SQLite:
                return new SQLiteFragment();
            case FramentManages.PopupWindow:
                return new PopupWindowFragment();
            case FramentManages.LoupeView:
                return new LoupeViewFragment();
            case FramentManages.ScratchCardView:
                return new ScratchCardViewFragment();
            case FramentManages.LinCommon:
                return new LinCommonFragment();
            case FramentManages.LinCleanData:
                return new LinCleanDataFragment();
            case FramentManages.LinCounty:
                return new LinCountyFragment();
            case FramentManages.LinPermission:
                return new LinPermissionFragment();
            case FramentManages.LinNetStatus:
                return new LinNetStatusFragment();
            case FramentManages.LinPhone:
                return new LinPhoneFragment();
            case FramentManages.MPAndroidChart:
                return new MPAndroidChartFragment();
            case FramentManages.LineCharts:
                return new LineChartsFragment();
            case FramentManages.BarCharts:
                return new BarChartsFragment();
            case FramentManages.BarCharts2:
                return new BarCharts2Fragment();
            case FramentManages.PieCharts:
                return new PieChartsFragment();
            case FramentManages.OtherCharts:
                return new OtherChartsFragment();
            case FramentManages.ScrollingCharts:
                return new ScrollingChartsFragment();
            case FramentManages.BarQrCode:
                return new BarQrCodeFragment();
            case FramentManages.LearnLanguage:
            case FramentManages.GitHubSources:
            case FramentManages.LearnWebsite:
                return new LearnWebsiteFragment();
            case FramentManages.Jetpack:
                return new JetpackFragment();
            case FramentManages.DataBinding:
                return new DataBindingFragment();
            case FramentManages.Lifecycle:
                return new LifecycleFragment();
            case FramentManages.LiveData:
                return new LiveDataFragment();
            case FramentManages.Navigation:
                return new NavigationFragment();
            case FramentManages.Paging:
                return new PagingFragment();
            case FramentManages.ViewModel:
                return new ViewModelFragment();
            case FramentManages.Room:
                return new RoomFragment();
            case FramentManages.WorkManager:
                return new WorkManagerFragment();
            case FramentManages.CoordinatorLayout:
                return new CoordinatorLayoutFragment();
            case FramentManages.FloatingWindow:
                return new FloatingWindowFragment();
        }
    }

    /**
     * 替换Frament 这里先删除再添加，完成替换操作
     *
     * @param viewId   Frament 放置的FramentLayout
     * @param activity
     * @param alias    别名
     */
    public void replaceFrament(int viewId, BaseFragmentActivity activity, String alias) {
        BaseFragment base = getFrament(activity, alias);
        if (base == null) {
            return;
        }
        popBackStack();
        list_Frament.add(base);
        fm.beginTransaction()
                // 添加Frament
                .add(viewId, base, alias).addToBackStack(null)
                // 提交
                .commit();
    }

    /**
     * 添加Frament
     *
     * @param viewId   Frament 放置的FramentLayout的id
     * @param activity 对应的Activity
     * @param alias    别名，用于管理Fragment的名字
     * @param bundle   传递的参数，如果不传，设置null即可
     * @param isAnim   是否添加动画
     */
    public void addFrament(int viewId, BaseFragmentActivity activity, String alias,
                           Bundle bundle, boolean isAnim) {
        BaseFragment base = getFrament(activity, alias);
        if (base == null) {
            return;
        }

        list_Frament.add(base);
        if (bundle != null) {
            base.setArguments(bundle);
        }
        FragmentTransaction bt = fm.beginTransaction();
        if (isAnim) {
            // 添加动画
            bt.setCustomAnimations(R.anim.right_push_in, R.anim.left_push_out,
                    R.anim.left_push_in, R.anim.right_push_out);
        }
        // 添加Frament
        bt.add(viewId, base, alias)
                // 添加到后退栈中
                .addToBackStack(null)
                // 提交
                .commitAllowingStateLoss();
    }

    public void popBackStack() {
        if (list_Frament.size() > 1) {
            // 从后退栈中弹出
            fm.popBackStack();
            list_Frament.remove(list_Frament.size() - 1);
        }
    }

    /**
     * 获取上一个片段
     *
     * @return
     */
    public BaseFragment getLastFrament() {
        if (list_Frament.size() - 1 >= 0) {
            return list_Frament.get(list_Frament.size() - 1);
        } else {
            return null;
        }
    }

    /**
     * 获取当前Activity的所有片段 Fragment
     *
     * @return
     */
    public ArrayList<BaseFragment> getAllFrament() {
        return list_Frament;
    }

    /**
     * 清除当前Activity的所有片段 Fragment
     */
    public void clearAllFrament() {
        for (int i = 0; i < list_Frament.size(); i++) {
            popBackStack();
        }
        list_Frament.clear();
    }
}

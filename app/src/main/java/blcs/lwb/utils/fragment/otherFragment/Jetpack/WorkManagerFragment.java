package blcs.lwb.utils.fragment.otherFragment.Jetpack;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.lwbtool.utils.LogUtils;
import blcs.lwb.utils.R;
import blcs.lwb.utils.fragment.BaseFragment;
import butterknife.BindView;
import butterknife.OnClick;

public class WorkManagerFragment extends BaseFragment {

    private OneTimeWorkRequest request1;

    @Override
    protected int bindLayout() {
        return R.layout.fragment_work_manager;
    }
    public static final String DateKey = "KEY";
    public static final String TAG = "cleanup";
    @BindView(R.id.tv_text)
    TextView tvText;

    @Override
    protected void initView() {
//      --------------------简单使用----------------------------------
        simple();
//      --------------------进阶-----------------------------------
        middle();
//      --------------------进阶-----------------------------------
        middle1();

//        PeriodicWorkRequest request = new PeriodicWorkRequest.Builder(MyWorker.class,1, TimeUnit.HOURS).build();

//        OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(MyWorker.class).build();
//        WorkManager.getInstance().enqueue(request);
////        取消和停止工作
//        WorkManager.getInstance().cancelWorkById(request.getId());
    }

    private void middle1() {
        /**
        //        链接工作
        OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(MyWorker.class).build();
        OneTimeWorkRequest request1 = new OneTimeWorkRequest.Builder(MyWorker.class).build();
        OneTimeWorkRequest request2 = new OneTimeWorkRequest.Builder(MyWorker.class).build();
        OneTimeWorkRequest request3 = new OneTimeWorkRequest.Builder(MyWorker.class).setInputMerger(OverwritingInputMerger.class).build();
        OneTimeWorkRequest request4 = new OneTimeWorkRequest.Builder(MyWorker.class).build();
//        为了管理来自多个父级 OneTimeWorkRequest 的输入，WorkManager 使用 InputMerger。
//        WorkManager 提供两种不同类型的 InputMerger：
//        OverwritingInputMerger 会尝试将所有输入中的所有键添加到输出中。如果发生冲突，它会覆盖先前设置的键。
//        ArrayCreatingInputMerger 会尝试合并输入，并在必要时创建数组。

        WorkManager.getInstance()
                .beginWith(Arrays.asList(request, request1, request2))//并行执行request、request1、request2
                .then(request3)//在执行request3
                .then(request4)//在执行request4
                .enqueue();
         */
    }

    private void middle() {
        //进阶：
        //进阶1：构建约束条件：
//        Uri uri = Uri.parse("xxxxx");
        Constraints constraints = new Constraints.Builder()
//                .setRequiredNetworkType(NetworkType.CONNECTED) //指定需要在有网的情况下
//                .setRequiresBatteryNotLow(true)//指定电量在可接受范围内运行
//                .setRequiresStorageNotLow(true)//指定在存储量在可接受范围内运行
//                .addContentUriTrigger(uri,true)//当Uri发生变化的时候运行
//                .setRequiresDeviceIdle(true)//当设备处于空闲状态时运行
                .setRequiresCharging(true)//当设备处于充电状态时运行
                .build();
        //进阶3:传入参数
        Data imageData = new Data.Builder()
                .putString(DateKey, "开始执行")
                .build();
        request1 = new OneTimeWorkRequest.Builder(MyWorker.class)
                .setConstraints(constraints)//添加约束
//                .setInitialDelay(1,TimeUnit.HOURS)//进阶2：延迟执行
//                .setBackoffCriteria(BackoffPolicy.LINEAR,
//                        OneTimeWorkRequest.MIN_BACKOFF_MILLIS,
//                        TimeUnit.MILLISECONDS)//进阶3：退避政策：当doWork()返回 Result.retry()时 启用
                .setInputData(imageData)//进阶4:传入参数
//                .addTag(TAG)//进阶4:标记请求任务
                .build();
//        WorkManager.getInstance().cancelAllWorkByTag(TAG);//取消使用特定标记的所有任务
//        WorkManager.getInstance().getWorkInfosByTagLiveData(TAG); //会返回 LiveData 和具有该标记的所有任务的状态列表

        //进阶5:监听工作状态
        WorkManager.getInstance().getWorkInfoByIdLiveData(request1.getId())
                .observe(this, new Observer<WorkInfo>() {
                    @Override
                    public void onChanged(@Nullable WorkInfo workInfo) {
                        if (workInfo != null && (workInfo.getState() == WorkInfo.State.SUCCEEDED)){
                            tvText.setText(workInfo.getOutputData().getString(DateKey));
                        }
                    }
                });
    }

    @OnClick(R.id.btn_click)
    public void onClick(){
        if (request1!=null)  WorkManager.getInstance().enqueue(request1);
    }

    private void simple() {
        /**
         *  步骤2：构建一次性请求
         */
//        OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(MyWorker.class).build();
        //构建周期性请求
//        PeriodicWorkRequest request = new PeriodicWorkRequest.Builder(MyWorker.class,1, TimeUnit.HOURS).build();
        /**
         *  步骤3：提交请求    总结：1.创建任务——2.配置请求——3.执行请求
         */
//        WorkManager.getInstance().enqueue(request);
    }

    @Override
    public void setMiddleTitle(Toolbar title) {

    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

    /**
     * 步骤1.创建后台任务
     */
    public static class MyWorker extends Worker {

        public MyWorker(@NonNull Context context, @NonNull WorkerParameters params) {
            super(context, params);
        }

        @Override
        public Result doWork() {
            String data = getInputData().getString(DateKey);
            LogUtils.e("data："+data);

            // 创建输出结果
            Data outputData = new Data.Builder()
                    .putString(DateKey,"已经开始充电")
                    .build();
            return Result.success(outputData);
//            return Result.failure();
//            return Result.retry();
        }
    }

}

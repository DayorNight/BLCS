package blcs.lwb.utils.fragment.otherFragment.Jetpack;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.lwbtool.utils.LogUtils;
import blcs.lwb.lwbtool.utils.RecyclerUtil;
import blcs.lwb.utils.Db.RoomDbManager;
import blcs.lwb.utils.Db.StudentDao;
import blcs.lwb.utils.MyApplication;
import blcs.lwb.utils.R;
import blcs.lwb.utils.adapter.StudentAdapter;
import blcs.lwb.utils.bean.Student;
import blcs.lwb.utils.fragment.BaseFragment;
import blcs.lwb.utils.utils.MyUtils;
import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class PagingFragment extends BaseFragment {

    @BindView(R.id.rv_paging)
    RecyclerView rvPaging;

    @Override
    protected int bindLayout() {
        return R.layout.fragment_paging;
    }

    @Override
    protected void initView() {
        StudentAdapter studentAdapter = new StudentAdapter(new CallBack());
        RecyclerUtil.init(getContext(), LinearLayoutManager.VERTICAL, studentAdapter, rvPaging);
        StudentDao studentDao = RoomDbManager.roomDb.getStudentDao();
        Flowable.create(new FlowableOnSubscribe<Object>() {
            @Override
            public void subscribe(FlowableEmitter<Object> e) {
                List<Student> all = studentDao.getAll();
                if (all.size() == 0) {
                    for (int i = 0; i < 20; i++) {
                        Student student = new Student();
                        student.setName("机器人" + i + "号");
                        student.setAge(i);
                        RoomDbManager.roomDb.getStudentDao().insertOne(student);
                    }
                }
                e.onNext(1);
            }
        }, BackpressureStrategy.MISSING).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                PagedList.Config build = new PagedList.Config.Builder()
                        .setPageSize(PAGE_SIZE)                         //配置分页加载的数量
                        .setEnablePlaceholders(ENABLE_PLACEHOLDERS)     //配置是否启动PlaceHolders
                        .setInitialLoadSizeHint(PAGE_SIZE)              //初始化加载的数量
                        .build();
                LiveData<PagedList<Student>> datas = new LivePagedListBuilder(studentDao.getAllStudent(), build).build();

                datas.observe(PagingFragment.this, new Observer<PagedList<Student>>() {
                    @Override
                    public void onChanged(@Nullable PagedList<Student> it) {
                        studentAdapter.submitList(it);
                    }
                });
            }
        });
    }

    private static int PAGE_SIZE = 15;
    private static Boolean ENABLE_PLACEHOLDERS = false;

    @OnClick(R.id.btn_paging)
    public void onClick(){
        MyUtils.toUrl(this,"Paging",getString(R.string.URL_Paging));
    }
    @Override
    public void setMiddleTitle(Toolbar title) {

    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

    @Override
    public void popBackListener(int returnCode, Bundle bundle) {

    }

    class CallBack extends DiffUtil.ItemCallback<Student> {

        @Override
        public boolean areItemsTheSame(@NonNull Student student, @NonNull Student t1) {
            return student.getId() == t1.getId();
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull Student student, @NonNull Student t1) {
            return student == t1;
        }
    }
}

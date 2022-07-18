package blcs.lwb.utils.fragment.otherFragment.Jetpack;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.lwbtool.utils.RecyclerUtil;
import blcs.lwb.utils.Db.RoomDbManager;
import blcs.lwb.utils.Db.StudentDao;
import blcs.lwb.utils.R;
import blcs.lwb.utils.adapter.StudentAdapter;
import blcs.lwb.utils.bean.Student;
import blcs.lwb.utils.fragment.BaseFragment;
import blcs.lwb.utils.utils.MyUtils;
import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableEmitter;
import io.reactivex.rxjava3.core.FlowableOnSubscribe;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PagingFragment extends BaseFragment {

    @BindView(R.id.rv_paging)
    RecyclerView rvPaging;

    @Override
    protected int bindLayout() {
        return R.layout.fragment_paging;
    }

    @Override
    protected void initView() {
        StudentAdapter studentAdapter = new StudentAdapter(new DiffUtil.ItemCallback<Student>() {
            @Override
            public boolean areItemsTheSame(@NonNull Student oldItem, @NonNull Student newItem) {
                return false;
            }

            @Override
            public boolean areContentsTheSame(@NonNull Student oldItem, @NonNull Student newItem) {
                return false;
            }
        });
        RecyclerUtil.init(getActivity(), LinearLayoutManager.VERTICAL, studentAdapter, rvPaging);
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

                datas.observe((LifecycleOwner) getActivity(), new Observer<PagedList<Student>>() {
                    @Override
                    public void onChanged(PagedList<Student> it) {
                        studentAdapter.submitList(it);
                    }
                });
            }
        });
    }

    private static final int PAGE_SIZE = 15;
    private static final Boolean ENABLE_PLACEHOLDERS = false;

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
        public boolean areItemsTheSame(Student student, Student t1) {
            return student.getId() == t1.getId();
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(Student student, Student t1) {
            return student == t1;
        }
    }
}

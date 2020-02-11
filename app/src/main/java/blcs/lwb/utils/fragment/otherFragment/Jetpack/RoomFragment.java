package blcs.lwb.utils.fragment.otherFragment.Jetpack;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.Callable;

import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.lwbtool.utils.RecyclerUtil;
import blcs.lwb.utils.Db.RoomDbManager;
import blcs.lwb.utils.Db.StudentDao;
import blcs.lwb.utils.MyApplication;
import blcs.lwb.utils.R;
import blcs.lwb.utils.adapter.RoomAdapter;
import blcs.lwb.utils.bean.Student;
import blcs.lwb.utils.fragment.BaseFragment;
import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class RoomFragment extends BaseFragment {
    private static final String TAG = "RoomFragment";
    private StudentDao studentDao;
    @BindView(R.id.rv_room)
    RecyclerView rv;

    private RoomAdapter roomAdapter;
    private int code = 0;
    private int UpdateCode = 0;
    private int method = 0;

    @Override
    protected int bindLayout() {
        return R.layout.fragment_room;
    }

    @Override
    protected void initView() {
        studentDao = RoomDbManager.roomDb.getStudentDao();
        roomAdapter = new RoomAdapter();
        RecyclerUtil.init(activity, LinearLayoutManager.VERTICAL, roomAdapter, rv);
    }

    /**
     * 初始化Builder属性
     */
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

    @OnClick({R.id.btn_room_Add, R.id.btn_room_Delete, R.id.btn_room_Update, R.id.btn_room_Query})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_room_Add:
                method = 1;
                break;
            case R.id.btn_room_Delete:
                method = 2;
                break;
            case R.id.btn_room_Update:
                method = 3;
                break;
            case R.id.btn_room_Query:
                method = 4;
                break;
        }
        observable.subscribe(consumer);
    }

    Observable<List<Student>> observable = Observable.defer(new Callable<Observable<List<Student>>>() {
        @Override
        public Observable<List<Student>> call() throws Exception {
            Log.e(TAG, "accept: " + method);
            switch (method) {
                case 1:
                    Student student = new Student();
                    student.setName("机器人" + code + "号");
                    student.setAge(code++);
                    studentDao.insertOne(student);
                    break;
                case 2://删除第一个
                    List<Student> all = studentDao.getAll();
                    if (all!=null&&all.size()>0){
                        studentDao.deleteOne(all.get(0));
                    }else{
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(activity,"数据库没有数据",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    break;
                case 3://更新第一个
                    List<Student> students= studentDao.getAll();
                    Log.e(TAG, "call: "+students.size() );
                    Log.e(TAG, "call: "+UpdateCode );
                    if (students!=null&&students.size()>0&&students.size()>UpdateCode){
                        Student student1 = students.get(UpdateCode);
                        if (student1.getName().startsWith("机器人升级版")){
                            student1.setName("机器人"+UpdateCode+"号");
                            student1.setAge(UpdateCode++);
                        }else{
                            student1.setName("机器人升级版"+UpdateCode+"号");
                            student1.setAge(UpdateCode++);
                        }
                        studentDao.update(student1);
                    }else{
                        UpdateCode = 0;
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(activity,"数据库没有数据",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    break;
                case 4:
//                    studentDao.getAll();
                    break;
            }
            List<Student> all = studentDao.getAll();
            return Observable.fromArray(all);
        }
    }).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());

    Consumer<List<Student>> consumer = new Consumer<List<Student>>() {
        @Override
        public void accept(List<Student> students) throws Exception {
            roomAdapter.setNewData(students);
        }
    };
}

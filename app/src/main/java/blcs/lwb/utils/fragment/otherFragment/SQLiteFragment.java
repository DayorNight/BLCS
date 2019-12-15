package blcs.lwb.utils.fragment.otherFragment;

import android.os.Bundle;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import java.util.ArrayList;
import java.util.List;
import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.lwbtool.utils.LogUtils;
import blcs.lwb.lwbtool.utils.RecyclerUtil;
import blcs.lwb.utils.bean.SqliteDemo;
import blcs.lwb.utils.R;
import blcs.lwb.utils.adapter.SQLiteShowAdapter;
import blcs.lwb.utils.fragment.BaseFragment;
import blcs.lwb.utils.Db.LinSQL;
import butterknife.BindView;
import butterknife.OnClick;

public class SQLiteFragment extends BaseFragment {

    @BindView(R.id.et_sql_id)
    EditText etSqlId;
    @BindView(R.id.et_sql_name)
    EditText etSqlName;
    @BindView(R.id.et_sql_address)
    EditText etSqlAddress;
    @BindView(R.id.rv_sql_show)
    RecyclerView rvSqlShow;
    @BindView(R.id.sp_sql)
    Spinner spSql;
    private SQLiteShowAdapter mAdapter;

    @Override
    protected int bindLayout() {
        return R.layout.fragment_sqlite;
    }
    private int type ;
    private List<String> datas = new ArrayList<>();
    @Override
    protected void initView() {
        LinSQL.init(activity.getApplicationContext());
        mAdapter = new SQLiteShowAdapter();
        RecyclerUtil.init(activity, OrientationHelper.VERTICAL, mAdapter, rvSqlShow);
        datas.add("SQL执行语句");
        datas.add("SQL执行Api");
        datas.add("开源LitePal");
        datas.add("开源Greendao");
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(activity, android.R.layout.simple_spinner_item,datas);
        spSql.setAdapter(stringArrayAdapter);
        spSql.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                LogUtils.e("====="+position);
                type = position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

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
    @OnClick({R.id.btn_sql_insert, R.id.btn_sql_delete, R.id.btn_sql_update, R.id.btn_sql_query})
    public void onViewClicked(View view) {
        String id = etSqlId.getText().toString().trim();
        String name = etSqlName.getText().toString().trim();
        String address = etSqlAddress.getText().toString().trim();
        switch (view.getId()) {
            case R.id.btn_sql_insert:
                LinSQL.insert(name, address,type,mAdapter);
                break;
            case R.id.btn_sql_delete:
                LinSQL.delete(name, address,type,mAdapter);
                break;
            case R.id.btn_sql_update:
                LinSQL.update(Integer.parseInt(id),name,address,type,mAdapter);
                break;
            case R.id.btn_sql_query:
                List<SqliteDemo> query = LinSQL.query(name,address,type);
                mAdapter.setNewData(query);

                break;
        }
        etSqlId.setText("");
        etSqlName.setText("");
        etSqlAddress.setText("");
    }

}

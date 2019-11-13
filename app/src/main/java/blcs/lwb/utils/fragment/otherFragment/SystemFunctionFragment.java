package blcs.lwb.utils.fragment.otherFragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.provider.CalendarContract;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.Calendar;

import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.lwbtool.utils.LinPermission;
import blcs.lwb.utils.R;
import blcs.lwb.utils.fragment.BaseFragment;
import butterknife.OnClick;

public class SystemFunctionFragment extends BaseFragment {

    @Override
    protected int bindLayout() {
        return R.layout.fragment_system_function;
    }

    @Override
    protected void initView() {

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

    @OnClick({R.id.btn_voice, R.id.btn_video, R.id.btn_camera, R.id.btn_alarm, R.id.btn_set_time,
            R.id.btn_calendar, R.id.btn_pick, R.id.btn_email, R.id.btn_selectImage})
    public void onViewClicked(View view) {
        switch (view.getId()) {
//            https://www.cnblogs.com/stnlcd/p/7151413.html
            case R.id.btn_voice:
                if (LinPermission.checkPermission(activity, new int[]{0, 7})) {
                    to(new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION));
                } else {
                    LinPermission.requestMultiplePermission(activity, new int[]{0, 7});
                }
                break;
            case R.id.btn_video:
                if (LinPermission.checkPermission(activity, new int[]{0, 4, 7})) {
                    to(new Intent(MediaStore.ACTION_VIDEO_CAPTURE));
                } else {
                    LinPermission.requestMultiplePermission(activity, new int[]{0, 4, 7});
                }
                break;
            case R.id.btn_camera:
                if (LinPermission.checkPermission(activity, new int[]{4, 7})) {
                    to(new Intent(MediaStore.ACTION_IMAGE_CAPTURE));
                } else {
                    LinPermission.requestMultiplePermission(activity, new int[]{4, 7});
                }
                break;
            case R.id.btn_alarm:
//                createAlarm("我是闹钟", 3, 33);
                to(new Intent(AlarmClock.ACTION_SHOW_ALARMS));
                break;
            case R.id.btn_set_time:
                to(new Intent(AlarmClock.ACTION_SHOW_TIMERS));
                break;
            case R.id.btn_calendar:
                if (LinPermission.checkPermission(activity, 3)) {
                    calendar("我是日历", "描述", "厦门");
                } else {
                    LinPermission.requestPermission(activity, 3);
                }
                break;
            case R.id.btn_pick:
                Intent intent1 = new Intent(Intent.ACTION_PICK);
                intent1.setType(ContactsContract.Contacts.CONTENT_TYPE);
                to(intent1);
                break;
            case R.id.btn_email:
                composeEmail(new String[]{"我是邮件"}, "小标题");
                break;
            case R.id.btn_selectImage:
//                Intent intent = new Intent(Intent.ACTION_PICK);
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
//                intent.setType("image/*;video/*");
                to(intent);
                break;
        }
    }

    private void to(Intent intent) {
        if (intent.resolveActivity(activity.getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void composeEmail(String[] addresses, String subject) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        to(intent);
    }

    private void calendar(String title, String desc, String location) {
        Calendar beginC = Calendar.getInstance();
        beginC.add(Calendar.DAY_OF_MONTH, 1);
        Calendar endC = Calendar.getInstance();
        endC.add(Calendar.DAY_OF_MONTH, 6);
        //action为Intent.ACTION_INSERT
        Intent intent = new Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                //事件的开始时间（从新纪年开始计算的毫秒数）。
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginC)
                //事件的结束时间（从新纪年开始计算的毫秒数）。
                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endC)
                //指定此事件是否为全天事件。
                .putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true)
                //事件地点。
                .putExtra(CalendarContract.Events.EVENT_LOCATION, location)
                //事件标题。
                .putExtra(CalendarContract.Events.TITLE, title)
                //事件说明
                .putExtra(CalendarContract.Events.DESCRIPTION, desc);
        startActivity(intent);
    }

    /**
     * 闹钟
     */
    public void createAlarm(String message, int hour, int minutes) {
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                .putExtra(AlarmClock.EXTRA_MESSAGE, message)
                .putExtra(AlarmClock.EXTRA_HOUR, hour)
                .putExtra(AlarmClock.EXTRA_MINUTES, minutes);
        startActivity(intent);
    }

}

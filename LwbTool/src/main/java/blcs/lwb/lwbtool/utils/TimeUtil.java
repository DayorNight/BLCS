/*Copyright ©2015 TommyLemon(https://github.com/TommyLemon)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.*/

package blcs.lwb.lwbtool.utils;

import android.annotation.SuppressLint;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * TODO 通用时间类
 *
 * @author Lemon
 * @use TimeUtil.
 * 1、获取时间,hh:mm:ss
 * 2、获取完整时间获取完整时间 yyyy年mm月dd日hh时mm分
 * 3、将long型时间长度数据转化为文字形式时间长度
 * 4、智能时间显示，12:30,昨天，前天...
 * 5、获取日期 年，月， 日 对应值
 * 6、获取日期  时， 分， 秒 对应值
 * 7、获取日期 年，月， 日， 时， 分， 秒 对应值
 * 8、获取两个时间的时间间隔
 * 9、根据生日获取年龄
 * 10、根据生日计算星座
 * 11、获取生日,不带年份
 * 12、获取智能生日
 * 13、判断现在是否属于一段时间,不包含端点
 * 14、获取系统时间 格式为："yyyy/MM/dd "
 * 15、获取系统时间 格式为："yyyy "
 * 16、获取系统时间 格式为："MM"
 * 17、获取系统时间 格式为："dd"
 * 18、获取当前时间戳
 * 19、时间戳转换成字符窜
 * 20、时间戳中获取年
 * 21、时间戳中获取月.
 * 22、时间戳中获取日
 * 23、将字符串转为时间戳
 * 24、获取指定日期周几
 * 25、获取指定日期对应周几的序列
 * 26、返回当前月份
 * 27、获取当前月号
 * 28、获取当前年份
 * 29、获取本月份的天数
 * 30、获取指定月份的天数
 * 31、根据年 月 获取对应的月份 天数
 * 32、时间的格式化
 */
public class TimeUtil {
    private static final String TAG = "TimeUtil";

    private TimeUtil() {/* 不能实例化**/}


    /**
     * 系统计时开始时间
     */
    public static final int[] SYSTEM_START_DATE = {1970, 0, 1, 0, 0, 0};


    public static final int LEVEL_YEAR = 0;
    public static final int LEVEL_MONTH = 1;
    public static final int LEVEL_DAY = 2;
    public static final int LEVEL_HOUR = 3;
    public static final int LEVEL_MINUTE = 4;
    public static final int LEVEL_SECOND = 5;
    public static final int[] LEVELS = {
            LEVEL_YEAR,
            LEVEL_MONTH,
            LEVEL_DAY,
            LEVEL_HOUR,
            LEVEL_MINUTE,
            LEVEL_SECOND,
    };

    public static final String NAME_YEAR = "年";
    public static final String NAME_MONTH = "月";
    public static final String NAME_DAY = "日";
    public static final String NAME_HOUR = "时";
    public static final String NAME_MINUTE = "分";
    public static final String NAME_SECOND = "秒";
    public static final String[] LEVEL_NAMES = {
            NAME_YEAR,
            NAME_MONTH,
            NAME_DAY,
            NAME_HOUR,
            NAME_MINUTE,
            NAME_SECOND,
    };


    /**
     * @param level
     * @return
     */
    public static boolean isContainLevel(int level) {
        for (int existLevel : LEVELS) {
            if (level == existLevel) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param level
     * @return
     */
    public static String getNameByLevel(int level) {
        return isContainLevel(level) ? LEVEL_NAMES[level - LEVEL_YEAR] : "";
    }


    public static class Day {

        public static final String NAME_THE_DAY_BEFORE_YESTERDAY = "前天";
        public static final String NAME_YESTERDAY = "昨天";
        public static final String NAME_TODAY = "今天";
        public static final String NAME_TOMORROW = "明天";
        public static final String NAME_THE_DAY_AFTER_TOMORROW = "后天";


        public static final int TYPE_SUNDAY = 0;
        public static final int TYPE_MONDAY = 1;
        public static final int TYPE_TUESDAY = 2;
        public static final int TYPE_WEDNESDAY = 3;
        public static final int TYPE_THURSDAY = 4;
        public static final int TYPE_FRIDAY = 5;
        public static final int TYPE_SATURDAY = 6;
        public static final int[] DAY_OF_WEEK_TYPES = {
                TYPE_SUNDAY,
                TYPE_MONDAY,
                TYPE_TUESDAY,
                TYPE_WEDNESDAY,
                TYPE_THURSDAY,
                TYPE_FRIDAY,
                TYPE_SATURDAY,
        };

        public static final String NAME_SUNDAY = "日";
        public static final String NAME_MONDAY = "一";
        public static final String NAME_TUESDAY = "二";
        public static final String NAME_WEDNESDAY = "三";
        public static final String NAME_THURSDAY = "四";
        public static final String NAME_FRIDAY = "五";
        public static final String NAME_SATURDAY = "六";
        public static final String[] DAY_OF_WEEK_NAMES = {
                NAME_SUNDAY,
                NAME_MONDAY,
                NAME_TUESDAY,
                NAME_WEDNESDAY,
                NAME_THURSDAY,
                NAME_FRIDAY,
                NAME_SATURDAY,
        };


        /**
         * @param type
         * @return
         */
        public static boolean isContainType(int type) {
            for (int existType : DAY_OF_WEEK_TYPES) {
                if (type == existType) {
                    return true;
                }
            }
            return false;
        }

        public static String getDayNameOfWeek(int type) {
            return isContainType(type) ? DAY_OF_WEEK_NAMES[type - TYPE_SUNDAY] : "";
        }

    }


    public static final int YEAR = 0;
    public static final int MONTH = 1;
    public static final int DAY_OF_MONTH = 2;
    public static final int HOUR_OF_DAY = 3;
    public static final int MINUTE = 4;
    public static final int SECOND = 5;


    public static final int[] minTimeDetails = {0, 0, 0};
    public static final int[] maxTimeDetails = {23, 59, 59};


    /**
     * TODO 1、获取时间,hh:mm:ss
     *
     * @param date
     */
    public static String getTime(Date date) {
        return date == null ? "" : getTime(date.getTime());
    }

    /**
     * 获取时间,hh:mm:ss
     * @param date
     * @return
     */
    public static String getTime(long date) {
        return new SimpleDateFormat("hh:mm:ss").format(new Date(date));
    }


    /**
     * 获取完整时间
     *
     * @param date
     * @return
     */
    public static String getWholeTime(Date date) {
        return date == null ? "" : getWholeTime(date.getTime());
    }

    /**
     * TODO 2、获取完整时间 yyyy年mm月dd日hh时mm分
     *
     * @param date
     * @return
     */
    public static String getWholeTime(long date) {
        int[] details = TimeUtil.getWholeDetail(date);

        return details[0] + "年" + details[1] + "月"
                + details[2] + "日  " + details[3] + "时" + details[4] + "分";
    }


    /**
     * TODO 3、将long型时间长度数据转化为文字形式时间长度
     * 去掉了1970年1月1日8时的初始值
     *
     * @param duration
     * @return
     */
    public static String getSmartTime(long duration) {

        int[] smartDetail = getWholeDetail(duration);

        String smartTime = "";

        if (smartDetail[5] > 0) {
            smartTime = String.valueOf(smartDetail[5]) + "秒" + smartTime;
        }
        if (smartDetail[4] > 0) {
            smartTime = String.valueOf(smartDetail[4]) + "分" + smartTime;
        }
        if (smartDetail[3] > 8) {
            smartTime = String.valueOf(smartDetail[3]) + "时" + String.valueOf(smartDetail[4]) + "分";
        }
        if (smartDetail[2] > 1) {
            smartTime = String.valueOf(smartDetail[2]) + "天" + String.valueOf(smartDetail[3]) + "时";
        }
        if (smartDetail[1] > 1) {
            smartTime = String.valueOf(smartDetail[1]) + "月" + String.valueOf(smartDetail[2]) + "天";
        }
        if (smartDetail[0] > 1970) {
            smartTime = String.valueOf(smartDetail[0]) + "年" + smartTime;
        }

        return smartTime;
    }

    public static String getSmartDate(Date date) {
        return date == null ? "" : getSmartDate(date.getTime());
    }

    /**
     * TODO 4、智能时间显示，12:30,昨天，前天...
     *
     * @param date
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static String getSmartDate(long date) {

        int[] nowDetails = getWholeDetail(System.currentTimeMillis());
        int[] smartDetail = getWholeDetail(date);

        String smartDate = "";

        if (nowDetails[0] == smartDetail[0]) {//this year
            if (nowDetails[1] == smartDetail[1]) {//this month
                long day = nowDetails[2] - smartDetail[2];//between/(24*3600);
                if (day >= 3) {//fomer day
                    smartDate = "本月" + String.valueOf(smartDetail[2]) + "日";
                } else if (day >= 2) {//fomer day
                    smartDate = "前天";
                } else if (day >= 1) {//fomer day
                    smartDate = "昨天";
                } else if (day >= 0) {//today
                    if (0 == (nowDetails[HOUR_OF_DAY] - smartDetail[HOUR_OF_DAY])) {
                        long minute = nowDetails[MINUTE] - smartDetail[MINUTE];
                        if (minute < 1) {
                            smartDate = "刚刚";
                        } else if (minute < 31) {
                            smartDate = minute + "分钟前";
                        } else {
                            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                            smartDate = sdf.format(date);
                        }
                    } else {
                        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                        smartDate = sdf.format(date);
                    }
                } else if (day >= -1) {//tomorrow
                    smartDate = "明天";
                } else if (day >= -2) {//the day after tomorrow
                    smartDate = "后天";
                } else {
                    smartDate = "本月" + String.valueOf(smartDetail[2]) + "日";
                }
            } else {//!!!
                smartDate = String.valueOf(smartDetail[1]) + "月" + String.valueOf(smartDetail[2]) + "日";
            }
        } else {//!!!
            smartDate = String.valueOf(smartDetail[0]) + "年" + String.valueOf(smartDetail[1]) + "月";
        }

        //		System.out.println("返回智能日期" + smartDate);
        return smartDate;
    }

    /**
     * TODO 5、获取日期 年，月， 日 对应值
     *
     * @param date
     * @return
     */
    public static int[] getDateDetail(Date date) {
        return date == null ? null : getDateDetail(date.getTime());
    }

    /**
     * 获取日期 年，月， 日 对应值
     *
     * @param time
     * @return
     */
    public static int[] getDateDetail(long time) {
        final Calendar mCalendar = Calendar.getInstance();
        mCalendar.setTimeInMillis(time);
        return new int[]{
                mCalendar.get(Calendar.YEAR),//0
                mCalendar.get(Calendar.MONTH) + 1,//1
                mCalendar.get(Calendar.DAY_OF_MONTH),//2
        };
    }

    /**
     * TODO 6、获取日期  时， 分， 秒 对应值
     *
     * @param time
     * @return
     */
    public static int[] getTimeDetail(long time) {
        final Calendar mCalendar = Calendar.getInstance();
        mCalendar.setTimeInMillis(time);
        return new int[]{
                mCalendar.get(Calendar.HOUR_OF_DAY),//3
                mCalendar.get(Calendar.MINUTE),//4
                mCalendar.get(Calendar.SECOND)//5
        };
    }

    /**
     * TODO 7、获取日期 年，月， 日， 时， 分， 秒 对应值
     *
     * @param time
     * @return
     */
    public static int[] getWholeDetail(long time) {
        final Calendar mCalendar = Calendar.getInstance();
        mCalendar.setTimeInMillis(time);
        return new int[]{
                mCalendar.get(Calendar.YEAR),//0
                mCalendar.get(Calendar.MONTH) + 1,//1
                mCalendar.get(Calendar.DAY_OF_MONTH),//2
                mCalendar.get(Calendar.HOUR_OF_DAY),//3
                mCalendar.get(Calendar.MINUTE),//4
                mCalendar.get(Calendar.SECOND)//5
        };
    }

    /**
     * TODO 8、获取两个时间的时间间隔
     *
     * @param sdf
     * @param dateLong0
     * @param dateLong1
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static long getBetween(SimpleDateFormat sdf, long dateLong0, long dateLong1) {
        if (sdf == null) {
            sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        }
        Date date0;
        Date date1;
        long between = 0;
        try {

            date0 = (Date) sdf.parse(sdf.format(new Date(dateLong0)));
            date1 = (Date) sdf.parse(sdf.format(new Date(dateLong1)));
            between = (date0.getTime() - date1.getTime()) / 1000;//除以1000是为了转换成秒
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //		System.out.println("between=" + String.valueOf(between));
        return between;
    }

    /**
     * TODO 9、根据生日获取年龄
     *
     * @param birthday
     * @return
     */
    public static int getAge(Date birthday) {
        if (birthday == null) {
            return 0;
        }
        if (birthday.getYear() > getDateDetail(System.currentTimeMillis())[0]) {
            birthday.setYear(birthday.getYear() - TimeUtil.SYSTEM_START_DATE[0]);
        }

        return getAge(new int[]{birthday.getYear(), birthday.getMonth(), birthday.getDay()});
    }

    /**
     * 根据生日获取年龄
     *
     * @param birthday
     * @return
     */
    public static int getAge(long birthday) {
        return getAge(getDateDetail(birthday));
    }

    /**
     * 根据生日获取年龄
     *
     * @return
     */
    public static int getAge(int[] birthdayDetail) {
        if (birthdayDetail == null || birthdayDetail.length < 3) {
            return 0;
        }

        int[] nowDetails = getDateDetail(System.currentTimeMillis());

        int age = nowDetails[0] - birthdayDetail[0];

        if (nowDetails[1] < birthdayDetail[1]) {
            age = age - 1;
        } else if (nowDetails[1] == birthdayDetail[1]) {
            if (nowDetails[2] < birthdayDetail[2]) {
                age = age - 1;
            }
        }

        return age;
    }


    /**
     * TODO 10、根据生日计算星座
     *
     * @param birthday
     * @return constellation
     */
    public static String getStar(Date birthday) {
        Calendar c = Calendar.getInstance();
        c.setTime(birthday);
        int month = c.get(Calendar.MONTH);                // 月份从0 ~ 11
        int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
        int[] DayArr = {19, 18, 20, 19, 20, 21, 22, 22, 22, 23, 22, 21};
        String[] starArr = {"魔羯座", "水瓶座", "双鱼座", "白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座"};
        if (dayOfMonth > DayArr[month]) {
            month = month + 1;
            if (month == 12) {
                month = 0;
            }
        }
        return starArr[month];
    }


    /**
     * TODO 11、获取生日,不带年份
     *
     * @param date
     * @return
     */
    public static String getBirthday(Date date) {
        return getBirthday(date, false);
    }

    /**
     * 获取生日
     *
     * @param date
     * @param needYear
     * @return
     */
    public static String getBirthday(Date date, boolean needYear) {
        return date == null ? "" : getBirthday(date.getTime(), needYear);
    }

    /**
     * 获取生日,不带年份
     *
     * @param date
     * @return
     */
    public static String getBirthday(long date) {
        return getBirthday(date, false);
    }

    /**
     * 获取生日
     *
     * @param date
     * @param needYear
     * @return
     */
    public static String getBirthday(long date, boolean needYear) {
        int[] details = TimeUtil.getWholeDetail(date);

        if (needYear) {
            return details[0] + "年" + details[1] + "月" + details[2] + "日";
        }
        return details[1] + "月" + details[2] + "日";
    }

    /**
     * TODO 12、获取智能生日
     *
     * @return
     */
    public static String getSmartBirthday(int[] birthdayDetails) {
        if (birthdayDetails == null || birthdayDetails.length < 3) {
            return "";
        }
        if (birthdayDetails[0] > TimeUtil.SYSTEM_START_DATE[0]) {
            birthdayDetails[0] = birthdayDetails[0] - TimeUtil.SYSTEM_START_DATE[0];
        }
        return getSmartBirthday(new Date(birthdayDetails[0], birthdayDetails[1], birthdayDetails[2]));
    }

    /**
     * @param birthday
     * @return
     */
    public static String getSmartBirthday(Date birthday) {
        if (birthday == null) {
            return "";
        }
        if (birthday.getYear() > getDateDetail(System.currentTimeMillis())[0]) {
            birthday.setYear(birthday.getYear() - TimeUtil.SYSTEM_START_DATE[0]);
        }

        return getSmartBirthday(birthday.getTime(), false) + " " + (TimeUtil
                .getDateDetail(System.currentTimeMillis())[0] - birthday.getYear()) + "岁";
    }

    /**
     * 获取智能生日
     *
     * @param birthday
     * @param needYear
     * @return
     */
    public static String getSmartBirthday(long birthday, boolean needYear) {
        int[] birthdayDetails = getDateDetail(birthday);
        int[] nowDetails = getDateDetail(System.currentTimeMillis());

        Calendar birthdayCalendar = Calendar.getInstance();
        birthdayCalendar.set(birthdayDetails[0], birthdayDetails[1], birthdayDetails[2]);

        Calendar nowCalendar = Calendar.getInstance();
        nowCalendar.set(nowDetails[0], nowDetails[1], nowDetails[2]);

        int days = birthdayCalendar.get(Calendar.DAY_OF_YEAR) - nowCalendar.get(Calendar.DAY_OF_YEAR);
        if (days < 8) {
            if (days >= 3) {
                return days + "天后";
            }
            if (days >= 2) {
                return Day.NAME_THE_DAY_AFTER_TOMORROW;
            }
            if (days >= 1) {
                return Day.NAME_TOMORROW;
            }
            if (days >= 0) {
                return Day.NAME_TODAY;
            }
        }

        if (needYear) {
            return birthdayDetails[0] + "年" + birthdayDetails[1] + "月" + birthdayDetails[2] + "日";
        }
        return birthdayDetails[1] + "月" + birthdayDetails[2] + "日";
    }


    public static boolean fomerIsBigger(int[] fomer, int[] current) {
        if (fomer == null || current == null) {
            Log.e(TAG, "fomerIsBigger  fomer == null || current == null" +
                    " >>  return false;");
            return false;
        }
        int compareLength = fomer.length < current.length ? fomer.length : current.length;

        for (int i = 0; i < compareLength; i++) {
            if (fomer[i] < current[i]) {
                return false;
            }
            if (fomer[i] > current[i]) {
                return true;
            }
        }

        return false;
    }

    /**
     * TODO 13、判断现在是否属于一段时间,不包含端点
     *
     * @param start
     * @param end
     * @return
     */
    public static boolean isNowInTimeArea(int[] start, int[] end) {
        return isInTimeArea(getTimeDetail(System.currentTimeMillis()), start, end);
    }

    /**
     * 判断一个时间是否属于一段时间,不包含端点
     * (start, end)可跨越0:00,即start < end也行
     *
     * @param time
     * @param start
     * @param end
     * @return
     */
    public static boolean isInTimeArea(int[] time, int[] start, int[] end) {
        if (fomerIsBigger(end, start)) {
            return fomerIsBigger(time, start) && fomerIsBigger(end, time);
        }

        if (fomerIsBigger(time, start) && fomerIsBigger(maxTimeDetails, time)) {
            return true;
        }
        if (fomerIsBigger(time, minTimeDetails) && fomerIsBigger(end, time)) {
            return true;
        }

        return false;
    }

    private static SimpleDateFormat sf;
    private static SimpleDateFormat sdf;

    /**
     * 14、获取系统时间 格式为："yyyy/MM/dd "
     **/
    public static String getCurrentDate() {
        Date d = new Date();
        sf = new SimpleDateFormat("yyyy年MM月dd日");
        return sf.format(d);
    }

    /**
     * 15、获取系统时间 格式为："yyyy "
     **/
    public static String getCurrentYear() {
        Date d = new Date();
        sf = new SimpleDateFormat("yyyy");
        return sf.format(d);
    }

    /**
     * 16、获取系统时间 格式为："MM"
     **/
    public static String getCurrentMonth() {
        Date d = new Date();
        sf = new SimpleDateFormat("MM");
        return sf.format(d);
    }

    /**
     * 17、获取系统时间 格式为："dd"
     **/
    public static String getCurrentDay() {
        Date d = new Date();
        sf = new SimpleDateFormat("dd");
        return sf.format(d);
    }

    /**
     * 18、获取当前时间戳
     *
     * @return
     */
    public static long getCurrentTime() {
        long d = new Date().getTime() / 1000;
        return d;
    }

    /**
     * 19、时间戳转换成字符窜
     */
    public static String getDateToString(long time) {
        Date d = new Date(time * 1000);
        sf = new SimpleDateFormat("yyyy年MM月dd日");
        return sf.format(d);
    }

    /**
     * 20、时间戳中获取年
     */
    public static String getYearFromTime(long time) {
        Date d = new Date(time * 1000);
        sf = new SimpleDateFormat("yyyy");
        return sf.format(d);
    }

    /**
     * 21、时间戳中获取月
     */
    public static String getMonthFromTime(long time) {
        Date d = new Date(time * 1000);
        sf = new SimpleDateFormat("MM");
        return sf.format(d);
    }

    /**
     * 22、时间戳中获取日
     */
    public static String getDayFromTime(long time) {
        Date d = new Date(time * 1000);
        sf = new SimpleDateFormat("dd");
        return sf.format(d);
    }

    /**
     * 23、将字符串转为时间戳
     */
    public static long getStringToDate(String time) {
        sdf = new SimpleDateFormat("yyyy年MM月dd日");
        Date date = new Date();
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date.getTime();
    }

    /**
     * 24、获取指定日期周几
     *
     * @param date 指定日期
     * @return 返回值为： "周日", "周一", "周二", "周三", "周四", "周五", "周六"
     */
    public static String getWeekOfDate(Date date) {
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (week < 0)
            week = 0;
        return weekDays[week];
    }

    /**
     * 25、获取指定日期对应周几的序列
     *
     * @param date 指定日期
     * @return 周一：1    周二：2    周三：3    周四：4    周五：5    周六：6    周日：7
     */
    public static int getIndexWeekOfDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int index = calendar.get(Calendar.DAY_OF_WEEK);
        if (index == 1) {
            return 7;
        } else {
            return --index;
        }
    }

    /**
     * 26、返回当前月份
     *
     * @return
     */
    public static int getNowMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 27、获取当前月号
     *
     * @return
     */
    public static int getNowDay() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DATE);
    }

    /**
     * 28、获取当前年份
     *
     * @return
     */
    public static int getNowYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 29、获取本月份的天数
     *
     * @return
     */
    public static int getNowDaysOfMonth() {
        Calendar calendar = Calendar.getInstance();
        return daysOfMonth(calendar.get(Calendar.YEAR), calendar.get(Calendar.DATE) + 1);
    }

    /**
     * 30、获取指定月份的天数
     *
     * @param year  年份
     * @param month 月份
     * @return 对应天数
     */
    public static int daysOfMonth(int year, int month) {
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                if ((year % 4 == 0 && year % 100 == 0) || year % 400 != 0) {
                    return 29;
                } else {
                    return 28;
                }
            default:
                return -1;
        }
    }

    /**
     * 获取当前日期时间 / 得到今天的日期
     * str yyyyMMddhhMMss 之类的
     *
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static String getCurrentDateTime(String format) {
        return simpleDateFormat(format, new Date());
    }

    /**
     * 将date转换成format格式的日期
     *
     * @param format 格式
     * @param date   日期
     * @return
     */
    public static String simpleDateFormat(String format, Date date) {
        if (isNullString(format)) {
            format = "yyyy-MM-dd HH:mm:ss SSS";
        }
        String content = new SimpleDateFormat(format).format(date);
        return content;
    }

    /**
     * 判断字符串是否为空 为空即true
     *
     * @param str 字符串
     * @return
     */
    public static boolean isNullString(@Nullable String str) {
        return str == null || str.length() == 0 || "null".equals(str);
    }

    /**
     * 根据年 月 获取对应的月份 天数
     */
    public static int getDaysByYearMonth(int year, int month) {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    /**
     * 时间的格式化
     * @param textView
     * @param millisecond
     */
    public static void updateTime(TextView textView, int millisecond) {
        int second = millisecond / 1000; //总共换算的秒
        int hh = second / 3600;  //小时
        int mm = second % 3600 / 60; //分钟
        int ss = second % 60; //时分秒中的秒的得数

        String str = null;
        if (hh != 0) {
            //如果是个位数的话，前面可以加0  时分秒
            str = String.format(Locale.CHINA,"%02d:%02d:%02d", hh, mm, ss);
        } else {
            str = String.format(Locale.CHINA,"%02d:%02d", mm, ss);
        }
        textView.setText(str);
    }
}

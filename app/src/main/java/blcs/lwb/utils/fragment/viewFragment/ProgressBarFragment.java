package blcs.lwb.utils.fragment.viewFragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import blcs.lwb.lwbtool.base.BasePresenter;
import blcs.lwb.lwbtool.utils.LogUtils;
import blcs.lwb.lwbtool.utils.StringUtils;
import blcs.lwb.lwbtool.utils.progressbar.RxIconRoundProgressBar;
import blcs.lwb.lwbtool.utils.progressbar.RxProgressBar;
import blcs.lwb.lwbtool.utils.progressbar.RxRoundProgress;
import blcs.lwb.lwbtool.utils.progressbar.RxRoundProgressBar;
import blcs.lwb.lwbtool.utils.progressbar.RxTextRoundProgressBar;
import blcs.lwb.utils.R;
import blcs.lwb.utils.fragment.BaseFragment;
import butterknife.BindView;
import butterknife.OnClick;

public class ProgressBarFragment extends BaseFragment {
    double money = 1000;
    Thread downLoadThread;
    Thread downLoadThread1;
    Thread downLoadThread2;
    Thread downLoadRxRoundPdThread;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            flikerbar.setProgress(msg.arg1);
            roundFlikerbar.setProgress(msg.arg1);
            if (msg.arg1 == 100) {
                flikerbar.finishLoad();
                roundFlikerbar.finishLoad();
            }
        }
    };
    @BindView(R.id.flikerbar)
    RxProgressBar flikerbar;
    @BindView(R.id.round_flikerbar)
    RxProgressBar roundFlikerbar;
    @BindView(R.id.rx_round_pd1)
    RxRoundProgressBar rxRoundPd1;
    @BindView(R.id.rx_round_pd2)
    RxRoundProgressBar rxRoundPd2;
    @BindView(R.id.rx_round_pd3)
    RxRoundProgressBar rxRoundPd3;
    @BindView(R.id.rx_round_pd4)
    RxRoundProgressBar rxRoundPd4;
    @BindView(R.id.rx_round_pd5)
    RxRoundProgressBar rxRoundPd5;
    @BindView(R.id.rx_round_pd6)
    RxRoundProgressBar rxRoundPd6;
    @BindView(R.id.rx_round_pd7)
    RxRoundProgressBar rxRoundPd7;
    @BindView(R.id.rx_round_pd8)
    RxIconRoundProgressBar rxRoundPd8;
    @BindView(R.id.rx_round_pd9)
    RxIconRoundProgressBar rxRoundPd9;
    @BindView(R.id.rx_round_pd10)
    RxIconRoundProgressBar rxRoundPd10;
    @BindView(R.id.rx_round_pd11)
    RxIconRoundProgressBar rxRoundPd11;
    @BindView(R.id.rx_round_pd12)
    RxIconRoundProgressBar rxRoundPd12;
    @BindView(R.id.rx_round_pd13)
    RxIconRoundProgressBar rxRoundPd13;
    @BindView(R.id.rx_round_pd14)
    RxIconRoundProgressBar rxRoundPd14;
    @BindView(R.id.rx_round_pd15)
    RxTextRoundProgressBar rxRoundPd15;
    @BindView(R.id.rx_round_pd16)
    RxTextRoundProgressBar rxRoundPd16;
    @BindView(R.id.rx_round_pd17)
    RxTextRoundProgressBar rxRoundPd17;
    @BindView(R.id.progress_one)
    RxIconRoundProgressBar progressOne;
    @BindView(R.id.progress_two)
    RxRoundProgressBar progressTwo;
    @BindView(R.id.progress_three)
    RxTextRoundProgressBar progressThree;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.pb_line_of_credit)
    ProgressBar pbLineOfCredit;
    @BindView(R.id.textView5)
    TextView textView5;
    @BindView(R.id.roundProgressBar1)
    RxRoundProgress mRxRoundProgress1;
    @BindView(R.id.activity_round_progress_bar)
    LinearLayout activityRoundProgressBar;
    private double progress;
    private int progress1;
    private int money1 = 10000;
    private int mRxRoundProgress;

    Handler mRxRoundPdHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            rxRoundPd1.setProgress(mRxRoundProgress);
            rxRoundPd2.setProgress(mRxRoundProgress);
            rxRoundPd3.setProgress(mRxRoundProgress);
            rxRoundPd4.setProgress(mRxRoundProgress);
            rxRoundPd5.setProgress(mRxRoundProgress);
            rxRoundPd6.setProgress(mRxRoundProgress);
            rxRoundPd7.setProgress(mRxRoundProgress);
            rxRoundPd8.setProgress(mRxRoundProgress);
            rxRoundPd9.setProgress(mRxRoundProgress);
            rxRoundPd10.setProgress(mRxRoundProgress);
            rxRoundPd11.setProgress(mRxRoundProgress);
            rxRoundPd12.setProgress(mRxRoundProgress);
            rxRoundPd13.setProgress(mRxRoundProgress);
            rxRoundPd14.setProgress(mRxRoundProgress);
            rxRoundPd15.setProgress(mRxRoundProgress);
            rxRoundPd16.setProgress(mRxRoundProgress);
            rxRoundPd17.setProgress(mRxRoundProgress);
            progressOne.setProgress(mRxRoundProgress);
            progressTwo.setProgress(mRxRoundProgress);
            progressThree.setProgress(mRxRoundProgress);
        }
    };


    private int mRxRoundPdMax = 100;

    @Override
    public void setMiddleTitle(Toolbar title) {

    }

    @Override
    protected void initView() {
        LogUtils.e("========initView========");
        initRoundProgress();
        initLineProgress();
        initRxRoundPd();
        downLoad();
    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

    @Override
    protected int bindLayout() {
        return R.layout.fragment_progressbar;
    }

    @Override
    public void popBackListener(int returnCode, Bundle bundle) {

    }

    private void initRoundProgress() {
        // TODO Auto-generated method stub
        progress = 0;// 进度初始化
        mRxRoundProgress1.setProgress(progress);
        mRxRoundProgress1.setMax(getMax(money));

        downLoadThread2 = new Thread(new Runnable() {

            @Override
            public void run() {

                try {
                    while (!downLoadThread2.isInterrupted()) {
                        while (progress < mRxRoundProgress1.getMax()) {
                            progress += mRxRoundProgress1.getMax() * 0.01;
                            if (progress < mRxRoundProgress1.getMax()) {
                                mRxRoundProgress1.setProgress(progress);
                            }
                            Thread.sleep(8);
                        }
                        while (progress > 0) {
                            progress -= mRxRoundProgress1.getMax() * 0.01;
                            if (progress > 0) {
                                mRxRoundProgress1.setProgress(progress);
                            }
                            Thread.sleep(8);
                        }

                        if (money != 0) {
                            while (progress < money) {
                                progress += money * 0.01;
                                mRxRoundProgress1.setProgress(progress);
                                Thread.sleep(10);
                            }
                        }

                        mRxRoundProgress1.setProgress(money);
                    }
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        });
        downLoadThread2.start();
    }

    private void initLineProgress() {
        // TODO Auto-generated method stub
        progress1 = 0;// 进度初始化

        pbLineOfCredit.setProgress(progress1);
        pbLineOfCredit.setMax(getMax(money1));

        downLoadThread1 = new Thread(new Runnable() {

            @Override
            public void run() {

                try {
                    while (!downLoadThread1.isInterrupted()) {
                        while (progress1 < pbLineOfCredit.getMax()) {
                            progress1 += pbLineOfCredit.getMax() * 0.01;
                            if (progress1 < pbLineOfCredit.getMax()) {
                                pbLineOfCredit.setProgress(progress1);
                                //tv_current.setText(progress1+"");
                            }
                            Thread.sleep(8);
                        }
                        while (progress1 > 0) {
                            progress1 -= pbLineOfCredit.getMax() * 0.01;
                            if (progress1 > 0) {
                                pbLineOfCredit.setProgress(progress1);
                                //tv_current.setText(progress1+"");
                            }
                            Thread.sleep(8);
                        }

                        while (progress1 < money1) {
                            progress1 += money1 * 0.01;
                            pbLineOfCredit.setProgress(progress1);
                            //tv_current.setText(progress1+"");
                            Thread.sleep(10);
                        }
                    }
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        });
        downLoadThread1.start();
    }

    private void initRxRoundPd() {
        // TODO Auto-generated method stub
        mRxRoundProgress = 0;// 进度初始化
        rxRoundPd1.setMax(mRxRoundPdMax);
        rxRoundPd2.setMax(mRxRoundPdMax);
        rxRoundPd3.setMax(mRxRoundPdMax);
        rxRoundPd4.setMax(mRxRoundPdMax);
        rxRoundPd5.setMax(mRxRoundPdMax);
        rxRoundPd6.setMax(mRxRoundPdMax);
        rxRoundPd7.setMax(mRxRoundPdMax);
        rxRoundPd8.setMax(mRxRoundPdMax);
        rxRoundPd9.setMax(mRxRoundPdMax);
        rxRoundPd10.setMax(mRxRoundPdMax);
        rxRoundPd11.setMax(mRxRoundPdMax);
        rxRoundPd12.setMax(mRxRoundPdMax);
        rxRoundPd13.setMax(mRxRoundPdMax);
        rxRoundPd14.setMax(mRxRoundPdMax);
        rxRoundPd15.setMax(mRxRoundPdMax);
        rxRoundPd16.setMax(mRxRoundPdMax);
        rxRoundPd17.setMax(mRxRoundPdMax);
        progressOne.setMax(mRxRoundPdMax);
        progressTwo.setMax(mRxRoundPdMax);
        progressThree.setMax(mRxRoundPdMax);
        downLoadRxRoundPdThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (!downLoadRxRoundPdThread.isInterrupted()) {
                        while (mRxRoundProgress < rxRoundPd1.getMax()) {
                            mRxRoundProgress += rxRoundPd1.getMax() * 0.01;
                            if (mRxRoundProgress < rxRoundPd1.getMax()) {
                                Message message = new Message();
                                message.what = 101;
                                mRxRoundPdHandler.sendMessage(message);
                            }
                            Thread.sleep(8);
                        }
                        while (mRxRoundProgress > 0) {
                            mRxRoundProgress -= rxRoundPd1.getMax() * 0.01;
                            if (mRxRoundProgress > 0) {
                                Message message = new Message();
                                message.what = 101;
                                mRxRoundPdHandler.sendMessage(message);
                            }
                            Thread.sleep(8);
                        }

                        while (mRxRoundProgress < mRxRoundPdMax) {
                            mRxRoundProgress += mRxRoundPdMax * 0.01;
                            Message message = new Message();
                            message.what = 101;
                            mRxRoundPdHandler.sendMessage(message);
                            Thread.sleep(10);
                        }
                    }
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        });
        downLoadRxRoundPdThread.start();
    }

    private void initFlikerProgressBar() {
        if (!flikerbar.isFinish()) {
            flikerbar.toggle();
            roundFlikerbar.toggle();

            if (flikerbar.isStop()) {
                downLoadThread.interrupt();
            } else {
                downLoad();
            }

        }
    }
    //==============================================================================================Flikerbar 加载事件处理 end
    private void downLoad() {
        downLoadThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (!downLoadThread.isInterrupted()) {
                        float progress = flikerbar.getProgress();
                        progress += 2;
                        Thread.sleep(200);
                        Message message = handler.obtainMessage();
                        message.arg1 = (int) progress;
                        handler.sendMessage(message);
                        if (progress == 100) {
                            break;
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        downLoadThread.start();
    }


    @Override
    public void onStop() {
        super.onStop();
        downLoadThread.interrupt();
        downLoadThread1.interrupt();
        downLoadThread2.interrupt();
        downLoadRxRoundPdThread.interrupt();
        mRxRoundPdHandler.removeCallbacksAndMessages(null);
        handler.removeCallbacksAndMessages(null);
    }


    @OnClick({R.id.flikerbar, R.id.round_flikerbar,R.id.btn_reload})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.flikerbar:
                initFlikerProgressBar();
                break;
            case R.id.round_flikerbar:
                initFlikerProgressBar();
                break;
            case R.id.btn_reload:
                downLoadThread.interrupt();
                // 重新加载
                flikerbar.reset();
                roundFlikerbar.reset();
                downLoad();
                break;
        }
    }
    private int getMax(double currentProgress) {
        if (currentProgress < 100 && currentProgress > 0) {
            return 100;
        } else if (currentProgress >= 100 && currentProgress < 1000) {
            return 1000;
        } else if (currentProgress >= 1000 && currentProgress < 5000) {
            return 5000;
        } else if (currentProgress >= 5000 && currentProgress < 20000) {
            return 20000;
        } else if (currentProgress >= 20000 && currentProgress < 100000) {
            return 100000;
        } else if (currentProgress >= 100000) {
            return StringUtils.stringToInt(currentProgress * 1.1 + "");
        } else {
            return StringUtils.stringToInt(currentProgress + "");
        }
    }
}

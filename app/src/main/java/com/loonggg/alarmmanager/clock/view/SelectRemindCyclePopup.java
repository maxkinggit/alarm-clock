package com.loonggg.alarmmanager.clock.view;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.loonggg.alarmmanager.clock.R;

public class SelectRemindCyclePopup implements OnClickListener {
    private TextView  tv_sure,every_day, tv_drugcycle_once;
    public PopupWindow mPopupWindow;
    private SelectRemindCyclePopupOnClickListener selectRemindCyclePopupListener;

    public PopupWindow getmPopupWindow() {
        return mPopupWindow;
    }

    private Context mContext;

    @SuppressWarnings("deprecation")
    public SelectRemindCyclePopup(Context context) {
        mContext = context;
        mPopupWindow = new PopupWindow(context);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.setWidth(WindowManager.LayoutParams.FILL_PARENT);
        mPopupWindow.setHeight(WindowManager.LayoutParams.FILL_PARENT);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setAnimationStyle(R.style.AnimBottom);
        mPopupWindow.setContentView(initViews());
        mPopupWindow.getContentView().setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mPopupWindow.setFocusable(false);
                // mPopupWindow.dismiss();
                return true;
            }
        });

    }

    public View initViews() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.selectremindcycle_pop_window,
                null);

        tv_drugcycle_once = (TextView) view.findViewById(R.id.tv_drugcycle_once);
        every_day = (TextView) view.findViewById(R.id.tv_drugcycle_0);

        tv_sure = (TextView) view.findViewById(R.id.tv_drugcycle_sure);
        tv_drugcycle_once.setOnClickListener(this);
        tv_sure.setOnClickListener(this);
        every_day.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        Drawable nav_right = mContext.getResources().getDrawable(R.drawable.cycle_check);
        nav_right.setBounds(0, 0, nav_right.getMinimumWidth(), nav_right.getMinimumHeight());
        switch (v.getId()) {
            case R.id.tv_drugcycle_once:
                selectRemindCyclePopupListener.obtainMessage(9, "");
                break;
            case R.id.tv_drugcycle_0:
                selectRemindCyclePopupListener.obtainMessage(8, "");
                break;
            default:
                break;
        }

    }

    public interface SelectRemindCyclePopupOnClickListener {
        void obtainMessage(int flag, String ret);
    }

    public void setOnSelectRemindCyclePopupListener(SelectRemindCyclePopupOnClickListener l) {
        this.selectRemindCyclePopupListener = l;
    }

    public void dismiss() {
        if (mPopupWindow != null && mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        }
    }

    public void showPopup(View rootView) {
        // 第一个参数是要将PopupWindow放到的View，第二个参数是位置，第三第四是偏移值
        mPopupWindow.showAtLocation(rootView, Gravity.BOTTOM, 0, 0);
    }
}

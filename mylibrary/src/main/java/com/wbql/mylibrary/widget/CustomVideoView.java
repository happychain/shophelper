package com.wbql.mylibrary.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

/**
 * @author  Created by MrRight on 2017/10/24.
 */
public class CustomVideoView extends VideoView {
    private Context mContext;
    final int defaultHeight=200; //单位DP

    public CustomVideoView(Context context) {
        super(context);
        mContext=context;
    }

    public CustomVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext=context;
    }

    public CustomVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext=context;
    }

    //widthMeasureSpec 和 heightMeasureSpec的值 由父容器决定
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super .onMeasure(widthMeasureSpec,heightMeasureSpec);
        // 默认高度，为了自动获取到focus
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = width;
        // 这个之前是默认的拉伸图像
        if (this.width > 0 && this.height > 0) {
            width = this.width;
            height = this.height;
        }
        setMeasuredDimension(width, height);
    }


    private int width,height;

    public void setMeasure(int width, int height) {
        this.width = width;
        this.height = height;
    }
}

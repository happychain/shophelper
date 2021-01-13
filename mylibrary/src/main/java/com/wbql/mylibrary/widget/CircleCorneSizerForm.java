package com.wbql.mylibrary.widget;

/**
 * Created by liuqiang 2020-12-01 .
 */
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;

import com.squareup.picasso.Transformation;
import com.wbql.mylibrary.utils.AppUtil;

/**
 * Created by libin on 16/7/22.
 * 圆角切片
 */
public class CircleCorneSizerForm implements Transformation {

    private int mSize= 0;
    public CircleCorneSizerForm(int size) {
        this.mSize = size;
    }

    @Override
    public Bitmap transform(Bitmap source) {
        int widthLight = source.getWidth();
        int heightLight = source.getHeight();

        Bitmap output = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(output);
        Paint paintColor = new Paint();
        paintColor.setFlags(Paint.ANTI_ALIAS_FLAG);

        RectF rectF = new RectF(new Rect(0, 0, widthLight, heightLight));

        canvas.drawRoundRect(rectF, dp2px(mSize), dp2px(mSize), paintColor);

        Paint paintImage = new Paint();
        paintImage.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP));
        canvas.drawBitmap(source, 0, 0, paintImage);
        source.recycle();
        return output;
    }

    @Override
    public String key() {
        return "roundcorner";
    }


    private int dp2px(int dp) {
        float density = AppUtil.getContext().getResources().getDisplayMetrics().density;
        return (int) (dp * density);
    }
}

package com.qwlyz.androidstudy;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class FadingRecyclerView extends RecyclerView {

    private Paint paint;
    private int height;
    private int width;
    private boolean isFadingEnabled = true;  // 渐隐效果开关，默认开启

    public FadingRecyclerView(Context context) {
        super(context);
        init();
    }

    public FadingRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FadingRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        height = h;
        width = w;

        LinearGradient linearGradient = new LinearGradient(
                0, 0, 0, height / 2,
                new int[]{0x00000000, 0x00000000, 0xFFFFFFFF, 0xFFFFFFFF},
                new float[]{0f, 0.8f, 0.85f, 1f},
                Shader.TileMode.CLAMP);
        paint.setShader(linearGradient);
    }

    @Override
    public void draw(Canvas c) {
        if (isFadingEnabled) {
            c.saveLayer(0, 0, width, height, null, Canvas.ALL_SAVE_FLAG);
            super.draw(c);
            c.drawRect(0, 0, width, height / 2, paint);
            c.restore();
        } else {
            super.draw(c);
        }
    }

    // 方法来启用或禁用渐隐效果
    public void setFadingEnabled(boolean enabled) {
        if (isFadingEnabled != enabled) {  // 只有在状态变化时才进行重绘
            isFadingEnabled = enabled;
            Log.d("liuyuzhe", "重新绘制");
            invalidate();  // 重新绘制视图
        }
    }

    // 方法来获取渐隐效果的状态
    public boolean isFadingEnabled() {
        return isFadingEnabled;
    }
}

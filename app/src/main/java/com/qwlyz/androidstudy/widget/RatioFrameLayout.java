 package com.qwlyz.androidstudy.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;

import com.qwlyz.androidstudy.R;

 /**
 * author: baiiu
 * date: on 16/8/17 15:45
 * description: 宽度固定,根据宽度来确定高度
 */
public class RatioFrameLayout extends FrameLayout {
    /**
     * 默认的宽高比,用于宽高都是wrap_content时,以宽为准,宽高相同
     *
     * height = width * scale
     */
    private static final float DEFAULT_SCALE = 1F;
    private float mDividerScale = DEFAULT_SCALE;
    private float mMultiplyRatio = 0;
     private float RATIO_BIG = 1.76f;
     private float RATIO_BIG_3_2 = 1.5f;

     public RatioFrameLayout(Context context) {
        this(context, null);
    }

    public RatioFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.d("wqq_ratio_image", "init.Context context, AttributeSet attrs: ");
        init(context, attrs);
    }

    public RatioFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RatioByWidthView);
        mDividerScale = typedArray.getFloat(R.styleable.RatioByWidthView_ratio, RATIO_BIG);
        mMultiplyRatio = typedArray.getFloat(R.styleable.RatioByWidthView_multiplyRatio, 0);

        int imageType = typedArray.getInt(R.styleable.RatioByWidthView_imageType, -1);
        switch (imageType) {
            case 0: // origin
                mDividerScale = 1.0F;
                break;
            case 1:// big
                mDividerScale = RATIO_BIG;
                break;
            case 2:
                mDividerScale = RATIO_BIG_3_2;
                break;
            default:

        }

        typedArray.recycle();
    }

    @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (isInEditMode()) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            return;
        }
        Log.d("wqq_ratio_image", "onMeasure: "+mDividerScale);
        int mode_width = MeasureSpec.getMode(widthMeasureSpec);
        int size_width = MeasureSpec.getSize(widthMeasureSpec);

        int mode_height = MeasureSpec.getMode(heightMeasureSpec);
        int size_height = MeasureSpec.getSize(heightMeasureSpec);

        int width_result;
        int height_result;

        width_result = size_width;

        if (mode_height == MeasureSpec.EXACTLY) {
            height_result = size_height;
        } else {
            if (mMultiplyRatio != 0) {
                height_result = (int) (width_result * mMultiplyRatio + 0.5);
            } else {
                Log.d("wqq_ratio_image", "onMeasure. 图片加载的尺寸比例: "+mDividerScale);
                height_result = (int) (width_result / mDividerScale + 0.5);
            }
        }


        int measureSpecWidth = MeasureSpec.makeMeasureSpec(width_result, MeasureSpec.EXACTLY);
        int measureSpecHeight = MeasureSpec.makeMeasureSpec(height_result, MeasureSpec.EXACTLY);

        super.onMeasure(measureSpecWidth, measureSpecHeight);
        Log.d("wqq_ratio_image", "onMeasure.after: "+mDividerScale);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        Log.d("wqq_ratio_image", "onFinishInflate: ");
    }

    public void setRatio(float ratio) {
        Log.d("wqq_ratio_image", "setRatio: "+ratio);

        mDividerScale = ratio;
        requestLayout();
    }
}

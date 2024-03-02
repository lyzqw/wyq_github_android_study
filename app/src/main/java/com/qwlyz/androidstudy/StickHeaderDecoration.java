package com.qwlyz.androidstudy;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ScreenUtils;
import com.qwlyz.androidstudy.fragment.StickFragment;


public class StickHeaderDecoration extends RecyclerView.ItemDecoration {

    private final View tabView;
    //头部的高
    private int mItemHeaderHeight;
    private int mTextPaddingLeft;

    //画笔，绘制头部和分割线
    private Paint mItemHeaderPaint;
    private Paint mTextPaint;
    private Paint mLinePaint;

    private Rect mTextRect;


    public StickHeaderDecoration(Context context, RecyclerView rv) {
        mItemHeaderHeight = dp2px(context, 40);
        mTextPaddingLeft = dp2px(context, 6);

        mTextRect = new Rect();

        mItemHeaderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mItemHeaderPaint.setColor(Color.BLUE);

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextSize(46);
        mTextPaint.setColor(Color.WHITE);

        mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLinePaint.setColor(Color.GRAY);
        tabView = LayoutInflater.from(context).inflate(R.layout.item_horizontal_tab, null, false);
//        tabView.findViewById(R.id.nearby).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("liuyuzhe", "onClick: 附近");
//            }
//        });
        tabView.findViewById(R.id.recommend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("liuyuzhe", "onClick: 推荐");
            }
        });
    }

    /**
     * 绘制Item的分割线和组头
     *
     * @param c
     * @param parent
     * @param state
     */
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (parent.getAdapter() instanceof StickFragment.NBAStarAdapter) {
            StickFragment.NBAStarAdapter adapter = (StickFragment.NBAStarAdapter) parent.getAdapter();
            Log.d("liuyuzhe", "onDraw.position: " + (
                    ((LinearLayoutManager) (parent.getLayoutManager())).findFirstVisibleItemPosition()
            ));
            int count = parent.getChildCount();//获取可见范围内Item的总数
            int height = 0;
            for (int i = 0; i < count; i++) {
                View view = parent.getChildAt(i);
                int position = parent.getChildLayoutPosition(view);
                boolean isHeader = adapter.isGroupHeader(position);
                int left = parent.getPaddingLeft();
                int right = parent.getWidth() - parent.getPaddingRight();
                if (isHeader) {
//                    c.drawRect(left, view.getTop() - mItemHeaderHeight, right, view.getTop(), mItemHeaderPaint);
//                    mTextPaint.getTextBounds(adapter.getGroupName(position), 0, adapter.getGroupName(position).length(), mTextRect);
//                    c.drawText(adapter.getGroupName(position), left + mTextPaddingLeft, (view.getTop() - mItemHeaderHeight) + mItemHeaderHeight / 2 + mTextRect.height() / 2, mTextPaint);
//                    val.layout(left, view.getTop() - mItemHeaderHeight, right, view.getTop());
//                    val.draw(c);
//                    View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_horizontal_tab, null, false);
//                    inflate.measure(0,0);
//                    inflate.layout(0, 0, inflate.getWidth(), inflate.getHeight());
//                    c.save();
//                    c.translate(0,view.getTop() - mItemHeaderHeight);
//                    inflate.draw(c);
//                    c.restore();
                    tabView.measure(0, 0);
                    tabView.layout(0, 0, tabView.getWidth(), tabView.getHeight());
                    c.save();
                    c.translate(0, view.getTop() - mItemHeaderHeight);
                    tabView.draw(c);
                    c.restore();
                } else {
                    c.drawRect(left, view.getTop() - 1, right, view.getTop(), mLinePaint);
                }
                height += view.getHeight();
            }

        }
    }


    /**
     * 绘制Item的顶部布局（吸顶效果）
     *
     * @param c
     * @param parent
     * @param state
     */
//    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (parent.getAdapter() instanceof StickFragment.NBAStarAdapter) {
            StickFragment.NBAStarAdapter adapter = (StickFragment.NBAStarAdapter) parent.getAdapter();
            int position = ((LinearLayoutManager) (parent.getLayoutManager())).findFirstVisibleItemPosition();
            Log.d("liuyuzhe", "onDrawOver.position: " + position);
            View view = parent.findViewHolderForAdapterPosition(position).itemView;
            boolean isHeader = adapter.isGroupHeader(position + 1);
            if (adapter.getGroupName(position).isEmpty()) {
                return;
            }
//            if (position < 18) {
//                return;
//            }
            int top = parent.getPaddingTop();
            int left = parent.getPaddingLeft();
            int right = parent.getWidth() - parent.getPaddingRight();
            if (isHeader) {
//                c.save();
//                int bottom = Math.min(mItemHeaderHeight, view.getBottom());
//                tabView.measure(0,0);
//                tabView.layout(0,0,tabView.getWidth(),tabView.getHeight());
//                tabView.getLayoutParams().height = bottom;
////                c.translate(0,bottom);
//                tabView.draw(c);
//                c.restore();

//                tabView.layout(left, top + view.getTop() - mItemHeaderHeight, right, top + bottom);
//                tabView.draw(c);
//                c.drawRect(left, top + view.getTop() - mItemHeaderHeight, right, top + bottom, mItemHeaderPaint);
//                mTextPaint.getTextBounds(adapter.getGroupName(position), 0, adapter.getGroupName(position).length(), mTextRect);
//                c.drawText(adapter.getGroupName(position), left + mTextPaddingLeft, top + mItemHeaderHeight / 2 + mTextRect.height() / 2 - (mItemHeaderHeight - bottom), mTextPaint);
            } else {
                tabView.measure(0, 0);
                tabView.layout(0, 0, tabView.getWidth(), tabView.getHeight());
                c.save();
                c.translate(0, top);
                tabView.draw(c);
                c.restore();
                Log.d("liuyuzhe", "onDrawOver.top:" + top + ",bottom: " + (top + tabView.getHeight()));

//                tabView.layout(left, top, right, top + mItemHeaderHeight);
//                tabView.draw(c);
//                c.drawRect(left, top, right, top + mItemHeaderHeight, mItemHeaderPaint);
//                mTextPaint.getTextBounds(adapter.getGroupName(position), 0, adapter.getGroupName(position).length(), mTextRect);
//                c.drawText(adapter.getGroupName(position), left + mTextPaddingLeft, top + mItemHeaderHeight / 2 + mTextRect.height() / 2, mTextPaint);
            }
            c.save();
        }

    }

    /**
     * 设置Item的间距
     *
     * @param outRect
     * @param view
     * @param parent
     * @param state
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (parent.getAdapter() instanceof StickFragment.NBAStarAdapter) {
            StickFragment.NBAStarAdapter adapter = (StickFragment.NBAStarAdapter) parent.getAdapter();
            int position = parent.getChildLayoutPosition(view);
            Log.d("liuyuzhe", "getItemOffsets.position: " + position);
            boolean isHeader = adapter.isGroupHeader(position);
            if (isHeader) {
                outRect.top = mItemHeaderHeight;
            } else {
                outRect.top = 1;
            }
        }
    }


    /**
     * dp转换成px
     */
    private int dp2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
package com.example.maxiaowu.customviewlearning;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import java.util.Random;

/**
 * Created by maxiaowu on 16/6/28.
 */
public class CustomTextView extends View {

    private String mText;
    private int mTextColor;
    private int mTextSize;

    private Paint mPaint;
    private Rect  mBound;

    public CustomTextView(Context context) {
        this(context,null);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray array=context.getTheme().obtainStyledAttributes(attrs,R.styleable.CustomTextView,
                                                                    defStyleAttr,0);
        int count=array.getIndexCount();

        for (int i=0;i<count;i++)
        {
            int attr=array.getIndex(i);

            switch (attr){
                case R.styleable.CustomTextView_text:
                    mText=array.getString(attr);
                    break;
                case R.styleable.CustomTextView_textColor:
                    mTextColor=array.getColor(attr, Color.BLUE);
                    break;
                case R.styleable.CustomTextView_textSize:
                    mTextSize=array.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,16,getResources().getDisplayMetrics()));
                    break;
            }
        }

        array.recycle();

        mPaint=new Paint();
        mPaint.setTextSize(mTextSize);
        mBound=new Rect();

        mPaint.getTextBounds(mText,0,mText.length(),mBound);

        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mText=getRandomText();
                postInvalidate();
            }
        });

    }

    public String getRandomText()
    {
        Random random=new Random();
        int i=random.nextInt(11);

        return i+"";
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setColor(Color.BLUE);
        canvas.drawRect(0,0,getMeasuredWidth(),getMeasuredHeight(),mPaint);

        mPaint.setColor(mTextColor);

        mPaint.getTextBounds(mText,0,mText.length(),mBound);
        canvas.drawText(mText,getWidth()/2-mBound.width()/2,getHeight()/2+mBound.height()/2,mPaint);

    }
}

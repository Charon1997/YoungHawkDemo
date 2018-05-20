package com.charon.www.younghawkdemo.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;

import com.charon.www.younghawkdemo.R;

import java.util.Arrays;
import java.util.List;

/**
 * 项目名称：TextCircleImageView
 * 类描述：
 * 创建人：Charon
 * 创建时间：2018/2/1 17:12
 * 修改人：Charon
 * 修改时间：2018/2/1 17:12
 * 修改备注：
 */

public class TextCircleImageView extends android.support.v7.widget.AppCompatImageView {
    private String text;
    private List<Integer> colorList;
    private int backgroundColor = Color.BLACK;
    private int textColor = Color.WHITE;
    private float[] stringSize = {(float) 0.7,1, (float) 1.2};
    private Paint backgroundPaint, textPaint;
    private boolean isFirst;
    private final static boolean DEBUG = false;
    private final static String TAG = TextCircleImageView.class.getSimpleName();

    /**
     * 让View兼容xml与Java
     */
    public TextCircleImageView(Context context) {
        this(context, null);
    }

    public TextCircleImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TextCircleImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TextCircleImageView);
        int n = ta.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = ta.getIndex(i);
            switch (attr) {
                case R.styleable.TextCircleImageView_text:
                    text = ta.getString(attr);
                    break;
                case R.styleable.TextCircleImageView_backgroundColor:
                    backgroundColor = ta.getColor(attr, backgroundColor);
                    break;
                case R.styleable.TextCircleImageView_textColor:
                    textColor = ta.getColor(attr, textColor);
                    break;
                case R.styleable.TextCircleImageView_first:
                    isFirst = ta.getBoolean(attr, false);
                    break;
                default:
                    break;
            }
        }
        ta.recycle();
        init();
    }

    private void init() {
        backgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //默认颜色组为Material Design饱和度为500的非亮色背景
        colorList = Arrays.asList(0xFFF44336, 0xFFE91E63, 0xFF9C27B0, 0xFF673AB7, 0xFF3F51B5, 0xFF009688, 0xFFFF5722, 0xFF795548, 0xFF607D8B);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getDefaultSize(widthMeasureSpec,true);
        int height = getDefaultSize(heightMeasureSpec,false);
        int radius = Math.min(width, height);
        if (DEBUG) {
            Log.d(TAG, "onMeasure: width"+width+"height"+height);
        }
        setMeasuredDimension(radius, radius);
    }

    private int getDefaultSize(int measureSpec,boolean isWidth) {
        int result = 0;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        //准确的值 parent dx
        if (mode == MeasureSpec.EXACTLY) {
            result = size;
        } else {
            //最少20
            int mRadius = 150;
            if (isWidth) {
                result = mRadius + getPaddingLeft() + getPaddingRight();
            }else {
                result = mRadius + getPaddingTop() + getPaddingBottom();
            }
            //最少的
            if (mode == MeasureSpec.AT_MOST) {
                result = Math.min(result, size);
            }
        }
        return result;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        final int paddingLeft = getPaddingLeft();
        final int paddingRight = getPaddingRight();
        final int paddingTop = getPaddingTop();
        final int paddingBottom = getPaddingBottom();
        int width = getWidth() - paddingLeft - paddingRight;
        int height = getHeight() - paddingTop - paddingBottom;
        int radius = Math.min(width, height)/2;
        if (DEBUG) {
            Log.d(TAG, "onDraw: left"+paddingLeft+"right"+paddingRight+"top"+paddingTop+"bottom"+paddingBottom+"width"+width+"height"+height+"radius"+radius);
        }
        backgroundPaint.setColor(getBackgroundViewColor());
        canvas.drawCircle(paddingLeft + width / 2, paddingTop + height / 2,radius, backgroundPaint);

        //最长3个字
        int stringNum = Math.min(getText().length(), 3);
        textPaint.setColor(getTextColor());
        //根据字数多少设定字大小
        textPaint.setTextSize(radius/2/stringSize[stringNum-1]);
        textPaint.setTextAlign(Paint.Align.CENTER);
        Paint.FontMetricsInt fontMetrics = textPaint.getFontMetricsInt();
        int baseline = (getMeasuredHeight() - fontMetrics.bottom - fontMetrics.top) / 2;
        canvas.drawText(getText(), paddingLeft + radius, baseline, textPaint);
    }

    public String getText() {
        if (null == text) {
            return "N";
        }
        return text;
    }

    public void setText(String text) {
        if (isFirst) {
            this.text = String.valueOf(text.charAt(0));
        } else {
            this.text = text.substring(0, Math.min(text.length(), 3));
        }
    }

    public void setText(String text, boolean isFirst) {
        this.isFirst = isFirst;
        this.setText(text);
    }

    public boolean isFirst() {
        return isFirst;
    }

    /**
     * 设置是否只显示第一个字
     *
     * @param first
     */
    public void setFirst(boolean first) {
        isFirst = first;
    }

    /**
     * 获取背景圆颜色
     *
     * @return 背景圆颜色
     */
    public int getBackgroundViewColor() {
        return backgroundColor;
    }

    /**
     * 设置背景圆颜色
     *
     * @param backgroundColor 背景圆颜色 如：0XFF123456
     */
    public void setBackgroundViewColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    /**
     * 获取文字颜色
     *
     * @return 文字颜色
     */
    public int getTextColor() {
        return textColor;
    }

    /**
     * 设置文字颜色
     *
     * @param textColor 文字颜色 如：0XFF123456
     */
    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    /**
     * 设置默认背景颜色
     *
     * @param position 设置颜色的序号
     */
    public void setDefaultBackgroundColor(int position) {
        backgroundColor = getColorList().get(position % colorList.size());
    }

    /**
     * 获取颜色列表信息
     *
     * @return 颜色列表信息
     */
    public List<Integer> getColorList() {
        return colorList;
    }

    /**
     * 设置颜色列表信息
     *
     * @param colorList 颜色列表 如：{0XFF123456，0XFF654321}
     */
    public void setColorList(List<Integer> colorList) {
        this.colorList = colorList;
    }
}

package com.nullcognition.learnbydoingandroid;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.RectShape;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;


/**
 * TODO: document your custom view class.
 */
public class MyView extends View {

	private String mExampleString; // TODO: use a default from R.string...
	private int   mExampleColor     = Color.RED; // TODO: use a default from R.color...
	private float mExampleDimension = 0; // TODO: use a default from R.dimen...
	private Drawable mExampleDrawable;

	private TextPaint mTextPaint;
	private float     mTextWidth;
	private float     mTextHeight;

	private ShapeDrawable rect;
	private ShapeDrawable rc, yc, gc;
	private Rect rect1;
	int left  = 0;
	int right = 100;

	public MyView(Context context){
		super(context);
		init(null, 0);
	}

	public MyView(Context context, AttributeSet attrs){
		super(context, attrs);
		init(attrs, 0);
	}

	public MyView(Context context, AttributeSet attrs, int defStyle){
		super(context, attrs, defStyle);
		init(attrs, defStyle);
	}

	public void setVVisibility(){
		this.setVisibility(View.INVISIBLE);
	}

	private void init(AttributeSet attrs, int defStyle){
		// Load attributes
		rect1 = new Rect(left, 0, right, 100);


		// 0 is right
		rect = new ShapeDrawable(new RectShape());
		rect.setBounds(left, 0, right, 300);

		rc = new ShapeDrawable(new OvalShape());
		rc.getPaint().setColor(0xFFFF0000);
		rc.setBounds(rect1);

		yc = new ShapeDrawable(new OvalShape());
		yc.getPaint().setColor(0xFFFFFF00);
		yc.setBounds(new Rect(left, 100, right, 200));


		gc = new ShapeDrawable(new OvalShape());
		gc.getPaint().setColor(0xFF009900);
		gc.setBounds(new Rect(left, 200, right, 300));

		final TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.MyView, defStyle, 0);

		mExampleString = a.getString(R.styleable.MyView_exampleString);
		mExampleColor = a.getColor(R.styleable.MyView_exampleColor, mExampleColor);
		// Use getDimensionPixelSize or getDimensionPixelOffset when dealing with
		// values that should fall on pixel boundaries.
        mExampleDimension = a.getDimension(
                R.styleable.MyView_exampleDimension,
                mExampleDimension);

        if (a.hasValue(R.styleable.MyView_exampleDrawable)) {
            mExampleDrawable = a.getDrawable(
                    R.styleable.MyView_exampleDrawable);
            mExampleDrawable.setCallback(this);
        }

        a.recycle();

        // Set up a default TextPaint object
        mTextPaint = new TextPaint();
        mTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextAlign(Paint.Align.LEFT);

        // Update TextPaint and text measurements from attributes
        invalidateTextPaintAndMeasurements();
    }

    private void invalidateTextPaintAndMeasurements() {
        mTextPaint.setTextSize(mExampleDimension);
        mTextPaint.setColor(mExampleColor);
        mTextWidth = mTextPaint.measureText(mExampleString);

        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        mTextHeight = fontMetrics.bottom;
    }

	boolean dr, dy, dg;

	public void setDrawLight(int light){
		switch(light){
			case 0:{
				dr = dr == true ? false : true;
				break;
			}
			case 1:{
				dy = dy == true ? false : true;
				break;
			}
			case 2:{
				dg = dg == true ? false : true;
				break;
			}
			default:
				return;
		}
		invalidate();


	}

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
	    rect.draw(canvas);
	    if(dr){ rc.draw(canvas); }
	    if(dy){ yc.draw(canvas); }
	    if(dg){ gc.draw(canvas); }

	    if((dr && dy && dg) == true){
		    dr = false;
		    Intent i = new Intent(getContext(), MyActivity2.class);
		    try{
			    Thread.sleep(100);
		    }
		    catch(InterruptedException e){
			    e.printStackTrace();
		    }
		    getContext().startActivity(i);
	    }
    }

    /**
     * Gets the example string attribute value.
     * @return The example string attribute value.
     */
    public String getExampleString() {
        return mExampleString;
    }

    /**
     * Sets the view's example string attribute value. In the example view, this string
     * is the text to draw.
     * @param exampleString The example string attribute value to use.
     */
    public void setExampleString(String exampleString) {
        mExampleString = exampleString;
        invalidateTextPaintAndMeasurements();
    }

    /**
     * Gets the example color attribute value.
     * @return The example color attribute value.
     */
    public int getExampleColor() {
        return mExampleColor;
    }

    /**
     * Sets the view's example color attribute value. In the example view, this color
     * is the font color.
     * @param exampleColor The example color attribute value to use.
     */
    public void setExampleColor(int exampleColor) {
        mExampleColor = exampleColor;
        invalidateTextPaintAndMeasurements();
    }

    /**
     * Gets the example dimension attribute value.
     * @return The example dimension attribute value.
     */
    public float getExampleDimension() {
        return mExampleDimension;
    }

    /**
     * Sets the view's example dimension attribute value. In the example view, this dimension
     * is the font size.
     * @param exampleDimension The example dimension attribute value to use.
     */
    public void setExampleDimension(float exampleDimension) {
        mExampleDimension = exampleDimension;
        invalidateTextPaintAndMeasurements();
    }

    /**
     * Gets the example drawable attribute value.
     * @return The example drawable attribute value.
     */
    public Drawable getExampleDrawable() {
        return mExampleDrawable;
    }

    /**
     * Sets the view's example drawable attribute value. In the example view, this drawable is
     * drawn above the text.
     * @param exampleDrawable The example drawable attribute value to use.
     */
    public void setExampleDrawable(Drawable exampleDrawable) {
        mExampleDrawable = exampleDrawable;
    }
}

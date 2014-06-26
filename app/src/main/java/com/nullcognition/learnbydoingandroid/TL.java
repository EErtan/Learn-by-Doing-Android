package com.nullcognition.learnbydoingandroid;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.ArcShape;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.RectShape;
import android.view.View;

/**
 * Created by ersin on 19/06/14.
 */
public class TL extends View{

	private ShapeDrawable rect;
	private ShapeDrawable rc, yc, gc;
	private Rect rect1;
	int left  = 0;
	int right = 100;


	public TL(Context context){
		super(context);


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

	protected void onDraw(Canvas canvas){
		rect.draw(canvas);
		if(dr){ rc.draw(canvas); }
		if(dy){ yc.draw(canvas); }
		if(dg){ gc.draw(canvas); }
	}
}

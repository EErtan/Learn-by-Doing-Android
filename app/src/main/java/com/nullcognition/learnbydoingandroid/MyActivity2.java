package com.nullcognition.learnbydoingandroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nullcognition.learnbydoingandroid.R;

public class MyActivity2 extends Activity {

	// important to know the scale type,
	/*
	* matrix = maintains aspect ration in bounds
	* fitXY = adjusts image to bounds
	* fit start, center, end, = fits(resize and align) it according to the X, or Y bounds based on what is available
	* center = centers...
	* centercrop = scales up to fit bounds with maintained aspect ratio then crops
	* center inside = centers in bounds for both x and y padding
	*
	* this combined with a set image size will guarantee image consistance across any device
	* */


	public void changeImg(View v){
		TextView tv = (TextView)findViewById(R.id.textView);
		tv.setCompoundDrawablesWithIntrinsicBounds(null,null,null, getResources().getDrawable(android.R.drawable.alert_dark_frame));

	}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_activity2);
	    ImageView s;
	    s = (ImageView)findViewById(R.id.imageView);
	    s.setScaleType(ImageView.ScaleType.MATRIX);
	    // changing the scale type programmatically can be nice for transitions and animations using scenes

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my_activity2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

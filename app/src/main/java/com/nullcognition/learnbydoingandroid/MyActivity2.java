package com.nullcognition.learnbydoingandroid;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nullcognition.learnbydoingandroid.book.BookActivity;

public class MyActivity2 extends Activity{

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
		TextView textView = (TextView)findViewById(R.id.theTitle);
		textView.setCompoundDrawablesWithIntrinsicBounds(null, null, null, getResources().getDrawable(android.R.drawable.alert_dark_frame));
	}

	public void listAct(View inView){
		Intent listAct = new Intent(this, ListAct.class);
		startActivity(listAct);
	}

	public void listActiv(View inView){
		Intent listAct = new Intent(this, ListActiv.class);
		startActivity(listAct);
	}

	public void bookActivity(View inViev){
		Intent ba = new Intent(this, BookActivity.class);
		startActivity(ba);
	}

	public void startGridActivity(View inView){
		Intent gridActivityIntent = new Intent(this, GridActivity.class);
		startActivity(gridActivityIntent);
	}

	public void startVibrationActivity(View inView){
		Intent vibrationActivityIntent = new Intent(this, VibrationActivity.class);
		startActivity(vibrationActivityIntent);
	}



	public void closeDialog(View inView){
		AlertDialog.Builder alertDiaBuilder = new AlertDialog.Builder(this);
		alertDiaBuilder.setTitle(R.string.dialog_title);
		alertDiaBuilder.setMessage(R.string.dialog_message);
		alertDiaBuilder.setPositiveButton(R.string.dialog_positive, new DialogInterface.OnClickListener(){
			@Override
			public void onClick(DialogInterface dialog, int which){
				Toast.makeText(getApplicationContext(), "pos", Toast.LENGTH_SHORT).show();
			}
		});
		alertDiaBuilder.setNegativeButton(R.string.dialog_negative, new DialogInterface.OnClickListener(){
			@Override
			public void onClick(DialogInterface dialog, int which){
				Toast.makeText(getApplicationContext(), "neg", Toast.LENGTH_SHORT).show();

			}
		});

		alertDiaBuilder.setCancelable(false); // stop onBack and off click actions from canceling the dialog

		AlertDialog ad = alertDiaBuilder.create(); // builder for the create
		ad.show(); // also can have null params for pos and neg just for a more secluded message
	}

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_activity2);
		ImageView s;
		s = (ImageView)findViewById(R.id.imageView);
		s.setScaleType(ImageView.ScaleType.MATRIX);
		// changing the scale type programmatically can be nice for transitions and animations using scenes
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_activity2, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if(id == R.id.action_settings){
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}

package com.nullcognition.learnbydoingandroid;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.util.Linkify;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MyActivity extends Activity implements LightFrag.OnFragmentInteractionListener{

	LightFrag f;
	String tfpath = "fonts/Fipps-Regular.otf"; // create the assets folder under main with java an res
	EditText editTextSite;
	TextView tv2;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my);
		//TL tl = new TL(this);
		//setContentView(tl);
		TextView tv = (TextView)findViewById(R.id.textViewInActivity);
		Typeface typef = Typeface.createFromAsset(getAssets(), tfpath);
		tv.setTypeface(typef);

		tv2 = (TextView)findViewById(R.id.textView2);
		editTextSite = (EditText)findViewById(R.id.editTextSite);
		editTextSite.addTextChangedListener(new TextWatcher(){
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after){

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count){

			}

			@Override
			public void afterTextChanged(Editable s){
				tv2.setAutoLinkMask(Linkify.WEB_URLS);
				tv2.setText(s); // or can set it to a r.id.string, but since i am doing on edit on click functionality, it is not needed
			}
		});

		addFrag();
	}

	@Override
	protected void onResume(){
		super.onResume();
		f.onInteraciton(0);
		f.onSwitchLight(2);
	}

	private void addFrag(){
		final FragmentManager fragmentManager = getFragmentManager();
		final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

		//fragmentTransaction.setCustomAnimations(android.R.animator.fade_in,android.R.animator.fade_out);
		f = new LightFrag();
		fragmentTransaction.add(R.id.relativeLayout, f);
		fragmentTransaction.commit();
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item){

		int id = item.getItemId();

		switch(id){
			case (R.id.action_red):{
				f.onSwitchLight(0);
				Log.e("error", "error level");
				return true;
			}
			case (R.id.action_yellow):{
				f.onSwitchLight(1);
				Log.v("verbose","verbose level");
				return true;
			}
			case (R.id.action_green):{
				f.onSwitchLight(2);
				Log.w("warn","warn level");
				return true;
			}
		}
		Log.d("debug","debug level");
		Log.i("info","info level");
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onFragmentInteraction(String s){
		Toast.makeText(this, s, Toast.LENGTH_SHORT).show();

	}
}

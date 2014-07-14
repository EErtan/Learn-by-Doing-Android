package com.nullcognition.learnbydoingandroid;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class GridActivity extends Activity{

	private class CountryAdapter extends ArrayAdapter<String>{

		private LayoutInflater la;
		private Resources      res;


		public CountryAdapter(Context context, int resource, List<String> con){
			super(context, resource, con);
			la = LayoutInflater.from(context);
			res = getResources();


		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent){

			TextView tv = (TextView)convertView;

			if(convertView == null){
				tv = (TextView)la.inflate(R.layout.country, null);
			}
			String str = getItem(position);
			tv.setText(str);

			tv.setCompoundDrawablesWithIntrinsicBounds(null, res.getDrawable(android.R.drawable.sym_def_app_icon), null, null);

			// or make the drawable dynamic to the call it is getting it from
			return tv;
		}
	}

	List<String> countries;

	private List<String> getCountries(){
		List<String> countries = new ArrayList<String>();

		countries.add("countryone");
		countries.add("countrytwo");
		countries.add("countrythee");
		countries.add("countryfour");
		countries.add("countryfive");
		countries.add("countrysix");
		countries.add("countryseven");
		countries.add("countryeight");
		countries.add("countrynine");countries.add("countryone");
		countries.add("countrytwo");
		countries.add("countrythee");
		countries.add("countryfour");
		countries.add("countryfive");
		countries.add("countrysix");
		countries.add("countryseven");
		countries.add("countryeight");
		countries.add("countrynine");countries.add("countryone");
		countries.add("countrytwo");
		countries.add("countrythee");
		countries.add("countryfour");
		countries.add("countryfive");
		countries.add("countrysix");
		countries.add("countryseven");
		countries.add("countryeight");
		countries.add("countrynine");

		return countries;
	}

	private GridView gv;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_grid);

		gv = (GridView)findViewById(R.id.gridView);
		countries = getCountries();
		gv.setAdapter(new CountryAdapter(this, R.layout.country, countries));

	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.grid, menu);
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

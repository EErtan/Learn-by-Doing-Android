package com.nullcognition.learnbydoingandroid;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class VibrationActivity extends Activity implements View.OnClickListener{

	private static final int COLOR_DIALOG = 1;
	Button vibButton;
	Button patButton;
	Button stopButton;
	Button listDialogButton;
	private Vibrator vib;


	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vibration);

		vibButton = (Button)findViewById(R.id.vibButton);
		patButton = (Button)findViewById(R.id.patButton);
		stopButton = (Button)findViewById(R.id.stopButton);

		listDialogButton = (Button)findViewById(R.id.listDialogButton);

		vibButton.setOnClickListener(this);
		patButton.setOnClickListener(this);
		stopButton.setOnClickListener(this);

		vib = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
		if(vib == null){
			Toast.makeText(this, "service not rec", Toast.LENGTH_SHORT).show();
		}

		showEditDialog();


		// Storage framework
		/*
		* getSharedPreferences() for multiple files stored as key value pairs, found by specifing the name
		* or the singular version getPrefenences()
		* deprecated PreferencesActivity, now use the PreferencesFragment either inflatable or headless
		* pref. can be eddited with the editor
		*
		*
		*
		*
		* */

		// this is the preferences that is saved with the app
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
		String username = preferences.getString(getString(R.string.pref_user_name), "NewUser"); // ex for first time, where user name is null

		// this is the editor to edit the saved preferences with in the app
		SharedPreferences.Editor editor = preferences.edit();
		editor.putString(getString(R.string.pref_user_name), "some input from user from a editText"); // thats the key value pairing
		// then commit the change
		editor.commit(); // any data primative

	}

	private void showEditDialog(){
		FragmentManager fm = getFragmentManager();
		EditNameDialog editNameDialog = new EditNameDialog();
		editNameDialog.show(fm, "fragment_edit_name");
	}

	@Override
	protected void onPause(){
		super.onPause();
		vib.cancel();
	}

	public boolean onCreateOptionsMenu(Menu menu){
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.vibration, menu);
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

	public void listUp(View v){
		Toast.makeText(this, "clicked me", Toast.LENGTH_SHORT).show();
		showEditDialog();
	}

	public void onFinishEditDialog(String inputText){
		Toast.makeText(this, "Hi, " + inputText, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onClick(View v){
		switch(v.getId()){
			case R.id.vibButton:
				vib.vibrate(1000);
				break;
			case R.id.patButton:
				long pattern[] = {0, 500, 250, 500};
				vib.vibrate(pattern, 1);
				break;
			case R.id.stopButton:
				vib.cancel();
				break;
			default:
				assert false;
		}

	}

	public static class EditNameDialog extends DialogFragment implements TextView.OnEditorActionListener, AdapterView.OnItemClickListener{

		private EditText mEditText;
		private ListView lv;


		public EditNameDialog(){
			// Empty constructor required for DialogFragment
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
			View view = inflater.inflate(R.layout.fragment_edit_name, container);
			getDialog().setTitle("Hello");

			lv = (ListView)view.findViewById(R.id.edit_name);

//			mEditText = (EditText)view.findViewById(R.id.txt_your_name);
//
//			// Show soft keyboard automatically
//			mEditText.requestFocus();
//			getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
//			mEditText.setOnEditorActionListener(this);

			lv.setOnItemClickListener(this);

			String[] entries = getResources().getStringArray(R.array.listViewEntries);
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, entries);
			lv.setAdapter(adapter);


			return view;
		}

		@Override
		public boolean onEditorAction(TextView v, int actionId, KeyEvent event){
			if(EditorInfo.IME_ACTION_DONE == actionId){
				// Return input text to activity
				EditNameDialogListener activity = (EditNameDialogListener)getActivity();
				activity.onFinishEditDialog(mEditText.getText().toString());
				this.dismiss();
				return true;
			}
			return false;
		}

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id){
			Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:+1 555 555 5555"));
			startActivity(callIntent);

			Intent smsIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:+1 555 555 5555"));
			smsIntent.putExtra("sms body", "call me back");


			Intent emailIntent = new Intent(Intent.ACTION_SEND);
			emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"msn@msn.com"}); // for multiple emails
			emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Re:");
			emailIntent.putExtra(Intent.EXTRA_TEXT, "basbuabeosbu");
			emailIntent.setType("message/rfc822");
			// startActivity(emailIntent);
			checkIntentValidity(emailIntent);
			checkIntentValidity(smsIntent);
			checkIntentValidity(callIntent);

		}

		private void checkIntentValidity(Intent inIntent){
			PackageManager pm = getActivity().getPackageManager();
			List<ResolveInfo> activitiesThatSupportTheIntent = pm.queryIntentActivities(inIntent, PackageManager.MATCH_DEFAULT_ONLY);
			if(activitiesThatSupportTheIntent.size() > 0){
				startActivity(inIntent);
			}
			else{Toast.makeText(getActivity(), "Action not availible", Toast.LENGTH_SHORT).show();}
		}

		public interface EditNameDialogListener{

			void onFinishEditDialog(String inputText);
		}
	}
}

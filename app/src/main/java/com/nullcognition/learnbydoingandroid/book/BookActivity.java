package com.nullcognition.learnbydoingandroid.book;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.nullcognition.learnbydoingandroid.R;

import java.util.List;

public class BookActivity extends ListActivity{

	private BookAdapter bookAdapter;
	private List<Book> books;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list2);

		books = BookStore.getBooks();

		bookAdapter = new BookAdapter(this, R.layout.book_item, books);

		setListAdapter(bookAdapter);

		registerForContextMenu(getListView());
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
		if(v == getListView()){
			MenuInflater mi = getMenuInflater();
			menu.setHeaderTitle(R.string.contextTitle);
			mi.inflate(R.menu.books, menu);
		}
		super.onCreateContextMenu(menu, v, menuInfo);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item){
		AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
		int pos = acmi.position;

		switch(item.getItemId()){
			case R.id.view:{
				BookAdapter ba = bookAdapter;
				Book book = ba.getItem(pos);
				Toast.makeText(this, book.toString(), Toast.LENGTH_LONG).show();
				return true;
			}
			case R.id.delete:{
				books.remove(pos);
				bookAdapter.notifyDataSetChanged();

				return true;
			}
			default: return super.onContextItemSelected(item);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.book, menu);
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

	class BookAdapter extends ArrayAdapter<Book>{

		private LayoutInflater layoutInflater;

		public BookAdapter(Context context, int textViewResouceId, List<Book> books){
			super(context, textViewResouceId, books);
			layoutInflater = LayoutInflater.from(context);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent){
			View view = convertView; //layoutInflater.inflate(R.layout.book_item, null); // bad way of loading all views

			Holder holder = null;

			if(view == null){ // using the recycler
				view = layoutInflater.inflate(R.layout.book_item, null);

				TextView title = (TextView)view.findViewById(R.id.theTitle);
				TextView author = (TextView)view.findViewById(R.id.theAuthor);

				holder = new Holder(title, author);

				view.setTag(holder);
			}
			else{
				holder = (Holder)view.getTag();
			}

			Book book = getItem(position);


			holder.title.setText(book.getTitle());
			holder.author.setText(book.getAuthor());

			return view;
		}
	}

	static class Holder{

		public TextView title;
		public TextView author;

		public Holder(TextView title, TextView author){
			this.author = author;
			this.title = title;
		}
	}
}

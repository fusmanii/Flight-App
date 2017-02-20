package b07.flightapp;

import flights.Itinerary;
import phase2.SystemControl;
import users.Client;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class BookActivity extends ActionBarActivity {
	
	private SystemControl systemControl;
	
	private Itinerary itinerary;
	
	private Client user;
	
	private boolean isAdmin;

	@Override
	// View the itinerary selected for booking
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_book);
		
		// get the Intent that connected previous Activity to 
		// this Activity
	    Intent intent = getIntent();
	    
	    // get the SystemControl object out of the Intent
	    systemControl = (SystemControl) 
	    		intent.getSerializableExtra(MainActivity.SYSTEM_CONTROL);
	    
	    itinerary = (Itinerary) 
	    		intent.getSerializableExtra(CentralActivity.ITINERARY);
	    
	    user = (Client) intent.getSerializableExtra(MainActivity.USER);
	    
	    isAdmin = (Boolean) 
	    		intent.getSerializableExtra(MainActivity.IS_ADMIN);
	    
	    TextView airline = (TextView) findViewById(R.id.airline);
	    TextView orig_dest = (TextView) findViewById(R.id.orig_dest);
	    TextView date = (TextView) findViewById(R.id.date);
	    TextView cost = (TextView) findViewById(R.id.cost);
	    TextView dur = (TextView) findViewById(R.id.dur);
	    Button userBook = (Button) findViewById(R.id.user_book);
	    
	    airline.setText(itinerary.getAirlines().toString());
	    orig_dest.setText(itinerary.getOrigin() + 
	    		" - " + itinerary.getDestination());
	    date.setText(itinerary.getDepartureDateTime() + 
	    		" - " + itinerary.getArrivalDateTime());
	    cost.setText(Double.valueOf(itinerary.getTotalCost()).toString());
	    dur.setText(itinerary.getTotalTime());
	    
	    if (isAdmin) {
	    	userBook.setVisibility(View.VISIBLE);
	    }
	    
	    
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.book, menu);
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
	
	public void book (View view) {
		if (!systemControl.bookItinerary(user.getUsername(), itinerary)) {
			Toast.makeText(this, "This itinerary is already booked",
					Toast.LENGTH_SHORT).show();
		} else {
			Intent intent = new Intent(this, CentralActivity.class);
			intent.putExtra(MainActivity.SYSTEM_CONTROL, systemControl);
			
			startActivity(intent);
		}
		/*
		else {
			Toast.makeText(this, user.getBookedItinerary().toString(),
					Toast.LENGTH_SHORT).show();
		}*/
	}
	
	/**
	 * Starts the activity that allows booking for a user.
	 * @param view the view
	 */
	public void bookForClient (View view) {
		Intent intent = new Intent(this, UserBookActivity.class);
		
		intent.putExtra(MainActivity.USERS, systemControl.getUsers());
		intent.putExtra(CentralActivity.ITINERARY, itinerary);
		
		startActivity(intent);
	}
}

package b07.flightapp;

import java.util.ArrayList;

import exceptions.NotLogedInException;
import flights.Flight;
import flights.Itinerary;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;
import phase2.SystemControl;


public class CentralActivity extends ActionBarActivity {

	private SystemControl systemControl;
	
	private ListView listViewDrawer;
	
	private String [] values;
	
	private ArrayAdapter<String> adapter;
	
	static final String FLIGHTS = "flights";
	
	static final String ITINERARIES = "itineraries";
	
	static final String ITINERARY = "itinerary";
	
	static final String IS_FLIGHT = "is_flight";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_central);
		
		// get the Intent that connected MainActivity to this Activity
	    Intent intent = getIntent();
	    
	    // get the SystemControl object out of the Intent
	    systemControl = (SystemControl) 
	    		intent.getSerializableExtra(MainActivity.SYSTEM_CONTROL);
	    
	    try {
			boolean isAdmin = systemControl.getUser().isAdmin();
			listViewDrawer = (ListView) findViewById(R.id.left_drawer);
		
			if (isAdmin) {
				values = new String[] {"Personal info", "Booked itineraries", 
			    		"View Clients", "View Flights", 
			    		"Upload" 
			    		};
			} else {
				
				values = new String[] {"Personal info", "Booked itineraries"};
			}
	    
			adapter = new ArrayAdapter<String>(this, 
					R.layout.drawer_list_item, values);
	    
			listViewDrawer.setAdapter(adapter);
	    
			
			listViewDrawer.setOnItemClickListener(new OnItemClickListener() {
		    	public void onItemClick(AdapterView<?> arg0, View v, 
		    			int position, long id) {
		    		
		    		selectItem(position);
		    	}
		    });
			
	    
	    } catch (NotLogedInException e) {
			Toast.makeText(this, "No user is logged in",
					Toast.LENGTH_SHORT).show();
		}
	    
	}
	
	private void selectItem (int position) {
		
		Intent intent;
		if (position == 0) {
			intent = new Intent(this, PersonalInfoActivity.class);
			intent.putExtra(MainActivity.SYSTEM_CONTROL, systemControl);
			
			startActivity(intent);
		} else if (position == 1) {
			intent = new Intent(this, BookedActivity.class);
			intent.putExtra(MainActivity.SYSTEM_CONTROL, systemControl);
			
			startActivity(intent);
		} else if (position == 2) {
			intent = new Intent(this, ViewClients.class);
			intent.putExtra(MainActivity.SYSTEM_CONTROL, systemControl);
			
			startActivity(intent);
		} else if (position == 3) {
			intent = new Intent(this, EditFlight.class);
			intent.putExtra(MainActivity.SYSTEM_CONTROL, systemControl);
			
			startActivity(intent);
		} else if (position == 4) {
			intent = new Intent(this, UploadActivity.class);
			intent.putExtra(MainActivity.SYSTEM_CONTROL, systemControl);
			
			startActivity(intent);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.central, menu);
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
	
	public void getAllSearchResults (View view) {
		
		EditText originEdit = (EditText) findViewById(R.id.get_origin);
		String origin = originEdit.getText().toString();
		
		EditText destinationEdit = (EditText) findViewById(R.id.get_destination);
		String destination = destinationEdit.getText().toString();
		
		EditText dateEdit = (EditText) findViewById(R.id.get_date);
		String date = dateEdit.getText().toString();
		
		EditText timeEdit = (EditText) findViewById(R.id.get_time);
		String time = timeEdit.getText().toString();
		
		date += " " + time;
		
		boolean flightsChecked = ((RadioButton) 
				findViewById(R.id.flights)).isChecked();
		
		boolean totalCost = ((RadioButton) 
				findViewById(R.id.total_cost)).isChecked();
		
		Intent intent = new Intent(this, SearchActivity.class);
		intent.putExtra(MainActivity.SYSTEM_CONTROL, systemControl);
		
		if (origin.matches("") || destination.matches("") 
				|| date.matches("")) {
			Toast.makeText(this, "All fields must be non-empty",
					Toast.LENGTH_SHORT).show();
			return;
		} else {
			if (flightsChecked) {
				ArrayList<Flight> flights = (ArrayList<Flight>) 
					systemControl.getFlights(date, origin, destination);
			
			
				intent.putExtra(FLIGHTS, flights);
				intent.putExtra(IS_FLIGHT, true);
			
				startActivity(intent);
			} else {
				ArrayList<Itinerary> itineraries;
				if (totalCost) {
					itineraries = (ArrayList<Itinerary>) 
							systemControl
						.getItinerariesSortedByCost(date, origin, destination);
				} else {
					itineraries = (ArrayList<Itinerary>) 
							systemControl
						.getItinerariesSortedByTime(date, origin, destination);
				}
			
				intent.putExtra(ITINERARIES, itineraries);
				intent.putExtra(IS_FLIGHT, false);
			
				startActivity(intent);
			}
		}
	}
	
	
}

package b07.flightapp;

import java.util.ArrayList;
import java.util.List;

import exceptions.NotLogedInException;
import flights.Flight;
import flights.Itinerary;

import phase2.SystemControl;
import users.Client;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class SearchActivity extends ActionBarActivity {

	private SystemControl systemControl;
	
	private Client user;
	
	private boolean isAdmin;
	
	private ListView listViewDrawer, listView;
	
	private boolean isFlight;
	
	private List<Flight> flights;
	
	private List<Itinerary> itineraries;
	
	private String [] data;
	
	private String [] values;
	
	private ArrayAdapter<String> adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		
		// get the Intent that connected MainActivity to this Activity
	    Intent intent = getIntent();
	    
	    // get the SystemControl object out of the Intent
	    systemControl = (SystemControl) 
	    		intent.getSerializableExtra(MainActivity.SYSTEM_CONTROL);
	    
	    try {
	    	user = systemControl.getUser();
			isAdmin = user.isAdmin();
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
	    
	    listView = (ListView) findViewById(R.id.list);
	    
	    isFlight =  (Boolean) intent.getSerializableExtra(CentralActivity.IS_FLIGHT);
	    
	    
	    if (isFlight) {
	    	flights = (ArrayList<Flight>) 
	    			intent.getSerializableExtra(CentralActivity.FLIGHTS);
	    	
	    	data = getFlightString(flights);
	    	
	    	
	    } else {
	    	itineraries  = (ArrayList<Itinerary>) 
	    			intent.getSerializableExtra(CentralActivity.ITINERARIES);
	    	
	    	data = getItinString(itineraries);
	    }
	    
	    adapter = new ArrayAdapter<String>(this, 
	    		R.layout.drawer_list_item, data);
    	listView.setAdapter(adapter);
    	
    	listView.setOnItemClickListener(new OnItemClickListener() {
	    	public void onItemClick(AdapterView<?> arg0, View v, 
	    			int position, long id) {
	    		
	    		bookItem(position);
	    		
	    	}
	    });
		
    	
	    
	}
	
	private void bookItem (int position) {
		Itinerary itinerary;
		if (isFlight) {
			Flight flight = flights.get(position);
			List<Flight> flightList = new ArrayList<Flight>();
			flightList.add(flight);
			itinerary = new Itinerary(flightList, 
					flight.getOrigin(), flight.getDestination(), 
					flight.getDepartureDateTime());
		} else {
			itinerary = itineraries.get(position);
		}
		
		Intent intent = new Intent(this, BookActivity.class);
		intent.putExtra(MainActivity.SYSTEM_CONTROL, systemControl);
		intent.putExtra(CentralActivity.ITINERARY, itinerary);
		intent.putExtra(MainActivity.USER, user);
		intent.putExtra(MainActivity.IS_ADMIN, isAdmin);
		
		startActivity(intent);
		
	}
	
	private String [] getFlightString (List<Flight> flights) {
		
		String [] flightString = new String [flights.size()];
		Flight curFlight;
		
		for (int i = 0; i < flights.size(); i++) {
			curFlight = flights.get(i);
			flightString[i] = curFlight.getAirline() + "\n" 
					 + curFlight.getDepartureDateTime() + " - "
					 + curFlight.getArrivalDateTime() + "\n"
					 + curFlight.getTravelTime() + "\n"
					 + curFlight.getCost();		
		}
		return flightString;
	}
	
	private String [] getItinString (List<Itinerary> itineraries) {
		
		String [] itinString = new String [itineraries.size()];
		Itinerary curItin;
		
		for (int i = 0; i < itineraries.size(); i++) {
			curItin = itineraries.get(i);
			itinString[i] = curItin.getAirlines() + "\n"
					+ curItin.getDepartureDateTime() + " - "
					+ curItin.getArrivalDateTime() + "\n"
					+ curItin.getTotalTime() + "\n"
					+ curItin.getTotalCost();
		}
		return itinString;
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
		getMenuInflater().inflate(R.menu.search, menu);
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

package b07.flightapp;

import exceptions.FlightTooLongException;
import exceptions.InvalidDepartureOrArrivalDateTimeException;
import flights.Flight;
import phase2.SystemControl;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditFlight extends ActionBarActivity {
	
	private SystemControl systemControl;
	
	// the origin of the flight
	private EditText origin;
	
	// the destination of the flight
	private EditText dest;
	
	// the arrival date of the flight
	private EditText arivDate;
	
	// the departure date of the flight
	private EditText deptDate;
	
	// the airline of the flight
	private EditText airline;
	
	// the cost of the flight
	private EditText cost;
	
	// the number of seats in the flight
	private EditText numSeats;

	private Button editButt;
	
	//flight number of the flight
	private Integer flightNum;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_flight);
		
		// get the Intent that connected MainActivity to this Activity
	    Intent intent = getIntent();
	    
	    // get the SystemControl object out of the Intent
	    systemControl = (SystemControl) 
	    		intent.getSerializableExtra(MainActivity.SYSTEM_CONTROL);
	    
	    
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_flight, menu);
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
	
	/**
	 * Edits the flight with the flight number using the given information.
	 * @param view the view
	 */
	public void editFlight (View view) {
		
		String originS = origin.getText().toString();
		String destS = dest.getText().toString();
		String arivDateS = arivDate.getText().toString();
		String deptDateS = deptDate.getText().toString();
		String airlineS = airline.getText().toString();
		Double costS = Double.parseDouble(cost.getText().toString());
		int numSeatsS = Integer.parseInt(numSeats.getText().toString());
		
		try {
			systemControl.addFlight(flightNum, deptDateS, arivDateS, 
					airlineS, originS, destS, costS, numSeatsS);
		} catch (FlightTooLongException e) {
			Toast.makeText(this, "Flight time too long.",
					Toast.LENGTH_SHORT).show();
			return;
		} catch (InvalidDepartureOrArrivalDateTimeException e) {
			Toast.makeText(this, "Invalid arrival or departure time.",
					Toast.LENGTH_SHORT).show();
			return;
		}
	}
	
	
	
	
	/**
	 * Gets the flight with the given flight number.
	 * @param view the view
	 */
	public void getFlight (View view) {
		
		EditText flightNumEdit = (EditText) findViewById(R.id.flight_num);
	    flightNum = Integer.parseInt(flightNumEdit.getText().toString());
	    
	    Flight flight = systemControl.getFlight(flightNum);
	    
	    origin = (EditText) findViewById(R.id.get_origin);
	    dest = (EditText) findViewById(R.id.get_dest);
	    arivDate = (EditText) findViewById(R.id.get_ari_date);
	    deptDate = (EditText) findViewById(R.id.get_dept_date);
	    airline = (EditText) findViewById(R.id.get_airline);
	    cost = (EditText) findViewById(R.id.get_cost);
	    numSeats = (EditText) findViewById(R.id.get_num_seat);
	    editButt = (Button) findViewById(R.id.edit_flights);
	    
	    
	    origin.setText(flight.getOrigin());
	    dest.setText(flight.getDestination());
	    arivDate.setText(flight.getArrivalDateTime());
	    deptDate.setText(flight.getDepartureDateTime());
	    airline.setText(flight.getAirline());
	    cost.setText(String.valueOf(flight.getCost()));
	    numSeats.setText(flight.getNumSeats());
	    
	    origin.setVisibility(View.VISIBLE);
	    dest.setVisibility(View.VISIBLE);
	    arivDate.setVisibility(View.VISIBLE);
	    deptDate.setVisibility(View.VISIBLE);
	    airline.setVisibility(View.VISIBLE);
	    cost.setVisibility(View.VISIBLE);
	    numSeats.setVisibility(View.VISIBLE);
	    editButt.setVisibility(View.VISIBLE);
	}
}

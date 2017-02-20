package b07.flightapp;

import exceptions.NotLogedInException;
import phase2.SystemControl;
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
import android.widget.TextView;

public class PersonalInfoActivity extends ActionBarActivity {
	
	private SystemControl systemControl;
	
	private ListView listView;
	
	private String [] values;
	
	private ArrayAdapter<String> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personal_info);
		
		// get the Intent that connected MainActivity to this Activity
	    Intent intent = getIntent();
	    
	    // get the SystemControl object out of the Intent
	    systemControl = (SystemControl) 
	    		intent.getSerializableExtra(MainActivity.SYSTEM_CONTROL);
	    
	    try {
			boolean isAdmin = systemControl.getUser().isAdmin();
			listView = (ListView) findViewById(R.id.left_drawer);
		
			if (isAdmin) {
				values = new String[] {"Personal info", "Booked itineraries", 
			    		"View Clients", "Upload Clients", 
			    		"Upload Flights", "View Flights"
			    		};
			} else {
				
				values = new String[] {"Personal info", "Booked itineraries"};
			}
	    
			adapter = new ArrayAdapter<String>(this, 
					R.layout.drawer_list_item, values);
	    
			listView.setAdapter(adapter);
			
			listView.setOnItemClickListener(new OnItemClickListener() {
		    	public void onItemClick(AdapterView<?> arg0, View v, 
		    			int position, long id) {
		    		
		    		selectItem(position);
		    	}
		    });
			
			
	    } catch (NotLogedInException e) {
			Toast.makeText(this, "No user is logged in",
					Toast.LENGTH_SHORT).show();
		}
	    
		TextView firstName = (TextView) findViewById(R.id.first_name_txt);
		
		TextView password = (TextView) findViewById(R.id.password_txt);
		
		TextView lastName = (TextView) findViewById(R.id.last_name_txt);
		
		TextView email = (TextView) findViewById(R.id.email_txt);
		
		TextView address = (TextView) findViewById(R.id.address_txt);
		
		TextView creditNum = (TextView) findViewById(R.id.credit_card_num_txt);
		
		TextView expiry = (TextView) findViewById(R.id.expiry_txt);
		
		TextView username = (TextView) findViewById(R.id.username_txt);
		
		try {
			firstName.setText(" " + systemControl.getUser().getFirstName());
			lastName.setText(" " + systemControl.getUser().getLastName());
			email.setText(" " + systemControl.getUser().getEmail());
			address.setText(" " + systemControl.getUser().getAddress());
			creditNum.setText(" " + systemControl.getUser().getCreditNum());
			expiry.setText(" " + systemControl.getUser().getCreditExpiry());
			username.setText(" " + systemControl.getUser().getUsername());
			password.setText(" " + systemControl.getUser().getPassword());
		} catch (NotLogedInException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.personal_info, menu);
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
	
	
	
	public void editInfo (View view) {
		
		
		Intent intent = new Intent(this, EditPersonalActivity.class);
		intent.putExtra(MainActivity.SYSTEM_CONTROL, systemControl);
		try {
			intent.putExtra(MainActivity.USER, systemControl.getUser());
		} catch (NotLogedInException e) {
			e.printStackTrace();
		}
	
		startActivity(intent);
	}

}

package b07.flightapp;

import java.io.File;

import exceptions.WrongPasswordException;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import phase2.SystemControl;

public class MainActivity extends ActionBarActivity {

	// the systemControl that keeps track of everything
	private SystemControl systemControl;
	
	// the name of the user
	static final String USER = "user";
	
	static final String USERS = "users";
	
	static final String IS_ADMIN = "is_admin";
	
	// the name of the file for user info
	private final String USER_FILE_NAME = "users.ser";
	
	// the name of the file for airport info
	private final String AIRPORT_FILE_NAME = "airport.ser";
	
	// the name of the file for passwords
	private final String PASSWORDS = "passwords.txt";
	
	// the name of directory that has the user files
	private final String USERDATADIR = "appdata";
	
	// username EditText
	private EditText usernameText;
	
	// password EditText
	private EditText passwordText;
	
	static final String SYSTEM_CONTROL = "systemControl";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		File dir = this.getApplicationContext().getDir(USERDATADIR, MODE_PRIVATE);
	    File user_file = new File(dir, USER_FILE_NAME);
		File airport_file = new File(dir, AIRPORT_FILE_NAME);
		
		this.systemControl = new SystemControl(user_file, airport_file);
		
		usernameText = (EditText) findViewById(R.id.get_username);
		passwordText = (EditText) findViewById(R.id.get_password);
		
		/*try {
			systemControl.addFlight(42, "2014-04-04 17:40", "2014-04-04 23:00", 
					"C", "Rome", "Vancouver", 500);
			systemControl.addFlight(3, "2014-04-03 12:10", "2014-04-04 18:30", 
					"Air Canada", "Toronto", "Vancouver", 450);
			systemControl.addFlight(12, "2014-04-04 17:30", "2014-04-05 20:35", 
					"United", "Rome", "Hong Kong", 600);
			systemControl.addFlight(45, "2014-04-03 12:10", "2014-04-04 18:20", 
					"Bull Shit", "Toronto", "Vancouver", 7000);
			systemControl.addFlight(21, "2014-04-04 23:40", "2014-04-05 07:45", 
					"Air", "Vancouver", "Paris", 200);
			systemControl.addFlight(30, "2014-04-03 12:10", "2014-04-04 17:21", 
					"A", "Toronto", "Rome", 300);
			systemControl.addFlight(2, "2014-04-05 12:00", "2014-04-05 20:10", 
					"B", "Paris", "Hong Kong", 350);
		} catch (FlightTooLongException e) {
			e.printStackTrace();
		} catch (InvalidDepartureOrArrivalDateTimeException e) {
			e.printStackTrace();
		} */
		
		
	}
	@Override
	protected void onResume () {
		super.onResume();
		usernameText.refreshDrawableState();
		passwordText.refreshDrawableState();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
	 * Opens a new activity if the username and password of the user id
	 * correct. Show a toast otherwise with message "Incorrect username 
	 * or password"
	 * @param view
	 */
	public void loginClient(View view) {
		
		
		usernameText = (EditText) findViewById(R.id.get_username);
		String username = usernameText.getText().toString();
		
		passwordText = (EditText) findViewById(R.id.get_password);
		String password = passwordText.getText().toString();
		
		try {
			systemControl.loginUser(username, password);
			
			Intent intent = new Intent(this, CentralActivity.class);
			intent.putExtra(SYSTEM_CONTROL, systemControl);
			
			usernameText.setText("");
			passwordText.setText("");
			
			startActivity(intent);
		} catch (WrongPasswordException e) {
			Toast.makeText(this, "Incorrect username or password",
					Toast.LENGTH_SHORT).show();
			return;
		}
		
	}
	
	/**
	 * Opens RegisterActivity to register a new user.
	 * @param view
	 */
	public void registerClient(View view) {
		Intent intent = new Intent(this, RegisterActivity.class);
		intent.putExtra(SYSTEM_CONTROL, systemControl);
		
		startActivity(intent);
	}
	
	
	
	
	
	
}

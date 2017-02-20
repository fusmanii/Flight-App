package b07.flightapp;

import java.io.File;

import phase2.SystemControl;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class UploadActivity extends ActionBarActivity {

	private SystemControl systemControl;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_upload);
		
		// get the Intent that connected MainActivity to this Activity
	    Intent intent = getIntent();
	    
	    // get the SystemControl object out of the Intent
	    systemControl = (SystemControl) 
	    		intent.getSerializableExtra(MainActivity.SYSTEM_CONTROL);
	    
		
	}
	
	public void uploadClients (View view) {
		
		EditText pathEdit = (EditText) findViewById(R.id.get_clients_path);
		String path = pathEdit.getText().toString();
		
		File passFile = this.getApplicationContext().getFilesDir();
		String pass = passFile.getAbsolutePath() + "/passwords.txt";
		
		systemControl.uploadUsers(path, pass);
	}
	
	/**
	 * 
	 * @param view
	 */
	public void uploadFlights (View view) {
		
		EditText pathEdit = (EditText) findViewById(R.id.get_flight_path);
		String path = pathEdit.getText().toString();
		
		systemControl.uploadFlights(path);
		
		Intent intent = new Intent(this, CentralActivity.class);
		intent.putExtra(MainActivity.SYSTEM_CONTROL, systemControl);
		
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.upload, menu);
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

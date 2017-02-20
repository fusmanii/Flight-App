package b07.flightapp;

import exceptions.NotLogedInException;
import phase2.SystemControl;
import users.Client;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditPersonalActivity extends ActionBarActivity {

	private Client user;
	
	private SystemControl systemControl;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_personal);
		
		Intent intent = getIntent();
		
		user = (Client) intent.getSerializableExtra(MainActivity.USER);
		systemControl = (SystemControl) 
		    	intent.getSerializableExtra(MainActivity.SYSTEM_CONTROL);
		
		EditText firstName = (EditText) findViewById(R.id.get_first_name);
		
		EditText password = (EditText) findViewById(R.id.get_password);
		
		EditText passwordCon = (EditText) findViewById(R.id.get_password_con);
		
		EditText lastName = (EditText) findViewById(R.id.get_last_name);
		
		EditText email = (EditText) findViewById(R.id.get_email);
		
		EditText address = (EditText) findViewById(R.id.get_address);
		
		EditText creditNum = (EditText) findViewById(R.id.get_credit_card_num);
		
		EditText expiry = (EditText) findViewById(R.id.get_expiry);
		
		EditText username = (EditText) findViewById(R.id.get_username);
		
		
		firstName.setText(user.getFirstName());
		lastName.setText(user.getLastName());
		email.setText(user.getEmail());
		address.setText(user.getAddress());
		creditNum.setText(user.getCreditNum());
		expiry.setText(user.getCreditExpiry());
		username.setText(user.getUsername());
		password.setText(user.getPassword());
		passwordCon.setText(user.getPassword());
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_personal, menu);
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
	 * Edit the users information with the given data obtained from
	 * the activity.
	 * @param view the view
	 */
	public void save (View view) {
		
		EditText firstNameEdit = (EditText) findViewById(R.id.get_first_name);
		String firstName = firstNameEdit.getText().toString();
		
		EditText lastNameEdit = (EditText) findViewById(R.id.get_last_name);
		String lastName = lastNameEdit.getText().toString();
		
		EditText emailEdit = (EditText) findViewById(R.id.get_email);
		String email = emailEdit.getText().toString();
		
		EditText addressEdit = (EditText) findViewById(R.id.get_address);
		String address = addressEdit.getText().toString();
		
		EditText creditNumEdit = (EditText) findViewById(R.id.get_credit_card_num);
		String creditNum = creditNumEdit.getText().toString();
		
		EditText expiryDateEdit = (EditText) findViewById(R.id.get_expiry);
		String expiryDate = expiryDateEdit.getText().toString();
				
		EditText passwordEdit = (EditText) findViewById(R.id.get_password);
		String password = passwordEdit.getText().toString();
		
		EditText passwordConEdit = (EditText) findViewById(R.id.get_password_con);
		String passwordCon = passwordConEdit.getText().toString();
		
		
		if (!firstName.matches("")){
			user.setFirstName(firstName);
		}
		
		if (!lastName.matches("")){
			user.setLastName(lastName);
		}
		
		if (!email.matches("")){
			user.setEmail(email);
		}
		
		if (!address.matches("")){
			user.setAddress(address);
		}
		
		if (!creditNum.matches("")){
			user.setCreditNum(creditNum);
		}
		
		if (!expiryDate.matches("")){
			user.setCreditExpiry(expiryDate);
		}
		
		
		if (!password.matches("")&& password.equals(passwordCon)) {
				user.setPassword(password);
	
		} else if (password.matches("") && !passwordCon.matches("")
				|| !password.matches("") && passwordCon.matches("")) {
			Toast.makeText(this, "Miss-match of password and " +
					"confirm password", Toast.LENGTH_SHORT).show();
			return;

		}
			systemControl.update();
			Intent intent = new Intent(this, CentralActivity.class);
			intent.putExtra(MainActivity.SYSTEM_CONTROL, systemControl);
		
			startActivity(intent);
		}

	
}

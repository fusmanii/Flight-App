package b07.flightapp;

import exceptions.UserAlreadyExistsException;
import phase2.SystemControl;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;

public class RegisterActivity extends ActionBarActivity {

	private SystemControl systemControl;
	
	private CheckBox isAdmin;
	
	private EditText adminPassEdit;
	
	@Override
	// gets the stored systemControl and checks for admin pass
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		
		// get the Intent that connected MainActivity to this Activity
	    Intent intent = getIntent();
	    
	    // get the SystemControl object out of the Intent
	    systemControl = (SystemControl) 
	    		intent.getSerializableExtra(MainActivity.SYSTEM_CONTROL);
	    
	    isAdmin = (CheckBox) findViewById(R.id.is_admin);
	    adminPassEdit = (EditText) findViewById(R.id.get_admin_pass);
	    adminPassEdit.setEnabled(false);
	    
	    isAdmin.setOnCheckedChangeListener(new OnCheckedChangeListener() {
	        @Override
	        public void onCheckedChanged(CompoundButton buttonView,
	            boolean isChecked) {
	            if (isChecked) {
	                adminPassEdit.setEnabled(true);
	            } else {
	                adminPassEdit.setEnabled(false);
	            }
	        }
	    });
	    
	    
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it 
		//is present.
		getMenuInflater().inflate(R.menu.register, menu);
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
	 * Gets all the information of the new client to be registered.
	 * @param view the view
	 */
	public void registerClient (View view) {
		
		isAdmin = (CheckBox) findViewById(R.id.is_admin);
		
		EditText firstNameEdit = (EditText) findViewById(R.id.get_first_name);
		String firstName = firstNameEdit.getText().toString();
		
		EditText lastNameEdit = (EditText) findViewById(R.id.get_last_name);
		String lastName = lastNameEdit.getText().toString();
		
		EditText emailEdit = (EditText) findViewById(R.id.get_email);
		String email = emailEdit.getText().toString();
		
		EditText addressEdit = (EditText) findViewById(R.id.get_address);
		String address = addressEdit.getText().toString();
		
		EditText creditNumEdit = (EditText) findViewById(R.id.get_credit_num);
		String creditNum = creditNumEdit.getText().toString();
		
		EditText expiryDateEdit = (EditText) findViewById(R.id.get_expiry_date);
		String expiryDate = expiryDateEdit.getText().toString();
		
		EditText usernameEdit = (EditText) findViewById(R.id.get_username);
		String username = usernameEdit.getText().toString();
		
		EditText passwordEdit = (EditText) findViewById(R.id.get_password);
		String password = passwordEdit.getText().toString();
		
		EditText passwordConEdit = (EditText) findViewById(R.id.get_password_con);
		String passwordCon = passwordConEdit.getText().toString();
		
		adminPassEdit = (EditText) findViewById(R.id.get_admin_pass);
		String adminPass = adminPassEdit.getText().toString();
		if (firstName.matches("") || lastName.matches("") 
				|| email.matches("") || address.matches("")
				|| creditNum.matches("") || expiryDate.matches("") 
				|| username.matches("") || password.matches("")) {
			Toast.makeText(this, "All fields must be non-empty",
					Toast.LENGTH_SHORT).show();
			return;
		} else {
			if (password.equals(passwordCon)) {
				if (isAdmin.isChecked()) {
					if (systemControl.ADMIN_PASS.equals(adminPass)) {
						try {
							systemControl.addAdmin(lastName, firstName, 
									email, address, creditNum, expiryDate, 
									username, password);
						} catch (UserAlreadyExistsException e) {
							Toast.makeText(this, "Username or email" +
									" already taken", Toast.LENGTH_SHORT)
									.show();
							return;
						}
					} else {
						Toast.makeText(this, "Incorrect admin password",
								Toast.LENGTH_SHORT).show();
						return;
					}
				} else {
					try {
						systemControl.addClient(lastName, firstName, 
								email, address, creditNum, expiryDate, 
								username, password);
					} catch (UserAlreadyExistsException e) {
						Toast.makeText(this, "Username or email" +
								" already taken", Toast.LENGTH_SHORT).show();
						return;
					}
				}
			} else {
				Toast.makeText(this, "Miss-match of password and " +
						"confirm password", Toast.LENGTH_SHORT).show();
				return;

			}
		
		
			Intent intent = new Intent(this, MainActivity.class);
			intent.putExtra(MainActivity.SYSTEM_CONTROL, systemControl);
		
			startActivity(intent);
		}
		
		
	}
	
	
	
	
	
	
	
}

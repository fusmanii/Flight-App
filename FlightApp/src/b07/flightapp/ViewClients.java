package b07.flightapp;

import java.util.ArrayList;
import java.util.List;

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
import android.widget.AdapterView.OnItemClickListener;

public class ViewClients extends ActionBarActivity {
	
	private SystemControl systemControl;
	
	private List<Client> users;
	
	private List<Client> clients;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_clients);
		
		Intent intent = getIntent();
		
		systemControl = (SystemControl) 
	    		intent.getSerializableExtra(MainActivity.SYSTEM_CONTROL);
		
		users = systemControl.getUsers();
		
		ListView listView = (ListView) findViewById(R.id.users);
		
		clients = new ArrayList<Client>();
		
		for (Client user : users) {
			if (!user.isAdmin()) {
				clients.add(user);
			}
		}
		
		String [] data = new String[clients.size()];
		Client curClient;
		
		for (int i = 0; i < clients.size(); i++) {
			curClient = clients.get(i);
			data[i] = curClient.getFirstName() + 
					" " + curClient.getLastName() + "\n" 
					+ curClient.getUsername();
		}
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, 
	    		R.layout.drawer_list_item, data);
		listView.setAdapter(adapter);
		
		listView.setOnItemClickListener(new OnItemClickListener() {
	    	public void onItemClick(AdapterView<?> arg0, View v, 
	    			int position, long id) {
	    		
	    		clientSelect(position);
	    	}
	    });
	}
	
	private void clientSelect (int position) {
		
		Intent intent = new Intent(this, EditPersonalActivity.class);
		intent.putExtra(MainActivity.SYSTEM_CONTROL, systemControl);
		intent.putExtra(MainActivity.USER, clients.get(position));
		
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_clients, menu);
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

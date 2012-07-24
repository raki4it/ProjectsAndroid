/**
 * 
 */
package activity.pack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.main.R;

import engine.pack.HostsLoadAndSave;

/**
 * @author winx
 * 
 */

public class HostsMain extends Activity {
	


	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hosts);

		HostsLoadAndSave hostFiles = new HostsLoadAndSave();
		ListView listView = (ListView) findViewById(R.id.listHosts);
		HostListAdapter adapter = new HostListAdapter(this,
				R.layout.listview_item_row, hostFiles.loadData());

		View header = (View) getLayoutInflater().inflate(
				R.layout.listview_header_row, null);

		listView.addHeaderView(header);
		listView.setAdapter(adapter);

		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(getApplicationContext(),
						"Click ListItem Number " + position, Toast.LENGTH_LONG)
						.show();
			}
		});

	}

	public boolean onCreateOptionsMenu(Menu menu) {

		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_menu, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.add_host:
			startActivity(new Intent(this, AddHost.class));
			return true;
		case R.id.settings:
			startActivity(new Intent(this, AndiXActivity.class));
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}

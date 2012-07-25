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

import com.main.R;

import engine.pack.HostAccount;
import engine.pack.HostsLoadAndSave;

/**
 * @author winx
 * 
 */

public class HostsMain extends Activity {
	private ListView listView;
	private Intent intentToMain;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hosts);

		HostsLoadAndSave hostFiles = new HostsLoadAndSave();
		intentToMain = new Intent(this, AndiXActivity.class);

		listView = (ListView) findViewById(R.id.listHosts);
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
				if (position != 0) {
					HostAccount hostToConnect = (HostAccount) listView
							.getItemAtPosition(position);
					
					intentToMain.putExtra("hostToConnect", hostToConnect);
					startActivity(intentToMain);
				}
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

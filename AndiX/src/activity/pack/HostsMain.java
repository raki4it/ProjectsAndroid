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
import android.widget.ArrayAdapter;
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
	@SuppressWarnings("rawtypes")
	private ArrayAdapter adapterEmpty;
	private HostListAdapter adapterFill;

	@SuppressWarnings("rawtypes")
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hosts);

		HostsLoadAndSave hostFiles = new HostsLoadAndSave();
		intentToMain = new Intent(this, AndiXActivity.class);

		listView = (ListView) findViewById(R.id.listHosts);
		View header = (View) getLayoutInflater().inflate(
				R.layout.listview_header_row, null);
		listView.addHeaderView(header);

		if (hostFiles.loadData() == null) {
			adapterEmpty = new ArrayAdapter(this, R.layout.listview_item_row);
			listView.setAdapter(adapterEmpty);
		} else {

			adapterFill = new HostListAdapter(this, R.layout.listview_item_row,
					hostFiles.loadData());
			listView.setAdapter(adapterFill);
		}

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

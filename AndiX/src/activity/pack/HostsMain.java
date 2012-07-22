/**
 * 
 */
package activity.pack;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.main.R;

import engine.pack.HostAccount;

/**
 * @author winx
 * 
 */
public class HostsMain extends Activity  {
	private TextView hostsListName;
	private TextView hostsListAddress;
	private TextView hostsListUser;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hosts);

		ListView listView = (ListView) findViewById(R.id.listHosts);
		String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
				"Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
				"Linux", "OS/2" };

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_expandable_list_item_1, android.R.id.text1, values);

		// Assign adapter to ListView
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

		//loadDataFromBin();

	}

	private void loadDataFromBin() {
		HostAccount b = null;
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream(new File(Environment
					.getExternalStorageDirectory().getPath()
					+ "/andix/"
					+ "save.bin"));
			ois = new ObjectInputStream(fis);

			b = (HostAccount) ois.readObject();

			hostsListName.setText(b.getHostName());
			hostsListAddress.setText(b.getHostIp());
			hostsListUser.setText(b.getUser());

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ois != null)
					ois.close();
			} catch (IOException e) {
			}
			try {
				if (fis != null)
					fis.close();
			} catch (IOException e) {
			}
		}

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

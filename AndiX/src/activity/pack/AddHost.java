package activity.pack;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;

import com.main.R;

import engine.pack.HostAccount;

public class AddHost extends Activity {

	private EditText hostName;
	private EditText hostIp;
	private EditText username;
	private EditText password;
	private Intent intent;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addhost);
		hostName = (EditText) findViewById(R.id.host_name);
		hostIp = (EditText) findViewById(R.id.host_ip);
		username = (EditText) findViewById(R.id.username);
		password = (EditText) findViewById(R.id.password);
		intent = new Intent(this, HostsMain.class);
		// Intent intent = getIntent();
		// String message = intent.getStringExtra(AndiXActivity.EXTRA_MESSAGE);
		// TextView textView = new TextView(this);
		// textView.setTextSize(40);
		// textView.setText(message);
		//
		// setContentView(textView);
	}

	public void save(View view) {
		
		if (hostName.getText().toString().length() == 0
				|| username.getText().toString().length() == 0
				|| password.getText().toString().length() == 0
				|| hostIp.getText().toString().length() == 0) {

			if (hostName.getText().toString().length() == 0)
				hostName.setError(getText(R.string.reqName));
			
			if (username.getText().toString().length() == 0)
				username.setError(getText(R.string.reqUser));
			
			if (password.getText().toString().length() == 0)
				password.setError(getText(R.string.reqPass));
			
			if (hostIp.getText().toString().length() == 0)
				hostIp.setError(getText(R.string.reqIp));
		}

		else {
			HostAccount host = new HostAccount(hostName.getText().toString(),
					hostIp.getText().toString(), username.getText().toString(),
					password.getText().toString());
			FileOutputStream fos = null;
			ObjectOutputStream oos = null;

			try {
				fos = new FileOutputStream(new File(Environment
						.getExternalStorageDirectory().getPath() + "/save.bin"));
				oos = new ObjectOutputStream(fos);
				oos.writeObject(host);

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {

				try {
					if (oos != null)
						oos.close();
				} catch (IOException e) {
				}
				try {
					if (fos != null)
						fos.close();
				} catch (IOException e) {
				}
			}

			startActivity(intent);
		}

	}

	public void cancel(View view) {
		hostIp.setText("");
		hostName.setText("");
		username.setText("");
		password.setText("");

		startActivity(intent);

	}
}

package activity.pack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.main.R;

import engine.pack.HostsLoadAndSave;

public class AddHost extends Activity {

	private EditText hostName;
	private EditText hostIp;
	private EditText username;
	private EditText password;
	private Intent intent;
	private HostsLoadAndSave saveHostFiles;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addhost);
		saveHostFiles = new HostsLoadAndSave();
		hostName = (EditText) findViewById(R.id.host_name);
		hostIp = (EditText) findViewById(R.id.host_ip);
		username = (EditText) findViewById(R.id.username);
		password = (EditText) findViewById(R.id.password);
		intent = new Intent(this, HostsMain.class);
		
	}

	private boolean validateDataForm() {
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
			return false;
		}
		return true;
	}

	public void save(View view) {

		if (validateDataForm()) {
			saveHostFiles.saveData(hostName.getText().toString(),
					hostIp.getText().toString(), username.getText().toString(),
					password.getText().toString());
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

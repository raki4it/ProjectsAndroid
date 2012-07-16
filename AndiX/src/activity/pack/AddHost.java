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
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addhost);
		// Intent intent = getIntent();
		// String message = intent.getStringExtra(AndiXActivity.EXTRA_MESSAGE);
		// TextView textView = new TextView(this);
		// textView.setTextSize(40);
		// textView.setText(message);
		//
		// setContentView(textView);
	}

	public void save(View view) {

		EditText hostName = (EditText) findViewById(R.id.host_name);
		EditText hostIp = (EditText) findViewById(R.id.host_ip);
		EditText username = (EditText) findViewById(R.id.username);
		EditText password = (EditText) findViewById(R.id.password);

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
		
		Intent intent = new Intent(this, HostsMain.class);
		startActivity(intent);

	}
}

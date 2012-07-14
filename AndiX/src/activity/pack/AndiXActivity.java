package activity.pack;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.jcraft.jsch.JSchException;
import com.main.R;

import engine.pack.ConnectSSH;

public class AndiXActivity extends Activity {
	/** Called when the activity is first created. */
	public final static String EXTRA_MESSAGE = "com.example.myapp.MESSAGE";
	String message;
	private ConnectSSH ssh;

	@Override
	public void onCreate(Bundle savedInstanceState)  {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		ssh = new ConnectSSH("192.168.1.88", "winx", 22);
		try {
			ssh.connect();
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/** Called when the user selects the Send button METRO UI lub identyczny z GNOMA 
	 * @throws Exception */
	public void play(View view) throws Exception {
		
		ssh.runCommand("env DISPLAY=:0.0 rhythmbox-client --no-start --play");
		// Do something in response to button
		// Intent intent = new Intent(this, DisplayMessageActivity.class);
		// EditText editText = (EditText) findViewById(R.string.hello);
		// editText.getText().toString();
		// intent.putExtra(EXTRA_MESSAGE, message);
		// startActivity(intent);
	}
	
	public void nextSong(View view) throws Exception{
		ssh.runCommand("env DISPLAY=:0.0 rhythmbox-client --no-start --next");
	}
	
	public void previousSong(View view) throws Exception{
		ssh.runCommand("env DISPLAY=:0.0 rhythmbox-client --no-start --previous");
	}
	
	public void pause(View view) throws Exception{
		ssh.runCommand("env DISPLAY=:0.0 rhythmbox-client --no-start --pause");
	}
	
}
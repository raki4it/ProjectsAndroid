package activity.pack;

import com.main.R;

import engine.pack.ConnectSSH;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AndiXActivity extends Activity {
	/** Called when the activity is first created. */
	public final static String EXTRA_MESSAGE = "com.example.myapp.MESSAGE";
	String message;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

	}

	/** Called when the user selects the Send button 
	 * @throws Exception */
	public void sendMessage(View view) throws Exception {
		
		ConnectSSH ssh = new ConnectSSH("192.168.1.88", "winx", 22);
		ssh.connect();
		ssh.runCommand("env DISPLAY=:0.0 rhythmbox-client --no-start --play");
		//ssh.closeConnect();
//		Properties config = new Properties();
//		config.put("StrictHostKeyChecking", "no");
//		JSch jsch = new JSch();
//		Session session = jsch.getSession("winx", "192.168.1.88", 22);
//		session.setPassword("kp45se!");
//		session.setConfig(config);
//		session.connect();
//
//		String command = "env DISPLAY=:0.0 rhythmbox-client --no-start --play";
//		Channel channel = session.openChannel("exec");
//		((ChannelExec) channel).setCommand(command);
//		channel.setInputStream(null);
//		((ChannelExec) channel).setErrStream(System.err);
//		InputStream in = channel.getInputStream();
//
//		channel.connect();
//
//	/*	byte[] tmp = new byte[1024];
//		while (true) {
//			while (in.available() > 0) {
//				int i = in.read(tmp, 0, 1024);
//				if (i < 0)
//					break;
//				System.out.print(new String(tmp, 0, i));
//			}
//			if (channel.isClosed()) {
//				System.out.println("exit-status: " + channel.getExitStatus());
//				break;
//			}
//			try {
//				Thread.sleep(1000);
//			} catch (Exception ee) {
//			}
//		}*/
//
//		channel.disconnect();
//		session.disconnect();

		// Do something in response to button
		Intent intent = new Intent(this, DisplayMessageActivity.class);
		// EditText editText = (EditText) findViewById(R.string.hello);
		// editText.getText().toString();
		intent.putExtra(EXTRA_MESSAGE, message);
		startActivity(intent);
	}
}
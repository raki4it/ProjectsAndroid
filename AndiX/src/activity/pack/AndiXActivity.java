package activity.pack;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;

import com.jcraft.jsch.JSchException;
import com.main.R;

import engine.pack.ConnectSSH;
import engine.pack.HostAccount;

public class AndiXActivity extends Activity implements OnSeekBarChangeListener {
	private ConnectSSH ssh;
	private SeekBar mSeekBar;
	HostAccount hostToConnect = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		System.out.println("CREATED!!!");
		mSeekBar = (SeekBar) findViewById(R.id.seekBar1);
		mSeekBar.setOnSeekBarChangeListener(this);
	//	mSeekBar.setProgress(progress);

//		ConnectivityManager connManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
//		NetworkInfo mWifi = connManager
//				.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

		Bundle bundel = getIntent().getExtras();
		hostToConnect = (HostAccount) bundel.get("hostToConnect");

		// if (mWifi.isConnected()) {
		//new Thread(new Runnable() {
		//	public void run() {
				ssh = new ConnectSSH(hostToConnect, 22);
				System.out.println("IN_THREAD");
				try {
					ssh.connect();
				} catch (JSchException e) {
					// TODO Auto-generated catch block
					Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
				}
				System.out.println("OUT_THREAD");

			//}
		//}).start();
		// } else {
		// AlertDialog ad = new AlertDialog.Builder(this).create();
		// ad.setCancelable(true); // This blocks the 'BACK' button
		// ad.setMessage("Please connect to network...");
		// ad.setButton("OK", new DialogInterface.OnClickListener() {
		// @Override
		// public void onClick(DialogInterface dialog, int which) {
		// startActivity(new
		// Intent(android.provider.Settings.ACTION_WIFI_SETTINGS));
		// }
		// });
		// ad.show();
		// }

	}
	
//	private int getVolume(){
//		ssh.runCommand(" env DISPLAY=:0.0 rhythmbox-client --no-start --print-volume");
//	}

	public void play(View view) {
		try {
			ssh.runCommand("env DISPLAY=:0.0 rhythmbox-client --no-start --play");
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
		}
	}

	public void nextSong(View view) {
		try {
			ssh.runCommand("env DISPLAY=:0.0 rhythmbox-client --no-start --next");
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
		}
	}

	public void previousSong(View view) {
		try {
			ssh.runCommand("env DISPLAY=:0.0 rhythmbox-client --no-start --previous");
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
		}
	}

	public void pause(View view) {
		try {
			ssh.runCommand("env DISPLAY=:0.0 rhythmbox-client --no-start --pause");
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
		}
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		double p = (double) progress / 10;
		try {
			ssh.runCommand("env DISPLAY=:0.0 rhythmbox-client --no-start --set-volume "
					+ p);
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
		}
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub

	}

}
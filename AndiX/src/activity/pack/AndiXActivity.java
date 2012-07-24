package activity.pack;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.main.R;

import engine.pack.ConnectSSH;

public class AndiXActivity extends Activity implements OnSeekBarChangeListener {
	private ConnectSSH ssh;
	private SeekBar mSeekBar;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		mSeekBar = (SeekBar) findViewById(R.id.seekBar1);
		mSeekBar.setOnSeekBarChangeListener(this);

		ConnectivityManager connManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
		NetworkInfo mWifi = connManager
				.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

		//TODO send object
//		HostAccount hostToConnect = getIntent().getExtras().getParcelable("hostToConnect");
//		if(hostToConnect!=null)
//			System.out.println(hostToConnect.getHostName());
		
		String message = getIntent().getStringExtra(HostsMain.HOST_NAME);
		System.out.println(message);
		
		if (mWifi.isConnected()) {
			new Thread(new Runnable() {
				public void run() {
					ssh = new ConnectSSH("192.168.1.88", "winx", 22);
					System.out.println("Start!");
					ssh.connect();
					System.out.println("Watek start!");
				}
			}).start();
		} else {
			AlertDialog ad = new AlertDialog.Builder(this).create();
			ad.setCancelable(true); // This blocks the 'BACK' button
			ad.setMessage("Please connect to network...");
			ad.setButton("OK", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {

				}
			});
			ad.show();
		}

	}

	public void play(View view) {
		ssh.runCommand("env DISPLAY=:0.0 rhythmbox-client --no-start --play");
	}

	public void nextSong(View view) {
		ssh.runCommand("env DISPLAY=:0.0 rhythmbox-client --no-start --next");
	}

	public void previousSong(View view) {
		ssh.runCommand("env DISPLAY=:0.0 rhythmbox-client --no-start --previous");
	}

	public void pause(View view) {
		ssh.runCommand("env DISPLAY=:0.0 rhythmbox-client --no-start --pause");
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		double p = (double) progress / 10;
		ssh.runCommand("env DISPLAY=:0.0 rhythmbox-client --no-start --set-volume "
				+ p);
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
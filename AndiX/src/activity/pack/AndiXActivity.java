package activity.pack;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
	private ProgressDialog pd;
	HostAccount hostToConnect = null;

	@SuppressLint("HandlerLeak")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		final Handler handler = new Handler() {
			public void handleMessage(final Message msgs) {
				if (msgs.what == 0) {
					pd = ProgressDialog.show(AndiXActivity.this, "Working...", "Connecting "+hostToConnect.getHostIp(), true,
			                false);
				}
				if(msgs.what == 9)
				{
					pd.dismiss();
					Toast.makeText(getApplicationContext(), "Connected",
							Toast.LENGTH_LONG).show();
					
				}
				if (msgs.what == 1) {
					Toast.makeText(getApplicationContext(), msgs.toString(),
							Toast.LENGTH_LONG).show();
				}
				System.out.println(msgs.what);
			}
		};

		mSeekBar = (SeekBar) findViewById(R.id.seekBar1);
		mSeekBar.setOnSeekBarChangeListener(this);
		// mSeekBar.setProgress(progress);

		// ConnectivityManager connManager = (ConnectivityManager)
		// getSystemService(CONNECTIVITY_SERVICE);
		// NetworkInfo mWifi = connManager
		// .getNetworkInfo(ConnectivityManager.TYPE_WIFI);

		Bundle bundel = getIntent().getExtras();
		hostToConnect = (HostAccount) bundel.get("hostToConnect");
		
		// if (mWifi.isConnected()) {
		
		new Thread(new Runnable() {
			public void run() {
				handler.sendEmptyMessage(0);
				ssh = new ConnectSSH(hostToConnect, 22);
				try {
					ssh.connect();
				} catch (JSchException e) {
					handler.sendMessage(handler.obtainMessage(1, e.getMessage()));
				}
				handler.sendEmptyMessage(9);
				
			}
		}).start();

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

	// private int getVolume(){
	// ssh.runCommand(" env DISPLAY=:0.0 rhythmbox-client --no-start --print-volume");
	// }

	public void play(View view) {
		try {
			ssh.runCommand("env DISPLAY=:0.0 rhythmbox-client --no-start --play");
		} catch (JSchException e) {
			Toast.makeText(getApplicationContext(), e.getMessage(),
					Toast.LENGTH_LONG).show();
		}
	}

	public void nextSong(View view) {
		try {
			ssh.runCommand("env DISPLAY=:0.0 rhythmbox-client --no-start --next");
		} catch (JSchException e) {
			Toast.makeText(getApplicationContext(), e.getMessage(),
					Toast.LENGTH_LONG).show();
		}
	}

	public void previousSong(View view) {
		try {
			ssh.runCommand("env DISPLAY=:0.0 rhythmbox-client --no-start --previous");
		} catch (JSchException e) {
			Toast.makeText(getApplicationContext(), e.getMessage(),
					Toast.LENGTH_LONG).show();
		}
	}

	public void pause(View view) {
		try {
			ssh.runCommand("env DISPLAY=:0.0 rhythmbox-client --no-start --pause");
		} catch (JSchException e) {
			Toast.makeText(getApplicationContext(), e.getMessage(),
					Toast.LENGTH_LONG).show();
		}
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {

		try {
			double p = (double) progress / 100;
			ssh.runCommand("env DISPLAY=:0.0 rhythmbox-client --no-start --set-volume "
					+ p);
		} catch (JSchException e) {
			Toast.makeText(getApplicationContext(), e.getMessage(),
					Toast.LENGTH_LONG).show();
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
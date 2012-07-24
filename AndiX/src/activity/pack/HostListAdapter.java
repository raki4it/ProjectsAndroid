package activity.pack;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.main.R;

import engine.pack.HostAccount;

public class HostListAdapter extends ArrayAdapter<HostAccount> {

	private int resource;
	private Context context;     
    private HostAccount data[] = null;
    
	public HostListAdapter(Context context, int textViewResourceId,
			HostAccount[] objects) {
		super(context, textViewResourceId, objects);
		this.resource = textViewResourceId;
		this.context = context;
        this.data = objects;
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		HostAccountHolder holder = null;

		if (row == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(resource, parent, false);

			holder = new HostAccountHolder();
			holder.txtHostName = (TextView) row.findViewById(R.id.txtHostName);
			holder.txtHostIP = (TextView) row.findViewById(R.id.txtHostIP);

			row.setTag(holder);
		} else {
			holder = (HostAccountHolder) row.getTag();
		}

		HostAccount hosts = data[position];
		holder.txtHostName.setText(hosts.getHostName());
		holder.txtHostIP.setText(hosts.getHostIp());
		

		return row;
	}

	static class HostAccountHolder {
		TextView txtHostName;
		TextView txtHostIP;
	}
}

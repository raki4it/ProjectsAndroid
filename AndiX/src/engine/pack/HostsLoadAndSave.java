/**
 * 
 */
package engine.pack;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import android.os.Environment;

/**
 * @author winx
 * 
 */
public class HostsLoadAndSave {

	private File localFiles = new File(Environment.getExternalStorageDirectory()
			.getPath() + "/andix/");
	
	public HostAccount[] loadData() {

		// TODO if not exist hostfiles function throw exception

		HostAccount hostAccountReturn[] = null;
		HostAccount b = null;
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		
		try {
			
			File[] faFiles = localFiles.listFiles();
			
			hostAccountReturn = new HostAccount[faFiles.length];
			int i = 0;
			for (File file : faFiles) {

				fis = new FileInputStream(file);
				ois = new ObjectInputStream(fis);

				b = (HostAccount) ois.readObject();

				hostAccountReturn[i] = b;
				i++;

			}
			return hostAccountReturn;

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
		return hostAccountReturn;

	}

	public void saveData(String hostName, String hostIp,
			String username, String password) {

		if (!localFiles.exists()) {
			localFiles.mkdir();
		}

		HostAccount host = new HostAccount(hostName, hostIp, username, password);
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;

		try {
			fos = new FileOutputStream(localFiles.getPath() + "/" + hostName);
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

	}

}

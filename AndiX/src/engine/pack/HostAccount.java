/**
 * 
 */
package engine.pack;

import java.io.Serializable;

/**
 * @author winx
 * 
 */
public class HostAccount implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 150644017193182562L;

	private String hostName = null;
	private String hostIp = null;
	private String user = null;
	private String password = null;

	public HostAccount(String host_name, String host_ip, String user,
			String password) {
		this.hostName = host_name;
		this.hostIp = host_ip;
		this.user = user;
		this.password = password;
	}

	public String getHostName() {
		return this.hostName;
	}

	public String getHostIp() {
		return this.hostIp;
	}

	public String getUser() {
		return this.user;
	}

	public String getPassword() {
		return this.password;
	}

}

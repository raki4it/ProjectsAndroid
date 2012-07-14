/**
 * 
 */
package engine.pack;

import java.io.IOException;
import java.util.Properties;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

/**
 * @author winx
 *
 */
public class ConnectSSH {

	private String Host;
	private String Username;
	private int Port; 
	private Session session;
	private Properties config;
	private Channel channel;
	/**
	 * 
	 */
	public ConnectSSH(String host, String username, int port) {
		this.Host = host;
		this.Port = port;
		this.Username = username;
		this.session =  null;
		this.config = new Properties();
		this.channel = null;
	}
	public void connect() throws JSchException{
		
		config.put("StrictHostKeyChecking", "no");

		JSch jsch = new JSch();
		session = jsch.getSession(Username, Host, Port);
		session.setPassword("");
		session.setConfig(config);
		session.connect();
	}
	public int runCommand(String cmd) throws JSchException, IOException{
		
		channel = session.openChannel("exec");
		((ChannelExec) channel).setCommand(cmd);
		channel.setInputStream(null);
		channel.connect();
		
		return channel.getExitStatus();
	}
	
	public void closeConnect() {
		channel.disconnect();
		session.disconnect();
	}
	

}

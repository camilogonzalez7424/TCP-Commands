package app;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Calendar;

import comm.Server;

public class Application implements Server.OnMessageListener{

private Server connection;
	
	public Application() {
		connection = new Server();
		connection.setPuerto(6000);
		connection.setListener(this);
	}

	public void init() {
		connection.start();
		
	}

	@Override
	public String time() {
		
			Calendar c = Calendar.getInstance();
			String fecha = c.getTime().toString();
			return fecha;
	}

	@Override
	public String ip() {
		InetAddress ip;
		String msg = "";
		try {
			ip = InetAddress.getLocalHost();
			msg = ip.getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		return msg;
	}
}

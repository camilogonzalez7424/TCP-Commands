package app;

import java.io.IOException;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.List;

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
	public ArrayList<String> interfaces() {
		int counter = 0;
    	String macSeparator = "";
    	ArrayList<String> mostrar = new ArrayList<>();

    	
		try {
			Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
			
			while(interfaces.hasMoreElements()) {
				NetworkInterface netN = interfaces.nextElement();
				
				if(netN.isUp()) {
					
					
					//System.out.println(netN.getName());
					mostrar.add(netN.getName());
					
					if(netN.getHardwareAddress() != null) {
						//System.out.println(netN.getHardwareAddress());
						String mac = new BigInteger(1,netN.getHardwareAddress()).toString(16);
						
						for (int i = 0; i < mac.length(); i++){
						    char letra = mac.charAt(i);
						    
						    macSeparator += letra;
						    
						    counter++;
						    
						    if(counter == 2 && i != 9) {
						    	//Tratamiento del caracter
						    	
						    	macSeparator += ":"; 
						    	counter = 0;
						    }
						    
						}
					  //  System.out.println("<<<"+macSeparator);
						mostrar.add(macSeparator);

					}
					
				}
				
				
				
			}
		} catch (SocketException e) {
			// TODO Auto-generated catch block

		
		}

    	
		
		return mostrar;
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

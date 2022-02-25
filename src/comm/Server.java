package comm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Calendar;

public class Server extends Thread{

	private ServerSocket server;
	private Socket socket;
	private int puerto;
	
	public OnMessageListener listener;
	
	public OutputStream os;
	public OutputStreamWriter osw;
	public BufferedWriter bwriter;
	
	
	public void setPuerto(int puerto) {
		this.puerto = puerto;
	}
	
	@Override
	public void run() {
		try {
			while(true) {
				server = new ServerSocket(puerto);
				System.out.println("Esperando un cliente");
				socket = server.accept();
				System.out.println("Cliente conectado");
				InputStream is = socket.getInputStream();
				BufferedReader breader = new BufferedReader(new InputStreamReader(is));
				
				os = socket.getOutputStream();
				osw = new OutputStreamWriter(os);
				bwriter = new BufferedWriter(osw) ;
				
				
					String msg = breader.readLine();
					if(msg.contains("whatTimeIsIt")) {
						String fecha = listener.time();
						bwriter.write(fecha+"\n");
						bwriter.flush();
					}else if(msg.contains("interfaces")) {
						ArrayList<String> interfaces = listener.interfaces();
						bwriter.write(interfaces+"\n");
						bwriter.flush();
					}else if(msg.contains("remoteIpconfig")){
						String ip = listener.ip();
						bwriter.write(ip+"\n");
						bwriter.flush();
					}else if(msg.contains("RTT")) {
						
					}else if(msg.contains("Speed")) {
						
					}else {
						bwriter.write("La palabra: "+msg+" no existe."+"Error en la etapa 8 OSI :)"+"\n");
						bwriter.flush();
					}
				
				server.close();
			}
			
		}catch(IOException ex) {
			
		}
	}
	
	public void setListener(OnMessageListener listener) {
		this.listener = listener;
	}
	
	public interface OnMessageListener{
		public String time();
		public ArrayList<String> interfaces();
		public String ip();
	}
}

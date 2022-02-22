package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {
		try {
		while(true) {
			Scanner scanner = new Scanner(System.in);
			String line = scanner.nextLine()+"\n";
			
			Socket socket = new Socket("127.0.0.1", 6000);
			
			OutputStream os = socket.getOutputStream();
			BufferedWriter bwriter = new BufferedWriter(new OutputStreamWriter(os));
			
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader breader = new BufferedReader(isr);
			
			
				bwriter.write(line);
				bwriter.flush();
				
				//Recibir mensaje
				String recibido = breader.readLine();
				System.out.println("Mensaje Recibido! "+recibido);
			}
			
		}catch(IOException ex) {
			
		}

	}
}

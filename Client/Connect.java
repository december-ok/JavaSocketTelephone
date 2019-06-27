package Client;

import java.io.IOException;
import java.io.*;
import java.net.*;

public class Connect extends Thread{
	Socket socket = null;
	BufferedInputStream input = null;
	BufferedOutputStream output = null;
	byte[] temp = new byte[10000];
	
	Phone p = null;
	Connect(Phone p) {
		this.p = p;
		try {
			socket = new Socket("127.0.0.1", 9999);
			System.out.println("Connected!");
			input = new BufferedInputStream(socket.getInputStream());
			output = new BufferedOutputStream(socket.getOutputStream());
			
			p.intro.setText("Connected!");
			this.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		while (true) {
			int t = p.time_cnt++;
			p.time.setText(t / 60 + ":" + t % 60);
			try {
				this.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

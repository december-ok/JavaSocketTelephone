package Client;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

public class Speaker extends Thread{
	SourceDataLine sourceLine = null;
	byte[] data = null;
	Phone p = null;
	
	Speaker(Phone p){
		this.p = p;
		try {
			DataLine.Info info = new DataLine.Info(SourceDataLine.class, p.format);
			sourceLine = (SourceDataLine) AudioSystem.getLine(info);
			sourceLine.open();
			data = new byte[(int) (sourceLine.getBufferSize() / 5512)];
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		try {
			sourceLine.open();
			sourceLine.start();
			while(true) {
				p.con.input.read(data, 0, data.length);
				sourceLine.write(data, 0, data.length);
			}
		} catch(Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
}

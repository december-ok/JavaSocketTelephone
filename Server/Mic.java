package Server;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.TargetDataLine;

public class Mic extends Thread{
	TargetDataLine targetLine = null;
	byte[] data = null;
	Phone p = null;
	
	public Mic(Phone p) {
		this.p = p;
		try {
			DataLine.Info info = new DataLine.Info(TargetDataLine.class, p.format);
			targetLine = (TargetDataLine) AudioSystem.getLine(info);
			targetLine.open();
			// System.out.println(targetLine.getBufferSize());
			data = new byte[(int) (targetLine.getBufferSize() / 5512)];
			System.out.println("Ready to use Mic");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run(){
		try {
			targetLine.open();
			targetLine.start();
			while (true) {
				System.out.println(data.length);
				targetLine.read(data, 0, data.length);
				p.con.output.write(data, 0, data.length);
				p.con.output.flush();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		}
	}
}

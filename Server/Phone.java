package Server;

import java.awt.FlowLayout;

import javax.sound.sampled.AudioFormat;
import javax.swing.*;

public class Phone extends JFrame {
	public JLabel intro = new JLabel("Waiting for connect...");
	public JLabel time = new JLabel("0:00");
	public int time_cnt = 0;

	public Connect con = null;
	public Mic mic = null;
	public Speaker spk = null;

	public AudioFormat format = 
			new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 11025, 16, 2, 4, 11025, false);

	public Phone() {
		this.setSize(300,80);
		this.setTitle("Server");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);
		this.setLayout(new FlowLayout());
		this.setVisible(true);
		this.setAlwaysOnTop(true);
		
		this.add(intro);
		this.add(time);
		
		this.con = new Connect(this);
		this.mic = new Mic(this);
		this.spk = new Speaker(this);
	}
	
	public void startCommunication() {
		this.mic.start();
		this.spk.start();
	}
}

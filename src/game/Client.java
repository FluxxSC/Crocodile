package game;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client implements Runnable{
	
	private Thread t;
	private String address;
	private int port;
	private boolean running = true;
	private Play play;
	
	private DatagramSocket clientSocket;
	private InetAddress IPAddress;

	public Client(String address, int port, Play play) {
		this.address = address;
		this.port = port;
		this.play = play;
	}
	
	public void run() {
		setup();
	}
	
	public void send(String message) {
		byte[] sendData = new byte[1024];
		sendData = message.getBytes();
		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
		try {
			clientSocket.send(sendPacket);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void setup() {
		try {
			clientSocket = new DatagramSocket();
			IPAddress = InetAddress.getByName(address);
			sendHello();
			listen();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void sendHello() {
		String msg = composeMessage(Commands.HELLO);
		send(msg);
	}
	
	private void listen() {
		while (running) {
			byte[] receiveData = new byte[1024];
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			try {
				clientSocket.receive(receivePacket);
			} catch (Exception e) {
				e.printStackTrace();
			}
			String message = new String(receivePacket.getData());
			handleMessage(message);
			
		}
	}
	
	private void handleMessage(String message) {
		System.out.println("Received from server: " + message);
	}
	
	private String composeMessage(Commands command) {
		return composeMessage(command, "");
	}
	
	private String composeMessage(Commands command, String arg) {
		return command + "|" + arg;
	}
	
	
	
	public void start() {
		if (t == null) {
			t = new Thread(this, "clientThead");
			t.start();
		}
	}
	
	public void stop() {
		running = false;
	}
	
	
	
	
	

}

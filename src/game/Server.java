package game;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Hashtable;

public class Server implements Runnable{
	
	private Thread t;
	private int port;
	private boolean running = true;
	private Play play;
	
	private DatagramSocket serverSocket;
	private Hashtable<Integer, ClientData> clients;
	private int currentID = 0;
	
	private int timeStamp = 120;
	
	
	public Server(int port, Play play) {
		this.port = port;
		this.play = play;
		clients = new Hashtable<Integer, ClientData>();
	}

	public void run() {
		setup();
	}
	
	private void setup() {
		try {
			serverSocket = new DatagramSocket(port);
			listen();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void listen() {
		while (running) {
			byte[] receiveData = new byte[1024];
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			try {
				serverSocket.receive(receivePacket);
			} catch (Exception e) {
				e.printStackTrace();
			}
			String message = new String(receivePacket.getData());
			InetAddress IPAddress = receivePacket.getAddress();
			int port = receivePacket.getPort();
			handleMessage(message, IPAddress, port);
		}
	}
	
	private void handleMessage(String message, InetAddress IPAddress, int port) {
		Commands command;
		String arg;
		
		String[] parts = message.split("|");
		command = Commands.is(parts[0]);
		if (parts.length < 1) {
			arg = parts[1];
		}
		
		//check if the client is new
		if (command == Commands.HELLO) {
			ClientData c = new ClientData(IPAddress, port, currentID);
			clients.put(currentID, c);
			String msg = Commands.ID + "|" + currentID + "|" + timeStamp;
			send(msg, c);
			currentID++;
			System.out.println(c);
		}
	}
	
	private void broadcast(String message) {
		// sends the message to all clients
		
		for (int i : clients.keySet()) {
			ClientData c = clients.get(i);
			send(message, c);
		}
	}
	
	private void send(String message, ClientData client) {
		//sends the message to one client
		
		byte[] sendData = new byte[1024];
		sendData = message.getBytes();
		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, client.IPAddress, client.port);
		try {
			serverSocket.send(sendPacket);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void start() {
		if (t == null) {
			t = new Thread(this, "serverThread");
			t.start();
		}
	}
	
	public void stop() {
		running = false;
	}
}

class ClientData {
	
	public InetAddress IPAddress;
	public int port;
	public int ID;
	
	public ClientData(InetAddress IPAddress, int port, int ID) {
		this.IPAddress = IPAddress;
		this.port = port;
		this.ID = ID;
	}
	
	public String toString() {
		return "IP:" + IPAddress + " port:" + port + " ID:" + ID;
	}
}

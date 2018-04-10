package cs.tcd.ie;

import java.net.DatagramPacket;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;

/* MIT License
 *
 * Copyright (c) 2017 Brandon Dooley - dooleyb1@tcd.ie
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 */

public class ControllerInformPacket implements PacketContent {
	
	byte[] src;
	byte[] connectionCount;
	byte[] flag;
	int[] connectionAddresses;
	
	
	public ControllerInformPacket(DatagramPacket packet) {
		byte[] buffer= null;
	
		this.src = new byte[SRC_ADDRESS_LENGTH];
		this.flag = new byte[FLAG_LENGTH];
		this.connectionCount = new byte[CONNECTION_COUNT_LENGTH];
		buffer= packet.getData();
		
		//ControllerInformPacket Protocol = [FLAG] | [SOURCE_ADDRESS] | [CONNECTION_COUNT (N)] | [CONNECTION_ADDRESS_1] | ... | [CONNECTION_N]
		
		//Extract flag 
		System.arraycopy(buffer, 0, this.flag, 0, FLAG_LENGTH);
		//Extract source address
		System.arraycopy(buffer, FLAG_LENGTH, this.src, 0, SRC_ADDRESS_LENGTH);
		//Extract connection count
		System.arraycopy(buffer, SRC_ADDRESS_LENGTH+FLAG_LENGTH, this.connectionCount, 0, CONNECTION_COUNT_LENGTH);
		this.connectionAddresses = new int[ByteBuffer.wrap(this.connectionCount).getInt()];
		
		//Extract connections
		int connectionAddress;
		byte[] tmp = new byte[CONNECTION_ADDRESS_LENGTH];
		int index = SRC_ADDRESS_LENGTH+CONNECTION_COUNT_LENGTH+FLAG_LENGTH;
		
		for(int i=0; i<ByteBuffer.wrap(this.connectionCount).getInt();i++) {
			 System.arraycopy(buffer, index, tmp, 0, CONNECTION_ADDRESS_LENGTH);
			 index += CONNECTION_ADDRESS_LENGTH;
			 connectionAddress = ByteBuffer.wrap(tmp).getInt();
			 this.connectionAddresses[i] = connectionAddress; 
		}
	}
	
	public ControllerInformPacket(int src, int[] connections) {
	
		this.src = new byte[SRC_ADDRESS_LENGTH];
		this.flag = new byte[FLAG_LENGTH];
		//Flag = 2 for ControllerInformPacket
		this.flag = ByteBuffer.allocate(PacketContent.FLAG_LENGTH).putInt(2).array();
		
		this.connectionCount = new byte[CONNECTION_COUNT_LENGTH];
		this.connectionAddresses = new int[connections.length];
		
		//ControllerInformPacket Protocol = [FLAG] | [SOURCE_ADDRESS] | [CONNECTION_COUNT (N)] | [CONNECTION_ADDRESS_1] | ... | [CONNECTION_N]
		this.flag = ByteBuffer.allocate(PacketContent.FLAG_LENGTH).putInt(2).array();
		this.src = ByteBuffer.allocate(PacketContent.SRC_ADDRESS_LENGTH).putInt(src).array();
		this.connectionCount = ByteBuffer.allocate(PacketContent.CONNECTION_COUNT_LENGTH).putInt(connections.length).array();
		this.connectionAddresses = connections;
	}
	
	
	public int[] getConnectionAddresses(){
		return this.connectionAddresses;
	}
	
	public int getSource(){
		return ByteBuffer.wrap(this.src).getInt();
	}
	
	public int getFlag() {
		return ByteBuffer.wrap(this.flag).getInt();
	}
	
	public int getConnectionCount(){
		return ByteBuffer.wrap(this.connectionCount).getInt();
	}
	
	public DatagramPacket toDatagramPacket() {
		DatagramPacket packet= null;
		byte[] buffer= null;
		int count = ByteBuffer.wrap(this.connectionCount).getInt();
		try {
			buffer= new byte[FLAG_LENGTH + SRC_ADDRESS_LENGTH + CONNECTION_COUNT_LENGTH + (count*CONNECTION_ADDRESS_LENGTH)];
			
			System.arraycopy(this.flag, 0, buffer, 0, FLAG_LENGTH);
			System.arraycopy(this.src, 0, buffer, FLAG_LENGTH, SRC_ADDRESS_LENGTH);
			System.arraycopy(connectionCount, 0, buffer, SRC_ADDRESS_LENGTH+FLAG_LENGTH, CONNECTION_COUNT_LENGTH);
			
			//Insert connections into buffer
			int connectionAddress;
			byte[] tmp = new byte[CONNECTION_ADDRESS_LENGTH];
			int index = FLAG_LENGTH+SRC_ADDRESS_LENGTH+CONNECTION_COUNT_LENGTH;
			
			System.out.println("Connections= " + ByteBuffer.wrap(this.connectionCount).getInt());
			for(int i=0; i<count;i++) {
				 connectionAddress = this.connectionAddresses[i];
				 tmp = ByteBuffer.allocate(PacketContent.CONNECTION_ADDRESS_LENGTH).putInt(connectionAddress).array();
				 System.arraycopy(tmp, 0, buffer, index, CONNECTION_ADDRESS_LENGTH);
				 index += CONNECTION_ADDRESS_LENGTH; 
			}
		
			packet= new DatagramPacket(buffer, buffer.length);
		}
		catch(Exception e) {e.printStackTrace();}
		return packet;
	}
}
package com.lux.you.SerialPort;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.TooManyListenersException;

import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;

public class CommUtil implements SerialPortEventListener {

	InputStream inputStream; // �Ӵ�������������
	OutputStream outputStream;// �򴮿��������
	SerialPort serialPort; // ���ڵ�����
	CommPortIdentifier portId;

	@SuppressWarnings("rawtypes")
	public CommUtil(Enumeration portList, String name) {
		while (portList.hasMoreElements()) {
			CommPortIdentifier temp = (CommPortIdentifier) portList.nextElement();
			if (temp.getPortType() == CommPortIdentifier.PORT_SERIAL) {// �ж�����˿������Ǵ���
				if (temp.getName().equals(name)) { // �ж�����˿��Ѿ�����������
					portId = temp;
				}
			}
		}
		try {
			serialPort = (SerialPort) portId.open("My"+name, 2000);
		} catch (PortInUseException e) {

		}
		try {
			inputStream = serialPort.getInputStream();
			outputStream = serialPort.getOutputStream();
		} catch (IOException e) {
		}
		try {
			serialPort.addEventListener(this); // ����ǰ�������һ��������
		} catch (TooManyListenersException e) {
		}
		serialPort.notifyOnDataAvailable(true); // ��������ʱ֪ͨ
		try {
			serialPort.setSerialPortParams(2400, SerialPort.DATABITS_8, // ���ô��ڶ�д����
					SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
		} catch (UnsupportedCommOperationException e) {
		}
	}

	public void serialEvent(SerialPortEvent event) {
		switch (event.getEventType()) {
		case SerialPortEvent.BI:
		case SerialPortEvent.OE:
		case SerialPortEvent.FE:
		case SerialPortEvent.PE:
		case SerialPortEvent.CD:
		case SerialPortEvent.CTS:
		case SerialPortEvent.DSR:
		case SerialPortEvent.RI:
		case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
			break;
		
		case SerialPortEvent.DATA_AVAILABLE:// ���п�������ʱ��ȡ����,���Ҹ����ڷ�������
			byte[] readBuffer = new byte[20];

			try {
				while (inputStream.available() > 0) {
					//System.out.println(inputStream.available());
					inputStream.read(readBuffer);
					//System.out.println(numBytes);
				}
				System.out.println(new String(readBuffer).trim());
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		}
	}
	public void send(String content){
		try {
			outputStream.write(content.getBytes());
			outputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void ClosePort() {
	    if (serialPort != null) {
	      serialPort.close();
	    }
	  }

	
}



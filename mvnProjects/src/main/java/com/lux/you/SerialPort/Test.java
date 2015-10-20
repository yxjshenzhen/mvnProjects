package com.lux.you.SerialPort;

import gnu.io.CommPortIdentifier;

import java.util.Enumeration;


public class Test {

	public static void main(String[] args) throws InterruptedException {
		@SuppressWarnings("rawtypes")
		Enumeration portList = CommPortIdentifier.getPortIdentifiers(); //�õ���ǰ�����ϵĶ˿�
		
		CommUtil comm3 = new CommUtil(portList,"COM1");
		
		while(!portList.hasMoreElements())
		{
			Thread.sleep(1000);
			comm3.send((int)(Math.random()*100)+"\n");
		}
		comm3.ClosePort();
	}

}


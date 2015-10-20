package com.lux.you.test;

import gnu.io.CommPortIdentifier;

import java.util.Enumeration;

import com.lux.you.SerialPort.CommUtil;


public class Test {

	public static void main(String[] args) throws InterruptedException {
		@SuppressWarnings("rawtypes")
		Enumeration portList = CommPortIdentifier.getPortIdentifiers(); //得到当前连接上的端口
		
		CommUtil comm3 = new CommUtil(portList,"COM1");
		
		while(!portList.hasMoreElements())
		{
			Thread.sleep(1000);
			comm3.send((int)(Math.random()*100)+"\n");
		}
		comm3.ClosePort();
	}

}


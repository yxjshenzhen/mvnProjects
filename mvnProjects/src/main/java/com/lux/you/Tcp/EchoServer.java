package com.lux.you.Tcp;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;


public class EchoServer {
    public static void main(String args[]) throws Exception
    { 
        Selector selector = Selector.open();
        
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        InetSocketAddress address = new InetSocketAddress(9000);
        serverSocketChannel.socket().bind(address);
        
        
        SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        
        
        while (true){
            int selectedNum = selector.select();
            System.out.println("Selected Number is :"+selectedNum);
            Iterator iter = selector.selectedKeys().iterator();
            
            while(iter.hasNext()){
                SelectionKey selectedKey = (SelectionKey)iter.next();
                
                if ((selectedKey.readyOps() & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT){
                    ServerSocketChannel serverChannel = (ServerSocketChannel)selectedKey.channel();
                    SocketChannel socketChannel = serverChannel.accept();
                    socketChannel.configureBlocking(false);
                    
                    SelectionKey readKey = socketChannel.register(selector, SelectionKey.OP_READ);                    
                    iter.remove();
                    
                    System.out.println("buffer: "+new String(readKey.toString()));                
                }
                else if ( (selectedKey.readyOps()&SelectionKey.OP_READ) == SelectionKey.OP_READ ){
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    SocketChannel socketChannel = (SocketChannel)selectedKey.channel();
                    while (true){
                        buffer.clear();
                        int i=socketChannel.read(buffer);
                    
                        if (i == -1) break;
                    
                        buffer.flip();
                        socketChannel.write(buffer);
                    }
                }
                
            }
            
        }
       

    }
}

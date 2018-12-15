package com.kun.learning.java.io;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by jrjiakun on 2018/11/26
 */
public class NioDemo {

    private static void  start() {
        try (   //Selector
                Selector selector = Selector.open();
        ) {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress("localhost", 8080));

            serverSocketChannel.configureBlocking(false);


            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            while (true) {
                selector.select();
                Set<SelectionKey> selectionKeySet = selector.selectedKeys();
                Iterator<SelectionKey> iter = selectionKeySet.iterator();
                while (iter.hasNext()) {
                    SelectionKey selectionKey = iter.next();
                    sayHelloWorld((ServerSocketChannel) selectionKey.channel());
                    iter.remove();
                }
            }
        } catch (Exception e) {

        }

    }

    private static void sayHelloWorld(ServerSocketChannel server) throws IOException {
        try (SocketChannel client = server.accept();) {
            client.write(Charset.defaultCharset().encode("Hello world!"));
        }
    }

    public static void main(String[]args){
        start();
    }

}

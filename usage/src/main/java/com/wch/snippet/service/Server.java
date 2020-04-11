package com.wch.snippet.service;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        int port = 9090;// 端口
        try {
            // 1、监听端口
            ServerSocket serverSocket = new ServerSocket(port);
            // 2、等待读取数据
            Socket socket;
            while (null != (socket = serverSocket.accept())) {


                BufferedWriter pw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                pw.write("xxx");
                pw.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
    }
}

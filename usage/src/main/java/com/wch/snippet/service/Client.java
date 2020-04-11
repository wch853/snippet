package com.wch.snippet.service;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        String ipAddress = "127.0.0.1";
        int port = 9090;
        try {
            Socket socket = new Socket(ipAddress, port);
            // 3、读取数据准备
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // 4、读取数据
            String str = br.readLine();
            System.out.println(str);
            socket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

package com.wolfff.wolfmessenger.Tools.Protocol;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by wolff on 02.10.2016.
 */
//in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
public class ConnectionMessenger {

    public Socket getConnectionSocket(String host,int port){
        Socket socket=null;
        InetAddress ipAddress = null;
        try {
            ipAddress = InetAddress.getByName(host);
        } catch (UnknownHostException e) {
            Log.e("getConnectionSocket",""+e.getLocalizedMessage());
        }
        try {
            socket = new Socket(ipAddress,port);
        } catch (IOException e) {
            Log.e("getConnectionSocket_2",""+e.getLocalizedMessage());
        }
        return socket;
    }

    public BufferedReader getSocketInputStream(Socket socket){
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        try {
            inputStreamReader = new InputStreamReader(socket.getInputStream());
            bufferedReader = new BufferedReader(inputStreamReader);
        } catch (IOException e) {
            Log.e("getSocketInput",""+e.getLocalizedMessage());
            bufferedReader = null;
        }
        return bufferedReader;
    }
    public PrintWriter getSocketOutputStream(Socket socket){
        PrintWriter printWriter = null;
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
            BufferedWriter bufferedWriter  =new BufferedWriter(outputStreamWriter);
            printWriter = new PrintWriter(bufferedWriter);
        } catch (IOException e) {

        }
        return printWriter;
        //PrintWriter out=new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
        //out.println("qqq");

        /*OutputStream sout = null;
        DataOutputStream dos = null;
        try {
            sout = socket.getOutputStream();
            dos = new DataOutputStream(sout);
        } catch (IOException e) {
            Log.e("getSocketOutputStream",""+e.getLocalizedMessage());
            dos=null;
        }
        return dos;

        */

    }
    //==================================================================================================



}

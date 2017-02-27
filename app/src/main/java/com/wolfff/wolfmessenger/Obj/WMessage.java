package com.wolfff.wolfmessenger.Obj;

/**
 * Created by wolff on 12.10.2016.
 */

public class WMessage {
    private long id;
    private long phoneNumber;
    private long contactPhoneNumber;
    private String message_body;
    private String idServer;
    private long time;
    private String typeMessage;
    private int inOut;
    private int statusIn;
    private int statusOut;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public long getContactPhoneNumber() {
        return contactPhoneNumber;
    }

    public void setContactPhoneNumber(long contactPhoneNumber) {
        this.contactPhoneNumber = contactPhoneNumber;
    }

    public String getMessage_body() {
        return message_body;
    }

    public void setMessage_body(String message_body) {
        this.message_body = message_body;
    }

    public String getIdServer() {
        return idServer;
    }

    public void setIdServer(String idServer) {
        this.idServer = idServer;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getTypeMessage() {
        return typeMessage;
    }

    public void setTypeMessage(String typeMessage) {
        this.typeMessage = typeMessage;
    }

    public int isInOut() {
        return inOut;
    }

    public void setInOut(int inOut) {
        this.inOut = inOut;
    }

    public int getStatusIn() {
        return statusIn;
    }

    public void setStatusIn(int statusIn) {
        this.statusIn = statusIn;
    }

    public int getStatusOut() {
        return statusOut;
    }

    public void setStatusOut(int statusOut) {
        this.statusOut = statusOut;
    }


    public WMessage(long id, long phoneNumber, long contactPhoneNumber, String message_body, String idServer, long time,
                    String typeMessage, int inOut, int statusIn, int statusOut){
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.contactPhoneNumber = contactPhoneNumber;
        this.message_body = message_body;
        this.idServer = idServer;
        this.time = time;
        this.typeMessage = typeMessage;
        this.inOut = inOut;
        this.statusIn =statusIn;
        this.statusOut = statusOut;
    }

    // _id
    // _phoneNumber - номер мой
    // _contactPhoneNumber - номер контакта
    // _message_body - текст или ссылка на локальный файл
    // _idServer - серверный ИД, для синхронизации, удаления и т.п.
    // _time - дата-время сообщения
    // _typeMessage - text,file,url
    // _inOut - in - 0,out - 1
    // _satusOut 0 - неотправленное, 1 - отправлено, 2 - доставлено, 3 - прочитано,
    // _statusIn 0 - получено, 1 - прочитано

}

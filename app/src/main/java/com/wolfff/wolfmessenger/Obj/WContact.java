package com.wolfff.wolfmessenger.Obj;

/**
 * Created by wolff on 15.09.2016.
 */
public class WContact {
    private long phone_number;
    private String nickName;
    private boolean hasMessenger;
    private String logoFilePath;
    private long logoFileTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private long id;

    public String getLogoFilePath() {
        return logoFilePath;
    }

    public void setLogoFilePath(String logoFilePath) {
        this.logoFilePath = logoFilePath;
    }

    public long getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(long phone_number) {
        this.phone_number = phone_number;
    }

    public String getNickName() {
        return nickName;
    }

    public long getLogoFileTime() {
        return logoFileTime;
    }

    public void setLogoFileTime(long logoFileTime) {
        this.logoFileTime = logoFileTime;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public WContact(long phone_number, String nickName,boolean hasMessenger,String logoFilePath,long id,long logoFileTime){
        this.phone_number=phone_number;
        this.nickName=nickName;
        this.hasMessenger=hasMessenger;
        this.logoFilePath=logoFilePath;
        this.id = id;
        this.logoFileTime = logoFileTime;
   }

    public boolean isHasMessenger() {
        return hasMessenger;
    }

    public void setHasMessenger(boolean hasMessenger) {
        this.hasMessenger = hasMessenger;
    }
}

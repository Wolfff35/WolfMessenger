package com.wolfff.wolfmessenger.Tools.LocalData;

import android.os.Environment;

import java.io.File;

/**
 * Created by wolff on 04.10.2016.
 */

public class LocalFileManager {
    public static String CATALOG_MESSENGER = "WMess";
    public static String CATALOG_AVATAR = "AVATAR";
    public static String CATALOG_USERPHOTO = "USERS_PHOTO";
    public static String CATALOG_USERFILES = "USERS_FILES";


    public File getCatalog_Program(){
        File catalogName;
        boolean hasSDCard=false;
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            hasSDCard=true;
        }
        if (hasSDCard) {
            catalogName = Environment.getExternalStorageDirectory();
        }else {
            catalogName = Environment.getDataDirectory();
        }
        catalogName = new File(catalogName.getAbsolutePath()+"/"+CATALOG_MESSENGER);
        if (!catalogName.exists()) {
            catalogName.mkdir();
        }
        return catalogName;
    }
    public File getCatalog_Avatar(){
        File catalogName;
        catalogName = getCatalog_Program();
        catalogName = new File(catalogName.getAbsolutePath()+"/"+CATALOG_AVATAR);
        if (!catalogName.exists()) {
            catalogName.mkdir();
        }
        return catalogName;
    }

}

/*
if (!Environment.getExternalStorageState().equals(
        Environment.MEDIA_MOUNTED)) {
        Log.d(LOG_TAG, "SD-карта не доступна: " + Environment.getExternalStorageState());
        return;
        }
        // получаем путь к SD
        File sdPath = Environment.getExternalStorageDirectory();
        // добавляем свой каталог к пути
        sdPath = new File(sdPath.getAbsolutePath() + "/" + DIR_SD);
        // создаем каталог
        sdPath.mkdirs();
   */
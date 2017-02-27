package com.wolfff.wolfmessenger;

/**
 * Created by wolff on 13.09.2016.

    //MESSAGE_TYPES
    // 01 - REGISTER USER
            // client send
                // 01;380673231646;wolf;password;

            // return server
                01;380673231646;nom;

                 nom:
                 0 - registered
                 1 - not registered, number exist

    // 02 - DELETE USER
            // client send
                //02;380673231646;password;
            // return server
                 02;380673231646;nom;

                 nom:
                 0 - deleted
                 1 - not deleted (password incorrect)


    // 03 - LOG IN
            //client send
                // 03;380673231646;password;

            // return server
                 03;380673231646;nom;

                nom:
                0 - authorized
                1 - not authentificated


    // 04 - LOG OFF
    // 05 - UPDATE USER LIST
    // 06 - SEND TEXT
    // 07 - SEND PHOTO
    // 08 - SEND FILE
    // 09 - SEND STICKER
    // 10 - SEND GPS
    // 11 - SEND READ CONFIRMATION
    // 12 - SEND IsTyping

    //LOCAL BD STRUCTURE


    //SERVER BD STRUCTURE

    // _USERS
    // - ID
    // - ISONLINE
    // - PUBLIC_KEY

    //_MESSAGES
    // - ID
    // - SENDER
    // - RECEIVER
    // - MESSAGE_TIME
    // - MESSAGE_TEXT
    // - MESSATE_BLOB - file or image

    // _GPS_COORDS
    // - ID
    // - USER
    // - LATITUDE
    // - LONGITUDE
    // - GPS_TIME

}
*/
/*
SELECT _id,receiver,text_mess,time_mess,in_out,isRead
FROM mess
WHERE sender="111"
GROUP BY receiver
HAVING max(time_mess)
 */
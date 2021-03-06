package project.capstone.com.matchingkak;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import com.google.firebase.messaging.RemoteMessage;

import project.capstone.com.matchingkak.Main.Main2Activity;

/**
 * Created by Lee on 2017-05-27.
 */

public class MyFirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {

    final static String MSG="MSG";
    final static String NEWS="NEWS";
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        String type="NEWS";
        //Log.d("onMessageReceived",remoteMessage.getData().toString());
        String message=remoteMessage.getData().get("message");
        type=remoteMessage.getData().get("type");
        //Log.d("onMessageReceived",type);
        sendNotfication(type,message);


    }

    private void sendNotfication(String type,String messageBody){  //푸시 알림 설정
        String url="http://matchingkak.com";
        Intent intent=new Intent(this,Main2Activity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Intent intent2;

        TaskStackBuilder  stackBuilder=TaskStackBuilder.create(this);
        switch(type){

            case MSG:
                url="http://matchingkak.com/list_message_receive.php";
                intent.putExtra(config.NOTI_SELECTED_TAB,1);

                 stackBuilder=TaskStackBuilder.create(this);
                stackBuilder.addParentStack(Main2Activity.class);
                stackBuilder.addNextIntent(intent);

                break;
            case NEWS:
                url="http://matchingkak.com/news.php";
                intent.putExtra(config.NOTI_SELECTED_TAB,3);
                 stackBuilder=TaskStackBuilder.create(this);
                stackBuilder.addParentStack(Main2Activity.class);
                stackBuilder.addNextIntent(intent);

                break;
        }




        PendingIntent pendingIntent=stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);



        NotificationCompat.Builder mBuilder=new NotificationCompat.Builder(this)
                                    .setSmallIcon(R.drawable.noti_icon)
                                    .setColor(Color.YELLOW)
                                    .setContentTitle("매칭각")
                                    .setContentText(messageBody)
                                    .setAutoCancel(true)
                                    .setDefaults(Notification.DEFAULT_SOUND)
                                    .setContentIntent(pendingIntent);

        NotificationManager notificationManager=
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,mBuilder.build());
    }
}

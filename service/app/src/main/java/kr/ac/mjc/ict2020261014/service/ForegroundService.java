package kr.ac.mjc.ict2020261014.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.Timer;
import java.util.TimerTask;

public class ForegroundService extends Service {

    NotificationManager notificationManager;
    int progress = 0;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        notificationManager=getSystemService(NotificationManager.class);

        NotificationChannel channel = new NotificationChannel("Download","Download",NotificationManager.IMPORTANCE_DEFAULT);
        notificationManager.createNotificationChannel(channel);

        Notification notification=getDownloadNotificaiton(progress);
        notificationManager.notify(1000,notification);
        startForeground(1000,notification);

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask(){
            @Override
            public void run(){
                progress += 10;
                Notification notification=getDownloadNotificaiton(progress);
                notificationManager.notify(1000,notification);

                if(progress>= 100){
                    timer.cancel();
                    Intent startIntent = new Intent(ForegroundService.this,MainActivity.class);
                    startIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    PendingIntent pendingIntent = PendingIntent.getActivity(ForegroundService.this,0,startIntent,PendingIntent.FLAG_IMMUTABLE);

                    notification=new Notification.Builder(ForegroundService.this)
                            .setContentTitle("Download Complete")
                            .setContentText("Download Complete")
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setChannelId("Download")
                            .setContentIntent(pendingIntent)
                            .build();

                    notificationManager.notify(1000,notification);
                }
            }
        };
        timer.schedule(timerTask,1000,1000);

        return super.onStartCommand(intent, flags, startId);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Notification getDownloadNotificaiton(int progress){
        Notification notification = new Notification.Builder(this)
                .setContentTitle("Download")
                .setContentText("Downloading...")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setProgress(100,progress,false)
                .setChannelId("Download")
                .build();

        return notification;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

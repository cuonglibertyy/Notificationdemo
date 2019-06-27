package com.example.notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btnTao;
    private Button btnDong;
    int notificationID = 113 ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhxa();
        eventClick();
    }

    private void eventClick() {
        btnTao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // bước 1 tạo notification

                taoNotification();

            }
        });

        btnDong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notificationManager.cancel(notificationID);
                notificationManager.cancelAll();
            }
        });
    }

    private void taoNotification() {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.icon)
                .setContentTitle(" Có thông báo")
                .setContentText("Bạn có tin nhắn từ Thư béo bụng bự");

        // bước 2 thông qua PendingIntent để trỏ tới activity được click thông báo

        Intent resultItent = new Intent(this,NotificationActivitiy.class);

        PendingIntent resultpendingIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        resultItent,
                        PendingIntent.FLAG_UPDATE_CURRENT);
        // bước 3 tạo âm thanh cho thông báo
        builder.setContentIntent(resultpendingIntent);
        Uri uri = Uri.parse("android.resource://"+getPackageName() + "/" +R.raw.tunnn);
        builder.setSound(uri);

        // bước 4 kick hoạt notification thông qua notificationManager
        notificationID = 113;
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        notificationManager.notify(notificationID,builder.build());
    }

    private void anhxa() {
        btnTao = (Button) findViewById(R.id.btn_tao);
        btnDong = (Button) findViewById(R.id.btn_dong);

    }
}

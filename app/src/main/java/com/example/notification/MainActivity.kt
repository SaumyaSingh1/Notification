package com.example.notification

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.NotificationCompat
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    @SuppressLint("PrivateResource")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nm:NotificationManager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O) {
            nm.createNotificationChannel(
                NotificationChannel(
                    "first",
                    "default",
                    NotificationManager.IMPORTANCE_DEFAULT
                )
            )
//            nm.createNotificationChannel(
//                NotificationChannel(
//                    "second",
//                    "default",
//                    NotificationManager.IMPORTANCE_DEFAULT
//                )
//            )
        }
        simple.setOnClickListener {

            val simpleNotification: Notification? =NotificationCompat.Builder(baseContext,"first")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Simple Notification")
                .setContentText("Captain Marvel")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build()
            nm.notify(System.currentTimeMillis().toInt(),simpleNotification)
        }

            intents.setOnClickListener {

                 val i=Intent()
                 i.action=Intent.ACTION_VIEW
                 i.data= Uri.parse("https://google.com")
                val pi:PendingIntent= PendingIntent.getActivity(baseContext,1234, i ,PendingIntent.FLAG_UPDATE_CURRENT)

                val clickablenotification: Notification? =NotificationCompat.Builder(baseContext,"first")
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setContentTitle("Simple Notification")
                    .setContentText("Captain Marvel")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .build()
//                nm.notify(2,clickablenotification)
                    nm.notify(System.currentTimeMillis().toInt(),clickablenotification)
            }

            expand.setOnClickListener {
                val icon:Bitmap?=BitmapFactory.decodeResource(baseContext.resources,R.drawable.ic_launcher_foreground)
                Log.e("TAG","${icon.toString()}")
                val expandnotification: Notification? =NotificationCompat.Builder(baseContext,"first")
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setContentTitle("Simple Notification")
                    .setContentText("Captain Marvel")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setLargeIcon(icon)
                    .setStyle(NotificationCompat.BigPictureStyle().bigPicture(icon))

                    .build()
//                nm.notify(2,expandnotification)
                nm.notify(System.currentTimeMillis().toInt(),expandnotification)
            }

             longtext.setOnClickListener {
                 val longnotification: Notification? =NotificationCompat.Builder(baseContext,"first")
                     .setSmallIcon(R.drawable.notification_icon_background)
                     .setContentTitle("Longtext Notification")
                     .setContentText("Hey Developer!")

                     .setPriority(NotificationManager.IMPORTANCE_DEFAULT)
                     .build()
                 nm.notify(System.currentTimeMillis().toInt(),longnotification)

             }

            action.setOnClickListener {
                val i=Intent()
                i.action=Intent.ACTION_VIEW
                i.data= Uri.parse("https://www.google.com")
                val pi:PendingIntent= PendingIntent.getActivity(baseContext,1234,i,PendingIntent.FLAG_UPDATE_CURRENT)
                val actionnotification : Notification?= if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    NotificationCompat.Builder(baseContext,"first")
                        .setSmallIcon(R.drawable.notification_icon_background)
                        .setContentTitle("intent  notification")
                        .setContentTitle("Hello People")
                        .addAction(R.drawable.ic_launcher_background,"click here",pi)

                        .setPriority(NotificationManager.IMPORTANCE_DEFAULT)
                        .build()
                } else {
                    TODO("VERSION.SDK_INT < N")
                }
                nm.notify(System.currentTimeMillis().toInt(),actionnotification)


            }


        } }


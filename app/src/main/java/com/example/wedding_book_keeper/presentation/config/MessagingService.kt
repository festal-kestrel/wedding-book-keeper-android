package com.example.wedding_book_keeper.presentation.config

//import android.R
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.Gravity
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.example.wedding_book_keeper.R
import com.example.wedding_book_keeper.presentation.view.MainActivity
import com.example.wedding_book_keeper.presentation.view.guest.LoginActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.firebase.messaging.ktx.remoteMessage


class MessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {

        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: ${remoteMessage.from}")
        Log.d(TAG, "From: ${remoteMessage}")
        Log.d(TAG, "From: ${remoteMessage.data}")
        Log.d(TAG, "From: ${remoteMessage.data.get("title")}")
        Log.d(TAG, "From: ${remoteMessage.data.get("groom")}")
        Log.d(TAG, "From: ${remoteMessage.data.get("bride")}")

        sendNotification(remoteMessage)
//
//        remoteMessage.data.get("title")?.let { sendNotification(it) }
//
//        // Check if message contains a notification payload.
//        remoteMessage.notification?.let {
//            Log.d(TAG, "Message Notification Body: ${it.body}")
//            it.body?.let { body -> sendNotification(body) }
//        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }


//    private fun sendRegistrationToServer(token: String?) {
//        Log.d(TAG, "sendRegistrationTokenToServer($token)")
//    }

    private fun sendNotification(remoteMessage: RemoteMessage) {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_MUTABLE,
        )

        val channelId = getString(R.string.default_notification_channel_id)
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val groom = remoteMessage.data.get("groom")
        val bride = remoteMessage.data.get("bride")
        val isGroomSide = remoteMessage.data.get("isGroomSide")
        val memberOrdering = if(isGroomSide.toBoolean())  "$groom & $bride" else "$bride & $groom"
        val messageBody = "${memberOrdering}의 결혼식이 곧 시작해요!"
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(com.example.wedding_book_keeper.R.mipmap.wbk_logo)
            .setContentTitle(remoteMessage.data.get("title"))
            .setContentText(messageBody)
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
            .setContentIntent(pendingIntent)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "결혼 시작 전 알림 받기",
                NotificationManager.IMPORTANCE_DEFAULT,
            )
            notificationManager.createNotificationChannel(channel)
        }

        val notificationId = 0
        notificationManager.notify(notificationId, notificationBuilder.build())
        Handler(Looper.getMainLooper()).post(Runnable {
            val toast = Toast.makeText(applicationContext, messageBody, Toast.LENGTH_LONG)
            toast.setGravity(Gravity.TOP or Gravity.CENTER_HORIZONTAL, 0, 0)
            toast.show()
        })
    }

    companion object {
        private const val TAG = "notification"
    }

}

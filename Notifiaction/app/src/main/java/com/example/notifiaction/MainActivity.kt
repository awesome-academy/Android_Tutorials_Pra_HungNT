package com.example.notifiaction



import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat

class MainActivity : AppCompatActivity() {

    private lateinit var buttonNotify: Button
    private lateinit var buttonCancel: Button
    private lateinit var buttonUpdate: Button
    /**
     * Constans
     */
    private val NOTIFICATION_ID = 0
    private val PRIMARY_CHANNEL_ID = "primary_notification_channel"
    private lateinit var mNotifyManager: NotificationManager
    private val ACTION_UPDATE_NOTIFICATION = "com.example.android.notifyme.ACTION_UPDATE_NOTIFICATION"
    private val mReceiver = NotificationReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /**
         * registerReceiver - ACTION_UPDATE_NOTIFICATION
         */
        registerReceiver(mReceiver, IntentFilter(ACTION_UPDATE_NOTIFICATION))

        /**
         * SendNotification
         */
        buttonNotify = findViewById(R.id.notify)
        buttonNotify.setOnClickListener {
            sendNotification()
        }
        /**
         * UpdateNotification
         */
        buttonUpdate = findViewById(R.id.update)
        buttonUpdate.setOnClickListener {
            updateNotification()
        }
        /**
         * CancelNotification
         */
        buttonCancel = findViewById(R.id.cancel)
        buttonCancel.setOnClickListener {
            cancelNotification()
        }
        /**
         * Create Notifiaction Channel
         */
        createNotificationChannel()
        /**
         * set trạng thái ban đầu cho các button
         */
        setNotificationButtonState(true, false, false)
    }


    private fun sendNotification() {
        val updateIntent = Intent(ACTION_UPDATE_NOTIFICATION)

        /**
         * tạo PendingIntent cho hành động Update Notifiaction
         */
        val updatePendingIntent = PendingIntent.getBroadcast(
            this,
            NOTIFICATION_ID,
            updateIntent,
            PendingIntent.FLAG_ONE_SHOT
        )

        /**
         * build ,thêm updatePendingIntent và show thông báo
         */
        val notifyBuilder = getNotificationBuilder()
        notifyBuilder.addAction(R.drawable.ic_update, "Update Notification", updatePendingIntent)
        mNotifyManager.notify(NOTIFICATION_ID, notifyBuilder.build())
        /**
         * set lại trạng thái cho các button
         */
        setNotificationButtonState(false, true, true)
    }

    private fun updateNotification() {
        /**
         * Lấy ảnh bitmap(mascot_1.png)
         */
        val androidImage = BitmapFactory.decodeResource(resources, R.drawable.mascot_1)

        /**
         * xậy dựng thông báo và hiển thị ảnh trên thông báo
         */
        val notifyBuilder = getNotificationBuilder()
        notifyBuilder.setStyle(
            NotificationCompat.BigPictureStyle()
                .bigPicture(androidImage)
                .setBigContentTitle("Notification Updated!")
        )
        mNotifyManager.notify(NOTIFICATION_ID, notifyBuilder.build())

        /**
         * set lại trạng thái cho các button
         */
        setNotificationButtonState(false, false, true)
    }

    private fun cancelNotification() {
        /**
         * hủy thông báo
         */
        mNotifyManager.cancel(NOTIFICATION_ID)
        /**
         * set lại trạng thái cho các button
         */
        setNotificationButtonState(true, false, false)
    }

    /**
     * Create Notification Channel with > Android(O) - API level 26
     */
    private fun createNotificationChannel() {
        mNotifyManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            /**
             *  Create a NotificationChannel
              */
            val notificationChannel = NotificationChannel(
                PRIMARY_CHANNEL_ID,
                "Mascot Notification",
                NotificationManager.IMPORTANCE_HIGH
            )
            /**
             * Đặt các thuộc tính cho kênh thông báo
             */
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.enableVibration(true)
            notificationChannel.description = "Notification from Mascot"

            mNotifyManager.createNotificationChannel(notificationChannel)
        }
    }

    /**
     * Xây dựng thông báo
     */
    private fun getNotificationBuilder(): NotificationCompat.Builder {
        /**
         * tạo Intent để khi người dùng nhấn vào thông báo
         * thì chuyển tới giao diện MainActivity
         */
        val notificationIntent = Intent(this, MainActivity::class.java)
        val notificationPendingIntent = PendingIntent.getActivity(
            this,
            NOTIFICATION_ID,
            notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        return NotificationCompat.Builder(this, PRIMARY_CHANNEL_ID)
            .setContentTitle("You've been notified!")
            .setContentText("This is your notification text.")
            .setSmallIcon(R.drawable.ic_android)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setContentIntent(notificationPendingIntent)
            .setAutoCancel(true)
    }

    /**
     * Thiết lập trạng thái cho các button
     * dựa trên các tham số truyền vào
     */
    private fun setNotificationButtonState(
        isNotifyEnabled: Boolean,
        isUpdateEnabled: Boolean,
        isCancelEnabled: Boolean
    ) {
        buttonNotify.isEnabled = isNotifyEnabled
        buttonUpdate.isEnabled = isUpdateEnabled
        buttonCancel.isEnabled = isCancelEnabled
    }

    override fun onDestroy() {
        unregisterReceiver(mReceiver)
        super.onDestroy()
    }

    /**
     * Lắng nghe hành động  Update notification
     * và gọi hàm UpdateNotification()
     */
    inner class NotificationReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            updateNotification()
        }
    }
}
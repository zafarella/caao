/**
 *
 * Computer science department 
 * Project: Context-Aware Agriculture Organizer 
 * Author: Zafar Khaydarov
 * E-mail: zkhayda@uef.fi 
 * Web: cs.joensuu.fi/~zkhayda 
 * Date: Mar 31, 2011
 */
package caao.com.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;
import caao.com.MainActivity;
import caao.com.R;

/**
 * We will use foreground service which will show the icon of the app in the
 * status bar. This class is called in main activity of the app. the service
 * will handle the requests from the server in background. Please don't overload
 * the code here as the system will not kill the activity as it's the foreground
 * service.
 *
 * @author zafar.khaydarov
 * @version $Revision: 1.9 $
 *          <p/>
 *          TODO: implementation of the methods of syncing data with the server
 * @see android.app.Service
 */
public class Service extends android.app.Service {
    /**
     * Field mNM.
     */
    static NotificationManager mNM;
    /**
     * indicates how to behave if the service is killed Field mStartMode.
     */
    int mStartMode;
    /**
     * Field mBinder.
     */
    IBinder mBinder;
    /**
     * indicates whether onRebind should be used Field interface for clients
     * that bind.
     */
    boolean mAllowRebind;
    /**
     * The function that runs in our worker thread Here we will do what we need
     * to do in background
     */
    Runnable mTask = new Runnable() {
        public void run() {
            // // Normally we would do some work here... for
            // // our sample, we will
            // // just sleep for 30 seconds.
            // long endTime = System.currentTimeMillis() + 15 * 1000;
            // while (System.currentTimeMillis() < endTime) {
            // synchronized (mBinder) {
            // try {
            // // mBinder.wait(endTime - System.currentTimeMillis());
            // }
            // catch (Exception e) {
            // // Toast.
            // }
            // }
            // }

            // Done with our work... stop the service!
            // Service.this.stopSelf();
        }
    };

    /**
     * Removes all notifications in status bar of the phone. Calling when the
     * user decides to exit the application.
     */
    public static void removeNotifications() {
        if (null != mNM)
            mNM.cancelAll();
    }

    /**
     * Method onCreate.
     */
    @Override
    public void onCreate() {
        Service.mNM = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        // show the icon in the status bar
        showNotification();

        // Start up the thread running the service. Note that we create a
        // separate thread because the service normally runs in the
        // process's
        // main thread, which we don't want to block.
        Thread thr = new Thread(null, this.mTask, "Service");
        thr.start();
        Toast.makeText(this, "Service started, separate thread created",
                Toast.LENGTH_SHORT).show();

    }

    /**
     * Method onStartCommand.
     *
     * @param intent  Intent
     * @param flags   int
     * @param startId int
     * @return int
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // The service is starting, due to a call to startService()
        return this.mStartMode;
    }

    /**
     * Method onBind.
     *
     * @param intent Intent
     * @return IBinder
     */
    @Override
    public IBinder onBind(Intent intent) {
        // A client is binding to the service with bindService()
        return this.mBinder;
    }

    /**
     * Method onUnbind.
     *
     * @param intent Intent
     * @return boolean
     */
    @Override
    public boolean onUnbind(Intent intent) {
        // All clients have unbound with unbindService()
        return this.mAllowRebind;
    }

    // -------------------------------------------------------------------------------------------

    /**
     * Method onRebind.
     *
     * @param intent Intent
     */
    @Override
    public void onRebind(Intent intent) {
        // A client is binding to the service with bindService(),
        // after onUnbind() has already been called
    }

    // -------------------------------------------------------------------------------------------

    /**
     * Method onDestroy.
     */
    @Override
    public void onDestroy() {
        // The service is no longer used and is being destroyed
        Toast.makeText(this, "Service destroyed", Toast.LENGTH_SHORT).show();
    }

    /**
     * Sends the notification to the status bar of the phone
     */
    private void showNotification() {
        // In this sample, we'll use the same text for the ticker and the
        // expanded notification
        CharSequence text = getText(R.string.service_label);

        // Set the icon, scrolling text and timestamp
        Notification notification = new Notification(
                R.drawable.icon_organic_cotton, text,
                System.currentTimeMillis());

        // The PendingIntent to launch our activity if the user selects this
        // notification
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, MainActivity.class), 0);

        // Set the info for the views that show in the notification panel.
        notification.setLatestEventInfo(this, getText(R.string.service_label),
                text, contentIntent);

        // Send the notification.
        // We use a layout id because it is a unique number. We use it later
        // to cancel.
        Service.mNM.notify(R.string.service_label, notification);
    }
}

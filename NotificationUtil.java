package com.lovecust.app.utils;


import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;

import com.lovecust.app.lovecust.AppContext;
import com.lovecust.app.R;

public class NotificationUtil {
	public static final int NOTIFICATION_DEFAULT_ID = 50920;


	public static void sendNotification ( String title, String text ) {
		sendNotification( title, text, R.mipmap.icon_launcher );
	}

	public static void sendNotification ( String title, String text, int icon ) {
		NotificationCompat.Builder mBuilder =
				new NotificationCompat.Builder( AppContext.getContext() )
						.setSmallIcon( icon )
						.setContentTitle( title )
						.setContentText( text );
		// Gets an instance of the NotificationManager service
		NotificationManager mNotifyMgr = (NotificationManager) AppContext.getContext().getSystemService( Context.NOTIFICATION_SERVICE );
		// Builds the notification and issues it.
		mNotifyMgr.notify( NOTIFICATION_DEFAULT_ID, mBuilder.build() );
	}

	public static void cancel () {
		cancel( NOTIFICATION_DEFAULT_ID );
	}

	public static void cancel ( int id ) {
		NotificationManager mNotifyMgr = (NotificationManager) AppContext.getContext().getSystemService( Context.NOTIFICATION_SERVICE );
		mNotifyMgr.cancel( id );
	}

	public static void cancelAll () {
		NotificationManager mNotifyMgr = (NotificationManager) AppContext.getContext().getSystemService( Context.NOTIFICATION_SERVICE );
		mNotifyMgr.cancelAll();
	}

}

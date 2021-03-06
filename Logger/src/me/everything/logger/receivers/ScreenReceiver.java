package me.everything.logger.receivers;

import me.everything.logger.Log;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;


/**
 * Logs screen changes of OM/OFF
 * 
 * @author sromku
 */
public class ScreenReceiver implements SystemReceiver {

	private static final String NAME = "Screen";
	private BroadcastReceiver screenReceiver;
	private boolean isRegistered = false;

	@Override
	public void register(Context context) {

		if (!isRegistered()) {
			// screen broadcast
			screenReceiver = new BroadcastReceiver() {

				// When Event is published, onReceive method is called
				@Override
				public void onReceive(Context context, Intent intent) {
					if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {

						Bundle bundle = new Bundle();
						bundle.putString("status", "ON");
						Log.system(getLoggerName(), bundle);

					} else if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {

						Bundle bundle = new Bundle();
						bundle.putString("status", "OFF");
						Log.system(getLoggerName(), bundle);

					}
				}
			};

			IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
			filter.addAction(Intent.ACTION_SCREEN_OFF);
			context.registerReceiver(screenReceiver, filter);
			isRegistered = true;
		}
	}

	@Override
	public String getLoggerName() {
		return NAME;
	}

	@Override
	public void unregister(Context context) {
		if (isRegistered()) {
			context.unregisterReceiver(screenReceiver);
			isRegistered = false;
		}
	}

	@Override
	public boolean isRegistered() {
		return isRegistered;
	}

}

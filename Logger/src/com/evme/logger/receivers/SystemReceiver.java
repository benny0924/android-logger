package com.evme.logger.receivers;

import android.content.Context;

public interface SystemReceiver {
	
	void register(Context context);

	String getLoggerName();

	void unregister(Context context);

}
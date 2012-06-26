package jp.ysks.behimo;

import java.lang.reflect.Method;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.IBinder;
import android.widget.Toast;

public class BehimoService extends Service {
	@Override
	public int onStartCommand(Intent intent, int flags, int startId){
        WifiManager wifi = (WifiManager)getSystemService(Context.WIFI_SERVICE);
        wifi.setWifiEnabled(false);
        
        try {
        	Method getConfigMethod = wifi.getClass().getMethod("getWifiApConfiguration");
        	WifiConfiguration config = (WifiConfiguration) getConfigMethod.invoke(wifi);
			
        	Method setEnabledMethod = wifi.getClass().getMethod("setWifiApEnabled", WifiConfiguration.class, boolean.class);
        	String ret = setEnabledMethod.invoke(wifi, config, true).toString();
        	
            Toast.makeText(this, "Success: " + ret, Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(this, "Failed", Toast.LENGTH_LONG).show();
        }

        stopSelf();
        return START_STICKY;
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
}
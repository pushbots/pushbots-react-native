package com.pushbots.reactNative;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.pushbots.push.Pushbots;
import android.content.IntentFilter;
import android.content.Intent;
import com.facebook.react.bridge.ReactContext;
import android.util.Log;
import android.content.BroadcastReceiver;
import com.pushbots.push.utils.PBConstants;
import android.os.Bundle;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.bridge.WritableMap;


public class RNPushbotsModule extends ReactContextBaseJavaModule {

	private final ReactApplicationContext reactContext;
	private static boolean registeredEvents = false;
	public static ReactContext mReactContext;

	public RNPushbotsModule(ReactApplicationContext reactContext) {
		super(reactContext);
		this.reactContext = reactContext;
		Pushbots.sharedInstance().init(reactContext.getApplicationContext());
        Pushbots.sharedInstance().openedCallback(new Pushbots.notificationOpenedHandler() {
            @Override
            public void opened(Bundle notification) {
                sendJSEvent("Pushbots__RemoteNotificationOpened", RNPushbotsUtils.toWritableMap(RNPushbotsUtils.getJsonObject(notification)));
            }
        });
        Pushbots.sharedInstance().receivedCallback(new Pushbots.notificationReceivedHandler() {
            @Override
            public void received(Bundle notification) {
                sendJSEvent("Pushbots__RemoteNotificationReceived", RNPushbotsUtils.toWritableMap(RNPushbotsUtils.getJsonObject(notification)));
            }
        });
	}
  
	private void sendJSEvent(String event, WritableMap params) {
		reactContext
			.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
			.emit(event, params);
	}
  
	@ReactMethod
	public void registerForRemoteNotifications(){
		Pushbots.sharedInstance().registerForRemoteNotifications();
	}

	@ReactMethod
	public void setAlias(String alias){
		if(alias != null && !alias.isEmpty()){
			Pushbots.sharedInstance().setAlias(alias);
		}
	}


	@ReactMethod
	public void removeAlias(){
		Pushbots.sharedInstance().removeAlias();
	}


	@ReactMethod
	public void setTag(String value){
		if(value != null && !value.isEmpty()){
			Pushbots.sharedInstance().tag(value);
		}
	}

	@ReactMethod
	public void removeTag(String value){
		if(value != null && !value.isEmpty()){
			Pushbots.sharedInstance().untag(value);
		}
	}

	@ReactMethod
	public void trackEvent(String value){
		if(value != null && !value.isEmpty()){
			Pushbots.sharedInstance().trackEvent(value);
		}
	}

	@ReactMethod
	public void setEmail(String value){
		if(value != null && !value.isEmpty()){
			Pushbots.sharedInstance().setEmail(value);
		}
	}

	@ReactMethod
	public void setPhone(String value){
		if(value != null && !value.isEmpty()){
			Pushbots.sharedInstance().setPhone(value);
		}
	}

	@ReactMethod
	public void setGender(String value){
		if(value != null && !value.isEmpty()){
			Pushbots.sharedInstance().setGender(value);
		}
	}

	@ReactMethod
	public void setName(String value){
		if(value != null && !value.isEmpty()){
			Pushbots.sharedInstance().setName(value);
		}
	}

	@ReactMethod
	public void setFirstName(String value){
		if(value != null && !value.isEmpty()){
			Pushbots.sharedInstance().setFirstName(value);
		}
	}
   
	@ReactMethod
	public void setLastName(String value){
		if(value != null && !value.isEmpty()){
			Pushbots.sharedInstance().setLastName(value);
		}
	}

	@ReactMethod
	public void debug(Boolean value){
		if(value == null){
			value = false;
		}
		Pushbots.sharedInstance().debug(value);
	}

	@ReactMethod
	public void toggleNotifications(Boolean value){
		if(value == null){
			value = false;
		}
		Pushbots.sharedInstance().setNotificationEnabled(value);
	}

	@Override
	public String getName() {
		return "Pushbots";
	}
}
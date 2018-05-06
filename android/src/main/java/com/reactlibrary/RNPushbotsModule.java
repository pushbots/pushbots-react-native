package com.pushbots.reactNative;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.pushbots.push.Pushbots;

public class RNPushbotsModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;

  public RNPushbotsModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }
  
  @ReactMethod
   public void registerForRemoteNotifications(){
      Pushbots.sharedInstance().init(reactContext.getApplicationContext());
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
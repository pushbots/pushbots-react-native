import { NativeModules, NativeEventEmitter, Platform } from 'react-native';

const RNPushbotsModule = NativeModules.Pushbots;

const not_handlers = new Map();
const eventEmitter = new NativeEventEmitter(RNPushbotsModule);


export default class Pushbots {
	static addEventListener(type: any, handler: Function) {
		var listener;
		if (type === 'received') {			
			listener = eventEmitter.addListener(
				'Pushbots__RemoteNotificationReceived',
				(notification) => {
					handler(notification);
				}
			);
		}else if (type === 'opened') {			
			listener = eventEmitter.addListener(
				'Pushbots__RemoteNotificationOpened',
				(notification) => {
					handler(notification);
				}
			);
		}
		not_handlers.set(type, listener);
	}
	
   static removeEventListener(type: any, handler: Function) {

       var listener = not_handlers.get(type);
       if (!listener) {
           return;
       }
       listener.remove();
       not_handlers.delete(type);
   }

    static setLogLevel(logcatLevel, uiLevel) {
		RNPushbotsModule.setLogLevel(logcatLevel, uiLevel);
	}

	static shareLocation(isTracking){
		RNPushbotsModule.shareLocation(isTracking);
	}

	static isInitialized() {
		return RNPushbotsModule.isInitialized();
	}

	static isRegistered() {
		return RNPushbotsModule.isRegistered();
	}

	//static isSharingLocation() {
	//	return RNPushbotsModule.isSharingLocation();
	//}
	static registerForRemoteNotifications () {	  
		RNPushbotsModule.registerForRemoteNotifications()
	}

	static setAlias (alias) {
		RNPushbotsModule.setAlias(alias)
	}

	static removeAlias(){
		RNPushbotsModule.removeAlias()
	}

	static setTag(tag){
		RNPushbotsModule.setTag(tag)
	}

	static trackEvent(event_key){
		RNPushbotsModule.trackEvent(event_key)
	}
	
	static setName(name){
		RNPushbotsModule.setName(name)
	}

	static setFirstName(first_name){
		RNPushbotsModule.setFirstName(first_name)
	}

	static setLastName(last_name){
		RNPushbotsModule.setLastName(last_name)
	}

	static setEmail(email){
		RNPushbotsModule.setEmail(email)
	}

	static setGender(gender){
		RNPushbotsModule.setGender(gender)
	}

	static setPhone(phone){
		RNPushbotsModule.setPhone(phone)
	}

	static removeTag(tag){
		RNPushbotsModule.removeTag(tag)
	}

	static toggleNotifications(value){
		RNPushbotsModule.toggleNotifications(value)
	}

	static incrementBadgeCountBy(count){
		if (Platform.OS === 'ios') {
			RNPushbotsModule.incrementBadgeCountBy(count)
		}

	}

	static decrementBadgeCountBy(count){
		if (Platform.OS === 'ios') {
			RNPushbotsModule.decrementBadgeCountBy(count)
		}

	}

	static clearBadgeCount(count){
		if (Platform.OS === 'ios') {
			RNPushbotsModule.clearBadgeCount()
		}
	}
}
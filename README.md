# pushbots-react-native

[![All Contributors](https://img.shields.io/badge/all_contributors-4-orange.svg?style=flat-square)](#contributors)
[![Twitter Follow](https://img.shields.io/twitter/follow/pushbots.svg?style=social&label=Follow&style=plastic)](https://twitter.com/pushbots)
[![npm](https://img.shields.io/npm/dw/localeval.svg)](https://www.npmjs.com/package/pushbots-react-native)

> React Native Library for PushBots Push Notifications Service
## Getting started

`npm install pushbots-react-native --save`

## Automatic Installation
`react-native link pushbots-react-native`

## Configuration

### iOS

#### Add Required Capabilities
1. Select the root project and then under Capabilities click to enable "Push Notifications".
2. Enable "Background Modes" and check "Remote notifications".
![alt text](https://user-images.githubusercontent.com/6784122/33188142-fdbb77f2-d09f-11e7-8506-feb8f3b66949.png)

#### Adding the Code

 * in `AppDelegate.m`:

  

    * On the `application didFinishLaunchingWithOptions` method, add the following code (replace APP_ID with your PushBots app ID):

       Add this line to **didFinishLaunchingWithOptions**:
```objc
 self.PushbotsClient = [[RCTPushbots alloc] initWithAppId:@"APP_ID"];
```

 * Voila!


### Android

Go to `build.gradle`  app level and add this in default config
Add the following to defaultConfig in `build.gradle` file inside the android/app folder

```gradle
defaultConfig {
  manifestPlaceholders = [
    pushbots_app_id: "APP_ID",
    google_sender_id: "SENDER_ID",
    pushbots_loglevel: "DEBUG"
    ]
  }
```


### Usage

in your App.js:


```javascript

import Pushbots from 'pushbots-react-native'

Pushbots.registerForRemoteNotifications()

//events work with iOS only in this version. 
export default class App extends Component<{}> {
	componentWillMount() {
		console.log("Adding listener");
		Pushbots.addEventListener('received', this.onReceived);
	}
	componentWillUnmount() {
		Pushbots.removeEventListener('received', this.onReceived);
	}
	onReceived(notification) {
		Alert.alert( 'Alert Title', JSON.stringify(notification), [ {text: 'Ask me later', onPress: () => console.log('Ask me later pressed')}, {text: 'Cancel', onPress: () => console.log('Cancel Pressed'), style: 'cancel'}, {text: 'OK', onPress: () => console.log('OK Pressed')}, ], { cancelable: false } )
	}
}

```

## Contributors

Thanks goes to these wonderful people:

<!-- ALL-CONTRIBUTORS-LIST:START - Do not remove or modify this section -->
| [<img src="https://avatars3.githubusercontent.com/u/644440?v=4" width="50px;"/><br /><sub><b>Mohamed Shaban</b></sub>](https://github.com/drmas)<br />💻| [<img src="https://avatars2.githubusercontent.com/u/733794?v=4" width="50px;"/><br /><sub><b>Abdullah Diaa</b></sub>](https://abdullahdiaa.com)<br />💻| [<img src="https://avatars2.githubusercontent.com/u/14788993?v=4" width="50px;"/><br /><sub><b>meMuhammadkamal</b></sub>](https://github.com/meMuhammadkamal)<br />💻| [<img src="https://avatars0.githubusercontent.com/u/6784122?v=4" width="50px;"/><br /><sub><b>amrsobhy</b></sub>](http://amrsobhy.com)<br /> 📖|
| :---: | :---: | :---: | :---: |
<!-- ALL-CONTRIBUTORS-LIST:END -->
Contributions of any kind welcome!

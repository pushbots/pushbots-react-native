# pushbots-react-native

[![All Contributors](https://img.shields.io/badge/all_contributors-4-orange.svg?style=flat-square)](#contributors)
[![Twitter Follow](https://img.shields.io/twitter/follow/pushbots.png?style=social&label=Follow&style=plastic)](https://twitter.com/pushbots)
[![npm](https://img.shields.io/npm/dw/localeval.png)](https://www.npmjs.com/package/pushbots-react-native)

> React Native Library for PushBots Push Notifications Service
## Getting started

`npm install pushbots-react-native --save && npm install`

run `pod install` in ios folder:
`cd ios && pod install`

## Automatic Installation
`react-native link pushbots-react-native`

## Configuration

### iOS

#### Add Required Capabilities
1. Select the root project and then under Capabilities click to enable "Push Notifications".
2. Enable "Background Modes" and check "Remote notifications".
![alt text](https://user-images.githubusercontent.com/6784122/33188142-fdbb77f2-d09f-11e7-8506-feb8f3b66949.png)

#### Adding the Code

* Open `AppDelegate.h` Import **RCTPushbots**:

```objc
#import "RCTPushbots.h"
```


* The add PushbotsClient propery below `@property (nonatomic, strong) UIWindow *window;`

```objc
@property (strong, nonatomic) RCTPushbots *PushbotsClient;
```

 * Open `AppDelegate.m`: Add PushBots code to `application:didFinishLaunchingWithOptions` method (replace APP_ID with your PushBots app ID):

```objc
 self.PushbotsClient = [[RCTPushbots alloc] initWithAppId:@"APP_ID" withLaunchOptions:launchOptions];
```

### Android

Go to `android/app/build.gradle` app level and add this in default config
Add the following to **defaultConfig** in `build.gradle` file inside the android/app folder

```gradle
defaultConfig {
  manifestPlaceholders = [
    pushbots_app_id: "APP_ID",
    google_sender_id: "SENDER_ID",
    pushbots_loglevel: "DEBUG"
    ]
  }
```

make sure **buildToolsVersion** and **compileSdkVersion** are set to at least 27:
```gradle
android {
    compileSdkVersion 27
    buildToolsVersion "27.0.3"
	....
}
```

### Usage

in your App.js:


```javascript
import { Text} from 'react-native';
import React, { Component } from 'react';
import Pushbots from 'pushbots-react-native'


Pushbots.registerForRemoteNotifications()
export default class App extends Component {
    constructor(properties) {
        super(properties);
        Pushbots.addEventListener('received', this.onReceived);
        Pushbots.addEventListener('opened', this.onOpened);
      }
    componentWillUnmount() {
        Pushbots.removeEventListener('received', this.onReceived);
        Pushbots.removeEventListener('opened', this.onOpened);    }
        
    onReceived(notification) {
        console.log('Received Notification: ', notification);
    }
    onOpened(notification) {
        console.log('Opened Notification: ', notification);
    }
    render() {
        return (
              <Text>Hello From Pushbots</Text>
            );
    }
}


```

## Contributors

Thanks goes to these wonderful people:

<!-- ALL-CONTRIBUTORS-LIST:START - Do not remove or modify this section -->
| [<img src="https://avatars3.githubusercontent.com/u/644440?v=4" width="50px;"/><br /><sub><b>Mohamed Shaban</b></sub>](https://github.com/drmas)<br />💻| [<img src="https://avatars2.githubusercontent.com/u/733794?v=4" width="50px;"/><br /><sub><b>Abdullah Diaa</b></sub>](https://abdullahdiaa.com)<br />💻📖| [<img src="https://avatars2.githubusercontent.com/u/14788993?v=4" width="50px;"/><br /><sub><b>meMuhammadkamal</b></sub>](https://github.com/meMuhammadkamal)<br />💻| [<img src="https://avatars0.githubusercontent.com/u/6784122?v=4" width="50px;"/><br /><sub><b>amrsobhy</b></sub>](https://amrsobhy.com)<br /> 📖| [<img src="https://avatars2.githubusercontent.com/u/21141301?v=4" width="50px;"/><br /><sub><b>ibrahimAlii</b></sub>](https://ibrahimalii.github.io/)<br /> 💻|
| :---: | :---: | :---: | :---: |:---: |
<!-- ALL-CONTRIBUTORS-LIST:END -->
Contributions of any kind welcome!

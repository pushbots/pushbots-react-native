#if __has_include(<React/RCTBridgeModule.h>)
#import <React/RCTBridgeModule.h>
#elif __has_include("RCTBridgeModule.h")
#import "RCTBridgeModule.h"
#endif

#import <UIKit/UIKit.h>
#if __has_include(<Pushbots/Pushbots.h>)
#import <Pushbots/Pushbots.h>
#else
#import "Pushbots.h"
#endif

@interface RCTPushbots : NSObject <RCTBridgeModule>
- (id) initWithAppId:(NSString*)appId withLaunchOptions:(NSDictionary *)launchOptions;
- (id) initWithAppId:(NSString*)appId withLaunchOptions:(NSDictionary *)launchOptions prompt:(BOOL)prompt;
@end

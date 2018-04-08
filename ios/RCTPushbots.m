#import <Foundation/Foundation.h>
#import <objc/runtime.h>
#import <UIKit/UIKit.h>

#if __has_include(<React/RCTConvert.h>)
#import <React/RCTConvert.h>
#import <React/RCTBridge.h>
#import <React/RCTEventDispatcher.h>
#import <React/RCTUtils.h>
#import <React/RCTLog.h>
#else
#import "RCTConvert.h"
#import "RCTBridge.h"
#import "RCTEventDispatcher.h"
#import "RCTUtils.h"
#import "RCTLog.h"
#endif

#import "RCTPushbots.h"

@interface RCTPushbots ()
@end

@implementation RCTPushbots

@synthesize bridge = _bridge;

RCT_EXPORT_MODULE()

static RCTBridge *curRCTBridge;

- (void)setBridge:(RCTBridge *)receivedBridge {
    _bridge = receivedBridge;
    curRCTBridge = receivedBridge;
}

- (void)dealloc {
    [[NSNotificationCenter defaultCenter] removeObserver:self];
}

- (id) initWithAppId:(NSString*)appId withLaunchOptions:(NSDictionary *)launchOptions; {
    NSLog(@"initWithAppId:");
    return [self initWithAppId:appId withLaunchOptions:launchOptions prompt:NO];
}

- (id) initWithAppId:(NSString*)appId withLaunchOptions:(NSDictionary *)launchOptions prompt:(BOOL)prompt{
    [Pushbots initWithAppId:appId withLaunchOptions:launchOptions prompt:prompt receivedNotification:^(NSDictionary *result) {
        [curRCTBridge.eventDispatcher sendAppEventWithName:@"Pushbots__RemoteNotificationReceived" body:result];
    }];
    return self;
}


RCT_EXPORT_METHOD(setAlias:(NSString *)alias)
{
    [Pushbots setAlias:alias];
}

RCT_EXPORT_METHOD(removeAlias)
{
    [Pushbots removeAlias];
}

RCT_EXPORT_METHOD(setTag:(NSString *)tag)
{
    [Pushbots tag:@[tag]];
}
RCT_EXPORT_METHOD(removeTag:(NSString *)tag)
{
    [Pushbots untag:@[tag]];
}
RCT_EXPORT_METHOD(trackEvent:(NSString *)event_key)
{
    [Pushbots trackEvent:event_key];
}
RCT_EXPORT_METHOD(setBadge:(int )count)
{
    [Pushbots setBadge:count];
}
RCT_EXPORT_METHOD(incrementBadgeCountBy:(int)count)
{
    [Pushbots incrementBadgeCountBy:count];
}
RCT_EXPORT_METHOD(decrementBadgeCountBy:(int)count)
{
    [Pushbots decrementBadgeCountBy:count];
}
RCT_EXPORT_METHOD(clearBadgeCount)
{
    [Pushbots clearBadgeCount];
}
RCT_EXPORT_METHOD(toggleNotifications:(BOOL*)toggle)
{
    [Pushbots toggleNotifications:toggle];
}

RCT_EXPORT_METHOD(registerForRemoteNotifications)
{
    [Pushbots registerForRemoteNotifications];
    curRCTBridge = self.bridge;
}


@end

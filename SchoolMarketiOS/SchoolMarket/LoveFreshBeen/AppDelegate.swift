
//  Created by 维尼的小熊 on 16/1/12.
//  Copyright © 2016年 tianzhongtao. All rights reserved.
//  GitHub地址:https://github.com/ZhongTaoTian/LoveFreshBeen
//  Blog讲解地址:http://www.jianshu.com/p/879f58fe3542
//  小熊的新浪微博:http://weibo.com/5622363113/profile?topnav=1&wvr=6

import UIKit

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate {
    
    var window: UIWindow?
    var adViewController: ADViewController?
    
    // MARK:- public方法
    func application(application: UIApplication, didFinishLaunchingWithOptions launchOptions: [NSObject: AnyObject]?) -> Bool {
        NSThread.sleepForTimeInterval(1.0)
        
//        setUM()
        
        setAppSubject()
        
//        addNotification()
        
        buildKeyWindow()
        

//        AVOSCloud.setApplicationId("yDLtJJnrbS0kxx1xJrMfcGFU-gzGzoHsz", clientKey:"8H140xe7DdaLsXzoy2FrJJ6E");
        return true
    }
    
    deinit {
        NSNotificationCenter.defaultCenter().removeObserver(self)
    }
    
    // MARK: - Public Method
    private func buildKeyWindow() {
        
        window = UIWindow(frame: ScreenBounds)
        window!.makeKeyAndVisible()
        MLTransition.validatePanPackWithMLTransitionGestureRecognizerType(MLTransitionGestureRecognizerTypePan)
        let isFristOpen = NSUserDefaults.standardUserDefaults().objectForKey("isFristOpenApp")
        
        if isFristOpen == nil {
            window?.rootViewController = GuideViewController()
            NSUserDefaults.standardUserDefaults().setObject("isFristOpenApp", forKey: "isFristOpenApp")
        } else {
//            loadADRootViewController()
            let act:LCAccount = LCAccount.sharedInstance()
            if act.logined {
                window!.rootViewController = MainTabBarController()
            }else {
                window!.rootViewController = LoginViewController()
            }
        }
    }
    
    func loadADRootViewController() {
//        adViewController = ADViewController()
//        
//        weak var tmpSelf = self
//        MainAD.loadADData { (data, error) -> Void in
//            if data?.data?.img_name != nil {
//                tmpSelf!.adViewController!.imageName = data!.data!.img_name
//                tmpSelf!.window?.rootViewController = self.adViewController
//            }
//        }
        
        window!.rootViewController = LoginViewController()
    }
    
//    func addNotification() {
//        NSNotificationCenter.defaultCenter().addObserver(self, selector: #selector(AppDelegate.showMainTabbarControllerSucess(_:)), name: ADImageLoadSecussed, object: nil)
//        NSNotificationCenter.defaultCenter().addObserver(self, selector: #selector(AppDelegate.showMainTabbarControllerFale), name: ADImageLoadFail, object: nil)
//        NSNotificationCenter.defaultCenter().addObserver(self, selector: #selector(AppDelegate.shoMainTabBarController), name: GuideViewControllerDidFinish, object: nil)
//    }
    
    func setUM() {
        UMSocialData.setAppKey("569f662be0f55a0efa0001cc")
        UMSocialWechatHandler.setWXAppId("wxb81a61739edd3054", appSecret: "c62eba630d950ff107e62fe08391d19d", url: "https://github.com/ZhongTaoTian")
        UMSocialQQHandler.setQQWithAppId("1105057589", appKey: "Zsc4rA9VaOjexv8z", url: "http://www.jianshu.com/users/5fe7513c7a57/latest_articles")
        UMSocialSinaSSOHandler.openNewSinaSSOWithAppKey("1939108327", redirectURL: "http://sns.whalecloud.com/sina2/callback")
        
        UMSocialConfig.hiddenNotInstallPlatforms([UMShareToWechatSession, UMShareToQzone, UMShareToQQ, UMShareToSina, UMShareToWechatTimeline])
    }
    
//    // MARK: - Action
//    func showMainTabbarControllerSucess(noti: NSNotification) {
//        _ = noti.object as! UIImage
//        let mainTabBar = LoginViewController()
////        mainTabBar.adImage = adImage
//        window?.rootViewController = mainTabBar
//    }
//    
//    func showMainTabbarControllerFale() {
//        window!.rootViewController = LoginViewController()
//    }
//    
//    func shoMainTabBarController() {
//        window!.rootViewController = LoginViewController()
//    }
    
    // MARK:- privete Method
    // MARK:主题设置
    private func setAppSubject() {
        let tabBarAppearance = UITabBar.appearance()
        tabBarAppearance.backgroundColor = UIColor.whiteColor()
        tabBarAppearance.backgroundColor = UIColor(red: 0, green: 0, blue: 0, alpha: 0)
        tabBarAppearance.tintColor = UIColor.redColor()
        let navBarnAppearance = UINavigationBar.appearance()
        navBarnAppearance.translucent = false
        navBarnAppearance.tintColor = UIColor.whiteColor()
    }
}




//
//  OrderPayWayViewController.swift
//  LoveFreshBeen
//
//  Created by 维尼的小熊 on 16/1/12.
//  Copyright © 2016年 tianzhongtao. All rights reserved.
//  GitHub地址:https://github.com/ZhongTaoTian/LoveFreshBeen
//  Blog讲解地址:http://www.jianshu.com/p/879f58fe3542
//  小熊的新浪微博:http://weibo.com/5622363113/profile?topnav=1&wvr=6

import UIKit
import Alamofire
import SwiftyJSON

/// 支付页
class OrderPayWayViewController: BaseViewController {

    private var scrollView = UIScrollView(frame: CGRectMake(0, 0, ScreenWidth, ScreenHeight - 64 - 50))
    private var tableHeaderView = UIView(frame: CGRectMake(0, 0, ScreenWidth, 40 + 15 + 190 + 30))
    private let leftMargin: CGFloat = 15
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        buildNavigationItem()
        
        buildScrollView()
    }
    
    override func viewWillAppear(animated: Bool) {
        super.viewWillAppear(animated)
        
        print(navigationController)
    }

    // MARK: - Action
    private func buildNavigationItem() {
        navigationItem.title = "结算付款"
    }

    private func buildScrollView() {
        scrollView.contentSize = CGSize(width: ScreenWidth, height: 1000)
        scrollView.backgroundColor = UIColor.clearColor()
        view.addSubview(scrollView)
        
        buildTableHeaderView()
        scrollView.addSubview(tableHeaderView)
    }
    
    private func buildTableHeaderView() {
        tableHeaderView.backgroundColor = UIColor.clearColor()
        
        buildCouponView()
        
        buildPayView()
        
        buildCarefullyView()
    }
    
    private func buildCouponView() {
        let couponView = UIView(frame: CGRectMake(0, 0, ScreenWidth, 40))
        couponView.backgroundColor = UIColor.whiteColor()
        tableHeaderView.addSubview(couponView)
        
        let couponImageView = UIImageView(image: UIImage(named: "v2_submit_Icon"))
        couponImageView.frame = CGRectMake(leftMargin, 10, 20, 20)
        couponView.addSubview(couponImageView)
        
        let couponLabel = UILabel(frame: CGRectMake(CGRectGetMaxX(couponImageView.frame) + 10, 0, ScreenWidth * 0.4, 40))
        couponLabel.text = "1张优惠劵"
        couponLabel.textColor = UIColor.redColor()
        couponLabel.font = UIFont.systemFontOfSize(14)
        couponView.addSubview(couponLabel)
        
        let arrowImageView = UIImageView(image: UIImage(named: "icon_go"))
        arrowImageView.frame = CGRectMake(ScreenWidth - 10 - 5, 15, 5, 10)
        couponView.addSubview(arrowImageView)
        
        let checkButton = UIButton(frame: CGRectMake(ScreenWidth - 60, 0, 40, 40))
        checkButton.setTitle("查看", forState: UIControlState.Normal)
        checkButton.setTitleColor(UIColor.blackColor(), forState: UIControlState.Normal)
        checkButton.titleLabel?.font = UIFont.systemFontOfSize(14)
        couponView.addSubview(checkButton)
        
        buildLineView(couponView, lineFrame: CGRectMake(0, 40 - 1, ScreenWidth, 1))
    }
    
    private func buildPayView() {
        let payView = UIView(frame: CGRectMake(0, 55, ScreenWidth, 190))
        payView.backgroundColor = UIColor.whiteColor()
        tableHeaderView.addSubview(payView)
        
        buildLabel(CGRectMake(leftMargin, 0, 150, 30), textColor: UIColor.lightGrayColor(), font: UIFont.systemFontOfSize(12), addView: payView, text: "选择支付方式")
        let payV = PayView(frame: CGRectMake(0, 30, ScreenWidth, 160))
        payView.addSubview(payV)
        
        buildLineView(payView, lineFrame: CGRectMake(0, 189, ScreenWidth, 1))
    }
    
    private func buildCarefullyView() {
        let carefullyView = UIView(frame: CGRectMake(0, 40 + 15 + 190 + 15, ScreenWidth, 30))
        carefullyView.backgroundColor = UIColor.whiteColor()
        tableHeaderView.addSubview(carefullyView)
        
        buildLabel(CGRectMake(leftMargin, 0, 150, 30), textColor: UIColor.lightGrayColor(), font: UIFont.systemFontOfSize(12), addView: carefullyView, text: "精选商品")
        
        let goodsView = ShopCartGoodsListView(frame: CGRectMake(0, CGRectGetMaxY(carefullyView.frame), ScreenWidth, 300))
        goodsView.frame.size.height = goodsView.goodsHeight
        scrollView.addSubview(goodsView)
        
        let costDetailView = CostDetailView(frame: CGRectMake(0, CGRectGetMaxY(goodsView.frame) + 10, ScreenWidth, 135))
        scrollView.addSubview(costDetailView)
        
        scrollView.contentSize = CGSizeMake(ScreenWidth, CGRectGetMaxY(costDetailView.frame) + 15)
        
        let bottomView = UIView(frame: CGRectMake(0, ScreenHeight - 50 - 50, ScreenWidth, 60))
        bottomView.backgroundColor = UIColor.whiteColor()
        buildLineView(bottomView, lineFrame: CGRectMake(0, 0, ScreenWidth, 1))
        view.addSubview(bottomView)
        
        buildLabel(CGRectMake(leftMargin, 0, 80, 50), textColor: UIColor.blackColor(), font: UIFont.systemFontOfSize(14), addView: bottomView, text: "实付金额:")
        var priceText = costDetailView.coupon == "0" ? UserShopCarTool.sharedUserShopCar.getAllProductsPrice() : "\((UserShopCarTool.sharedUserShopCar.getAllProductsPrice() as NSString).floatValue - 5)"
        if (priceText as NSString).floatValue < 30 {
            priceText = "\((priceText as NSString).floatValue + 8)"
        }
        buildLabel(CGRectMake(85, 0, 150, 50), textColor: UIColor.redColor(), font: UIFont.systemFontOfSize(14), addView: bottomView, text: "$" + priceText)
        
        let payButton = UIButton(frame: CGRectMake(ScreenWidth - 100, 1, 100, 49))
        payButton.titleLabel?.font = UIFont.systemFontOfSize(14)
        payButton.setTitle("确认付款", forState: UIControlState.Normal)
        payButton.backgroundColor = LFBNavigationYellowColor
        payButton.setTitleColor(UIColor.blackColor(), forState: UIControlState.Normal)
        payButton.addTarget(self, action: #selector(OrderPayWayViewController.pay(_:)), forControlEvents: .TouchUpInside)
        bottomView.addSubview(payButton)
    }
    
    private func buildLineView(addView: UIView, lineFrame: CGRect) {
        let lineView = UIView(frame: lineFrame)
        lineView.backgroundColor = UIColor.blackColor()
        lineView.alpha = 0.1
        addView.addSubview(lineView)
    }
    
    private func buildLabel(labelFrame: CGRect, textColor: UIColor, font: UIFont, addView: UIView, text: String) {
        let label = UILabel(frame: labelFrame)
        label.textColor = textColor
        label.font = font
        label.text = text
        addView.addSubview(label)
    }
    
    /**
     付款
     
     - parameter sender: <#sender description#>
     */
    func pay(sender:UIButton) {
        let act = LCAccount.sharedInstance()
        let goods:[Goods] = UserShopCarTool.sharedUserShopCar.getShopCarProducts()
        var goodDic:[String:String] = ["":""]
        var goodArray:[NSDictionary] = []
        var count = 0
        for good in goods {
            goodDic.updateValue(good.id!, forKey: "Pid")
            goodDic.updateValue(String(good.userBuyNumber), forKey: "Num")
            goodArray.append(goodDic)
            count += good.userBuyNumber
        }
        //时间
        let dateformat = NSDateFormatter()
        dateformat.dateFormat = "yyyy-MM-dd HH:mm"
        dateformat.timeZone = NSTimeZone(name: "Asia/Shanghai")
        
        print("用户id：\(LCAccount.sharedInstance().userId)")
        print("用户地址：\(LCAccount.sharedInstance().address)")
        print("用户号码：\(LCAccount.sharedInstance().mobileNum)")


        let dataDic = ["Cid":LCAccount.sharedInstance().userId,"address":LCAccount.sharedInstance().address,"receivcerPhone":act.mobileNum,"Price":UserShopCarTool.sharedUserShopCar.getAllProductsPrice(),"Date":dateformat.stringFromDate(NSDate()),"orderDetail":goodArray,"count":count]
        
        print(dataDic.description)

        
        Alamofire.request(.GET, "http://192.168.191.1:8080/SchoolMarketWebService/order.jsp",parameters: ["data":dataDic.mj_JSONString(),"type":"1"])
            .responseJSON { response in
                if let value = response.result.value {
                    print("\(value)")
                }
                switch (response.result) {
                case .Success:
                    print("交互成功")
               case .Failure(let error):
                    print("错误信息：\(error)")
                }
        }

        dismissViewControllerAnimated(true){}
        
    }
    
}


//
//  SupermarketResouce.swift
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
import MJExtension
import SVProgressHUD


class Supermarket: NSObject, DictModelProtocol {
    var code: Int = -1
    var msg: String?
    var reqid: String?
    var data: SupermarketResouce!
    class func loadSupermarketData(completion:(data: Supermarket?, error: NSError?) -> Void) {
        
        
        Alamofire.request(.GET, "http://192.168.191.1:8080/SchoolMarketWebService/products.jsp",parameters: ["":""])
            .responseJSON { response in
                if let value = response.result.value {
                    print("\(value)")
                }
//                let A:Supermarket = Supermarket.mj_objectWithKeyValues(response.result.value)
                switch (response.result) {
                case .Success:
                    let json = JSON(response.result.value!)
                    let message = json["msg"]
                    if message.string != "success" {
                        SVProgressHUD.showErrorWithStatus("\(message)")
                    }else {
                        print(json.dictionaryObject!);
//                        let modelTool = DictModelManager.sharedManager
                        let superMarketData = Supermarket()
                        superMarketData.code = json["code"].intValue
                        superMarketData.msg = message.string
                        superMarketData.reqid = "123"
                        
                        let resouce:SupermarketResouce! = SupermarketResouce()
                        let categorie:Categorie = Categorie()
                        categorie.id = String(json["data"]["categories"][0]["id"].intValue)
                        categorie.name = json["data"]["categories"][0]["name"].string
                        categorie.sort = String(json["data"]["categories"][0]["sort"].intValue)
//                        resouce.categories![0] = categorie
                        let products:Products = Products()
                        let productArray = json["data"]["products"]["a82"].arrayValue
                        products.a82.removeAll()
                        for i in 0..<productArray.count {
                            let good = Goods()
                            good.brand_id  = productArray[i]["Pid"].stringValue
                            good.id = good.brand_id
                            good.is_xf = 1
                            good.name = productArray[i]["PName"].string
                            good.price = String(productArray[i]["Price"].intValue)
                            good.number = productArray[i]["Pcount"].intValue
                            good.partner_price = good.price
                            good.specifics = productArray[i]["Ptype"].stringValue
                            products.a82.append(good)
                        }
                        resouce.trackid = "11"
                        resouce.products = products
                        superMarketData.data = SupermarketResouce(categories: [categorie], products: products, trackid: "11")
                        print(superMarketData.data.categories)
                        completion(data: superMarketData, error: nil)

                    }
                case .Failure(let error):
                    SVProgressHUD.showErrorWithStatus("登录失败")
                    print("\(error)")
                }
        }

        
//        let path = NSBundle.mainBundle().pathForResource("supermarket", ofType: nil)
//        let data = NSData(contentsOfFile: path!)
//        
//        if data != nil {
//            let dict: NSDictionary = (try! NSJSONSerialization.JSONObjectWithData(data!, options: .AllowFragments)) as! NSDictionary
//            let modelTool = DictModelManager.sharedManager
//            let data = modelTool.objectWithDictionary(dict, cls: Supermarket.self) as? Supermarket
//            completion(data: data, error: nil)
//        }
//        
        
    }
    
    class func searchCategoryMatchProducts(supermarketResouce: SupermarketResouce) -> [[Goods]]? {
        var arr = [[Goods]]()
        
        let products = supermarketResouce.products
//        for cate in supermarketResouce.categories! {
//           let goodsArr = products!.valueForKey(cate.id!) as! [Goods]
//            arr.append(goodsArr)
//        }
        let goodsArr = products!.a82
        arr.append(goodsArr)
        return arr
    }
    
    static func customClassMapping() -> [String : String]? {
        return ["data" : "\(SupermarketResouce.self)"]
    }
}

class SupermarketResouce: NSObject {
    var categories: [Categorie]?
    var products: Products?
    var trackid: String?
    override init() {
        super.init()
    }
    convenience init(categories: [Categorie]?, products: Products?, trackid: String?) {
        self.init()
        self.categories = categories
        self.products = products
        self.trackid = trackid
    }
    static func customClassMapping() -> [String : String]? {
        return ["categories" : "\(Categorie.self)", "products" : "\(Products.self)"]
    }
}

class Categorie: NSObject {
    var id: String?
    var name: String?
    var sort: String?
}

class Products: NSObject, DictModelProtocol {
    var a82: [Goods]! = [Goods()]
    var a96: [Goods]?
    var a99: [Goods]?
    var a106: [Goods]?
    var a134: [Goods]?
    var a135: [Goods]?
    var a136: [Goods]?
    var a137: [Goods]?
    var a141: [Goods]?
    var a143: [Goods]?
    var a147: [Goods]?
    var a151: [Goods]?
    var a152: [Goods]?
    var a158: [Goods]?
    
    static func customClassMapping() -> [String : String]? {
        return ["a82" : "\(Goods.self)", "a96" : "\(Goods.self)", "a99" : "\(Goods.self)", "a106" : "\(Goods.self)", "a134" : "\(Goods.self)", "a135" : "\(Goods.self)", "a136" : "\(Goods.self)", "a141" : "\(Goods.self)", "a143" : "\(Goods.self)", "a147" : "\(Goods.self)", "a151" : "\(Goods.self)", "a152" : "\(Goods.self)", "a158" : "\(Goods.self)", "a137" : "\(Goods.self)"]
    }
}


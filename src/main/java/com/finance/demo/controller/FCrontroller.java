package com.finance.demo.controller;


import com.finance.demo.model.StockHistory;
import com.finance.demo.model.StockModel;
import com.finance.demo.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Controller
public class FCrontroller {

    @Autowired
    StockService stockService ;


    @RequestMapping("/")
    public String mainMethod() {
        return "home";
    }




    @RequestMapping(value = "/exportStockHistory" , params = {"stockName","freq","year"})
    public String exportHistory(@RequestParam("stockName") String stockName, @RequestParam("year") String yearr,
                                @RequestParam("freq") String searchType, Model model) throws Exception {

        if( (searchType.equals("Other") && (yearr==null || yearr.isEmpty() ) )){
            StockModel stock = stockService.getStockDetails(stockName);
            List<StockModel> stockList = new ArrayList<>();
            stockList.add(stock);
            model.addAttribute("stockList", stockList);
            return "home";
        }
        //System.out.println("Baba name year and type is"+stockName+yearr+searchType+"baba");
        if(searchType.equals("Other")){
            searchType="MONTHLY";
        }
        int year ;
        //System.out.println("Year is "+yearr);
        if(yearr==null || yearr.isEmpty()){
            year = 0 ;
        }
        else {
            year = Integer.parseInt(yearr);
        }
        //System.out.println("year and freq is "+year+searchType);

        if(searchType!=null)
            searchType = searchType.toUpperCase();

        List<StockHistory> histories = stockService.getHistory(stockName, year, searchType);
        model.addAttribute("msg", searchType+ " Stock History of " + stockName + " : ");
        model.addAttribute("histories", histories);
        return "home";

    }

    @RequestMapping(value = "/exportStockHistory/{stockName}")
    public String getHistory(@PathVariable String stockName, Model model) throws Exception {

        List<StockHistory> histories = stockService.getHistory(stockName, 0, null);
        model.addAttribute("msg", "Stock History till Today:");
        model.addAttribute("histories", histories);
        return "home";


    }

//    @GetMapping("/getStock")
//    public String getStockDetails(@RequestParam("stockName") String stockName, Model model) throws Exception {
//
//        StockModel stock = stockService.getStockDetails(stockName);
//        List<StockModel> stockList = new ArrayList<>();
//        stockList.add(stock);
//        model.addAttribute("stockList", stockList);
//        return "home";
//
//
//
//    }
//
//    @GetMapping("/getStock/{stockName}")
//    public String getStockDetails11(@PathVariable String stockName, Model model) throws Exception {
//
//        StockModel stock = stockService.getStockDetails(stockName);
//        List<StockModel> stockList = new ArrayList<>();
//        stockList.add(stock);
//        model.addAttribute("stockList", stockList);
//        return "home";
//
//    }
//
//

//
//    @RequestMapping(value = "/exportStockHistory" ,params = {"stockName","year"})
//    public String exportHistory1(@RequestParam("stockName") String stockName, @RequestParam("year") String yearr , Model model ) throws Exception {
//
//        int year ;
//        //System.out.println("Year is "+yearr);
//        if(yearr==null || yearr.isEmpty()){
//            year = 0 ;
//        }
//        else {
//            year = Integer.parseInt(yearr);
//        }
//        List<StockHistory> histories = stockService.getHistory(stockName, year, "MONTHLY");
//        model.addAttribute("histories", histories);
//
//        //System.out.println("nme+year"+year);
//        return "home";
//    }
//
//    @RequestMapping(value = "/exportStockHistory" , params = {"stockName","freq"})
//    public String exportHistory2(@RequestParam("stockName") String stockName,
//                                 @RequestParam("freq") String searchType , Model model) throws Exception {
//
//        List<StockHistory> histories = stockService.getHistory(stockName, 0, searchType);
//        System.out.println("nme+freq"+searchType);
//        model.addAttribute("histories", histories);
//
//        return "home";
//    }
//
//    public Map<String, Stock> getStock(String[] stockNames) throws IOException {
//        Map<String, Stock> stock = YahooFinance.get(stockNames);
//        return stock;
//    }

}


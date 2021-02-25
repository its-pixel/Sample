package com.finance.demo.service;

import com.finance.demo.model.StockHistory;
import com.finance.demo.model.StockModel;
import org.springframework.stereotype.Service;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
@Service
public class StockService {
    public StockModel getStockDetails(String stockName) throws IOException {
        Stock stock = YahooFinance.get(stockName.toUpperCase(), true);

        return new StockModel(stock.getName(), stock.getQuote().getPrice(),
                stock.getCurrency(), stock.getQuote().getChange(),stock.getQuote().getBid(),
                stock.getQuote().getChangeFromYearHighInPercent(),
                stockName.toUpperCase());
    }

    public List<StockHistory> getHistory(String stockName, int year, String type) throws Exception {
        List<StockHistory> histories = new ArrayList<>();
        Stock stock = null;
        Calendar from  = null ;
        Calendar to = null ;
        List<HistoricalQuote> quotes = null;
        if (type != null && (year == 0)) {
            Interval intrvl = getInterval(type);
            stock = YahooFinance.get(stockName, true);
            quotes = stock.getHistory(intrvl);
            //System.out.println("dono 1 0");


        }
        else if((type == null && year != 0 )){
            from = Calendar.getInstance();
            LocalDate d = LocalDate.now();
            int ago = d.getYear() - year;
            from.add(Calendar.YEAR, -ago);
            quotes = stock.getHistory(from, getInterval("MONTHLY"));
            //System.out.println("dono 0 1");


        }
        else if(type==null && year == 0){
            from = Calendar.getInstance();
            to = Calendar.getInstance();
            LocalDate d = LocalDate.now();
            System.out.println("dono 0 0");

            from.add(Calendar.YEAR, -1);
            stock = YahooFinance.get(stockName);
            quotes = stock.getHistory(from, to, getInterval("MONTHLY"));

        }
        else {
            from = Calendar.getInstance();
            to = Calendar.getInstance();
            LocalDate d = LocalDate.now();

            int ago = d.getYear() - year;
            from.add(Calendar.YEAR, -ago);
            stock = YahooFinance.get(stockName);
            quotes = stock.getHistory(from, to, getInterval(type));

            //quotes = stock.getHistory(from, to, getInterval(type));
            //System.out.println("Control reached here");
            //System.out.println("value of ago is "+ago);
            System.out.println("dono 1 1");

        }

        for (HistoricalQuote quote : quotes) {
            histories.add(new StockHistory(quote.getSymbol(), convertor(quote.getDate()), quote.getHigh(),
                    quote.getLow(), quote.getClose()));

        }
        return histories;
    }
    private String convertor(Calendar cal) {
        cal.add(Calendar.DATE, 1);
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        String formatted = format1.format(cal.getTime());
        return formatted;
    }

    private Interval getInterval(String searchType) {
        Interval interval = null;
        if(searchType.equals("DAILY")){
            interval = Interval.DAILY;
        }
        else if(searchType.equals("WEEKLY")){
            interval = Interval.WEEKLY;
        }
        else if(searchType.equals("MONTHLY")){
            interval = Interval.MONTHLY;
        }
        else{
            interval = Interval.MONTHLY;
        }
        return interval;
    }

}

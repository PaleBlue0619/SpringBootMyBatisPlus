package com.maxim.mybatisplus.controller;
import com.maxim.mybatisplus.Response;
import com.maxim.mybatisplus.entity.stockDayKDTO;
import com.maxim.mybatisplus.mapper.stockDayKMapper;
import com.maxim.mybatisplus.mapper.stockDayKMapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class stockDayKController {
    @Autowired
    private stockDayKMapperImpl stockDayKMapper;

    @GetMapping("/get/by-id")
    public Response<stockDayKDTO> getStockDayKById(@RequestParam(name = "id") Long id) {
        return stockDayKMapper.selectById( id);
    }

    @GetMapping("/test")
    public Response<List<stockDayKDTO>> testStockDayK(){
        return stockDayKMapper.selectByLambda();
    }

    @GetMapping("/test/by-day")
    public Response<List<stockDayKDTO>> testStockDayKByDay(@RequestParam(name = "trade_date") String day){
        return stockDayKMapper.selectBySQL(day);
    }

    @GetMapping("/test/page")
    public Response<List<stockDayKDTO>> testStockDayKPage(@RequestParam(name = "page_num", required = true) Integer pageNum,
                                                         @RequestParam(name = "page_size", required = true) Integer pageSize){
        return stockDayKMapper.selectByPage(pageNum, pageSize);
    }

    @PostMapping("/post/by-day-symbol")
    public Response<stockDayKDTO> postStockDayK(@RequestParam(name = "symbol", required = true) String symbol,
                                               @RequestParam(name = "trade_date", required = true) String tradeDate,
                                               @RequestParam(name = "open", required = false) Double open,
                                               @RequestParam(name = "high", required = false) Double high,
                                               @RequestParam(name = "low", required = false) Double low,
                                               @RequestParam(name = "close", required = false) Double close,
                                               @RequestParam(name = "pre_close", required = false) Double preClose,
                                               @RequestParam(name = "chg_amount", required = false) Double chgAmount,
                                               @RequestParam(name = "chg_rate", required = false) Double chgRate,
                                               @RequestParam(name = "volume", required = false) Long volume,
                                               @RequestParam(name = "amount", required = false) Double amount,
                                               @RequestParam(name = "turnover_rate", required = false) Double turnoverRate,
                                               @RequestParam(name = "pe", required = false) Double pe,
                                               @RequestParam(name = "pb", required = false) Double pb,
                                               @RequestParam(name = "mv", required = false) Double mv,
                                               @RequestParam(name = "cmv", required = false) Double cmv){
        return stockDayKMapper.insertObj(symbol, tradeDate, open, high, low, close, preClose, chgAmount, chgRate, volume, amount, turnoverRate, pe, pb, mv, cmv);
    }

    @DeleteMapping("/delete/by-id")
    public Response<String> delStockDayKById(@RequestParam(name = "id") Long id) {
        return stockDayKMapper.deleteById(id);
    }

    @PutMapping("/put/by-day-symbol")
    public Response<stockDayKDTO> putStockDayK(@RequestParam(name = "symbol", required = true) String symbol,
                                               @RequestParam(name = "trade_date", required = true) String tradeDate,
                                               @RequestParam(name = "open", required = false) Double open,
                                               @RequestParam(name = "high", required = false) Double high,
                                               @RequestParam(name = "low", required = false) Double low,
                                               @RequestParam(name = "close", required = false) Double close,
                                               @RequestParam(name = "pre_close", required = false) Double preClose,
                                               @RequestParam(name = "chg_amount", required = false) Double chgAmount,
                                               @RequestParam(name = "chg_rate", required = false) Double chgRate,
                                               @RequestParam(name = "volume", required = false) Long volume,
                                               @RequestParam(name = "amount", required = false) Double amount,
                                               @RequestParam(name = "turnover_rate", required = false) Double turnoverRate,
                                               @RequestParam(name = "pe", required = false) Double pe,
                                               @RequestParam(name = "pb", required = false) Double pb,
                                               @RequestParam(name = "mv", required = false) Double mv,
                                               @RequestParam(name = "cmv", required = false) Double cmv){
        return stockDayKMapper.updateObj(symbol, tradeDate, open, high, low, close, preClose, chgAmount, chgRate,
                volume, amount, turnoverRate, pe, pb, mv, cmv);
    }

    @PutMapping("/test/optimize")
    public Response<String> testOptimize(){
        return stockDayKMapper.concurrentUpdate();
    }
}

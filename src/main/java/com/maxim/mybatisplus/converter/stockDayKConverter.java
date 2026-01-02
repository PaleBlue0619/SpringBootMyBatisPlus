package com.maxim.mybatisplus.converter;

import com.maxim.mybatisplus.entity.stockDayK;
import com.maxim.mybatisplus.entity.stockDayKDTO;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;

@UtilityClass // 静态方法工具类
public class stockDayKConverter {
    public static stockDayK toDAO(stockDayKDTO dto){
        stockDayK obj = stockDayK.builder()
                .symbol(dto.getSymbol())
                .tradeDate(dto.getTradeDate())
                .open(dto.getOpen())
                .high(dto.getHigh())
                .low(dto.getLow())
                .close(dto.getClose())
                .preClose(dto.getPreClose())
                .chgAmount(dto.getChgAmount())
                .chgRate(dto.getChgRate())
                .mv(dto.getMv())
                .pe(dto.getPe())
                .pb(dto.getPb())
                .build();
        LocalDateTime ts = LocalDateTime.now();
        obj.setCreateTime(ts);
        obj.setUpdateTime(ts);
        return obj;
    }

    public static stockDayKDTO toDTO(stockDayK obj){
        return stockDayKDTO.builder()
                .symbol(obj.getSymbol())
                .tradeDate(obj.getTradeDate())
                .open(obj.getOpen())
                .high(obj.getHigh())
                .low(obj.getLow())
                .close(obj.getClose())
                .preClose(obj.getPreClose())
                .chgAmount(obj.getChgAmount())
                .chgRate(obj.getChgRate())
                .pe(obj.getPe())
                .pb(obj.getPb())
                .build();
    }
}

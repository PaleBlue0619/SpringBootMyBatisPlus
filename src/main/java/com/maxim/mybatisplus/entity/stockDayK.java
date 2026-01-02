package com.maxim.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "stockDayK")
public class stockDayK {
    @TableId(type = IdType.AUTO)
    private String id;
    @TableField(value = "symbol")
    private String symbol;
    @TableField(value = "trade_date")
    private LocalDate tradeDate;
    @TableField(value = "open_price")
    private Double open;
    @TableField(value = "high_price")
    private Double high;
    @TableField(value = "low_price")
    private Double low;
    @TableField(value = "close_price")
    private Double close;
    @TableField(value = "pre_close")
    private Double preClose;
    @TableField(value = "change_amount")
    private Double chgAmount;
    @TableField(value = "change_rate")
    private Double chgRate;
    @TableField(value = "volume")
    private Long volume;
    @TableField(value = "amount")
    private Double amount;
    @TableField(value = "turnover_rate")
    private Double turnoverRate;
    @TableField(value = "pe_ratio")
    private Double pe;
    @TableField(value = "pb_ratio")
    private Double pb;
    @TableField(value = "market_cap")
    private Double mv;
    @TableField(value = "circulating_market_cap")
    private Double cmv;
    @TableField(value = "create_time")
    private LocalDateTime createTime;
    @TableField(value = "update_time")
    private LocalDateTime updateTime;
}

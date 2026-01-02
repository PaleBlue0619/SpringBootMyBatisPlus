package com.maxim.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class stockDayKDTO {
    private String symbol;
    private LocalDate tradeDate;
    private Double open;
    private Double high;
    private Double low;
    private Double close;
    private Double preClose;
    private Double chgAmount;
    private Double chgRate;
    private Long volume;
    private Double amount;
    private Double turnoverRate;
    private Double pe;
    private Double pb;
    private Double mv;
    private Double cmv;
}

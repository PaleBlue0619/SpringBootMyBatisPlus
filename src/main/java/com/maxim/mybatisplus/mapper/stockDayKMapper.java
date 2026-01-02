package com.maxim.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.maxim.mybatisplus.Response;
import com.maxim.mybatisplus.entity.stockDayK;
import com.maxim.mybatisplus.entity.stockDayKDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@Mapper // Mapper注解
public interface stockDayKMapper extends BaseMapper<stockDayK> {

    /*
    * 静态SQL查询
    * */
    @Select(value = "select * from stockDayK where trade_date = #{tradeDate}")
    List<stockDayK> selectByDate(@Param("tradeDate") String tradeDate);

    /*
    * 动态SQL查询
    * */
    // 以后再说

}

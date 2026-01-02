package com.maxim.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.maxim.mybatisplus.Response;
import com.maxim.mybatisplus.entity.stockDayK;
import com.maxim.mybatisplus.entity.stockDayKDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Mapper // Mapper注解
public interface stockDayKMapper extends BaseMapper<stockDayK> {
}

package com.maxim.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.maxim.mybatisplus.entity.stockDayK;
import com.maxim.mybatisplus.mapper.stockDayKMapper;
import org.junit.jupiter.api.Test; // Junit 5 的包
import org.junit.platform.commons.util.StringUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@SpringBootTest
class MyBatisplusApplicationTests{
	/* 增删改查测试 */
	@Autowired
	private stockDayKMapper stockDayKMapper;

	@Test
	void testInsert(){
		stockDayK obj = stockDayK.builder()
				.symbol("000001.SZ").tradeDate(LocalDate.now())
				.open(10.0).high(10.0).low(10.0).amount(10.0)
				.close(10.0).preClose(10.0).chgAmount(10.0).chgRate(10.0)
				.volume(Long.parseLong("10")).turnoverRate(10.0).pe(10.0).pb(10.0).mv(10.0).cmv(10.0)
				.createTime(LocalDateTime.now()).updateTime(LocalDateTime.now())
				.build();
		stockDayKMapper.insert(obj);
	}

	@Test
	void testSelect(){
		stockDayK obj = stockDayKMapper.selectById("1");
		System.out.println(obj);
	}

	@Test
	void testDelete(){
		stockDayKMapper.deleteById("1");
	}

	@Test
	void testUpdate(){
		stockDayK obj = stockDayKMapper.selectById("1");
		obj.setSymbol("000001.SZ");
		stockDayKMapper.updateById(obj); //先查询数据库中的对象 -> 更新对象中的所有非空字段
	}

	@Test
	void testQueryWrapper(){
		QueryWrapper<stockDayK> queryWrapper = new QueryWrapper<>();
		List<String> selectCols = List.of("symbol", "trade_date", "open_price", "high_price", "low_price", "close_price");
		queryWrapper.select(selectCols);
		queryWrapper.likeLeft("symbol", ".SZ");
		queryWrapper.between("trade_date", LocalDate.of(2024, 1, 1),
				LocalDate.of(2024,1,31));
		List<stockDayK> objList = stockDayKMapper.selectList(queryWrapper);
		System.out.println(objList.size());
	}

	@Test
	void testCondQueryWrapper(){
		/* Condition Wrapper */
		String symbol = "";
		QueryWrapper<stockDayK> queryWrapper = new QueryWrapper<>();
		// 方式1:
		// queryWrapper.eq(StringUtils.isNotBlank(symbol), "symbol", symbol);
		// 方式2:
		if (StringUtils.isNotBlank(symbol)){
			queryWrapper.eq("symbol", symbol);
		}
	}

	@Test
	void testObjectWrapper(){
		/* Object Wrapper */
		stockDayK obj = stockDayK.builder().symbol("000001.SZ")
				.tradeDate(LocalDate.of(2024,1,1)).build();
		QueryWrapper<stockDayK> queryWrapper = new QueryWrapper<>(obj);
		List<stockDayK> objList = stockDayKMapper.selectList(queryWrapper);
		System.out.println(objList.size());
	}


	@Test
	void testUpdateWrapper(){
		UpdateWrapper<stockDayK> updateWrapper = new UpdateWrapper<>();
		updateWrapper.ge("trade_date", LocalDate.of(2024, 1, 1));
		updateWrapper.likeLeft("symbol", ".SZ");
		updateWrapper.set("symbol", "000001.SSZ");
		var count = stockDayKMapper.update(null, updateWrapper);
	}
}

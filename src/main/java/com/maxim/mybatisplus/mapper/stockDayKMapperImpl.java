package com.maxim.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.maxim.mybatisplus.Response;
import com.maxim.mybatisplus.converter.stockDayKConverter;
import com.maxim.mybatisplus.entity.stockDayK;
import com.maxim.mybatisplus.entity.stockDayKDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
//@Slf4j
public class stockDayKMapperImpl{
    @Autowired
    private stockDayKMapper stockDayKMapper;

    public Response<String> insertObj(){
        stockDayK obj = stockDayK.builder()
                .symbol("000001.SZ").tradeDate(LocalDate.now())
                .open(10.0).high(10.0).low(10.0).amount(10.0)
                .close(10.0).preClose(10.0).chgAmount(10.0).chgRate(10.0)
                .volume(Long.parseLong("10")).turnoverRate(10.0).pe(10.0).pb(10.0).mv(10.0).cmv(10.0)
                .createTime(LocalDateTime.now()).updateTime(LocalDateTime.now())
                .build();
        stockDayKMapper.insert(obj);
        return Response.newSuccess("Success");
    }

    public Response<stockDayKDTO> selectById(Long id){
        /* 先需要检查是否在数据库中 */
        QueryWrapper<stockDayK> queryWrapper = new QueryWrapper<>();
        if (!stockDayKMapper.exists(queryWrapper.eq("id", id))){
            return Response.newEmpty("Not Found");
        }
        stockDayK obj = stockDayKMapper.selectById(id);
        return Response.newSuccess(stockDayKConverter.toDTO(obj));
    }

    public Response<String> deleteById(Long id){
        /* 先需要检查是否在数据库中 */
        QueryWrapper<stockDayK> queryWrapper = new QueryWrapper<>();
        if (!stockDayKMapper.exists(queryWrapper.eq("id", id))){
            return Response.newEmpty("Not Found");
        }
        stockDayKMapper.deleteById(id);
        return Response.newSuccess("Success");
    }

    /* MyBatis 进阶查询 */
    public Response<List<stockDayKDTO>> selectByWrappers(){
        /*
        * 1. 多个QueryWrapper条件相叠加 +
        * 2. 只取部分列
        */
        QueryWrapper<stockDayK> queryWrapper = new QueryWrapper<>();
        QueryWrapper<stockDayK> queryWrapper_ = queryWrapper.eq("trade_date",
                        LocalDate.of(2024,1,1))
                .like("symbol",".SZ"); // .first(true,"symbol");
        List<String> colList = List.of("symbol","trade_date","open_price","high_price","low_price","close_price");
        queryWrapper_.select(colList); // 加入了列的筛选
        List<stockDayK> objList = stockDayKMapper.selectList(queryWrapper_);
        return Response.newSuccess(objList.stream().map(
                obj -> stockDayKConverter.toDTO(obj)
        ).toList());
    }

    public Response<List<stockDayKDTO>> selectByCondition(){
        /* 类似于JAP的动态查询
        * 如: 判断这个对象的A字段是否为空, 若不为空, 则加入 where 条件进行过查询
        * */
        QueryWrapper<stockDayK> queryWrapper = new QueryWrapper<>();
        String symbol = "";
        // 方式1:
        // queryWrapper.eq(StringUtils.isNotBlank(symbol), "symbol", symbol);
        // 方式2:
        if (StringUtils.isNotBlank(symbol)){
            queryWrapper.eq("symbol", symbol);
        }
        List<stockDayK> objList = stockDayKMapper.selectList(queryWrapper);
        return Response.newSuccess(objList.stream().map(
                stockDayKConverter::toDTO
        ).toList());

    }

    public Response<List<stockDayKDTO>> selectByEntity(){
        /* 根据这个对象所有的非空属性作为where条件进行查询
        * */
        stockDayK obj = stockDayK.builder().symbol("000001.SZ")
                .tradeDate(LocalDate.of(2024,1,1)).build();
        QueryWrapper<stockDayK> queryWrapper = new QueryWrapper<>(obj);
        List<stockDayK> objList = stockDayKMapper.selectList(queryWrapper);
        return Response.newSuccess(objList.stream().map(
                stockDayKConverter::toDTO
        ).toList());
    }

    public Response<List<stockDayKDTO>> selectByAllEq(){
        /* AllEq 表达式进行查询
        * 传入Map<String, Object> -> 所有属性进行where条件查询
        * */
        QueryWrapper<stockDayK> queryWrapper = new QueryWrapper<>();
        Map<String, Object> map = new HashMap<>(){};
        map.put("symbol", "000001.SZ");
        map.put("trade_date", LocalDate.of(2024,1,1));
        List<stockDayK> objList = stockDayKMapper.selectList(queryWrapper.allEq(map));
        return Response.newSuccess(objList.stream().map(
                stockDayKConverter::toDTO
        ).toList());
    }

    public Response<List<stockDayKDTO>> selectByLambda() {
        /*
        * Lambda 表达式进行查询 -> ::getter + 链式调用
        * */
        LambdaQueryWrapper<stockDayK> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(stockDayK::getSymbol, "000001.SZ")
                .between(stockDayK::getTradeDate, LocalDate.of(2024,1,1),
                        LocalDate.of(2025,1,1));
        List<stockDayK> objList = stockDayKMapper.selectList(queryWrapper);
        return Response.newSuccess(objList.stream().map(
                stockDayKConverter::toDTO
        ).toList());
    }

    public Response<List<stockDayKDTO>> selectBySQL(String tradeDate){
        /* 使用Mapper层的@Select (类似Spring Data JPA 对方法注册SQL)*/
        List<stockDayK> objList = stockDayKMapper.selectByDate(tradeDate);
        return Response.newSuccess(objList.stream().map(
                stockDayKConverter::toDTO).toList());
    }

    public Response<stockDayKDTO> insertObj(String symbol, String tradeDate, Double open, Double high, Double low, Double close,
                                            Double preClose, Double chgAmount, Double chgRate, Long volume, Double amount,
                                            Double turnoverRate, Double pe, Double pb, Double mv, Double cmv) {
        /* 先检查 symbol - tradeDate 这个对象是否在数据库中 */
        LocalDate date = LocalDate.parse(tradeDate);
        stockDayKDTO obj = new stockDayKDTO(symbol, date, open, high, low, close, preClose, chgAmount,
                chgRate, volume, amount, turnoverRate, pe, pb, mv, cmv);
        QueryWrapper<stockDayK> queryWrapper = new QueryWrapper<>();
        if (stockDayKMapper.exists(queryWrapper.eq("symbol", symbol).eq("trade_date", date))){
            return Response.newEmpty("Already Exists");
        }
        /* 若不在这个数据库中, 则进行插入 */
        stockDayKMapper.insert(stockDayKConverter.toDAO(obj));
        return Response.newSuccess(obj);
    }

    public Response<stockDayKDTO> updateObj(String symbol, String tradeDate, Double open, Double high, Double low, Double close,
                                            Double preClose, Double chgAmount, Double chgRate, Long volume, Double amount,
                                            Double turnoverRate, Double pe, Double pb, Double mv, Double cmv){
        /* 先检查 symbol - tradeDate 这个对象是否在数据库中 */
        LocalDate date = LocalDate.parse(tradeDate);
        stockDayKDTO obj = new stockDayKDTO(symbol, date, open, high, low, close, preClose, chgAmount,
                chgRate, volume, amount, turnoverRate, pe, pb, mv, cmv);
        QueryWrapper<stockDayK> queryWrapper = new QueryWrapper<>();
        if (!stockDayKMapper.exists(queryWrapper.eq("symbol", symbol).eq("trade_date", date))){
            return Response.newEmpty("Not Found");
        }
        /* 若在这个数据库中, 则进行更新 */
        long count = stockDayKMapper.update(stockDayKConverter.toDAO(obj),
                queryWrapper.eq("symbol", symbol).eq("trade_date", date));
        return Response.newSuccess(obj);
    }

    /* 分页查询 */
    public Response<List<stockDayKDTO>> selectByPage(Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<stockDayK> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.gt(stockDayK::getChgRate, 0.0)
                .eq(stockDayK::getTradeDate, LocalDate.of(2024,1,1));
        Page<stockDayK> page = new Page<>(pageNum, pageSize);
        List<stockDayK> objList = stockDayKMapper.selectPage(page, queryWrapper)
                .getRecords();
        long total = page.getTotal(); // 返回查询总数量
        return Response.newSuccess(objList.stream().map(
                stockDayKConverter::toDTO
        ).toList());
    }

    /* 乐观锁 */
    @Transactional // 添加事务注解 -> 表示下述函数始终是在一个事务中执行（原子性）
    public Response<String> concurrentUpdate(){
        List<stockDayK> objList = stockDayKMapper.selectByDate("2024-01-01");
        var obj1 = objList.get(0);
        var obj2 = objList.get(0);
        obj1.setChgRate(obj1.getChgRate() == null? 0.0 : obj1.getChgRate() + 1);
        obj2.setChgRate(obj2.getChgRate() == null? 0.0 : obj2.getChgRate() + 1);
        stockDayKMapper.updateById(obj1); // 结果: 插入成功
        int count = stockDayKMapper.updateById(obj2); // 结果: 更新失败（obj2中仍然是旧版本号, 而数据库中已经是新版本号）
        if (count == 0){
            return Response.newEmpty("更新失败");
        }
        return Response.newSuccess("更新成功");
    }
}

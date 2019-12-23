package com.ningyv.smallcat.handler;

import com.netflix.discovery.converters.Auto;
import com.ningyv.smallcat.resultEntity.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author LCX
 * @create 2019-12-22 15:23
 */
@RestController  //控制器
public class RedisOpertionHandler {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/redis/remote/set/key/value")
    public ResultEntity<String> setKeyValue(@RequestParam("key") String key, @RequestParam("value") String value){
        try {
            stringRedisTemplate.opsForValue().set(key,value);
            return ResultEntity.successWithoutData();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
        }
    };

    @RequestMapping("/redis/remote/set/key/value/time/out")
    public ResultEntity<String> setKeyValueTimeOut(@RequestParam("key") String key,
                                                   @RequestParam("value") String value,
                                                   @RequestParam("timeUnit")TimeUnit timeUnit,
                                                   @RequestParam("timeOut") Integer timeOut){
        try {
            stringRedisTemplate.opsForValue().set(key,value,timeOut,timeUnit);
            return ResultEntity.successWithoutData();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
        }
    };

    @RequestMapping("/redis/get/value/by/key")
    public ResultEntity<String> getValueByKey(@RequestParam("key")String key){
        try {
            String value = stringRedisTemplate.opsForValue().get(key);
            return ResultEntity.successWithData(value);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
        }
    };


    @RequestMapping("/redis/remove/by/key")
    public ResultEntity<String> reMoveByKey(@RequestParam("key")String key){
        try {
            stringRedisTemplate.delete(key);
            return ResultEntity.successWithoutData();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultEntity.failed(e.getMessage());
        }
    };
}

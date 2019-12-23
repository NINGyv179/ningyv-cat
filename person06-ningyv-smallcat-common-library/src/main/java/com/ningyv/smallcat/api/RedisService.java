package com.ningyv.smallcat.api;

import com.ningyv.smallcat.resultEntity.ResultEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.concurrent.TimeUnit;

/**
 * @author LCX
 * @create 2019-12-22 15:12
 */
@FeignClient("redis-provider")
public interface RedisService {

    @RequestMapping("/redis/remote/set/key/value")
    public ResultEntity<String> setKeyValue(@RequestParam("key") String key, @RequestParam("value") String value);

    @RequestMapping("/redis/remote/set/key/value/time/out")
    public ResultEntity<String> setKeyValueTimeOut(@RequestParam("key") String key,
                                                   @RequestParam("value") String value,
                                                   @RequestParam("timeUnit") TimeUnit timeUnit,
                                                   @RequestParam("timeOut") Integer timeOut);

        @RequestMapping("/redis/get/value/by/key")
    public ResultEntity<String> getValueByKey(@RequestParam("key") String key);


    @RequestMapping("/redis/remove/by/key")
    public ResultEntity<String> reMoveByKey(@RequestParam("key") String key);


}

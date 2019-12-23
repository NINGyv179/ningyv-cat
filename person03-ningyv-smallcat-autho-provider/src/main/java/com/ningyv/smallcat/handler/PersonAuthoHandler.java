package com.ningyv.smallcat.handler;

import com.ningyv.smallcat.api.MysqlService;
import com.ningyv.smallcat.api.RedisService;
import com.ningyv.smallcat.constant.Constant;
import com.ningyv.smallcat.entity.vo.MemberVo;
import com.ningyv.smallcat.resultEntity.ResultEntity;
import com.ningyv.smallcat.utils.CrowdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author LCX
 * @create 2019-12-22 20:55
 */
@RestController
public class PersonAuthoHandler {

    @Autowired
    private MysqlService mysqlService;

    @Autowired
    private RedisService redisService;

    @RequestMapping("/send/message/to/redis")
    public ResultEntity<String> sendMessage(@RequestParam("phoneNum") String phoneNum){
        //判断字符串是否有效
        if (!CrowdUtils.stringEffectCheck(phoneNum)){
            return ResultEntity.failed(Constant.MSG_STRING_ERROR);
        }

        //获取随机验证码，长度是4
        int length = 4;
        String randNum = CrowdUtils.getRandNum(length);

        //发短信
        CrowdUtils.sendRandMessage(phoneNum,randNum);

        //存储到redis
        //存到redis的key
        String key = Constant.PREFIX_RANDOM_KEY+phoneNum;
        //过期时间
        Integer time = 15;
        //执行存储
        ResultEntity<String> resultEntity = redisService.setKeyValueTimeOut(key, randNum, TimeUnit.MINUTES, time);

        //返回结果
        return resultEntity;
    }

    @RequestMapping("/do/member/to/register")  //传入一个VO对象   注册有账号 密码 手机号 验证码
    public ResultEntity<String> doRegister( MemberVo memberVo){
        System.err.println("!!!!!!!!!!!!!!!!!!!1");

        //判断输入的验证码是否有效
        String randomCode = memberVo.getRandomCode();
        if (!CrowdUtils.stringEffectCheck(randomCode)){
            return ResultEntity.failed(Constant.MSG_STRING_RANDOM_ERROR);
        }

        //判断输入的手机号是否有效
        String phoneNum = memberVo.getPhoneNum();
        if (!CrowdUtils.stringEffectCheck(phoneNum)){
            return ResultEntity.failed(Constant.MSG_STRING_NUMBER_ERROR);
        }

        //为了在redis里面查找到验证码，所以要拼接key
        String key = Constant.PREFIX_RANDOM_KEY+phoneNum;
        //然后调用redis方法根据keu获取值
        ResultEntity<String> getValueresultEntity = redisService.getValueByKey(key);

        //如果获取失败
        if (ResultEntity.FAILED.equals(getValueresultEntity.getResult())){
            return getValueresultEntity;
        }

        //成功了就获取redis里面的验证码,有可能是空值
        String resultData = getValueresultEntity.getData();

        if (resultData==null){
            return ResultEntity.failed(Constant.MSG_STRING_NULL_RANDOM);
        }
        //跟用户输入的而验证码比较,如果不一致
        if (!Objects.equals(randomCode,resultData)){
            return ResultEntity.failed(Constant.MSG_STRING_ERROR_RANDOM);
        }

        //删除redis的验证码，一次性的
        ResultEntity<String> deleteKey = redisService.reMoveByKey(key);

        if (ResultEntity.FAILED.equals(deleteKey.getResult())){
            return deleteKey;
        }

        //获取用户输入的账号
        String loginacct = memberVo.getLoginacct();
        //查一下在数据库中有没有
        ResultEntity<Integer> loginacctCount = mysqlService.getLoginacctCount(loginacct);
        if (ResultEntity.FAILED.equals(loginacctCount.getResult())){
            return ResultEntity.failed(loginacctCount.getMessage());
        }

        //获取查到的数量
        Integer data = loginacctCount.getData();
        //账号被占用
        if (data>0){
            return ResultEntity.failed(Constant.MSG_STRING_MORE_MEMBER);
        }

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        String userpswd = memberVo.getUserpswd();
        if (!CrowdUtils.stringEffectCheck(userpswd)){
            return ResultEntity.failed(Constant.MSG_STRING_PAWD_ERROR);
        }

        userpswd = bCryptPasswordEncoder.encode(userpswd);

        memberVo.setUserpswd(userpswd);
        //执行保存
        return mysqlService.saveMember(memberVo);
    }

}

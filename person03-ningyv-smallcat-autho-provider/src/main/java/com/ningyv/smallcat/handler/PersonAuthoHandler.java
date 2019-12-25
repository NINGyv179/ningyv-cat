package com.ningyv.smallcat.handler;

import com.ningyv.smallcat.api.MysqlService;
import com.ningyv.smallcat.api.RedisService;
import com.ningyv.smallcat.constant.Constant;
import com.ningyv.smallcat.entity.po.MenberPo;
import com.ningyv.smallcat.entity.vo.MemberLoginVo;
import com.ningyv.smallcat.entity.vo.MemberSuccessVo;
import com.ningyv.smallcat.entity.vo.MemberVo;
import com.ningyv.smallcat.resultEntity.ResultEntity;
import com.ningyv.smallcat.utils.CrowdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.UUID;
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

       ResultEntity<Integer> resultEntityPhoneNum = mysqlService.selectPhoneNum(phoneNum);

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

    //注册
    @RequestMapping("/do/member/to/register")  //传入一个VO对象   注册有账号 密码 手机号 验证码
    public ResultEntity<String> doRegister(@RequestBody MemberVo memberVo){

        System.err.println(memberVo);

        //判断输入的验证码是否有效
        String randomCode = memberVo.getRandomCode();
        if (!CrowdUtils.stringEffectCheck(randomCode)){
            return ResultEntity.failed(Constant.MSG_STRING_RANDOM_ERROR);
        }

        //判断输入的手机号是否有效
        String phoneNum = memberVo.getPhonenum();
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

        //检查手机号是否被占用
        ResultEntity<Integer> num = mysqlService.selectPhoneNum(phoneNum);

        if (ResultEntity.FAILED.equals(num.getResult())){
            return ResultEntity.failed(num.getMessage());
        }
        Integer numCount = num.getData();

        if (numCount>0){
            return ResultEntity.failed(Constant.MSG_STRING_MORE_PHONENUM);
        }

        //执行保存
        return mysqlService.saveMember(memberVo);
    }


    //登录
    @RequestMapping("/member/do/login")
    public ResultEntity<MemberSuccessVo> doLogin(@RequestBody MemberLoginVo memberLoginVo){
        //1.验证用户名密码是否正确
        ResultEntity<MenberPo> resultEntity = mysqlService.getMemberPOByFormVO(memberLoginVo);
        //判断操作是否成功
        String result = resultEntity.getResult();
        if (ResultEntity.FAILED.equals(result)) {
            return ResultEntity.failed(resultEntity.getMessage());
        }
        //获取表单提交的数据封装成MemberPo
        MenberPo memberPo = resultEntity.getData();

        //如果数据库里面时空的
        if (memberPo ==null) {
            return ResultEntity.failed(Constant.MSG_LOGIN_FAILED);
        }

        //生成token
        String tokenKey=Constant.PREFIX_MEMBER_LOGIN_TOKEN+UUID.randomUUID()
                .toString().replaceAll("-", "");
        String tokenValue = memberPo.getId()+"";

        //3存入redis
        int timeout = 30;
        ResultEntity<String> setKeyValueTimeOut = redisService.setKeyValueTimeOut(tokenKey, tokenValue, TimeUnit.MINUTES, timeout);

        if (ResultEntity.FAILED.equals(setKeyValueTimeOut.getResult())) {
            return ResultEntity.failed(setKeyValueTimeOut.getMessage());
        }

        //封装MemberSuccessVo
        MemberSuccessVo memberSuccessVo = new MemberSuccessVo();

        memberSuccessVo.setToken(tokenKey);
        memberSuccessVo.setUsername(memberPo.getUsername());
        memberSuccessVo.setLoginacct(memberPo.getLoginacct());

        return ResultEntity.successWithData(memberSuccessVo);
    }

}

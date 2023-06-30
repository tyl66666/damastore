package com.mi.personel.controller;


import com.mi.personel.biz.MiInformationBiz;
import com.mis.bean.Memberinfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("miInformation")
public class MiInformationController {

    @Autowired
    private MiInformationBiz miInformationBiz;

    @Autowired
    private ApplicationContext applicationContext;

    //显示用户信息
    @RequestMapping("showUserInfo")
    public Map<String, Object> showUserInfo(@RequestParam Integer mno) {
        Map<String, Object> map = new HashMap<>();
        Memberinfo memberinfo = null;
        try {
            memberinfo = miInformationBiz.getInfo(mno);
        } catch (Exception e) {
            map.put("code", 0);
            map.put("msg", e.getCause());
            return map;
        }
        map.put("code", 1);
        map.put("data", memberinfo);
        return map;
    }

    //获取验证码
    @RequestMapping("getYZM")
    public Map<String, Object> getYZM(@RequestParam Integer mno,
                                      @RequestParam String email) {
        //log.info(email);
        Map<String, Object> map = new HashMap<>();
        if (email == null) {
            map.put("code", 0);
            //log.info("传入的email是空");
            return map;
        }
        Memberinfo memberinfo = this.miInformationBiz.getInfo(mno);
        String UserMail = memberinfo.getEmail();
        //log.info(UserMail);
        if (!(UserMail.trim().equals(email.trim()))) {//如果用户写的邮箱和数据库里的不一致
            map.put("code", 0);
            //log.info("两个值不相等");
            return map;
        }
        String YZM = this.miInformationBiz.getYZM(email);
        map.put("code", 1);
        map.put("data", YZM);

        return map;
    }

    //修改信息
    @RequestMapping("modify")
    public Map<String, Object> modify(@RequestParam Integer mno,
                                      @RequestParam String nickname,
                                      @RequestParam String pwd,
                                      @RequestParam String tel) {
        Map<String, Object> map = new HashMap<>();

        int result = this.miInformationBiz.modify(mno, nickname, pwd, tel);
        if (result != 1) {
            map.put("code", 0);
            return map;
        }
        map.put("code", 1);
        return map;
    }

    //修改头像
    @PostMapping("updatePhoto")
    public Map<String, Object> updatePhoto(@RequestParam Integer mno,
                                           @RequestPart MultipartFile photo) throws IOException {
        Map<String, Object> map = new HashMap<>();
        if (photo == null) {
            map.put("code", 0);
            return map;
        }
        int result = this.miInformationBiz.updatePhoto(mno, photo);
        if (result != 1) {
            map.put("code", 0);
            return map;
        }
        map.put("code", 1);
        return map;
    }
}

package com.mis.miSecurity.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mis.bean.Admininfo;
import com.mis.bean.Memberinfo;
import com.mis.miSecurity.dao.AdminDao;
import com.mis.miSecurity.dao.MiuserDao;
import com.mis.miSecurity.util.JWTUtils;
import com.mis.miSecurity.util.Md5;
import com.mis.miSecurity.util.QQUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class UserController {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private MiuserDao userDao;
    @Autowired
    private AdminDao adminDao;
    @Autowired
    private Memberinfo memberinfo;

    private int expireTime = 3600;

    @RequestMapping("/checkLogin.action")
    public Map checkLogin(@RequestHeader String token) throws ServletException, IOException {
        log.info("待检测的token为:" + token);
        Map map = new HashMap();
        if ("".equals(token)) {
            map.put("code", 0);
            return map;
        }
        boolean flag = this.redisTemplate.hasKey(token);
        if (flag) {
            map.put("code", 1);
            String t = (String) this.redisTemplate.opsForHash().get(token, "token");
            Map<String, Object> info = JWTUtils.getTokenInfo(t);
            map.put("data", info);
        } else {
            map.put("code", 0);
        }
        return map;
    }

    @RequestMapping("/logout")
    public Map logout(@RequestHeader String token) throws ServletException, IOException {
        this.redisTemplate.delete(token);
        Map map = new HashMap();
        map.put("code", 1);
        return map;
    }

    @RequestMapping("/login.action")
    public Map login(@RequestParam String nickname,
                     @RequestParam String pwd,
                     @RequestParam String valcode,
                     HttpSession session) throws ServletException, IOException {
        Map map = new HashMap();
        //用户输入的验证码
//        String valcode = request.getParameter("valcode");
        //取出标准验证码
        //session.setAttribute("code", 123);
        String code = session.getAttribute("code").toString();
        //验证码判断
        if (valcode == null || valcode.isEmpty()) {
            map.put("code", 0);
            map.put("msg", "请输入验证码");
            return map;
        }
        if (!code.equals(valcode)) {
            map.put("code", 0);
            map.put("msg", "验证码错误");
            return map;
        }
        QueryWrapper qw = new QueryWrapper();
        qw.eq("nickname", nickname);
        //qw.eq("pwd", DigestUtils.md5DigestAsHex(pwd.getBytes()));
        qw.eq("pwd", pwd);
        Memberinfo user = userDao.selectOne(qw);
        if (user != null) {
            map.put("code", 1);
            Map m = new HashMap();
            m.put("username", nickname);
            m.put("userid", user.getMno().toString());
            String token = JWTUtils.creatToken(m, expireTime);

            Map obj = new HashMap<String, Object>();
            obj.put("token", token);
            obj.put("username", nickname);
            obj.put("mno", user.getMno());

            map.put("data", obj);

            redisTemplate.opsForHash().put(token, token, "");
            redisTemplate.expire(token, expireTime, TimeUnit.SECONDS);
            return map;
        }
        map.put("code", 0);
        map.put("msg", "查无此用户名和密码");
        return map;
    }

    @RequestMapping("/admin.action")
    public Map login(Admininfo admin, HttpServletRequest request) {
        Map map = new HashMap();
        QueryWrapper qw = new QueryWrapper();
        qw.eq("aname", admin.getAname());
        qw.eq("pwd", DigestUtils.md5DigestAsHex(admin.getPwd().getBytes()));
        Admininfo one = adminDao.selectOne(qw);
        if (one != null) {
            map.put("code", 1);
            Map m = new HashMap();
            m.put("aname", admin.getAname());
            m.put("aid", admin.getAid());
            String token = JWTUtils.creatToken(m, expireTime);

            Map obj = new HashMap<String, Object>();
            obj.put("token", token);
            obj.put("aname", admin.getAname());

            map.put("data", obj);  // data:{admintoken: xxx, aname:xxx}

            redisTemplate.opsForHash().put(token, "token", token);
            redisTemplate.expire(token, expireTime, TimeUnit.SECONDS);

            return map;
        }
        map.put("code", 0);
        map.put("msg", "查无此用户名和密码");
        return map;
    }

    //op=checkEmail
    @RequestMapping("/checkEmail")
    protected Map checkEmail(@RequestParam String nickname,
                             @RequestParam String email) throws ServletException, IOException {
        Map map = new HashMap();
        //String nickName = request.getParameter("nickName");
        if (nickname == null || nickname.isEmpty()) {
            map.put("code", 0);
            map.put("msg", "用户名为空");
            return map;
        }

        try {
            //User user = super.parseRequestToT(request, User.class);

            //查询数据库
            QueryWrapper<Memberinfo> wrapper = new QueryWrapper<>();
            wrapper.eq("nickName", nickname);
            wrapper.eq("email", email);
            Memberinfo rs = userDao.selectOne(wrapper);

            if (rs != null) {
                map.put("code", 1);
                //map.put("data", rs);
                map.put("msg", "该邮箱已被注册");
            } else {
                map.put("code", 0);
                map.put("msg", "用户名或密码填写错误");
            }

        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", 0);
            map.put("msg", "出错了");
        }
        return map;
    }


    @RequestMapping("/findPwd")//找回密码
    protected Map findPwd(@RequestParam String email,
                          @RequestParam String password,
                          @RequestParam String password2) throws ServletException, IOException {
        Map map = new HashMap();

        if (password.length() < 6) {
            map.put("code", 0);
            map.put("msg", "密码不能小于6位");
            return map;
        }

        if (password == null || password == "" && password2 == null || password2 == "") {
            map.put("code", 0);
            map.put("msg", "密码不能为空");
            return map;
        }

        if (!password.equals(password2)) {
            map.put("code", 0);
            map.put("msg", "两次密码填写不一致");
            //super.writeJson(jm, response);
            return map;
        }

        try {
//            UserService userService = SpringContextUtil.getBean(UserService.class);
//            boolean success = userService.updatePasswordByEmail(email, password);
//            if (success) {
//                jm.setCode(1);
//                jm.setMsg("修改成功");
//            } else {
//                jm.setCode(3);
//                jm.setMsg("修改失败");
//            }
        } catch (Exception e) {
            map.put("code", 0);
            map.put("msg", "修改失败");
            //super.writeJson(jm, response);
            return map;
        }

        map.put("code", 1);
        map.put("msg", "密码修改成功！");
        //super.writeJson(jm, response);
        return map;
    }


    @RequestMapping("/register")
    protected Map register(@RequestParam String nickname,
                           @RequestParam String pwd,
                           @RequestParam String tel,
                           @RequestParam String email,
                           @RequestParam String code) throws ServletException, IOException {
        Map map = new HashMap();

        memberinfo.setNickname(nickname);
        memberinfo.setPwd(pwd);
        memberinfo.setTel(tel);
        memberinfo.setEmail(email);
        try {
            String c = QQUtil.QQmail;
            log.info("验证码为" + c + "输入值为:" + code);
            if (!code.equals(c)) {
                map.put("code", 0);
                map.put("msg", "验证码错误");
                return map;
            }
            QueryWrapper<Memberinfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("tel", memberinfo.getTel());
            List<Memberinfo> list = (List<Memberinfo>) userDao.selectOne(queryWrapper);
            if (list != null && list.size() > 0) {
                map.put("code", 0);
                map.put("msg", "该手机号已注册！！");
                return map;
            }
            QueryWrapper<Memberinfo> queryWrapper2 = new QueryWrapper<>();
            queryWrapper2.eq("email", memberinfo.getEmail());
            List<Memberinfo> list2 = (List<Memberinfo>) userDao.selectOne(queryWrapper);
            if (list2 != null && list2.size() > 0) {
                map.put("code", 0);
                map.put("msg", "该邮箱已注册！！");
                return map;
            }
            //查询数据库
            //memberinfo.setPwd(Md5.MD5Encode(memberinfo.getPwd(), "utf-8"));用户密码加密
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = now.format(formatter);
            memberinfo.setRegdate(formattedDateTime);
            memberinfo.setStatus(1);
            int result = userDao.insert(memberinfo);
            //String sql = "insert into memberinfo(nickName,realName,pwd,tel,email,photo,regDate,status) values(?,'',?,?,?,'',now(),1)";
            if (result != 0) {
                map.put("code", 1);
                map.put("msg", "注册成功");
                return map;
                //访问redis
            } else {
                map.put("code", 0);
                map.put("msg", "注册失败");
                return map;
            }
        } catch (Exception e) {
            map.put("code", 0);
            map.put("msg", e.getMessage());
            return map;

        }
    }
}

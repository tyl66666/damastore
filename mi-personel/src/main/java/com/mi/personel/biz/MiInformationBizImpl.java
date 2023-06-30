package com.mi.personel.biz;


import com.mi.personel.dao.MiInformationDao;
import com.mi.personel.util.QQUtil;
import com.mis.bean.Memberinfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


@Service
@Transactional(propagation = Propagation.SUPPORTS,
        isolation = Isolation.DEFAULT, timeout = 2000,
        readOnly = true, rollbackFor = RuntimeException.class
)
@Slf4j
public class MiInformationBizImpl implements MiInformationBiz {

    @Autowired
    private MiInformationDao miInformationDao;

    //获取mno用户的信息
    @Override
    public Memberinfo getInfo(Integer mno) {
        return this.miInformationDao.selectById(mno);
    }

    //获取验证码
    @Override
    public String getYZM(String email) {
        String YZM = null;
        try {
            QQUtil.sendCode(email);
            YZM = QQUtil.QQmail;
            log.info(YZM);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return YZM;
    }

    //修改用户信息
    @Override
    public int modify(Integer mno, String nickname, String pwd, String tel) {
        Memberinfo memberinfo = new Memberinfo();
        memberinfo.setMno(mno);
        memberinfo.setNickname(nickname);
        memberinfo.setPwd(pwd);
        memberinfo.setTel(tel);
        this.miInformationDao.updateById(memberinfo);
        return 1;
    }

    //修改头像
    @Override
    public int updatePhoto(Integer mno, MultipartFile photo) {
        Memberinfo memberinfo = new Memberinfo();
        memberinfo.setMno(mno);
        memberinfo.setPhoto(photo.getOriginalFilename());
        int result = this.miInformationDao.updateById(memberinfo);
        if (result < 0) {
            throw new RuntimeException("修改头像失败");
        }
        //项目绝对路径
        String absolutePath = new File("").getAbsolutePath();
        log.info("absolutePath:" + absolutePath);
        // 保存到项目中的文件夹中
        //String path = absolutePath + "mi-index/src/main/resources/static/images/upload";
        File localFile = new File(absolutePath + "/mi-index/src/main/resources/static/images" + File.separator + memberinfo.getPhoto());
        try {
            photo.transferTo(localFile);
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }

        return 1;
    }
}

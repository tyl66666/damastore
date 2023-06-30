package com.mi.personel.biz;

import com.mis.bean.Memberinfo;
import org.springframework.web.multipart.MultipartFile;


public interface MiInformationBiz {
    public Memberinfo getInfo(Integer mno);

    public String getYZM(String email);

    public int modify(Integer mno, String nickname, String pwd, String tel);

    public int updatePhoto(Integer mno, MultipartFile photo);

//    public boolean checkImageExists(String imageName);
}

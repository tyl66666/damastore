package com.mis.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @program: bbs
 * @description:
 * @author: 作者
 * @create: 2022-10-30 15:36
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Memberinfo implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer mno;
    private String  nickname;
    private String  pwd;
    private String tel;
    private String  email;
    private String  photo;
    private String regdate;
    private Integer status;

}

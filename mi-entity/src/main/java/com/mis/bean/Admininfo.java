package com.mis.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admininfo {
    @TableId(type = IdType.AUTO)
    private Integer aid;

    private String aname;
    private String pwd;
    private String tel;
    private Integer status;
}

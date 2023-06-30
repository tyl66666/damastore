package com.mis.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Addrinfo {

    @TableId(type = IdType.AUTO)
    private Integer ano;

    private Integer mno;
    private String name;
    private String tel;
    private String province;
    private String city;
    private String area;
    private String addr;
    private Integer flag;
    private Integer status;
}

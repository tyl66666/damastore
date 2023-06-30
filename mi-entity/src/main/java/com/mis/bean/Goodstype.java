package com.mis.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Goodstype {
    @TableId(type = IdType.AUTO)
    private Integer tno;

    private String tname;
    private Integer status;
}

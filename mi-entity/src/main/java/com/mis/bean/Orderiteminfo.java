package com.mis.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orderiteminfo {

    @TableId(type = IdType.AUTO)
    private Integer ino; /*物品号*/

    private String ono; /*物品号*/
    private Integer gno; /*商品编号*/
    private Integer nums; /*数量*/
    private String price; /*价格*/
    private Integer status;
}

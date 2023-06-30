package com.mis.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orderinfo {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String ono; /*订单编号*/

    private String odate; /*订单时间*/
    private String ano; /*地址编号*/
    private String sdate;
    private String rdate;
    private Integer status;
    private String price; /*价格*/
    private Integer invoice;
}

package com.mis.miOrder.biz;


import com.mis.bean.Addrinfo;
import com.mis.bean.Memberinfo;
import com.mis.bean.Orderinfo;
import com.mis.bean.Orderiteminfo;

import com.mis.miOrder.dao.MiOrderInfoDao;
import com.mis.miOrder.dao.MiOrderItemInfoDao;
import com.mis.miOrder.model.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

/**
 * 这是点击下单之后操作数据的biz类
 */
@Service
public class MiOrderInfoBizImpl implements MiOrderInfoBiz {
    @Autowired
    private MiOrderInfoDao miOrderInfoDao;
    @Autowired
    private MiOrderItemInfoDao miOrderItemInfoDao;

    /*下单插入数据库*/
    @Override
    public int order(Integer ano, List<CartItem> cartItems) {
        // 创建LocalDateTime对象，表示当前日期和时间
        LocalDateTime now = LocalDateTime.now();
        // 创建DateTimeFormatter对象，指定格式为"yyyy-MM-dd HH:mm"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        Orderinfo orderinfo = new Orderinfo();
        orderinfo.setOdate(formatter.format(now));
        orderinfo.setAno(ano + "");
        orderinfo.setStatus(0);
        orderinfo.setInvoice(0);
        float price1 = 0;//总价
        for (CartItem ci1 : cartItems) {
            float price2 = Float.parseFloat(ci1.getGoodsinfo().getPrice()) * ci1.getNum();//单价*数量
            price1 += price2;
        }
        orderinfo.setPrice(price1 + "");
        int rows = this.miOrderInfoDao.insertOrderinfo(orderinfo);
        //获取到生成的ono 作为Orderiteminfo表中的一个列的值
        int orderId = Integer.parseInt(orderinfo.getOno());
        for (CartItem ci : cartItems) {
            Orderiteminfo orderiteminfo = new Orderiteminfo();
            // 将主键作为订单编号插入到Orderitem表中
            orderiteminfo.setOno(String.valueOf(orderId));
            orderiteminfo.setGno(ci.getGoodsinfo().getGno());
            orderiteminfo.setNums(ci.getNum());
            double price = Double.parseDouble(ci.getGoodsinfo().getPrice());
            Double sum = price * ci.getNum();//sum是单价*数量再传入数据库
            orderiteminfo.setPrice(sum.toString());
            orderiteminfo.setStatus(0);
            miOrderItemInfoDao.insert(orderiteminfo);

            miOrderInfoDao.updateGoodsNumByGno(ci.getGoodsinfo().getGno(), ci.getGoodsinfo().getBalance() - ci.getNum());
        }
        return orderId;
    }

    @Override
    public int confirmDelivery(Integer ono) {
        // 创建LocalDateTime对象，表示当前日期和时间
        LocalDateTime now = LocalDateTime.now();
        // 创建DateTimeFormatter对象，指定格式为"yyyy-MM-dd HH:mm"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String rdate = formatter.format(now);
        try {
            miOrderInfoDao.updateOrderStatusByOno(ono, 1, rdate);
            miOrderItemInfoDao.updateOrderItemStatusByOno(ono, 1);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }


}

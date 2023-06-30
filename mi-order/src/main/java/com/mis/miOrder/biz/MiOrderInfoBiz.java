package com.mis.miOrder.biz;


import com.mis.bean.Addrinfo;
import com.mis.bean.Memberinfo;
import com.mis.miOrder.model.CartItem;

import java.util.List;
import java.util.Set;

public interface MiOrderInfoBiz {
    public int order(Integer ano, List<CartItem> cartItems);

    public int confirmDelivery(Integer ono);
}

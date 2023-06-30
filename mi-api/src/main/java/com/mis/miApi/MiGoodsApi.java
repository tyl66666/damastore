package com.mis.miApi;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(value = "mi-goods", path = "miGoods")
public interface MiGoodsApi {

    @RequestMapping(value = "findAll")
    public Map<String, Object> findAllGoods();

    @RequestMapping("findById/{gno}")
    public Map<String, Object> findById(@PathVariable String fid);

    @RequestMapping("findByPage")
    public Map<String, Object> findByPage(@RequestParam int pageno,
                                          @RequestParam int pagesize,
                                          @RequestParam String sortby,
                                          @RequestParam String sort);

    @RequestMapping("findByTno")
    public Map<String, Object> findByTno(@RequestParam Integer tno,
                                         @RequestParam int pageno,
                                         @RequestParam int pagesize,
                                         @RequestParam String sortby,
                                         @RequestParam String sort);

    @RequestMapping("/findByIndex")
    public Map<String, Object> findByIndex(@RequestParam int pageno,
                                           @RequestParam int pagesize,
                                           @RequestParam String sortby,
                                           @RequestParam String sort,
                                           @RequestParam int sprice,
                                           @RequestParam int eprice,
                                           @RequestParam String gname,
                                           @RequestParam Integer tno);
}

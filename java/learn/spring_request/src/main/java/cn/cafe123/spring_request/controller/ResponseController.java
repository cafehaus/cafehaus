package cn.cafe123.spring_request.controller;

import cn.cafe123.spring_request.pojo.Address;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
// RestController 注解等于下面两个注解
//@Controller
//@ResponseBody
public class ResponseController {
    // 1、返回字符串
    @RequestMapping("/strAddr")
    public String strAddr() {
        return "广东深圳";
    }

    // 2、返回json
    @RequestMapping("/jsonAddr")
    public Address jsonAddr() {
        Address addr = new Address();
        addr.setProvince("广东");
        addr.setCity("深圳");
        return addr;
    }

    // 3、返回集合
    @RequestMapping("/listAddr")
    public List<Address> listAddr() {
        Address addr = new Address();
        addr.setProvince("广东");
        addr.setCity("深圳");

        Address addr2 = new Address();
        addr.setProvince("四川");
        addr.setCity("达州");

        List<Address> addrList = new ArrayList<>();
        addrList.add(addr);
        addrList.add(addr2);

        return addrList;
    }
}

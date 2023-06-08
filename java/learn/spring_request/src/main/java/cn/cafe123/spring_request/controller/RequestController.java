package cn.cafe123.spring_request.controller;

import cn.cafe123.spring_request.pojo.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * 测试请求参数
 */
@RestController
public class RequestController {
   // 1、get请求原始方式
   @RequestMapping("/simpleParam")
   public  String simpleParam(HttpServletRequest request) {
       String name = request.getParameter("name");
       String ageStr = request.getParameter("age");
       int age = Integer.parseInt(ageStr);
       System.out.println("name:" + name);
       System.out.println("age:" + age);
       return "名字是: " + name + ", 年龄: " + age;
   }

    // get请求spring方式
    @RequestMapping("/simpleParam2")
    public  String simpleParam2(String name, String age) {
        System.out.println("name:" + name);
        System.out.println("age:" + age);
        return "spring接收参数的名字是: " + name + ", 年龄: " + age;
    }

    // get请求spring方式：@RequestParam 注解重命名参数
    // @RequestParam 不设置 required 时默认是 true，不传的时候会报错
    // name/value、required、defaultValue 这3个参数，通过设置 name/value 可以设置参数别名
    @RequestMapping("/simpleParam3")
    public  String simpleParam3(@RequestParam(value = "username", required = false) String name, String age) {
        System.out.println("name:" + name);
        System.out.println("age:" + age);
        return "spring接收参数的名字是: " + name + ", 年龄: " + age;
    }

    // 2、get请求spring方式：实体参数
    // 复杂参数用 . 语法：?name=zhou&age=18&address.province=guangdong&address.city=shenzhen
    @RequestMapping("/simplePojo")
    public  String simplePojo(User user) {
        System.out.println(user);
        return "ok";
    }

    // 3、数组参数
    // ?hobby=eat&hobby=drink&hobby=play
    @RequestMapping("/arrayParam")
    public String arrayParam(String[] hobby) {
        System.out.println(Arrays.toString(hobby));
        return "ok";
    }

    // 4、集合参数
    // 注意需要加上 @RequestParam 注解
    // ?hobby=football&hobby=basketball&hobby=pingpang
    @RequestMapping("/listParam")
    public String listParam(@RequestParam List<String> hobby) {
        System.out.println(hobby);
        return "ok";
    }

    // 5、日期参数
    @RequestMapping("/dateParam")
    public LocalDateTime dateParam(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime updateTime) {
        System.out.println(updateTime);
        return updateTime;
    }

    // 6、json 参数
    @RequestMapping("/jsonParam")
    public String jsonParam(@RequestBody User user) {
        System.out.println(user);
       return "ok";
    }

    // 7、路径参数
    @RequestMapping("/pathParam/{id}")
    public String pathParam(@PathVariable String id) {
        System.out.println(id);
       return "ok: " + id;
    }
}

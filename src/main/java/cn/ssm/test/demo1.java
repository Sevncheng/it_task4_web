package cn.ssm.test;

import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.UUID;

public class demo1 {

    @Test
    public void tset11(){

     Long ss = new Date().getTime();
     String str = new Date().toString();
     System.out.println(ss);
     System.out.println(str);
    }
}

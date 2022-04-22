package com;

import javax.xml.soap.SAAJResult;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * @Author: Zero
 * @Date: 2022/4/22 08:54
 * @Description:
 */
public class Mythread implements Runnable {
    @Override
    public void run() {
    }
}
class Mythred2 implements Callable<String> {

    @Override
    public String call() throws Exception {
        return null;
    }
}
class Main {
    public static void main(String[] args) {
        System.out.println(Instant.now());
    }
}

package com.yumi.red.pack.auth;

import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class JwtTokenTest {


    @Test
    public void testVerify() {
        String token = "eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJqdGkiOiJjYjEwYzQ3YS1kYmJiLTQ2MTgtOTJiNS1kYjVkNDFiYjI3OGUiLCJzdWIiOiIxODYwNjA5MzI0NDE0OTI2ODUwIiwiaWF0IjoxNzMyNDQzNzU4fQ._bGOvMX7GdqZFx858sTxGQQSxiVVvTGVDw13-s3Xjv4";


        System.out.println(
                Jwts.parser()
                .setSigningKey("yumi123qqqqqqqqqqqqqqqqqqqqqqqqqqq1111111111111111111111111111dddddd")
                .build()
                .parse(token)
                        .getBody()

        );
    }



    @Test
    public void test() throws Exception {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                1, 2, 20, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1)
        );

        for (int i = 0; i < 2; i++) {
            threadPoolExecutor.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(20);
                    System.out.println("----");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        System.out.println(threadPoolExecutor.getActiveCount());

        threadPoolExecutor.shutdown();

        TimeUnit.SECONDS.sleep(200);
    }

}

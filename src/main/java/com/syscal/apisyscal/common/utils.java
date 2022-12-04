package com.syscal.apisyscal.common;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class utils {

    public Integer generateCode() {
        Random rand = new Random();
        String concat = ""+rand.nextInt(10)+rand.nextInt(10)+rand.nextInt(10)+rand.nextInt(10);
        return Integer.parseInt(concat);
    }

}

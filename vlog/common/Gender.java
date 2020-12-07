package com.southwind.vlog.common;

/**
 * @ClassName Gender
 * @Description TODO
 * @Author 86139
 * @Date 2020/12/5
 **/

public enum Gender {
    /**
     *
     */
    secret(0,"保密"),
    male(1,"男"),
    female(2,"女");
    public final Integer type;
    public final String value;

    Gender(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}

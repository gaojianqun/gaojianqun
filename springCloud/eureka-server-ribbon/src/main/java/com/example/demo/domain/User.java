package com.example.demo.domain;

/**
 * Created by gaojianqun on 2018/3/13.
 */
/**
 * 用户模型
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since TODO
 */
public class User {

    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
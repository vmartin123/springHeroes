package com.vm.heroes.model;

import lombok.Data;

@Data
public class Hero {

    private int id;
    private String name;

    public Hero() {
    }

    public Hero(int id, String name) {
        this.id = id;
        this.name = name;
    }

}

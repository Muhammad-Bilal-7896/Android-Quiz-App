package com.example.intermediatequizapp;

public class Chapter {
    public static final int  Measurements= 1;
    public static final int Vectors_and_Equilibrium= 2;
    public static final int  Motion_and_Force= 4;
    public static final int  Work_and_energy= 5;
    public static final int Circular_Motion= 6;
    public static final int Fluid_Dynamics= 7;
    public static final int  Oscillations= 8;
    public static final int Waves= 9;
    public static final int  Physical_Optics= 10;
    public static final int Heat_and_Thermodynamics = 11;

    private int id;
    private String name;


    public Chapter() {
    }

    public Chapter(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getName();
    }
}
package com.nokia.test.mobile.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DessertResponse {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("ppu")
    @Expose
    private Double ppu;
    @SerializedName("batters")
    @Expose
    private Batters batters;
    @SerializedName("topping")
    @Expose
    private List<Topping> topping = null;



    public DessertResponse(Integer id, String type, String name, Double ppu, Batters batters, List<Topping> topping) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.ppu = ppu;
        this.batters = batters;
        this.topping = topping;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPpu() {
        return ppu;
    }

    public void setPpu(Double ppu) {
        this.ppu = ppu;
    }

    public Batters getBatters() {
        return batters;
    }

    public void setBatters(Batters batters) {
        this.batters = batters;
    }

    public List<Topping> getTopping() {
        return topping;
    }

    public void setTopping(List<Topping> topping) {
        this.topping = topping;
    }

}


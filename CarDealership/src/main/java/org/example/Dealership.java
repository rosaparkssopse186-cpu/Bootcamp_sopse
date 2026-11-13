package org.example;

import java.util.ArrayList;
import java.util.List;

public class Dealership {

 private String name;
 private String address;
 private String phone;
 private ArrayList<Vechicle> Inventory;

    public Dealership(String name, String address, String phone, ArrayList<Vechicle> inventory) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        Inventory = inventory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ArrayList<Vechicle> getInventory() {
        return Inventory;
    }

    public void setInventory(ArrayList<Vechicle> inventory) {
        Inventory = inventory;
    }
}

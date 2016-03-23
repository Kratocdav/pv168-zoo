/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zoomanager;

/**
 *
 * @author Matus Sedlak
 */
public class Captivity {

    private Long id;
    private String pavilion;
    private int capacity;
    private String info;

    public Captivity(String pavilion, int capacity, String info) {
        this.pavilion = pavilion;
        this.capacity = capacity;
        this.info = info;
    }

    public Captivity(){}
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPavilion() {
        return pavilion;
    }

    public void setPavilion(String pavilion) {
        this.pavilion = pavilion;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

}

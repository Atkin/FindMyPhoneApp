package com.jsonwitneses.findmyphone.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;


@Document(collection = "users")
public class User {
    @Id
    private Long id;


    private String name;

    private Set<Device> devices;

    public User(Long id, String name, Set<Device> devices) {
        this.id = id;
        this.name = name;
        this.devices = devices;
    }

    public User() {

    }

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

    public Set<Device> getDevices() {
        return devices;
    }

    public void setDevices(Set<Device> devices) {
        this.devices = devices;
    }
}

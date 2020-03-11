package com.xiaobao.pro_manage_sys.entity.user;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "expert")
public class Expert extends User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exp_id")
    private Integer id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;

    public Expert() {
    }

    public Expert(String username, String password) {
        this.username=username;
        this.password=password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String
    toString() {
        return "User{" +
                "uId=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

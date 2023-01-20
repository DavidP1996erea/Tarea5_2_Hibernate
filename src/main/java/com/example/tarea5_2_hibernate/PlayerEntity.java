package com.example.tarea5_2_hibernate;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Player")
public class PlayerEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPlayer")
    private int id_Player;


    @Column(name ="Nick")
    private String nick;

    @Column(name="password")
    private String password;

    @Column(name = "email")
    private String email;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name ="idPlayer")
    private List<ComprasEntity> listaCompras;


    public PlayerEntity() {

    }

    public PlayerEntity(String nick, String password, String email) {
        this.nick = nick;
        this.password = password;
        this.email = email;

    }



    public int getId_Player() {
        return id_Player;
    }


    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<ComprasEntity> getListaCompras() {
        return listaCompras;
    }

    public void setListaCompras(List<ComprasEntity> listaCompras) {
        this.listaCompras = listaCompras;
    }
}

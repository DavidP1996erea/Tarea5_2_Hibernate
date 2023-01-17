package com.example.tarea5_2_hibernate;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table (name = "Compras")
public class ComprasEntity implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCompra")
    private int id_Compras;


    @ManyToOne
    @JoinColumn(name = "idPlayer")
    private  PlayerEntity id_Player;

    @ManyToOne
    @JoinColumn(name = "idGames")
    private GamesEntity id_Games;

    @Column(name = "Cosa")
    private int Cosa;


    @Column(name = "Precio")
    private double Precio;

    @Column(name = "FechaCompra")
    private Date FechaCompra;



    public ComprasEntity() {

    }

    public ComprasEntity(int id_Compras, PlayerEntity id_Player, GamesEntity id_Games, int cosa, double precio, Date fechaCompra) {
        this.id_Compras = id_Compras;
        this.id_Player = id_Player;
        this.id_Games = id_Games;
        Cosa = cosa;
        Precio = precio;
        FechaCompra = fechaCompra;
    }

    public int getId_Compras() {
        return id_Compras;
    }


    public PlayerEntity getId_Player() {
        return id_Player;
    }


    public GamesEntity getId_Games() {
        return id_Games;
    }

    public int getCosa() {
        return Cosa;
    }

    public void setCosa(int cosa) {
        Cosa = cosa;
    }

    public double getPrecio() {
        return Precio;
    }

    public void setPrecio(double precio) {
        Precio = precio;
    }

    public Date getFechaCompra() {
        return FechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        FechaCompra = fechaCompra;
    }
}

package com.example.tarea5_2_hibernate;

import jakarta.persistence.*;

import java.sql.Time;

@Entity
@Table(name = "Games")
public class GamesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idGames")
    private int id_Games;

    @Column(name = "Nombre")
    private String Nombre;

    @Column(name = "tiempoJugado")
    private Time tiempoJugado;

    public GamesEntity() {
    }

    public GamesEntity(int id_Games, String nombre, Time tiempoJugado) {
        this.id_Games = id_Games;
        Nombre = nombre;
        this.tiempoJugado = tiempoJugado;
    }

    public int getId_Games() {
        return id_Games;
    }
    
    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public Time getTiempoJugado() {
        return tiempoJugado;
    }

    public void setTiempoJugado(Time tiempoJugado) {
        this.tiempoJugado = tiempoJugado;
    }
}

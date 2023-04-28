package com.example.mesqbeerapp.model;

public class UnidadeMedida {

    private long id = 0;
    public String sigla = "";

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public UnidadeMedida(long id, String sigla) {
        this.id = id;
        this.sigla = sigla;
    }
}

package br.iff.pooa20172.oficinarealm.Model;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by higor on 16/03/18.
 */

public class Peca extends RealmObject implements Serializable {

    @PrimaryKey
    private int id;
    private String nome;
    private String descricao;

    public Peca(int id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public Peca() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descreicao) {
        this.descricao = descreicao;
    }
}

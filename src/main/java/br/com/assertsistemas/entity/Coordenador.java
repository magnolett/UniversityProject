package br.com.assertsistemas.entity;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
		@NamedQuery(name = "Coordenador.findByUsuario", query = "SELECT c FROM Coordenador c WHERE c.usuario = :usuario") })

public class Coordenador extends Pessoa {

}

package entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "empresa")
public class Empresa extends Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "emp_razao_social", nullable = false)
    private String razao_social;
    @Column(name = "emp_nome_fantasia", nullable = false)
    private String nome_fantasia;
    @Column(name = "emp_data_cadastro", nullable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date data_cadastro = new Date();

    public String getRazao_social() {
        return razao_social;
    }

    public void setRazao_social(String razao_social) {
        this.razao_social = razao_social;
    }

    public String getNome_fantasia() {
        return nome_fantasia;
    }

    public void setNome_fantasia(String nome_fantasia) {
        this.nome_fantasia = nome_fantasia;
    }

    public Date getData_cadastro() {
        return data_cadastro;
    }

    public void setData_cadastro(Date data_cadastro) {
        this.data_cadastro = data_cadastro;
    }

}

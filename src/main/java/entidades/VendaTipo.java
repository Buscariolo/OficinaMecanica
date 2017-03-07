package entidades;

public enum VendaTipo {

    ORCAMENTO("Orçamento"),
    VENDA("Venda");

    private final String descricao;

    private VendaTipo(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}

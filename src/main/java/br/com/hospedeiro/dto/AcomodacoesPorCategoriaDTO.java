package br.com.hospedeiro.dto;

public class AcomodacoesPorCategoriaDTO {

    private String categoria;
    private Long quantidadeAcomodacao;


    public AcomodacoesPorCategoriaDTO(String categoria, Long quantidadeAcomodacao) {
        this.categoria = categoria;
        this.quantidadeAcomodacao = quantidadeAcomodacao;
    }

    public AcomodacoesPorCategoriaDTO() {
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Long getQuantidadeAcomodacao() {
        return quantidadeAcomodacao;
    }

    public void setQuantidadeAcomodacao(Long quantidadeAcomodacao) {
        this.quantidadeAcomodacao = quantidadeAcomodacao;
    }
}

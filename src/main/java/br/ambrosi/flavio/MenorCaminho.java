package br.ambrosi.flavio;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * @author (ambrosi@cpqd.com.br)
 *
 */
public class MenorCaminho implements Serializable {

    private String menorCaminho;

    private Number distanciaRota;

    private BigDecimal custoRota;

    private String mensagemErro;


    /**
     * Obtém o valor do atributo menorCaminho. Verifique o comentário do atributo para mais detalhes.
     * @return O menorCaminho.
     */
    public String getMenorCaminho() {
        return this.menorCaminho;
    }


    /**
     * Define o valor do atributo menorCaminho. Verifique o comentário do atributo para mais detalhes.
     * @param menorCaminho O menorCaminho a ser definido.
     */
    public void setMenorCaminho(String menorCaminho) {
        this.menorCaminho = menorCaminho;
    }


    /**
     * Obtém o valor do atributo distanciaRota. Verifique o comentário do atributo para mais detalhes.
     * @return O distanciaRota.
     */
    public Number getDistanciaRota() {
        return this.distanciaRota;
    }


    /**
     * Define o valor do atributo distanciaRota. Verifique o comentário do atributo para mais detalhes.
     * @param distanciaRota O distanciaRota a ser definido.
     */
    public void setDistanciaRota(Number distanciaRota) {
        this.distanciaRota = distanciaRota;
    }



    /**
     * Obtém o valor do atributo custoRota. Verifique o comentário do atributo para mais detalhes.
     * @return O custoRota.
     */
    public BigDecimal getCustoRota() {
        return this.custoRota;
    }



    /**
     * Define o valor do atributo custoRota. Verifique o comentário do atributo para mais detalhes.
     * @param custoRota O custoRota a ser definido.
     */
    public void setCustoRota(BigDecimal custoRota) {
        this.custoRota = custoRota;
    }


    /**
     * Obtém o valor do atributo mensagemErro. Verifique o comentário do atributo para mais detalhes.
     * @return O mensagemErro.
     */
    public String getMensagemErro() {
        return this.mensagemErro;
    }



    /**
     * Define o valor do atributo mensagemErro. Verifique o comentário do atributo para mais detalhes.
     * @param mensagemErro O mensagemErro a ser definido.
     */
    public void setMensagemErro(String mensagemErro) {
        this.mensagemErro = mensagemErro;
    }


}

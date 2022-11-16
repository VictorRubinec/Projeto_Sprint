/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package banco;

/**
 *
 * @author vinicius
 */
public class TbComponente {
    private Integer idComponente;
    private String fkMaquina;
    private String tipo;
    private String metrica;
    private Integer metricaMaxima;

    public Integer getIdComponente() {
        return idComponente;
    }

    public void setIdComponente(Integer idComponente) {
        this.idComponente = idComponente;
    }
    
    public String getFkMaquina() {
        return fkMaquina;
    }

    public void setFkMaquina(String fkMaquina) {
        this.fkMaquina = fkMaquina;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMetrica() {
        return metrica;
    }

    public void setMetrica(String metrica) {
        this.metrica = metrica;
    }

    public Integer getMetricaMaxima() {
        return metricaMaxima;
    }

    public void setMetricaMaxima(Integer metricaMaxima) {
        this.metricaMaxima = metricaMaxima;
    }
    
    
}

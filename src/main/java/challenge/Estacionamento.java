package challenge;

import com.sun.org.apache.bcel.internal.generic.ATHROW;

import java.util.ArrayList;
import java.util.List;

public class Estacionamento {

    final int capacidadeDeCarros = 10;
    final int maiorIdade = 18;
    final int maxDePontos = 20;
    final int idadeMaxima = 55;

    List<Carro> vagas = new ArrayList<>();

    public void estacionar(Carro carro) {

        validarDados(carro);

        if( vagas.size() < capacidadeDeCarros){
            vagas.add(carro);
        }else{
            verificarDisponibilidadeVagas(carro);
        }

    }

    public int carrosEstacionados() { return vagas.size();  }

    public boolean carroEstacionado(Carro carro) {
        return vagas.contains(carro);
    }







    public void validarDados (Carro carro){
        if (carro.getMotorista() == null){
            throw new EstacionamentoException("Proibido estacionar carros autônomos");
        }
        if (carro.getMotorista().getIdade() < maiorIdade){
            throw new EstacionamentoException("Proibido estacionar, motorista menor de idade");
        }
        if(carro.getMotorista().getPontos() >= maxDePontos){
            throw new EstacionamentoException("Proibido estacionar, a habilitação do motorista está suspensa");
        }
    }

    public boolean verificarDisponibilidadeVagas(Carro carro){

        for (Carro carroEstacionado: vagas) {
            if(carroEstacionado.getMotorista().getIdade() < idadeMaxima){
                vagas.remove(carroEstacionado);
                vagas.add(carro);
                return true;
            }
        }

        throw new EstacionamentoException("Estacionamento Cheio!");

    }




}

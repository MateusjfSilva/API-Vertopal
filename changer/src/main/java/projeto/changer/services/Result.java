package projeto.changer.services;

public class Result {
    private Output output;

    public Result(Output saida) {
        this.output = saida;
    }

    public Output getSaida() {
        return output;
    }

    public void setSaida(Output saida) {
        this.output = saida;
    }
}

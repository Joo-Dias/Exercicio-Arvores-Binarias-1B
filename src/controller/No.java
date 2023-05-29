package controller;

public class No {
	
	public No(String dado) {
        this.dado = dado;
    }

	public String dado;
	public No direita;
	public No esquerda;

	@Override
	public String toString() {
		return "No [dado=" + dado + "]";
	}
}
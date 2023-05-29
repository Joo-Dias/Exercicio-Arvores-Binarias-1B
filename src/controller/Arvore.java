package controller;

public class Arvore {

    No raiz;

    public Arvore() {
        raiz = null;
    }

    private void insertLeaf(No no, No raizSubArvore) {
        if (raiz == null) {
            raiz = no;
        } else if (no.dado.compareTo(raizSubArvore.dado) < 0) {
            if (raizSubArvore.esquerda == null) {
                raizSubArvore.esquerda = no;
            } else {
                insertLeaf(no, raizSubArvore.esquerda);
            }
        } else if (no.dado.compareTo(raizSubArvore.dado) >= 0) {
            if (raizSubArvore.direita == null) {
                raizSubArvore.direita = no;
            } else {
                insertLeaf(no, raizSubArvore.direita);
            }
        }
    }

    public void insert(String dado) {
        No no = new No(dado);
        no.dado = dado;
        no.esquerda = null;
        no.direita = null;
        insertLeaf(no, raiz);
    }

    private No nodeSearch(No raizSubArvore, String valor) throws Exception {
        if (raiz == null) {
            throw new Exception("Árvore vazia");
        } else if (raizSubArvore.dado.compareTo(valor) > 0) {
            return nodeSearch(raizSubArvore.esquerda, valor);
        } else if (raizSubArvore.dado.compareTo(valor) < 0) {
            return nodeSearch(raizSubArvore.direita, valor);
        } else {
            return raizSubArvore;
        }
    }

    private int nodeLevel(No raizSubArvore, String valor) throws Exception {
        if (raiz == null) {
            throw new Exception("Árvore vazia");
        } else if (raizSubArvore.dado.compareTo(valor) > 0) {
            return 1 + nodeLevel(raizSubArvore.esquerda, valor);
        } else if (raizSubArvore.dado.compareTo(valor) < 0) {
            return 1 + nodeLevel(raizSubArvore.direita, valor);
        } else {
            return 0;
        }
    }

    public void search(String valor) throws Exception {
        try {
            No no = nodeSearch(raiz, valor);
            int level = nodeLevel(raiz, valor);
            System.out.println("Valor " + no.dado + " nível " + level);
        } catch (Exception e) {
            throw new Exception("Valor não encontrado");
        }
    }

    private No removeChild(No raizSubArvore, String valor) throws Exception {
        // Buscar o nó recursivamente
        if (raiz == null) {
            throw new Exception("Árvore vazia");
        } else if (raizSubArvore.dado.compareTo(valor) > 0) {
            raizSubArvore.esquerda = removeChild(raizSubArvore.esquerda, valor);
        } else if (raizSubArvore.dado.compareTo(valor) < 0) {
            raizSubArvore.direita = removeChild(raizSubArvore.direita, valor);
        } else { // achou o nó
            if (raizSubArvore.esquerda == null && raizSubArvore.direita == null) { // folha
                raizSubArvore = null;
            } else if (raizSubArvore.esquerda == null) { // Nó com filho à direita
                raizSubArvore = raizSubArvore.direita;
            } else if (raizSubArvore.direita == null) { // Nó com filho à esquerda
                raizSubArvore = raizSubArvore.esquerda;
            } else {
                No no = raizSubArvore.esquerda;
                while (no.direita != null) {
                    no = no.direita;
                }
                raizSubArvore.dado = no.dado; // buscar para trocar o nó de posição
                no.dado = valor;
                raizSubArvore.esquerda = removeChild(raizSubArvore.esquerda, valor);
            }
        }
        return raizSubArvore;
    }

    public void remove(String valor) throws Exception {
        try {
            removeChild(raiz, valor);
        } catch (Exception e) {
            throw new Exception("Valor não existente");
        }
    }

    private void prefix(No raizSubArvore) throws Exception {
        if (raiz == null) {
            throw new Exception("Árvore vazia");
        } else {
            System.out.print(raizSubArvore.dado + " ");
            if (raizSubArvore.esquerda != null) {
                prefix(raizSubArvore.esquerda);
            }
            if (raizSubArvore.direita != null) {
                prefix(raizSubArvore.direita);
            }
        }
    }

    private void infix(No raizSubArvore) throws Exception {
        if (raiz == null) {
            throw new Exception("Árvore vazia");
        } else {
            if (raizSubArvore.esquerda != null) {
                infix(raizSubArvore.esquerda);
            }

            System.out.print(raizSubArvore.dado + " ");

            if (raizSubArvore.direita != null) {
                infix(raizSubArvore.direita);
            }
        }
    }

    private void postfix(No raizSubArvore) throws Exception {
        if (raiz == null) {
            throw new Exception("Árvore vazia");
        } else {
            if (raizSubArvore.esquerda != null) {
                postfix(raizSubArvore.esquerda);
            }
            if (raizSubArvore.direita != null) {
                postfix(raizSubArvore.direita);
            }
            System.out.print(raizSubArvore.dado + " ");
        }
    }

    public void prefixSearch() throws Exception {
        prefix(raiz);
    }

    public void infixSearch() throws Exception {
        infix(raiz);
    }

    public void postfixSearch() throws Exception {
        postfix(raiz);
    }

}


public class ArvoreAVL {

    private No raiz;

    // públicos (obrigatórios)
    public void inserir(int v) {

    }

    public void remover(int v) {

    }

    public boolean buscar(int v) {
        StringBuilder caminho = new StringBuilder();
        No atual = raiz;

        while (atual != null) {
            caminho.append(atual.getValor()).append(" ");

            if (v == atual.getValor()) {
                System.out.println("Caminho: " + caminho.toString().trim());
                System.out.println("Encontrado: sim");

                return true;
            } else if (v < atual.getValor()) {
                atual = atual.getEsquerda();
            } else {
                atual = atual.getDireita();
            }
        }

        System.out.println("Caminho: " + caminho.toString().trim());
        System.out.println("Encontrado: não");

        return false;
    }

    public String percursoPreOrdem() {
        StringBuilder sb = new StringBuilder();
        pre(raiz, sb);

        return sb.toString().trim();
    }

    public String percursoEmOrdem() {
        StringBuilder sb = new StringBuilder();
        em(raiz, sb);

        return sb.toString().trim();
    }

    public String percursoPosOrdem() {
        StringBuilder sb = new StringBuilder();
        pos(raiz, sb);

        return sb.toString().trim();
    }

    public String imprimirArvore() {
        StringBuilder sb = new StringBuilder();
        imprimir(raiz, "", true, sb);

        return sb.toString();
    }

    public boolean contem(int v) {
        return false;
    }

    // privados (balanceamento)
    private int altura(No n) {
        return 1;
    }

    private int fatorBalanceamento(No n) {
        return 1;
    }

    private No rotacaoDireita(No y) {
        return new No(1);
    }

    private No rotacaoEsquerda(No x) {
        return new No(2);
    }

    private No inserirRec(No n, int v) {
        return new No(1);
    }

    private No removerRec(No n, int v) {
        return new No(1);
    }

    private void pre(No n, StringBuilder sb) {
        if (n != null) {
            sb.append(n.getValor()).append(" ");
            pre(n.getEsquerda(), sb);
            pre(n.getDireita(), sb);
        }
    }

    private void em(No n, StringBuilder sb) {
        if (n != null) {
            em(n.getEsquerda(), sb);
            sb.append(n.getValor()).append(" ");
            em(n.getDireita(), sb);
        }
    }

    private void pos(No n, StringBuilder sb) {
        if (n != null) {
            pos(n.getEsquerda(), sb);
            pos(n.getDireita(), sb);
            sb.append(n.getValor()).append(" ");
        }
    }

    private void imprimir(No n, String prefixo, boolean ultimo, StringBuilder sb) {
        if (n != null) {
            sb.append(prefixo);
            sb.append(ultimo ? "└── " : "├── ");
            sb.append(n.getValor()).append("\n");

            String novoPrefixo = prefixo + (ultimo ? "    " : "│   ");
            boolean temEsquerda = (n.getEsquerda() != null);
            boolean temDireita = (n.getDireita() != null);

            if (temEsquerda || temDireita) {
                imprimir(n.getEsquerda(), novoPrefixo, !temDireita, sb);
                imprimir(n.getDireita(), novoPrefixo, true, sb);
            }
        }
    }
}

import java.util.Objects;

public class ArvoreAVL {

    private No raiz;

    // públicos (obrigatórios)
    public void inserir(int v) {
        raiz = inserirRec(raiz, v);
    }

    public void remover(int v) {
        this.raiz = removerRec(this.raiz, v);
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
        return buscar(v);
    }

    // privados (balanceamento)
    private int altura(No n) {
        if (n == null) {
            return 0;
        }

        return 1 + Math.max(altura(n.getEsquerda()), altura(n.getDireita()));
    }

    private int fatorBalanceamento(No n) {
        if (n == null) {
            return 0;
        }

        return altura(n.getEsquerda()) - altura(n.getDireita());
    }

    private No rotacaoDireita(No y) {
        No x = y.getEsquerda();
        No z = x.getDireita();
        x.setDireita(y);
        y.setEsquerda(z);

        y.setAltura(1 + Math.max(altura(y.getEsquerda()), altura(y.getDireita())));
        x.setAltura(1 + Math.max(altura(x.getEsquerda()), altura(x.getDireita())));

        return x;
    }

    private No rotacaoEsquerda(No x) {
        No y = x.getDireita();
        No z = y.getEsquerda();
        y.setEsquerda(x);
        x.setDireita(z);

        x.setAltura(1 + Math.max(altura(x.getEsquerda()), altura(x.getDireita())));
        y.setAltura(1 + Math.max(altura(y.getEsquerda()), altura(y.getDireita())));

        return y;
    }

    private No inserirRec(No n, int v) {
        if (n == null) {
            return new No(v);
        }

        if (v < n.getValor()) {
            n.setEsquerda(inserirRec(n.getEsquerda(), v));
        } else if (v > n.getValor()) {
            n.setDireita(inserirRec(n.getDireita(), v));
        } else {
            return n;
        }

        n.setAltura(1 + Math.max(altura(n.getEsquerda()), altura(n.getDireita())));

        if (fatorBalanceamento(n) < -1) {
            if (v > n.getDireita().getValor()) {
                return rotacaoEsquerda(n);
            }

            if (v < n.getDireita().getValor()) {
                n.setDireita(rotacaoDireita(n.getDireita()));
                return rotacaoEsquerda(n);
            }
        }

        if (fatorBalanceamento(n) > 1) {
            if (v < n.getEsquerda().getValor()) {
                return rotacaoDireita(n);
            }

            if (v > n.getEsquerda().getValor()) {
                n.setEsquerda(rotacaoEsquerda(n.getEsquerda()));
                return rotacaoDireita(n);
            }
        }

        return n;
    }

    private No removerRec(No n, int v) {
        if (Objects.isNull(n)) {
            return null;
        }

        if (v < n.getValor()) {
            n.setEsquerda(removerRec(n.getEsquerda(), v));
        } else if (v > n.getValor()) {
            n.setDireita(removerRec(n.getDireita(), v));
        } else {
            if (n.getEsquerda() == null || n.getDireita() == null) {
                No temp = (n.getEsquerda() != null) ? n.getEsquerda() : n.getDireita();

                if (Objects.isNull(temp)) {
                    n = null;
                } else {
                    n = temp;
                }
            } else {
                No temp = n.getDireita();

                while (temp.getEsquerda() != null) {
                    temp = temp.getEsquerda();
                }

                n.setValor(temp.getValor());
                n.setDireita(removerRec(n.getDireita(), temp.getValor()));
            }
        }

        if (n == null) {
            return null;
        }

        n.setAltura(1 + Math.max(altura(n.getEsquerda()), altura(n.getDireita())));

        int balance = fatorBalanceamento(n);

        if (balance > 1 && fatorBalanceamento(n.getEsquerda()) >= 0) {
            return rotacaoDireita(n);
        }

        if (balance > 1 && fatorBalanceamento(n.getEsquerda()) < 0) {
            n.setEsquerda(rotacaoEsquerda(n.getEsquerda()));

            return rotacaoDireita(n);
        }

        if (balance < -1 && fatorBalanceamento(n.getDireita()) <= 0) {
            return rotacaoEsquerda(n);
        }

        if (balance < -1 && fatorBalanceamento(n.getDireita()) > 0) {
            n.setDireita(rotacaoDireita(n.getDireita()));

            return rotacaoEsquerda(n);
        }

        return n;
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

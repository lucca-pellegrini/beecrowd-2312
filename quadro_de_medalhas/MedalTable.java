import java.util.Scanner;

public class MedalTable
{
    public static void main(String[] args)
    {
        Pais[] paises;

        try (Scanner sc = new Scanner(System.in)) {
            int n = sc.nextInt();
            paises = new Pais[n];

            for (int i = 0; i < n; ++i) {
                paises[i] = new Pais();
                paises[i].nome = sc.next();
                paises[i].ouro = sc.nextInt();
                paises[i].prata = sc.nextInt();
                paises[i].bronze = sc.nextInt();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        insertionSort(paises);

        for (Pais p : paises)
            System.out.println(p.toString());
    }

    public static <T extends Comparable<T> > void insertionSort(T[] vec)
    {
        for (int i = 1; i < vec.length; ++i) {
            T tmp = vec[i];
            int j;

            for (j = i - 1; j >= 0 && tmp.compareTo(vec[j]) > 0; --j)
                vec[j + 1] = vec[j];

            vec[j + 1] = tmp;
        }
    }

    static class Pais implements Comparable<Pais>
    {
        String nome;
        int ouro, prata, bronze;

        @Override public int compareTo(Pais outro)
        {
            int res = 0;

            if (ouro != outro.ouro) {
                res = ouro - outro.ouro;
            } else if (prata != outro.prata) {
                res = prata - outro.prata;
            } else if (bronze != outro.bronze) {
                res = bronze - outro.bronze;
            } else {
                // Compara nomes em ordem alfabética.
                int tam = (nome.length() > outro.nome.length()) ?
                              outro.nome.length() :
                              nome.length();

                for (int i = 0; i < tam; ++i) {
                    res = outro.nome.charAt(i) - nome.charAt(i);
                    if (res != 0)
                        i = tam;
                }

                // Se forem iguais, compara os tamanhos dos nomes.
                res = (res != 0) ? res : outro.nome.length() - nome.length();
            }

            return res;
        }

        public String toString()
        {
            return "" + nome + " " + ouro + " " + prata + " " + bronze;
        }
    }
}

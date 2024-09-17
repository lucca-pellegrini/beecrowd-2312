// Beecrowd 2312

import java.util.Scanner;

public class MedalTable
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // Lê o número de elementos.
        Pais[] paises = new Pais[n]; // Inicializa o vetor de n elementos.

        // Lê cada país e o coloca no vetor.
        for (int i = 0; i < n; ++i)
            paises[i] =
                new Pais(sc.next(), sc.nextInt(), sc.nextInt(), sc.nextInt());

        sc.close(); // Fecha o Scanner.

        insertionSort(paises); // Ordena o vetor.

        // Mostra o resultado.
        for (int i = 0; i < n; ++i)
            System.out.println(paises[i]);
    }

    // Decidi usar o método de inserção porque acho mais fácil de lembrar.
    public static void insertionSort(Pais[] vec)
    {
        for (int i = 1; i < vec.length; ++i) {
            Pais temp = vec[i]; // Salvamos o elemento da posição atual.
            int j; // Iterador do laço interno.

            /* Usamos o método .compareTo para comparar o objeto temporário a
             * cada elemento anterior no vetor. Fazemos isso para determinar a
             * ordem relativa entre eles. (Tem o mesmo efeito para objeto
             * “Pais” que o operador `>` tem para variáveis “int”.) */
            for (j = i - 1; j >= 0 && temp.compareTo(vec[j]) > 0; --j)
                vec[j + 1] = vec[j]; // Desloca para a direita.

            vec[j + 1] = temp; // Insere o elemento salvo.
        }
    }
}

class Pais
{
    // Atributos (todos são privados, mas não precisamos de getter e setter.)
    private String nome;
    private int numOuros, numPratas, numBronzes;

    // Construtor.
    public Pais(String nome, int ouro, int prata, int bronze)
    {
        this.nome = nome;
        this.numOuros = ouro;
        this.numPratas = prata;
        this.numBronzes = bronze;
    }

    // Função que compara dois países. Usaremos no lugar do operador `>` no
    // sort. Retornará positivo se o país em questão tiver que aparecer antes
    // do `outro` na lista.
    public int compareTo(Pais outro)
    {
        int res = 0; // Valor de resposta que retornaremos.

        if (numOuros != outro.numOuros) {
            // Compara número das medalhas de ouro.
            res = numOuros - outro.numOuros;
        } else if (numPratas != outro.numPratas) {
            // Se forem iguais, compara o das medalhas de prata.
            res = numPratas - outro.numPratas;
        } else if (numBronzes != outro.numBronzes) {
            // Se forem iguais, compara as medalhas de bronze.
            res = numBronzes - outro.numBronzes;
        } else {
            // Se os números de medalhas forem iguais, compara nomes em ordem
            // alfabética. Não podemos usar os métodos padrão da classe String,
            // então fazemos a comparação caractere por caractere, manualmente.

            // Tamanho do menor nome.
            int tamanhoMinimo = Math.min(outro.nome.length(), nome.length());

            // Compara cada caractere.
            for (int i = 0; i < tamanhoMinimo; ++i) {
                res = outro.nome.charAt(i) - nome.charAt(i);
                if (res != 0)
                    i = tamanhoMinimo; // Se encontramos diferença, termina o loop.
            }

            // Se nenhuma diferença foi encontrada, ainda é necessário comparar
            // os tamanhos dos nomes.
            res = (res != 0) ? res : outro.nome.length() - nome.length();
        }

        return res;
    }

    // Função que converte o país pra uma string. Isso será implicitamente
    // usado pelo System.out.println() quando quisermos printar o país com o
    // número de medalhas.
    public String toString()
    {
        return nome + " " + numOuros + " " + numPratas + " " + numBronzes;
    }
}

import java.util.*;

public class ExemploOrdenaçãoSet {
    public static void main(String[] args) {

        System.out.println("--\tOrdem Aleatória\t--");
        Set<Serie> mminhasSeries = new HashSet<>(){{
            add(new Serie("got", "fantasia", 60));
            add(new Serie("dark", "drama", 60));
            add(new Serie("that '70s show", "comédia", 25));
        }};
        for (Serie serie: mminhasSeries) System.out.println(serie.getNome() + " - "
            + serie.getGenero() + " - " + serie.getTempoEpisodio());

        System.out.println("--\tOrdem Inserção\t--");
        Set<Serie> mminhasSeries1 = new LinkedHashSet<>(){{
            add(new Serie("got", "fantasia", 60));
            add(new Serie("dark", "drama", 60));
            add(new Serie("that '70s show", "comédia", 25));
        }};
        for (Serie serie: mminhasSeries1) System.out.println(serie.getNome() + " - "
                + serie.getGenero() + " - " + serie.getTempoEpisodio());

        System.out.println("--\tOrdem Natural (TempoEpisodio)\t--");
        Set<Serie> mminhasSeries2 = new TreeSet<>(mminhasSeries1);
        for (Serie serie: mminhasSeries2) System.out.println(serie.getNome() + " - "
                + serie.getGenero() + " - " + serie.getTempoEpisodio());
        System.out.println(mminhasSeries2);

        System.out.println("--\tOrdem Nome/Genero/TempoEpisodio/t--");
        Set<Serie> mminhasSeries3 = new TreeSet<>(new Serie.ComparatorNomeGeneroTempoEpisodio());
        mminhasSeries3.addAll(mminhasSeries);

        for (Serie serie: mminhasSeries3) System.out.println(serie.getNome() + "-"
            + serie.getGenero() + "-" + serie.getTempoEpisodio());
            

    }
}

class Serie implements Comparable<Serie>{

    private String nome;
    private String genero;
    private Integer tempoEpisodio;

    public Serie(String nome, String genero, Integer tempoEpisodio) {
        this.nome = nome;
        this.genero = genero;
        this.tempoEpisodio = tempoEpisodio;
    }

    public String getNome() {
        return nome;
    }

    public String getGenero() {
        return genero;
    }

    public Integer getTempoEpisodio() {
        return tempoEpisodio;
    }

    @Override
    public String toString() {
        return "{" +
                "nome='" + nome + '\'' +
                ", genero='" + genero + '\'' +
                ", tempoEpisodio=" + tempoEpisodio +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Serie serie = (Serie) o;
        return nome.equals(serie.nome) && genero.equals(serie.genero) && tempoEpisodio.equals(serie.tempoEpisodio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, genero, tempoEpisodio);
    }

    @Override
    public int compareTo(Serie serie ) {
        int tempoEpisodio = Integer.compare(this.getTempoEpisodio(), serie.getTempoEpisodio());
        if (tempoEpisodio != 0) return tempoEpisodio;

        return  this.getGenero().compareTo(serie.getGenero());

    }
static class ComparatorNomeGeneroTempoEpisodio implements Comparator<Serie>{

    @Override
    public int compare(Serie s1, Serie s2) {
        int nome = s1.getNome().compareTo(s2.getNome());
        if (nome != 0) return nome;

        int genero = s1.getGenero().compareTo(s2.getGenero());
        if(genero != 0) return genero;

        return Integer.compare(s1.getTempoEpisodio(), s2.getTempoEpisodio());

    }
}

}

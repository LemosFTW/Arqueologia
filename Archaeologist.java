/*
 * @author Rodrigo Lemos Fernandes
 * A classe Archaeologist representa um participante do concurso e cria metodos e variaveis que interagem diretamente com a main.
 */
public class Archaeologist {
	//constantes
	private static final int TEN = 10; //constante que representa o numero 10.
	private static final int ZERO = 0; //constante que representa o numero 0.
	//variaveis de instancia
	// pre: string entre 1 e 40 caracteres
	//variavel que define o nome do participante.
    private String name; 
    
    private int pos; //variavel que consta a posicao do participante.
    private int merit; //variavel que consta o merito do participante
    private boolean disqualified; //variavel que verificar se o candidato foi desqualificado.
    
    //construtor
    public Archaeologist(String name) {
    //cria um arqueologo recebendo uma string e, definindo o nome, a posicao inicial e o merito inicial do participante.
        setName(name); 
        setPos(ZERO);
        setMerit(ZERO);
    }
    
    //metodos
    /**
     * metodo que registra a posicao do participante.
     * @return posicao do participante.
     */
    public int getPos() { 
        return pos;
    }
    
    /**
     *  metodo que define a nova posicao do participante.
     * @param posicao do participante.
     */
    public void setPos(int pos) { 
        this.pos = pos;
    }
    
    /**
     * .
     * metodo que registra o nome do participante.
     * @return nome do participante.
     */
    public String getName() { 
        return name;
    }

    /**
     * pre: string entre 1 e 40 caracteres.
     * metodo que define o nome do participante, obedecendo suas restricoes.
     * @param nome do participante.
     */
    public void setName(String name) { 
        this.name = name;
    }
    /**
     * metodo que verifica se o participante foi desqualificado.
     * @return
     */
    
    public boolean isDisqualified() { 
        return disqualified;
    }
    /**
     * metodo que classifica o participante como desqualificado.
     * @param recebe a informacao se participante esta desqualificado
     */

    public void setDisqualified(boolean disqualified) { 
        this.disqualified = disqualified;
    }
    /**
     * metodo que verifica o merito do participante.
     * @return o valor do merito.
     */

    public int getMerit() { 
        return merit;
    }
    /**
     * metodo que define o merito do participante.
     * @param recebe o merito, valor inteiro, e redefine o merito.
     */

    public void setMerit(int merit) { 
        this.merit = merit;
    }
    /**
     * 
     * metodo que contabiliza descontos de valores no merito do participante.
     * @param quantidade de vezes que o terreno foi escavado.
     */
    
    public void penalize(int times) { 
        merit -= TEN * times;
    }
    /**
     * metodo que incrementa o novo merito do participante.
     * @param recebe um inteiro que indica o merito.
     */
    
    public void incrementMerit(int merit) { 
        this.merit += merit;
    }
    
    /**
     * metodo que move o participante para determinada posicao.
     * @param reebe um inteiro indicando a quantidade de passos do participante.
     */
    public void move(int steps) { 
        this.pos += steps;
    }
}
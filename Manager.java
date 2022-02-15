/*
 * @author Rodrigo Lemos Fernandes.
 * A classe Manager sera responsavel por criar os metodos,arrays e variaveis que interage com a classe Main.
 */
public class Manager {
	//constantes
	private static final int ZERO = 0; //constante que representa o numero 0.
	private static final int ONE = 1; //constante que representa o numero 1.
	private static final int TWO = 2; //constante que representa o numero 2.
    // variaveis de instancia
    private Land[] terrain; // cria uma array referente ao terreno.
    private Archaeologist[] participants; // cria uma array que identifica os participantes do concurso.

    // construtores.
    public Manager(String arqueologo1, String arqueologo2, int numeroTalhoes, int[] plotsRewards) {
        this.participants = new Archaeologist[TWO];
        this.terrain = new Land[numeroTalhoes];
        this.participants[ZERO] = new Archaeologist(arqueologo1);
        this.participants[ONE] = new Archaeologist(arqueologo2);
        this.terrain = new Land[numeroTalhoes];

        for (int i = ZERO; i < numeroTalhoes; i++) {
            this.terrain[i] = new Land(plotsRewards[i]);
        }
    }
    
    // metodos.
	/*
	 * metodo que obtem as recompensas dos terrenos.
	 * @return as recompensas. 
	 */
    public int getRewards() {
        int rewards = ZERO;

        for (int i = ZERO; i < terrain.length; i++) {
            rewards += terrain[i].getReward();
        }

        return rewards;
    }
	/*
	 * metodo que obtem o estado do terreno.
	 * @return o estado do terreno.
	 */
    public boolean[] getTerrainState() {
        int numberOfPlots = terrain.length;
        boolean[] state = new boolean[numberOfPlots];

        for (int i = ZERO; i < numberOfPlots; i++) {
            state[i] = terrain[i].hasReward();
        }

        return state;
    }
    /*
     * metodo que obtem o participante cujo nome e igual a variavel name.
     * @return o participante ou nulo.
     */
    public Archaeologist getParticipant(String name) {
        for (int i = ZERO; i < participants.length; i++) {
            Archaeologist participant = participants[i];
            if (participant.getName().equals(name)) {
                return participant;
            }
        }

        return null;
    }

    /*
     * metodo que informa o tamanho do terreno do concurso.
     * @return o tamanho do terreno.
     */
    public int getTerrainLength() {
        return terrain.length;
    }

    /*
     * metodo que identifica o talhao escolhido.
     * @return o talhao escolhido ou nulo, caso index seja invalido.
     */
    public Land getPlot(int index) {
        if (index > terrain.length) {
            return null;
        }

        return terrain[index];
    }

    /*
     * metodo que verifica se os participantes ainda tem licenca.
     * @return true se pelo menos um dos participantes tem lincensa e falso caso contrario.
     */
    public boolean participantsHaveLicense() {
        for (int i = ZERO; i < participants.length; i++) {
            if (!participants[i].isDisqualified()) {
                return true;
            }
        }

        return false;
    }
}
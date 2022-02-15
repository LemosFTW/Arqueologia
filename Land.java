/*
 * @author Rodrigo Lemos Fernandes.
 * A classe Land sera responsavel por criar metodos utilizados na main para ajudar o funcionamento do concurso.
 */
public class Land {
	//constantes
	private static final int ZERO = 0; //constante que representa o numero 0.
	//variaveis de instancia
    private int dugOutTimes; // variavel que consta quantas vezes foi escavado o terreno
    private int reward; // variavel que guarda o valor das recompensas dos terrenos
    
    // construtor.
    public Land(int reward) {
        // criacao de um talhao que ja vem com a recompensa inicial e a quantidade de
        // vezes que o terreno foi escavado inicialmente.
        setDugOutTimes(ZERO);
        setReward(reward);
    }
    // metodos.

    /*
     * metodo que retira a recompensa do talhao.
     */
    public void dig() {
        setReward(ZERO);
    }

    /*
     * metodo para verificar a recompensa do terreno.
     */
    public int getReward() {
        return reward;
    }

    /*
     * metodo para definir a recompensa do terreno.
     * @param recebe o numero inteiro da recompensa.
     */
    public void setReward(int reward) {
        this.reward = reward;
    }

    /*
     * metodo para verificar se tem recompensa no terreno.
     * @return se ha recompensa no terreno.
     */
    public boolean hasReward() {
        return reward != ZERO;
    }

    /*
     * metodo para verificar se o terreno ja foi escavado.
     * @return se o terreno ja foi escavado.
     */
    public boolean isDugOut() {
        return dugOutTimes != ZERO;
    }
    /*
     * metodo que verifica a quantidade de vezes que o terreno foi escavado.
     * @return a quantidade de vezes que o terreno foi escavado.
     */

    public int getDugOutTimes() {
        return dugOutTimes;
    }
    /*
     * metodo que define a quantidade de vezes que o terreno foi escavado.
     * @param recebe a quantidade de vezes que o terreno foi escavado.
     */

    public void setDugOutTimes(int dugOutTimes) {
        this.dugOutTimes = dugOutTimes;
    }
}
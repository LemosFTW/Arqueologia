import java.util.Scanner;

/*
 * @author Rodrigo Lemos Fernandes
 * A classe Main e responsavel por controlar o concurso.
 */
public class Main {
	//constantes
	private static final int ZERO = 0; //constante que representa o numero 0.
	private static final int ONE = 1; //constante que representa o numero 1.
	private static final String DEFAULT = ""; // constante que representa um String vazia.
	private static final String SLASH = "-" ; //constante que representa um traco.
	private static final String ASTERISK = "*" ; //constante que representa um asterisco.
	private static final String SPACE = " " ; //constante que representa um espaco.
	private static final String TWO_POINTS = ": "; //constante que representa dois pontos e um espaco.
	private static final String LEAVE = "sair"; // constante que representa o comando sair.
	private static final String TERRENO = "terreno"; // constante que representa o comando terreno.
	private static final String RIQUEZA = "riqueza"; // constante que representa o comando riqueza.
	private static final String MERITO = "merito"; // constante que representa o comando merito.
	private static final String ESCAVACAO = "escavacao"; // constante que representa o comando escavacao.
	private static final String PARTICIPANT_DOES_NOT_EXIST = "Arqueologo inexistente"; 
	// constante que representa a mensagem dita ao nao identificar o participante solicitado.
	private static final String PARTICIPANT_DISQUALIFIED = "Arqueologo desclassificado";
	// constante que representa a mensagem dita ao identificar participante desqualificado.
	private static final String PARTICIPANT_MERIT = "Merito de"; 
	// constante que representa a mensagem dita como prefixo do merito.
	private static final String PARTICIPANT_LOST_LICENSE = "perdeu a licenca de escavacao"; 
	// constante que representa a mensagem ao participante que perdeu a licensa.
	private static final String INVALID_STEP = "Salto invalido"; 
	// constante que representa a mensagem referente ao erro ocasionado por usar um valor de salto invalido.
	private static final String INVALID_COMMAND = "Comando invalido"; 
	// constante que representa a mensagem dita a comandos invalidos.
	private static final String TERRAIN_REWARD = "Riqueza enterrada:";
	// constante que representa a mensagem que identifica a riqueza presente no terreno.
	private static final String BOTH_DISQUALIFIED = "Correu mal! Foram ambos desclassificados."; 
	// constante que representa a mensagem dita ao identificar participante desqualificado.
	private static final String UNDISCOVERED_REWARDS = "Ainda havia tesouros por descobrir..."; 
	// constante que representa a mensagem dita ao identificar que ainda havia tesouros pra descobrir.
	private static final String ALL_REWARDS_DISCOVERD = "Todos os tesouros foram descobertos!"; 
	// constante que representa a mensagem dita ao descobrir todos os tesouros.

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		String arqueologo1 = in.nextLine().trim();
		String arqueologo2 = in.nextLine().trim();
		//pre: os arqueologos so podem ter entre 1 e 40 caracteres.

		int numeroTalhoes = in.nextInt();
		//pre: é um numero inteirp entre 1 e 100.

		int[] plotsRewards = new int[numeroTalhoes];
		//pre: as recompensas sao valores entre 1 e 50000.

		for (int i = 0; i < numeroTalhoes; i++) {
			plotsRewards[i] = in.nextInt();
		}
		

		Manager gameManager = new Manager(arqueologo1, arqueologo2, numeroTalhoes, plotsRewards);
		
		String command = in.next();

		while (!command.equals(LEAVE)) {

			handleCommand(gameManager, in, command);

			command = in.next();
		}
			in.close();

		if (!gameManager.participantsHaveLicense()) {
			System.out.println(BOTH_DISQUALIFIED);
		} else {
			if (gameManager.getRewards() != ZERO) {
				System.out.println(UNDISCOVERED_REWARDS);
			} else {
				System.out.println(ALL_REWARDS_DISCOVERD);
			}
		}		
	}
	//metodo
	/*
	 * metodo que executa os comandos.
	 * @param recebe o gameManager, o scanner e o comando.
	 */
	public static void handleCommand(Manager gameManager, Scanner in, String command) {
		switch (command) {
		case TERRENO:
			boolean[] terrainState = gameManager.getTerrainState();
			int terrainStateLength = terrainState.length;
			String result = DEFAULT;

			for (int i = ZERO; i < terrainStateLength; i++) {
				if (terrainState[i]) {
					result += ASTERISK;
				} else {
					result += SLASH;
				}
			}

			System.out.println(result);
			break;
		case MERITO:
			Archaeologist meritParticipant = gameManager.getParticipant(in.nextLine().trim());

			if (meritParticipant == null) {
				System.out.println(PARTICIPANT_DOES_NOT_EXIST);
				break;
			}

			if (meritParticipant.isDisqualified()) {
				System.out.println(PARTICIPANT_DISQUALIFIED);
				break;
			}

			String name = meritParticipant.getName();
			int merit = meritParticipant.getMerit();

			System.out.println(PARTICIPANT_MERIT + SPACE + name + TWO_POINTS + merit);

			break;
		case RIQUEZA:
			System.out.println(TERRAIN_REWARD + SPACE + gameManager.getRewards());

			break;
		case ESCAVACAO:
			int steps = in.nextInt();
			String participantName = in.nextLine().trim();

			Archaeologist escavationParticipant = gameManager.getParticipant(participantName);

			if (steps == ZERO) {
				System.out.println(INVALID_STEP);
				break;
			}

			if (escavationParticipant == null) {
				System.out.println(PARTICIPANT_DOES_NOT_EXIST);
				break;
			}

			if (escavationParticipant.isDisqualified()) {
				System.out.println(PARTICIPANT_DISQUALIFIED);
				break;
			}

			int terrainLength = gameManager.getTerrainLength();
			int participantCurrentPos = escavationParticipant.getPos();
			int participantExpectedPos = participantCurrentPos + steps;

			if (participantExpectedPos > terrainLength || participantExpectedPos < 1) {
				System.out.println(participantName + " " + PARTICIPANT_LOST_LICENSE);
				escavationParticipant.setDisqualified(true);
				break;
			}

			escavationParticipant.setPos(participantExpectedPos);

			int currentPlotIndex = escavationParticipant.getPos() - ONE;
			Land currentPlot = gameManager.getPlot(currentPlotIndex);

			int dugOutTimes = currentPlot.getDugOutTimes();

			if (currentPlot.isDugOut()) {
				escavationParticipant.penalize(dugOutTimes);
			} else {
				int plotReward = currentPlot.getReward();
				escavationParticipant.incrementMerit(plotReward);
				currentPlot.dig();
			}

			currentPlot.setDugOutTimes(dugOutTimes + ONE);
			break;
		default:
			System.out.println(INVALID_COMMAND);

			in.nextLine();

			break;
		}
	}
}
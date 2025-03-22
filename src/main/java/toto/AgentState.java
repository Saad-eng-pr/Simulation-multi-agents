package toto;

/**
 * Classe représentant l'état d'un agent.
 * Cette classe gère la variable indiquant si l'agent est vivant ou non.
 */
public class AgentState {

    /**
     * Indique si l'agent est vivant.
     */
    protected boolean vivant;

    /**
     * Constructeur par défaut. Initialise l'agent comme étant vivant.
     */
    public AgentState() {
        vivant = true;
    }

    /**
     * Définit l'état de vie de l'agent.
     *
     * @param state Le nouvel état de l'agent : {@code true} si l'agent est vivant, {@code false} sinon.
     */
    public void setVivant(boolean state) {
        vivant = state;
    }

    /**
     * Récupère l'état de vie de l'agent.
     *
     * @return {@code true} si l'agent est vivant, {@code false} sinon.
     */
    public boolean getVivant() {
        return vivant;
    }

    /**
     * Méthode vide qui sert de placeholder pour les sous-classes.
     * Les sous-classes doivent implémenter cette méthode pour modifier l'état de l'agent.
     */
    public void enVie() {
    }
}

/**
 * Classe représentant l'état d'un agent vivant.
 * Hérite de {@link AgentState} et définit la méthode {@link #enVie()} pour marquer l'agent comme vivant.
 */
class AgentEnVie extends AgentState {

    /**
     * Constructeur qui initialise l'agent comme étant vivant.
     */
    public AgentEnVie() {
        super();
    }

    /**
     * Définit l'agent comme étant vivant.
     */
    @Override
    public void enVie() {
        this.vivant = true;
    }
}

/**
 * Classe représentant l'état d'un agent mort.
 * Hérite de {@link AgentState} et définit la méthode {@link #enVie()} pour marquer l'agent comme mort.
 */
class AgentDead extends AgentState {

    /**
     * Constructeur qui initialise l'agent comme étant mort.
     */
    public AgentDead() {
        super();
    }

    /**
     * Définit l'agent comme étant mort.
     */
    @Override
    public void enVie() {
        this.vivant = false;
    }
}

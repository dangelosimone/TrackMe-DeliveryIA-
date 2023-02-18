package projectJava.mainJava.sample.entity;

public abstract class Entity {
    protected String entityId;

    protected Entity(String entityId) {
        this.entityId = entityId;
    }

    /**
     * Ritorna l'identificatore univoco associato ad ogni classe che estende Entity
     * @return l'identificatore univoco
     */
    public String getID() {
        { // choose a Character random from this String
            return entityId;
        }
    }

    public void setID(String id) {
        this.entityId = id;
    }
}

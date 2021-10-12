
package world;

public class Destination extends CreatureAI {

    private CreatureFactory factory;
    private int spreadcount = 0;


    public Destination(Creature creature, CreatureFactory factory) {
        super(creature);
        this.factory = factory;
    }
}

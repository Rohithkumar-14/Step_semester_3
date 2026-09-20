interface Attackable {
    String attack();
    String attack(String weaponName);
}

interface Defendable {
    String defend();
}

abstract class GameCharacter {
    private final String characterId;
    private static int counter = 0;

    protected GameCharacter() {
        characterId = "CHAR-" + (++counter);
    }

    public abstract String getSpecialMove();

    String getCharacterId() {
        return characterId;
    }
}

class Warrior extends GameCharacter implements Attackable, Defendable {
    private String name;

    public Warrior(String name) {
        this.name = name;
    }

    @Override
    public String attack() {
        return name + " strikes with a blade";
    }

    @Override
    public String attack(String weaponName) {
        return name + " strikes with an " + weaponName;
    }

    @Override
    public String defend() {
        return name + " raises a shield";
    }

    @Override
    public String getSpecialMove() {
        return name + " unleashes Whirlwind Slash";
    }
}

class Trap implements Defendable {
    private String trapType;

    public Trap(String trapType) {
        this.trapType = trapType;
    }

    @Override
    public String defend() {
        return trapType + " triggers automatically";
    }
}

class Arena {
    static void resolveDefense(Defendable[] combatants) {
        for (Defendable combatant : combatants)
            System.out.println(combatant.defend());
    }
}

public class P4_ArenaBattle {
    public static void main(String[] args) {
        Warrior warrior = new Warrior("Kael");
        Trap trap = new Trap("Spike Pit");

        System.out.println(warrior.getCharacterId());
        System.out.println(warrior.attack());
        System.out.println(warrior.attack("sword"));
        System.out.println(warrior.defend());
        System.out.println(warrior.getSpecialMove());

        System.out.println(trap.defend());

        Defendable[] combatants = {warrior, trap};
        Arena.resolveDefense(combatants);
    }
}
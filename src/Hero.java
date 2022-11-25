/**
 * Heroのクラスです。
 */
public class Hero extends Actor {

  private final Integer id;
  private final String name;
  private int hp;
  private final int attack;
  private final int defence;
  private final int[] movements;
  private boolean isDefensivePosture;

  public Hero(Integer id, String name, int hp, int attack, int defence, int[] movements,
      boolean isDefensivePosture) {
    this.id = id;
    this.name = name;
    this.hp = hp;
    this.attack = attack;
    this.defence = defence;
    this.movements = movements;
    this.isDefensivePosture = isDefensivePosture;
  }

  public Integer getID() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public int getHp() {
    return this.hp;
  }

  public int getAttack() {
    return this.attack;
  }

  public int getDefence() {
    return this.defence;
  }

  public int[] getMovement() {
    return this.movements;
  }

  public boolean getIsDefensivePosture() {
    return this.isDefensivePosture;
  }

  public void setHp(int hp) {
    if (hp < 1) {
      this.hp = 0;
    } else {
      this.hp = hp;
    }
  }

  public void setIsDefensivePosture(boolean defensivePosture) {
    this.isDefensivePosture = defensivePosture;
  }
}


/**
 * すべてのActorが元にする抽象クラスです。
 */
public abstract class Actor {

  public Integer id;
  public String name;
  public int hp;
  public int attack;
  public int defence;
  public int[] movements;

  public abstract Integer getID();

  public abstract String getName();

  public abstract int getHp();

  public abstract int getAttack();

  public abstract int getDefence();

  public abstract int[] getMovement();

  public abstract void setHp(int hp);

}

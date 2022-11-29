/**
 * すべてのActorが元にする抽象クラスです。
 */
public abstract class Actor {


  public abstract Integer getID();

  public abstract String getName();

  public abstract int getHp();

  public abstract int getAttack();

  public abstract int getDefence();

  public abstract int[] getMovement();

  public abstract void setHp(int hp);

}

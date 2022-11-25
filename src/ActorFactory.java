/**
 * Actorオブジェクトを生成する、抽象ファクトリクラスです。
 */
public abstract class ActorFactory {

  public abstract void createActor(String name, int hp, int attack,
      int defence,
      int[] movements, boolean isDefensivePosture);

  public abstract void registerActor(Actor actor);

}

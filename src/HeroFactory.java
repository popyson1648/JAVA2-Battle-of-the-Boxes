import java.util.LinkedHashMap;

/**
 * Heroオブジェクトを生成し、リストに格納・保持するファクトリクラスです。
 */
public class HeroFactory extends ActorFactory {

  // 自分が作ったヒーローを格納するリスト
  private final LinkedHashMap<Integer, Hero> heroList;
  private Integer id;

  public HeroFactory() {
    this.heroList = new LinkedHashMap<>(GameParameters.MAX_HEROES.getParamVal());
    this.id = 1;
  }

  /**
   * ヒーロオブジェクトを生成します
   *
   * @param name               名前です
   * @param hp                 HPです
   * @param attack             攻撃力です
   * @param defence            防御力です
   * @param movements          技リストです
   * @param isDefensivePosture ディフェンス状態示す真偽値です
   */
  @Override
  public void createActor(String name, int hp, int attack, int defence,
      int[] movements, boolean isDefensivePosture) {
    var hero = new Hero(this.id, name, hp, attack, defence, movements, isDefensivePosture);
    registerActor(hero);
  }

  /**
   * 生成したヒーロオブジェクトをリストに登録します。
   *
   * @param actor 生成したヒーロオブジェクト
   */
  public void registerActor(Actor actor) {
    heroList.put(this.id, (Hero) actor);
    this.id++;
  }

  /**
   * 生成したヒーロオブジェクトを格納するリストを返します。
   *
   * @return 生成したヒーロオブジェクトを格納するリスト
   */
  public LinkedHashMap<Integer, Hero> getHeroList() {
    return this.heroList;
  }

}


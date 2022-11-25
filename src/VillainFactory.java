import java.util.LinkedHashMap;

/**
 * Villainオブジェクトを生成し、保持するファクトリクラスです。
 */
public class VillainFactory extends ActorFactory {

  // 自分が作ったヒーローのリスト
  private final LinkedHashMap<Integer, Villain> villainList;
  private Integer id;

  public VillainFactory() {
    this.villainList = new LinkedHashMap<>(GameParameters.MAX_HEROES.getParamVal());
    this.id = 1;
  }

  /**
   * ヴィランオブジェクトを生成します
   *
   * @param name      名前です
   * @param hp        HPです
   * @param attack    攻撃力です
   * @param defence   防御力です
   * @param movements 技リストです
   */
  @Override
  public void createActor(String name, int hp, int attack, int defence,
      int[] movements, boolean isDefensivePosture) {
    var villain = new Villain(this.id, name, hp, attack, defence, movements);
    registerActor(villain);
  }

  /**
   * 生成したヴィランオブジェクトをリストに登録します。
   *
   * @param actor 生成したヴィランオブジェクト
   */
  public void registerActor(Actor actor) {
    villainList.put(this.id, (Villain) actor);
    this.id++;
  }

  /**
   * 生成したヴィランオブジェクトを格納するリストを返します。
   *
   * @return 生成したヴィランオブジェクトを格納するリスト
   */
  public LinkedHashMap<Integer, Villain> getVillainList() {
    return this.villainList;
  }

}

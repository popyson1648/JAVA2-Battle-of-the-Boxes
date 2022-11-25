
/**
 * ゲームに登場するヴィランのタイプの一覧をもつ列挙型クラスです。
 */
public enum VillainTypes {
  SLIME(1, "スライム", new int[]{1, 6, 8}),
  GOLEM(2, "ゴーレム", new int[]{2, 3, 4}),
  GHOST(3, "ゴースト", new int[]{5, 6, 8}),
  GOBLIN(4, "ゴブリン", new int[]{3, 10, 11});
  private final int id;
  private final String name;
  private final int[] movementsList;


  VillainTypes(int id, String name, int[] movementsList) {
    this.id = id;
    this.name = name;
    this.movementsList = movementsList;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return this.name;
  }

  public int[] getMovementsList() {
    return movementsList;
  }
}

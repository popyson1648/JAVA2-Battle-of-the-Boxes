/**
 * ゲームに登場するヒーローのタイプの一覧をもつ列挙型クラスです。
 */
public enum HeroTypes {
  BRAVEST(1, "勇者", new int[]{1, 2, 3});

  private final int id;
  private final String name;
  private final int[] movementsList;


  HeroTypes(int id, String name, int[] movementsList) {
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

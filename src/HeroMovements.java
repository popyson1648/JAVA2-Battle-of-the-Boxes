/**
 * ヒーローが使用できる技の列挙型クラスです
 */
public enum HeroMovements {
  SLASH(1, "きりさく", 500),
  COSMIC_POWER(2, "コスモパワー", 500),
  SACRE_SWORD(3, "せいなるつるぎ", 500);

  private final int id;
  private final String movementName;
  private final int power;

  HeroMovements(int id, String movementName, int power) {
    this.id = id;
    this.movementName = movementName;
    this.power = power;
  }

  /**
   * idを取得します
   *
   * @return id
   */
  public int getId() {
    return id;
  }

  /**
   * 技リストを取得します
   *
   * @return movementName
   */
  public String getMovementName() {
    return movementName;
  }

  /**
   * 技の威力を取得します
   *
   * @return power
   */
  public int getPower() {
    return power;
  }
}
/**
 * ヴィランが使用できる技の列挙型クラスです
 */

public enum VillainMovements {
  TACKLE(1, "たいあたり", 30),
  BRUTAL_SWING(2, "ぶんまわす", 50),
  HEADBUTT(3, "ずつき", 40),
  EARTH_POWER(4, "だいちのちから", 50),
  HEX(5, "たたりめ", 20),
  FEINT_ATTACK(6, "だましうち", 40),
  PEEK(7, "つつく", 10),
  TELEKINESIS(8, "テレキネシス", 30),
  BOUNCE(9, "とびはねる", 0),
  DYNAMIC_PUNCH(10, "ばくれつパンチ", 50),
  TOPSY_TURVY(11, "ひっくりかえす", 40);

  private final int id;
  private final String movementName;
  private final int power;

  VillainMovements(int id, String movementName, int power) {
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

/**
 * ゲーム内のパタメータを設定する列挙型クラスです
 */
public enum GameParameters {
  MIN_HEROES(1),
  MAX_HEROES(1),
  MIN_VILLAINS(2),
  MAX_VILLAINS(5),

  MIN_HP(10),
  MAX_HP(500),
  MIN_ATTACK(10),
  MAX_ATTACK(500),
  MIN_DEFENCE(10),
  MAX_DEFENCE(500);

  private final int paramVal;

  GameParameters(int paramVal) {
    this.paramVal = paramVal;
  }

  /**
   * パラメータの値を取得します
   *
   * @return パラメータの値
   */
  public int getParamVal() {
    return paramVal;
  }
}

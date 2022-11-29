import java.util.Random;

/**
 * ゲームの実行に必要な初期処理を行うクラスです
 */
public class GameInitializer {

  int numOfVillain;
  Random random;
  HeroFactory heroFactory;
  VillainFactory villainFactory;


  public GameInitializer() {
    random = new Random();
    heroFactory = new HeroFactory();
    villainFactory = new VillainFactory();
  }

  /**
   * ゲームに必要な初期処理を行います。<br>
   * <ul>
   *   <li>ヒーロー、ヴィランの生成数を決定</li>
   *   <li>決定された生成数に従い、ヒーロー、ヴィランの生成</li>
   * </ul>
   */
  public void initializingGame() {
    int numOfHero = determineNumOfActor(GameParameters.MIN_HEROES.getParamVal(),
        GameParameters.MAX_HEROES.getParamVal());
    numOfVillain = determineNumOfActor(GameParameters.MIN_VILLAINS.getParamVal(),
        GameParameters.MAX_VILLAINS.getParamVal());
    createActor(0, numOfHero);
    createActor(1, numOfVillain);
  }

  /**
   * 生成するアクターの数を指定された範囲内でランダムに決定します
   *
   * @param min 生成するアクターの下限値
   * @param max 生成するアクターの上限値
   * @return 生成するアクターの数
   */
  private int determineNumOfActor(int min, int max) {
    int randomVal;
    int temp = random.nextInt(max + 1);
    if (temp < min) {
      randomVal = temp + (min - temp);
    } else {
      randomVal = temp;
    }
    return randomVal;
  }

  /**
   * 指定されたアクターをアクタータイプはランダムに、指定された数だけ生成します。
   *
   * @param actor            生成するアクターの種類
   *                         <ul>
   *                          <li>0: Hero</li>
   *                          <li>1: Villain</li>
   *                         </ul>
   * @param numOfActorsToGen 生成するアクターの数
   */
  private void createActor(int actor, int numOfActorsToGen) {
    final int LIST_SIZE;
    int hp;
    int attack;
    int defence;
    int[] eigenValues;
    int temp;

    switch (actor) {
      case 0 -> {
        int heroTypeId;
        String heroName = null;
        int[] movementArr = new int[3];
        LIST_SIZE = HeroTypes.values().length;

        for (int i = 0; i < numOfActorsToGen; i++) {
          // ランダムなヒーロタイプを選ぶ
          temp = random.nextInt(LIST_SIZE + 1);
          heroTypeId = temp == 0 ? temp + 1 : temp;
          // ヒーロータイプの名前と技リストを選択
          for (var x : HeroTypes.values()) {
            if (x.getId() == heroTypeId) {
              heroName = x.getName();
              movementArr = x.getMovementsList();
            }
          }
          eigenValues = determineEigenValues();
          hp = eigenValues[0];
          attack = eigenValues[1];
          defence = eigenValues[2];

          heroFactory.createActor(heroName, hp, attack, defence, movementArr, false);
        }
      }
      case 1 -> {
        int villainTypeId;
        String villainName = null;
        int[] movementArr = new int[3];
        LIST_SIZE = VillainTypes.values().length;

        for (int i = 0; i < numOfActorsToGen; i++) {
          temp = random.nextInt(LIST_SIZE + 1);
          villainTypeId = temp == 0 ? temp + 1 : temp;

          for (var x : VillainTypes.values()) {
            if (x.getId() == villainTypeId) {
              villainName = x.getName();
              movementArr = x.getMovementsList();
            }
          }
          eigenValues = determineEigenValues();
          hp = eigenValues[0];
          attack = eigenValues[1];
          defence = eigenValues[2];

          villainFactory.createActor(villainName, hp, attack, defence, movementArr, false);
        }
      }
    }
  }


  /**
   * HP, ATTACK, DEFENCE の値をランダムに決定します
   *
   * @return HP, ATTACK, DEFENCE の値を格納した配列。
   */
  private int[] determineEigenValues() {
    int[] arr = new int[3];
    arr[0] = randomParamValGen(GameParameters.MIN_HP.getParamVal(),
        GameParameters.MAX_HP.getParamVal());
    arr[1] = randomParamValGen(GameParameters.MIN_ATTACK.getParamVal(),
        GameParameters.MAX_ATTACK.getParamVal());
    arr[2] = randomParamValGen(GameParameters.MIN_DEFENCE.getParamVal(),
        GameParameters.MAX_DEFENCE.getParamVal());
    return arr;
  }

  /**
   * アクターのステータスの固有値を指定された範囲内でランダムに決定します。
   *
   * @param min 固有値の下限値
   * @param max 固有値の上限値
   * @return 固有値
   */
  private int randomParamValGen(int min, int max) {
    int val;
    int temp = random.nextInt(max + 1);
    if (temp < min) {
      val = temp + (min - temp);
    } else {
      val = temp;
    }
    // val の一の位を四捨五入し、0にする。例: 94 => 90, 90 => 90
    val = (int) (
        Math.round((double) (val) / 10)
    ) * 10;
    return val;
  }
}
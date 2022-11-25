import java.util.LinkedHashMap;
import java.util.Random;

/**
 * ゲームの進行を管理するクラスです。
 */
public class GameMaster {

  public static LinkedHashMap<Integer, Hero> aliveHeroList;
  public static LinkedHashMap<Integer, Villain> aliveVillainList;
  public static LinkedHashMap<Integer, Hero> deadHeroList;
  public static LinkedHashMap<Integer, Villain> deadVillainList;
  private final GameInitializer gameInitializer;
  private final GameMessage gameMessage;
  private final Random random;
  boolean isHeroDead = false;
  boolean isVillainsDead = false;

  public GameMaster() {
    gameInitializer = new GameInitializer();
    gameMessage = new GameMessage();
    random = new Random();
  }

  /**
   * ゲームの初期化、以下のリストの初期化をします。
   * <ul>
   *   <li>aliveHeroList</li>
   *   <li>aliveVillainList</li>
   *   <li>deadHeroList</li>
   *   <li>deadVillainList</li>
   * </ul>
   */
  public void startGame() {
    gameInitializer.initialingGame();
    aliveHeroList = new LinkedHashMap<>(gameInitializer.heroFactory.getHeroList());
    aliveVillainList = new LinkedHashMap<>(gameInitializer.villainFactory.getVillainList());
    deadHeroList = new LinkedHashMap<>(gameInitializer.heroFactory.getHeroList().size());
    deadVillainList = new LinkedHashMap<>(gameInitializer.villainFactory.getVillainList().size());
  }

  /**
   * ヒーローによる攻撃を実行します
   *
   * @param villainId 攻撃対象のヴィランのID
   */
  public void attack(int villainId) {
    boolean isArgumentError = false;
    if (!(aliveVillainList.containsKey(villainId))) {
      gameMessage.error();
      isArgumentError = true;
    }

    if (!(isArgumentError)) {
      int heroId = 1; // 現在はヒーローは一人しか想定していないため、実数リテラルを代入。
      Hero hero = aliveHeroList.get(heroId);
      int[] heroMovement = hero.getMovement();
      int randVal;
      int chosenMovementId;
      int chosenMovementPower = 0;
      int heroAttack = hero.getAttack();
      int heroDefence = hero.getDefence();
      int damage;

      Villain villain = aliveVillainList.get(villainId);
      int villainHp = villain.getHp();

      // Heroオブジェクトのもつ、技配列から技をランダムに選択
      randVal = random.nextInt(hero.getMovement().length);
      chosenMovementId = heroMovement[randVal];
      for (var x : HeroesMovements.values()) {
        if (x.getId() == chosenMovementId) {
          chosenMovementPower = x.getPower();
        }
      }
      damage = chosenMovementPower * roundParam((heroAttack / heroDefence));
      villain.setHp(villainHp - damage);
      MessageEffectForHeroAttack(heroId, chosenMovementId, damage, villainId);
    }
  }

  /**
   * ヴィランによる攻撃を実行します
   */
  public void villainsAttack() {
    int heroId = 1; // 現在はヒーローは一人しか想定していないため、実数リテラルを代入。
    var hero = aliveHeroList.get(heroId);
    int heroHp = hero.getHp();
    int[] villainMovement;
    int randVal;
    int chosenMovementId;
    int chosenMovementPower = 0;

    int villainAttack;
    int villainDefence;
    int damage;

    // それぞれのヴィランがヒーローにアタック
    for (var x : aliveVillainList.values()) {
      villainAttack = x.getAttack();
      villainDefence = x.getDefence();
      villainMovement = x.getMovement();

      // Villainオブジェクトのもつ技配列から技を選ぶ
      randVal = random.nextInt(x.getMovement().length);
      chosenMovementId = villainMovement[randVal];
      for (var y : VillainMovements.values()) {
        if (y.getId() == chosenMovementId) {
          chosenMovementPower = y.getPower();
        }
      }
      damage = chosenMovementPower * roundParam((villainAttack / villainDefence));
      hero.setHp(heroHp - damage);
      MessageEffectForVillainsAttack(x.getID(), chosenMovementId, damage, heroId);
    }
    MessageStatus();
  }

  /**
   * ヒーローのディフェンスを実行します
   */
  public void defence() {
    int heroId = 1; // 現在はヒーローは一人しか想定していないため、実数リテラルを代入。
    aliveHeroList.get(heroId).setIsDefensivePosture(true);
    MessageDefence(0);
    MessageStatus();
  }

  /**
   * ヒーローのディフェンスの解除を実行します
   */
  public void unlockDefence() {
    int heroId = 1; // 現在はヒーローは一人しか想定していないため、実数リテラルを代入。
    aliveHeroList.get(heroId).setIsDefensivePosture(false);
  }

  /**
   * ヒーローのディフェンスの状態を確認します
   *
   * @return ディフェンスの状態の真偽値を返します
   */
  public boolean isDefence() {
    int heroId = 1; // 現在はヒーローは一人しか想定していないため、実数リテラルを代入。
    return aliveHeroList.get(heroId).getIsDefensivePosture();
  }

  /**
   * ヒーローの回復を実行します 回復値は、HPの最大値以下でランダムに決定されます
   */
  public void recovery() {
    int heroId = 1; // 現在はヒーローは一人しか想定していないため、実数リテラルを代入。
    Hero hero = aliveHeroList.get(heroId);
    int heroHp = hero.getHp();
    int MAX_HP = GameParameters.MAX_HP.getParamVal();
    int recoveryVal = 0;
    if (heroHp < MAX_HP) {
      recoveryVal = random.nextInt((MAX_HP - heroHp) + 1);
    }
    heroHp = heroHp + recoveryVal;
    hero.setHp(heroHp);
    MessageRecover(heroId, recoveryVal);
  }

  /**
   * 一の位を四捨五入したパラメータの値をランダムに決定します。
   *
   * @param x
   * @return
   */
  private int roundParam(int x) {
    int temp;
    temp = (int) (
        Math.round((double) (x))
    );
    temp = temp == 0 ? 1 : temp;
    return temp;
  }

  /**
   * HPが0となったヒーローおよびヴィランの処理を行います
   */
  public void organizeDeceased() {
    // HPが0のヒーロがいないか検査
    int[] deadHeroId = new int[aliveHeroList.size()];
    int dHeroIdx = 0;
    for (var x : aliveHeroList.values()) {
      if (x.getHp() == 0) {
        // 葬る
        deadHeroList.put(x.getID(), x);
        deadHeroId[dHeroIdx] = x.getID();
        dHeroIdx = dHeroIdx + 1;
      }
    }
    for (int i = 0; i < dHeroIdx; i++) {
      aliveHeroList.remove(deadHeroId[i]);
    }

    // HPが0のヴィランがいないか検査
    int[] deadVillainsId = new int[aliveVillainList.size()];
    int dVillainsIdx = 0;
    if (!(isHeroDead)) {
      // HPが0のヴィラン探し
      for (var x : aliveVillainList.values()) {
        if (x.getHp() == 0) {
          // すでに deadVillainsId に格納されていないか
          if (deadVillainList.containsKey(x.getID())) {
            break;
          }
          // 葬る
          deadVillainList.put(x.getID(), x);
          deadVillainsId[dVillainsIdx] = x.getID();
          dVillainsIdx = dVillainsIdx + 1;
        }
      }
      for (int i = 0; i < dVillainsIdx; i++) {
        aliveVillainList.remove(deadVillainsId[i]);
      }
    }

    if (aliveVillainList.size() == 0) {
      this.isVillainsDead = true;
    }
    if (aliveHeroList.size() == 0) {
      this.isHeroDead = true;
    }
  }

  /**
   * タイトルメッセージを実行します
   */
  public void MessageTitle() {
    gameMessage.title();
  }

  /**
   * ゲーム開始のメッセージを実行します
   */
  public void MessageStart() {
    gameMessage.start();
  }

  /**
   * ステータスメッセージを実行します
   */
  public void MessageStatus() {
    gameMessage.status();
  }

  /**
   * アクション選択メッセージを実行します
   */
  public void MessageChoiceAction() {
    gameMessage.choiceAction();
  }

  /**
   * ヴィラン選択メッセージを実行します
   */
  public void MessageChoiceVillain() {
    gameMessage.choiceVillain();
  }

  /**
   * ヒーローによる攻撃のエフェクトメッセージを実行します
   *
   * @param heroId     ヒーロオブジェクトのid
   * @param movementId ヒーロオブジェクトの技リスト
   * @param damage     ヴィランに与えたダメージ
   * @param villainId  攻撃を行ったヴィランオブジェクトのid
   */
  public void MessageEffectForHeroAttack(int heroId, int movementId, int damage, int villainId) {
    gameMessage.effectForHeroAttack(heroId, movementId, damage, villainId);
  }

  /**
   * ヴィランによる攻撃のエフェクトメッセージを実行します
   *
   * @param villainsId ヴィランオブジェクトのid
   * @param movementId ヴィランオブジェクトの技リスト
   * @param damage     ヒーローに与えたダメージ
   * @param heroId     攻撃を行ったヒーロオブジェクトのid
   */
  public void MessageEffectForVillainsAttack(int villainsId, int movementId, int damage,
      int heroId) {
    gameMessage.effectForVillainsAttack(villainsId, movementId, damage, heroId);
  }

  /**
   * ディフェンスメッセージを実行します
   *
   * @param defenceStatus ディフェンスメッセージの種類を選択します
   *                      <ul>
   *                                           <li>0: デェフェンス実行時ディフェンスメッセージ</li>
   *                                           <li>1: ディフェンス実行後ディフェンスメッセージ</li>
   *                      </ul>
   */
  public void MessageDefence(int defenceStatus) {
    int heroId = 1; // 現在はヒーローは一人しか想定していないため、実数リテラルを代入。
    if (defenceStatus == 0) {
      gameMessage.defence(heroId);
    } else {
      gameMessage.defending(heroId);
    }
  }

  /**
   * 回復メッセージを実行します
   *
   * @param heroId 回復を実行したヒーロオブジェクトのid
   * @param val    回復値
   */
  public void MessageRecover(int heroId, int val) {
    gameMessage.recovery(heroId, val);
  }

  /**
   * エラーメッセージを実行します
   */
  public void MessageError() {
    gameMessage.error();
  }

  // ゲームオーバメッセージを実行します
  public void MessageGameOver() {
    if (this.isHeroDead) {
      gameMessage.gameOver(0);
    }
    if (this.isVillainsDead) {
      gameMessage.gameOver(1);
    }
  }

  /**
   * ゲームオーバの判定を行います
   *
   * @return ゲームオーバの場合は true を返します。
   */
  public boolean isGameOver() {
    return aliveHeroList.size() == 0 || aliveVillainList.size() == 0;
  }
}

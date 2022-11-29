/**
 * すべてのゲームメッセージを管理するクラスです
 */
public class GameMessage {

  /**
   * タイトルメッセージを出力します
   */
  public void title() {
    System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
    System.out.println("┃                           ┃");
    System.out.println("┃    Battle of the Boxes    ┃");
    System.out.println("┃                           ┃");
    System.out.println("┃ © 2022 Shunsuke SETOGUCHI ┃");
    System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
  }

  /**
   * ゲーム開始時のメッセージを出力します
   */
  public void start() {
    System.out.println("敵が現れた！");
  }

  /**
   * ステータスメッセージを出力します
   */
  public void status() {
    delimiterLine();
    for (var x : GameMaster.aliveHeroList.values()) {
      System.out.printf("%sのHP: %d", x.getName(), x.getHp());
      System.out.println();
    }
    for (var x : GameMaster.aliveVillainList.values()) {
      System.out.println(x.getName() + " の HP:" + x.getHp());
    }
    System.out.println("+++ 残敵数:" + GameMaster.aliveVillainList.size() + " +++");
    delimiterLine();
  }

  /**
   * アクション選択メッセージを出力します
   */
  public void choiceAction() {
    System.out.println("[ 攻撃:1 防御:2 回復:3 を選択してください ]");
    System.out.print(" >> ");
  }

  /**
   * ヴィラン選択メッセージを出力します
   */
  public void choiceVillain() {

    System.out.print("[ ");
    for (var x : GameMaster.aliveVillainList.values()) {
      System.out.print(x.getName() + ":" + x.getID() + " ");
    }
    System.out.print("どれに攻撃？");
    System.out.println(" ]");
    System.out.print(" >> ");
  }

  /**
   * ヒーローによる攻撃のエフェクトメッセージを出力します
   *
   * @param heroId     ヒーロオブジェクトのid
   * @param movementId ヒーロオブジェクトの技リスト
   * @param damage     ヴィランに与えたダメージ
   * @param villainId  攻撃を行ったヴィランオブジェクトのid
   */
  public void effectForHeroAttack(int heroId, int movementId, int damage, int villainId) {
    String villainName = GameMaster.aliveVillainList.get(villainId).getName();
    String heroName = GameMaster.aliveHeroList.get(heroId).getName();
    String movementName = null;
    for (var x : HeroMovements.values()) {
      if (x.getId() == movementId) {
        movementName = x.getMovementName();
      }
    }
    System.out.printf("%sは「%s」で%sに %d のダメージを与えた", heroName, movementName, villainName, damage);
    System.out.println();
  }

  /**
   * ヴィランによる攻撃のエフェクトメッセージを出力します
   *
   * @param villainsId ヴィランオブジェクトのid
   * @param movementId ヴィランオブジェクトの技リスト
   * @param damage     ヒーローに与えたダメージ
   * @param heroId     攻撃を行ったヒーロオブジェクトのid
   */
  public void effectForVillainsAttack(int villainsId, int movementId, int damage, int heroId) {
    String villainName = GameMaster.aliveVillainList.get(villainsId).getName();
    String heroName = GameMaster.aliveHeroList.get(heroId).getName();
    String movementName = null;
    for (var x : VillainMovements.values()) {
      if (x.getId() == movementId) {
        movementName = x.getMovementName();
      }
    }
    System.out.printf("%sは「%s」で%sに %d のダメージを与えた", villainName, movementName, heroName, damage);
    System.out.println();
  }

  /**
   * デェフェンス実行時ディフェンスメッセージを出力します
   *
   * @param heroId ディフェンスを実行したヒーロオブジェクトのid
   */
  public void defence(int heroId) {
    System.out.printf("%sは防御をした", GameMaster.aliveHeroList.get(heroId).getName());
    System.out.println();
    System.out.println("1ターンの間攻撃を受けない");
  }

  /**
   * ディフェンス実行後ディフェンスメッセージを出力します
   *
   * @param heroId ディフェンスを実行済みのヒーローオブジェクトのid
   */
  public void defending(int heroId) {
    System.out.printf("%sは防御をしているため攻撃を受けなかった", GameMaster.aliveHeroList.get(heroId).getName());
    System.out.println();
  }

  /**
   * 回復メッセージを出力します
   *
   * @param heroId 回復を実行したヒーロオブジェクトのid
   * @param val    回復値
   */
  public void recovery(int heroId, int val) {
    System.out.printf("%sは %d 回復した", GameMaster.aliveHeroList.get(heroId).getName(), val);
    System.out.println();
  }

  /**
   * エラーメッセージを出力します
   */
  public void error() {
    System.out.println("入力が正しくありません。");
    delimiterLine();
  }

  /**
   * ゲームオーバメッセージを出力します
   *
   * @param deadActor HPが0となったアクターの種類
   *                  <ul>
   *                            <li>0: ヒーローのゲームオーバメッセージ</li>
   *                            <li>1: ヴィランのゲームオーバメッセージ</li>
   *                  </ul>
   */
  public void gameOver(int deadActor) {
    if (deadActor == 0) {
      try {
        System.out.println("目の前がまっくらになった...\n");
        System.out.println("   _________    __  _________");
        Thread.sleep(100);
        System.out.println("  / ____/   |  /  |/  / ____/");
        Thread.sleep(100);
        System.out.println(" / / __/ /| | / /|_/ / __/   ");
        Thread.sleep(100);
        System.out.println("/ /_/ / ___ |/ /  / / /___   ");
        Thread.sleep(100);
        System.out.println("\\____/_/  |_/_/  /_/_____/   ");
        Thread.sleep(100);
        System.out.println();
        Thread.sleep(100);
        System.out.println("   ____ _    ____________ ");
        Thread.sleep(100);
        System.out.println("  / __ \\ |  / / ____/ __ \\");
        Thread.sleep(100);
        System.out.println(" / / / / | / / __/ / /_/ /");
        Thread.sleep(100);
        System.out.println("/ /_/ /| |/ / /___/ _, _/ ");
        Thread.sleep(100);
        System.out.println("\\____/ |___/_____/_/ |_|  ");
      } catch (InterruptedException e) {
        for (int i = 0; i < 20; i++) {
          System.out.println();
        }
        System.out.flush();
      }
    }
    if (deadActor == 1) {
      try {
        System.out.println("__  ______  __  __");
        Thread.sleep(100);
        System.out.println("\\ \\/ / __ \\/ / / /");
        Thread.sleep(100);
        System.out.println(" \\  / / / / / / / ");
        Thread.sleep(100);
        System.out.println(" / / /_/ / /_/ /  ");
        Thread.sleep(100);
        System.out.println("/_/\\____/\\____/   ");
        System.out.println();
        Thread.sleep(100);
        System.out.println(" _       _______   __");
        Thread.sleep(100);
        System.out.println("| |     / /  _/ | / /");
        Thread.sleep(100);
        System.out.println("| | /| / // //  |/ / ");
        Thread.sleep(100);
        System.out.println("| |/ |/ // // /|  /  ");
        Thread.sleep(100);
        System.out.println("|__/|__/___/_/ |_/   ");
        Thread.sleep(100);
      } catch (InterruptedException e) {
        for (int i = 0; i < 20; i++) {
          System.out.println();
        }
        System.out.flush();
      }
    }
  }

  /**
   * メッセージを区切る区切り線を出力します
   */
  private void delimiterLine() {
    System.out.println("-----------------------------------------");
  }

}

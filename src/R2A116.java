import java.util.Scanner;

/**
 * ゲームのメインロジックのクラスです
 *
 * @author shunsuke SETOGUCHI
 */
public class R2A116 {

  public static void main(String[] args) {
    var gameMaster = new GameMaster();
    int action;
    int villainNum;

    gameMaster.MessageTitle();
    gameMaster.startGame();
    gameMaster.MessageStart();
    gameMaster.MessageStatus();

    while (true) {
      Scanner sc = new Scanner(System.in);
      gameMaster.MessageChoiceAction();
      try {
        action = sc.nextInt();
        if (action < 1 || 3 < action) {
          throw new IllegalArgumentException();
        }
      } catch (RuntimeException e) {
        gameMaster.MessageError();
        sc.reset();
        continue;
      }

      switch (action) {
        // attack
        case 1:
          while (true) {
            gameMaster.MessageChoiceVillain();
            try {
              int[] villainsId = new int[GameMaster.aliveVillainList.size()];
              int idx = 0;
              boolean isPresent = false;

              for (var x : GameMaster.aliveVillainList.values()) {
                villainsId[idx] = x.getID();
                idx = idx + 1;
              }
              villainNum = sc.nextInt();
              for (var x : villainsId) {
                if (x == villainNum) {
                  isPresent = true;
                  break;
                }
              }
              if (isPresent) {
                gameMaster.attack(villainNum);
                sc.reset();
                break;
              } else {
                throw new IllegalArgumentException();
              }
            } catch (RuntimeException e) {
              gameMaster.MessageError();
              sc.reset();
            }
          }
          break;
        case 2:
          gameMaster.defence();
          break;
        case 3:
          gameMaster.recovery();
          break;
      }
      if (!(gameMaster.isDefence())) {
        gameMaster.organizeDeceased();
        gameMaster.villainsAttack();
      } else {
        gameMaster.MessageDefence(1);
        gameMaster.unlockDefence();
      }
      gameMaster.organizeDeceased();
      if (gameMaster.isGameOver()) {
        gameMaster.MessageGameOver();
        break;
      }
    }
  }
}
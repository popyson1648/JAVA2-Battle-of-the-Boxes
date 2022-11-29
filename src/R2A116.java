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

    gameMaster.messageTitle();
    gameMaster.startGame();
    gameMaster.messageStart();
    gameMaster.messageStatus();

    while (true) {
      Scanner sc = new Scanner(System.in);
      gameMaster.messageChoiceAction();
      try {
        action = sc.nextInt();
        if (action < 1 || 3 < action) {
          throw new IllegalArgumentException();
        }
      } catch (RuntimeException e) {
        gameMaster.messageError();
        sc.reset();
        continue;
      }

      switch (action) {
        // attack
        case 1:
          while (true) {
            gameMaster.messageChoiceVillain();
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
              gameMaster.messageError();
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
        gameMaster.messageDefence(1);
        gameMaster.unlockDefence();
      }
      gameMaster.organizeDeceased();
      if (gameMaster.isGameOver()) {
        gameMaster.messageGameOver();
        break;
      }
    }
  }
}
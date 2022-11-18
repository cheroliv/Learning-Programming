package fr.chalodss.classes;

/**
 * @author Charles T.
 *
 */
enum EState {

  X(1), O(-1), E(0);

  int value;

  EState(int value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

}

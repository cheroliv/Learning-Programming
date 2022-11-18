package fr.chalodss.classes;

import java.util.List;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Charles T.
 * 
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor(staticName = "of")
@EqualsAndHashCode(of = {"name", "power"})
@ToString(of = {"name", "power"})
@Slf4j
public final class Warrior {

  @NonNull
  String  name;
  @NonNull
  Double  power;
  @NonNull
  List<?> weapons;

  public void speak() {
    log.info(this + " ready to fight.");
  }

}

package parsing.plants.pickers;

import instructions.concrete.ConDecree;
import instructions.concrete.base.*;
import instructions.concrete.extended.ExecuteScript;
import instructions.concrete.extended.FilterContainsName;
import instructions.concrete.extended.MaxByDate;
import instructions.concrete.extended.SumOfAnnualTurnover;
import instructions.rotten.IClued;
import instructions.rotten.ITitled;
import instructions.rotten.RawDecree;
import instructions.rotten.base.*;
import instructions.rotten.extended.RawExecuteScript;
import instructions.rotten.extended.RawFilterContainsName;
import instructions.rotten.extended.RawMaxByDate;
import instructions.rotten.extended.RawSumOfAnnualTurnover;
import parsing.customer.distro.LimboKeeper;
import parsing.supplying.interpreter.Shell;

/**
 *
 */
public final class JustCommandBuilder {
  /**
   *
   * @param c
   * @param receiver
   * @param parser
   * @return
   */
  public ConDecree make(RawDecree c, LimboKeeper receiver) {
    if (c instanceof ITitled) {
      String name = ((ITitled) c).Name();
      if (c instanceof RawExecuteScript)
        return new ExecuteScript(receiver, name);
      else return new FilterContainsName(receiver, name);
    } else if (c instanceof IClued) {
      Integer key = ((IClued) c).Key();
      return new RemoveKey(receiver, key);
    } else {
      if (c instanceof RawHelp) return new Help(receiver);
      else if (c instanceof RawClear) return new Clear(receiver);
      else if (c instanceof RawInfo) return new Info(receiver);
      else if (c instanceof RawSave) return new Save(receiver);
      else if (c instanceof RawShow) return new Show(receiver);
      else if (c instanceof RawMaxByDate) return new MaxByDate(receiver);
      else if (c instanceof RawSumOfAnnualTurnover) return new SumOfAnnualTurnover(receiver);
      else return new Help(receiver);
    }
  }
}

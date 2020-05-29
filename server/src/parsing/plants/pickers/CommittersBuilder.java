package parsing.plants.pickers;

import entities.Organization;
import instructions.concrete.ConDecree;
import instructions.concrete.base.Committer;
import instructions.concrete.base.Help;
import instructions.concrete.base.Insert;
import instructions.concrete.base.Update;
import instructions.concrete.extended.RemoveLower;
import instructions.concrete.extended.ReplaceIfGreater;
import instructions.concrete.extended.ReplaceIfLower;
import instructions.rotten.IClued;
import instructions.rotten.IJunked;
import instructions.rotten.ITitled;
import instructions.rotten.RawCommitter;
import instructions.rotten.base.RawInsert;
import instructions.rotten.base.RawRemoveKey;
import instructions.rotten.base.RawUpdate;
import instructions.rotten.extended.RawRemoveLower;
import instructions.rotten.extended.RawReplaceIf;
import instructions.rotten.extended.RawReplaceIfGreater;
import parsing.customer.Receiver;
import parsing.customer.distro.LimboKeeper;
import parsing.plants.Factory;

/**
 * Фабрика по созданию "конкретных" комманд, манипулирующих объектом организации.
 *  @author Come_1LL_F00 aka Lenar Khannanov
 *  @author Leargy aka Anton Sushkevich
 */
public final class CommittersBuilder {
  /**
   * Метор формирования "конкретной" команды.
   * @param c RawCommitter
   * @param r LimboKeeper
   * @param f Factory of Organization
   * @return ConDecree
   */
  public final ConDecree make(RawCommitter c, LimboKeeper r, Factory<Organization> f) {
    Organization o = f.make(((IJunked) c).Params());
    if (c instanceof IClued) {
      Integer p = ((IClued) c).Key();
      if (c instanceof RawInsert)
        return new Insert(r, p, o);
      else if (c instanceof RawUpdate)
        return new Update(r, p, o);
      else if (c instanceof RawReplaceIfGreater)
          return new ReplaceIfGreater(r, p, o);
      else return new ReplaceIfLower(r, p, o);
    } else if (c instanceof RawRemoveLower) return new RemoveLower(r, o);
    else return new Help(r);
  }
}

package parsing.plants.pickers;

import entities.Organization;
import instructions.concrete.base.Committer;
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

public final class CommittersBuilder {
  public final Committer make(RawCommitter c, LimboKeeper r, Factory<Organization> f) {
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
    } else return new RemoveLower(r, o);
  }
}

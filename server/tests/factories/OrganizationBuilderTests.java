package factories;

import communication.Component;
import communication.Mediator;
import communication.Segment;
import communication.Valuable;
import entities.Junker;
import entities.Organization;
import parsing.Resolver;
import parsing.SubProcessController;
import parsing.plants.Factory;
import parsing.plants.OrganizationBuilder;

public final class OrganizationBuilderTests implements Mediator {
  private final Resolver magiV = new SubProcessController(this);
  private final Factory<Organization> factory = new OrganizationBuilder(magiV);

  public void NullBuilding() {
    Organization result = factory.make(null);
    System.out.println(result);
    assert (result == null) : "Organization factory argument is null";
    System.out.println("Test Null Safety passed");
  }

  public void AllEmptyArguments() {
    Organization result = factory.make(new Junker(null, null, null, null));
    System.out.println(result);
    assert (result == null) : "All arguments null";
    System.out.println("Test Empty arguments passed");
  }

  public void EmptyDigitParameters() {
    Organization result = factory.make(new Junker(new long[]{}, null, null, null));
    System.out.println(result);
    assert (result == null) : "Empty long arguments";
    System.out.println("Test Empty digit parameters passed");
  }

  public void EmptyCogitParameters() {
    Organization result = factory.make(new Junker(null, new double[]{}, null, null));
    System.out.println(result);
    assert (result == null) : "Empty double arguments";
    System.out.println("Test Empty cogit parameters passed");
  }

  public void EmptyLinesParameters() {
    Organization result = factory.make(new Junker(null, null, new String[]{}, null));
    System.out.println(result);
    assert (result == null) : "Empty String arguments";
    System.out.println("Test Empty lines parameters passed");
  }

  public void EmptyGutsParameters() {
    Organization result = factory.make(new Junker(null, null, null, new Junker[]{}));
    System.out.println(result);
    assert (result == null) : "Empty Junker arguments";
    System.out.println("Test Empty guts parameters passed");
  }

  @Override
  public void notify(Component sender, Segment data) {
    System.out.println(data.getData());
  }
}

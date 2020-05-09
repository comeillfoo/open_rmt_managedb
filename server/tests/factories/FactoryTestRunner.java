package factories;

public final class FactoryTestRunner {
  public static void main(String[] args) {
    runOrganizationTests();
    System.out.println("All OrganizationBuilder tests passed");
    runAddressTests();
    System.out.println("All AddressBuilder tests passed");
    runCoordinatesTests();
    System.out.println("All CoordinatesBuilder tests passed");
    runLocationTests();
    System.out.println("All LocationBuilder tests passed");
    System.out.println("Congratulation! All tests passed");
  }

  public static void runOrganizationTests() {
    OrganizationBuilderTests tests = new OrganizationBuilderTests();
    tests.NullBuilding();
    tests.AllEmptyArguments();
    tests.EmptyDigitParameters();
    tests.EmptyCogitParameters();
    tests.EmptyLinesParameters();
    tests.EmptyGutsParameters();
  }

  public static void runAddressTests() {

  }

  public static void runCoordinatesTests() {

  }

  public static void runLocationTests() {

  }
}

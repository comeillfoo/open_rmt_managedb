package dataSection.enumSection;

/**
 * Класс enum хронящий типы организаций.
 * @author Leargy aka Anton Sushkevich
 * @author Come_1LL_F00 aka Lenar Khannanov
 */
public enum OrganizationType {
        PUBLIC,
        TRUST,
        PRIVATE_LIMITED_COMPANY,
        OPEN_JOINT_STOCK_COMPANY;

        public static String getValues() {
            StringBuilder value = new StringBuilder();
            value.append("\n──────Organization types───────\n");
            value.append("----------- " + PUBLIC + " ------------\n");
            value.append("----------- " + TRUST + " -------------\n");
            value.append("--- " + PRIVATE_LIMITED_COMPANY + " ---\n");
            value.append("--- " + OPEN_JOINT_STOCK_COMPANY + " --\n");
            value.append("───────────────────────────────\n");
            return value.toString();
        }
}

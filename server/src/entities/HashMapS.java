package entities;

import sun.nio.cs.CharsetMapping;

import java.util.*;

public class HashMapS<K, V extends Organization> extends HashMap {

    public LinkedHashMap<Integer, Organization>  sortByNameOfOrganization() { //Вот эта вот херня была придумана Укропами, была взята прям с боем.
        ArrayList<Organization> organizationArrayList = new ArrayList<>();
        LinkedHashMap<Integer, Organization> newOrganizationHashMap = new LinkedHashMap<>();
        Iterator<Entry<Integer, Organization>> iter1 = ((HashMap<Integer,Organization>)this).entrySet().iterator();
        while (iter1.hasNext()) {
            organizationArrayList.add(iter1.next().getValue());
        }

        organizationArrayList.sort(new Comparator<Organization>() {
            @Override
            public int compare(Organization o1, Organization o2) {
                return o1.Name().compareTo(o2.Name());
            }
        });

        Iterator<Organization> iter2 = organizationArrayList.iterator();
        while (iter2.hasNext()) {
            Organization organization = iter2.next();
            newOrganizationHashMap.put(organization.Key(),organization);
        }
        return newOrganizationHashMap;
    }
}

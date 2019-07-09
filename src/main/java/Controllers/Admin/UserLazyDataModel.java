/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Admin;

//import Models.UserDetails;
import Models.Users;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author hp_user
 */
public class UserLazyDataModel extends LazyDataModel<Users> {

    private List<Users> datasource;

    public UserLazyDataModel(List<Users> datasource) {
        this.datasource = datasource;
    }

    @Override
    public Object getRowKey(Users object) {
        return object.getUserId();
    }

    @Override
    public Users getRowData(String rowKey) {

        for (Users obj : datasource) {
            if (obj.getUserId().toString().equals(rowKey)) {
                return obj;
            }
        }

        return null;
    }

    @Override
    public List<Users> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        List<Users> data = new ArrayList<>();

        //filter
        for (Users user : datasource) {
            boolean match = true;

            if (filters != null) {
//                System.out.println("Filters inside");
                for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
//                    System.out.println("foreach inside");

                    try {
                        String filterProperty = it.next();
                        Object filterValue = filters.get(filterProperty);


                        Class userClass = user.getClass();
                        Field declaredField = userClass.getDeclaredField(filterProperty);
                        declaredField.setAccessible(true);
                        Object userObject = declaredField.get(user);
                        String fieldValue = String.valueOf(userObject);
                        declaredField.setAccessible(false);

                        if (filterValue == null || fieldValue.toUpperCase().startsWith(filterValue.toString().toUpperCase())) {
//                            System.out.println("first if inside");

                            match = true;
                        } else {
//                            System.out.println("first else inside");

                            match = false;
                            break;
                        }
                    } catch (IllegalArgumentException | NoSuchFieldException | IllegalAccessException | SecurityException e) {
                        System.out.println("exception throwed\n" + e.toString());

                        match = false;
                    }
                }
            }

            if (match) {
//                System.out.println("data eklendi");

                data.add(user);
            }
        }

        //sort
        if (sortField != null) {
//            System.out.println("sorfield != null");

            Collections.sort(data, new LazySorterUsers(sortField, sortOrder));
        }

        //rowCount
        int dataSize = data.size();
        this.setRowCount(dataSize);

        //paginate
        if (dataSize > pageSize) {
            try {
                return data.subList(first, first + pageSize);
            } catch (IndexOutOfBoundsException e) {
                return data.subList(first, first + (dataSize % pageSize));
            }
        } else {
            return data;
        }
    }

}

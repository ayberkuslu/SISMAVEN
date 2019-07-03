/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Admin;

//import Models.UserDetails;
import Models.Users;
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
public class UserLazyDataModel extends LazyDataModel<Users>  {


    private List<Users> datasource;

    public UserLazyDataModel(List<Users> datasource) {
        this.datasource = datasource;
    }
    
    @Override
    public Object getRowKey(Users object) {
        return object.getUserId();
    }
    
     @Override
    public Users  getRowData(String rowKey) {
        try {
 
            @SuppressWarnings("unchecked")
            List<Users> list = (List<Users>) getWrappedData();
            for (Users obj : list) {
                if (obj.getUserId().toString().equals(rowKey))
                    return obj;
            }
 
        } catch (Exception e) {
 
            System.out.println("getRowData method error" + e.getMessage());
            e.printStackTrace();
        }
 
        return null;
    }
    
@Override
    public List<Users> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) {
        List<Users> data = new ArrayList<Users>();
 
        //filter
        for(Users user : datasource) {
            boolean match = true;
 
            if (filters != null) {
                for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
                    try {
                        String filterProperty = it.next();
                        Object filterValue = filters.get(filterProperty);
                        String fieldValue = String.valueOf(user.getClass().getField(filterProperty).get(user));
 
                        if(filterValue == null || fieldValue.startsWith(filterValue.toString())) {
                            match = true;
                    }
                    else {
                            match = false;
                            break;
                        }
                    } catch(Exception e) {
                        match = false;
                    }
                }
            }
 
            if(match) {
                data.add(user);
            }
        }
 
        //sort
        if(sortField != null) {
            Collections.sort(data, new LazySorterUsers(sortField, sortOrder));
        }
 
        //rowCount
        int dataSize = data.size();
        this.setRowCount(dataSize);
 
        //paginate
        if(dataSize > pageSize) {
            try {
                return data.subList(first, first + pageSize);
            }
            catch(IndexOutOfBoundsException e) {
                return data.subList(first, first + (dataSize % pageSize));
            }
        }
        else {
            return data;
        }
    }

}

    


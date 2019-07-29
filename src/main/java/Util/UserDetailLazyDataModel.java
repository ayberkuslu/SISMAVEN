/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import Models.UserDetails;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
/**
 *
 * @author Ayberk
 */
public class UserDetailLazyDataModel extends LazyDataModel<UserDetails>  {


    private final List<UserDetails> datasource;

    public UserDetailLazyDataModel(List<UserDetails> datasource) {
        this.datasource = datasource;
    }
    
    @Override
    public Object getRowKey(UserDetails object) {
        return object.getUserId().getUserId();
    }
    
     @Override
    public UserDetails  getRowData(String rowKey) {
        try {
 
            @SuppressWarnings("unchecked")
            List<UserDetails> list = (List<UserDetails>) getWrappedData();
            for (UserDetails obj : list) {
                if (obj.getUserId().getUserId().toString().equals(rowKey))
                    return obj;
            }
 
        } catch (Exception e) {
 
            System.out.println("getRowData method error" + e.getMessage());
        }
 
        return null;
    }
    
@Override
    public List<UserDetails> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) {
        List<UserDetails> data = new ArrayList<>();
 
        //filter
        for(UserDetails userDetail : datasource) {
            boolean match = true;
 
            if (filters != null) {
                for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
                    try {
                        String filterProperty = it.next();
                        Object filterValue = filters.get(filterProperty);
                        String fieldValue = String.valueOf(userDetail.getClass().getField(filterProperty).get(userDetail));
 
                        if(filterValue == null || fieldValue.startsWith(filterValue.toString())) {
                            match = true;
                    }
                    else {
                            match = false;
                            break;
                        }
                    } catch(IllegalAccessException | IllegalArgumentException | NoSuchFieldException | SecurityException e) {
                        match = false;
                    }
                }
            }
 
            if(match) {
                data.add(userDetail);
            }
        }
 
        //sort
        if(sortField != null) {
            Collections.sort(data, new LazySorterUserDetails(sortField, sortOrder));
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

/*
 * Copyright (C) 2019 Ayberk.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package Util;

import Models.Logs;
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
 * @author Ayberk
 */
public class LogsLazyDataModel extends LazyDataModel<Logs> {
    
     private List<Logs> datasource;

    public LogsLazyDataModel(List<Logs> datasource) {
        this.datasource = datasource;
    }

    @Override
    public Object getRowKey(Logs object) {
        return object.getLogId();
    }

    @Override
    public Logs getRowData(String rowKey) {

        for (Logs obj : datasource) {
            if (obj.getLogId().toString().equals(rowKey)) {
                return obj;
            }
        }

        return null;
    }
    
    
        @Override
    public List<Logs> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        List<Logs> data = new ArrayList<>();

        //filter
        for (Logs log : datasource) {
            boolean match = true;

            if (filters != null) {
                for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {

                    try {
                        String filterProperty = it.next();
                        Object filterValue = filters.get(filterProperty);


                        Class logClass = log.getClass();
                        Field declaredField = logClass.getDeclaredField(filterProperty);
                        declaredField.setAccessible(true);
                        Object logObject = declaredField.get(log);
                        String fieldValue = String.valueOf(logObject);
                        declaredField.setAccessible(false);

                        if (filterValue == null || fieldValue.toUpperCase().startsWith(filterValue.toString().toUpperCase())) {
                            match = true;
                        } else {

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

                data.add(log);
            }
        }

        //sort
        if (sortField != null) {
//            System.out.println("sorfield != null");

            Collections.sort(data, new LazySorterLogs(sortField, sortOrder));
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

    

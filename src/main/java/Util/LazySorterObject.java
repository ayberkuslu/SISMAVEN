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

import java.util.Comparator;
import org.primefaces.model.SortOrder;

/**
 *
 * @author Ayberk
 */
public class LazySorterObject implements Comparator<Object> {

    private final String sortField;

    private final SortOrder sortOrder;

    public LazySorterObject(String sortField, SortOrder sortOrder) {
        this.sortField = sortField;
        this.sortOrder = sortOrder;
    }

    @Override
    public int compare(Object object1, Object object2) {
        try {
            // SHOULD BE CHANGED ! 
            int value = object1.hashCode() - object2.hashCode();
            return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
        } catch (Exception e) {
            throw new RuntimeException("for object compare @");
        }
    }

}

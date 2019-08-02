/*
 * Copyright (C) 2019 hp_user.
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
import java.util.Comparator;
import org.primefaces.model.SortOrder;

/**
 *
 * @author hp_user
 */
public class LazySorterLogs implements Comparator<Logs> {
    
    
    
    
    private final String sortField;

    private final SortOrder sortOrder;

    public LazySorterLogs(String sortField, SortOrder sortOrder) {
        this.sortField = sortField;
        this.sortOrder = sortOrder;
    }

    @Override
    public int compare(Logs log1, Logs log2) {
        try {
            int value = log1.getLogId() - log2.getLogId();
            return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
        } catch (Exception e) {
            throw new RuntimeException("for Logs compare @");
        }
    }
    
}

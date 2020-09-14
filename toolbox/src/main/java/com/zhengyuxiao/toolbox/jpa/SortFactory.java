package com.zhengyuxiao.toolbox.jpa;

import org.springframework.data.domain.Sort;

import java.util.LinkedList;
import java.util.List;

/**
 * @author xzy
 * @date 2020-09-14 14:12
 * 说明：获取用于分页查询的 Pageable 参数
 */
public class SortFactory {

    public static final String ASCENDING_UP = "ASC";
    public static final String ASCENDING_DOWN = "asc";
    public static final String DESCENDING_UP = "DESC";
    public static final String DESCENDING_DOWN = "desc";

    public static Sort getSort(String direction, String columnName) {
        if (ASCENDING_UP.equals(direction) || ASCENDING_DOWN.equals(direction)) {
            return new Sort(Sort.Direction.ASC, columnName);
        } else if (DESCENDING_UP.equals(direction) || DESCENDING_DOWN.equals(direction)) {
            return new Sort(Sort.Direction.DESC, columnName);
        } else {
            return null;
        }
    }

    public static Sort getSort(String direction, List<String> columnNameList) {
        if (columnNameList == null || columnNameList.size() == 0) {
            throw new IllegalArgumentException("未指定排序字段");
        }

        List<Sort> sortList = new LinkedList<>();
        for (String columnName : columnNameList) {
            sortList.add(getSort(direction, columnName));
        }

        Sort finalSort = null;
        for (Sort sort : sortList) {
            if (sort != null) {
                finalSort = finalSort != null ? finalSort.and(sort) : sort;
            }
        }
        return finalSort;
    }
}

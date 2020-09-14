package com.zhengyuxiao.toolbox.jpa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * @author xzy
 * @date 2020-09-14 14:08
 * 说明：获取用于分页查询的 Pageable 参数
 */
public class PageFactory {

    /**
     * 默认页码
     */
    private static final int DEFAULT_PAGE_NUMBER = 0;

    /**
     * 默认单页数据条数
     */
    private static final int DEFAULT_PAGE_SIZE = 30;

    /**
     * @param pageNumber - 页码
     * @param pageSize   - 页数据量
     */
    public static Pageable getPageable(Integer pageNumber, Integer pageSize) {
        return PageRequest.of(pageNumber, pageSize);
    }

    /**
     * @param pageNumber - 页码
     * @param pageSize   - 页数据量
     * @param sort       - 排序信息
     */
    public static Pageable getPageable(Integer pageNumber, Integer pageSize, Sort sort) {
        return sort == null ? PageRequest.of(pageNumber, pageSize) : PageRequest.of(pageNumber, pageSize, sort);
    }

    /**
     * @param pageNumber - 页码
     * @param pageSize   - 页数据量
     * @param direction  - 排序方向
     * @param columnName - 排序字段
     */
    public static Pageable getPageable(Integer pageNumber, Integer pageSize, String direction, String columnName) {
        if (pageNumber == null || pageSize == 0) {
            pageSize = DEFAULT_PAGE_SIZE;
        }

        if (pageNumber == null || pageNumber < 0) {
            pageNumber = DEFAULT_PAGE_NUMBER;
        }

        Sort sort = SortFactory.getSort(direction, columnName);
        return sort == null ? PageRequest.of(pageNumber, pageSize) : PageRequest.of(pageNumber, pageSize, sort);
    }

    /**
     * @param pageNumber  - 页码
     * @param pageSize    - 页数据量
     * @param direction   - 排序方向
     * @param columnName  - 排序字段
     * @param defaultSort - 默认、固定排序
     */
    public static Pageable getPageable(Integer pageNumber, Integer pageSize, String direction, String columnName, Sort defaultSort) {
        if (pageNumber == null || pageSize == 0) {
            pageSize = DEFAULT_PAGE_SIZE;
        }

        if (pageNumber == null || pageNumber < 0) {
            pageNumber = DEFAULT_PAGE_NUMBER;
        }

        Sort sort = SortFactory.getSort(direction, columnName);
        if (sort != null) {
            defaultSort = defaultSort.and(sort);
        }

        return PageRequest.of(pageNumber, pageSize, defaultSort);
    }

    public static Pageable getPageable(Page page) {
        return getPageable(page.getPageNumber(), page.getPageSize(), page.getSortDirection(), page.getSortColumn());
    }

    public static Pageable getPageable(Page page, Sort defaultSort) {
        return getPageable(page.getPageNumber(), page.getPageSize(), page.getSortDirection(), page.getSortColumn(), defaultSort);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Page {
        private Integer pageNumber = 0;
        private Integer pageSize = 10;
        private String sortDirection = "";
        private String sortColumn = "";

        /**
         * 判断是否未设置排序条件
         */
        public boolean sortCriteriaNotSet() {
            return sortColumn == null || "".equals(sortColumn);
        }
    }
}

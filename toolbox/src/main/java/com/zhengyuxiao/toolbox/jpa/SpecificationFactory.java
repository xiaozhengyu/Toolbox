package com.zhengyuxiao.toolbox.jpa;

import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

/**
 * @author xzy
 * @date 2020-06-18 18:57
 * 说明：
 */
public class SpecificationFactory {
    /**
     * like查询
     *
     * @param attribute - 用于匹配的字段
     * @param value     -该字段需要匹配的值
     */
    public static <T> Specification<T> like(String attribute, String value) {
        return (root, query, cb) -> cb.like(root.get(attribute), "%" + value + "%");
    }

    public static <T> Specification<T> like(String attribute, Date value) {
        return (root, query, cb) -> cb.like(root.get(attribute), "%" + value + "%");
    }

    /**
     * not like查询
     *
     * @param attribute - 用于匹配的字段
     * @param value     -该字段需要匹配的值
     */
    public static <T> Specification<T> notLike(String attribute, String value) {
        return (root, query, cb) -> cb.notLike(root.get(attribute), "%" + value + "%");
    }

    /**
     * equal查询
     *
     * @param attribute - 用于匹配的字段
     * @param value     - 用于匹配的值
     */
    public static <T> Specification<T> equal(String attribute, Object value) {
        return (root, query, cb) -> cb.equal(root.get(attribute), value);
    }

    public static <T> Specification<T> equal(String attribute, Date value) {
        return (root, query, cb) -> cb.equal(root.get(attribute), value);
    }

    public static <T> Specification<T> notEqual(String attribute, Object value) {
        return (root, query, cb) -> cb.notEqual(root.get(attribute), value);
    }

    /**
     * between查询
     *
     * @param attribute - 用于匹配的字段
     * @param min       - 区间下限
     * @param max       - 区间上限
     */
    public static <T> Specification<T> between(String attribute, int min, int max) {
        return (root, query, cb) -> cb.between(root.get(attribute), min, max);
    }

    public static <T> Specification<T> between(String attribute, double min, double max) {
        return (root, query, cb) -> cb.between(root.get(attribute), min, max);
    }

    public static <T> Specification<T> between(String attribute, Date min, Date max) {
        return (root, query, cb) -> cb.between(root.get(attribute), min, max);
    }

    /**
     * in查询
     *
     * @param attribute - 用于匹配的属性
     * @param c         - 满足条件的属性值集合
     */
    public static <T> Specification<T> in(String attribute, Collection c) {
        return (root, query, cb) -> root.get(attribute).in(c);
    }

    /**
     * 大于等于
     *
     * @param attribute - 用于匹配的属性
     * @param date      - 日期值
     */
    public static <T> Specification<T> greaterOrEqual(String attribute, Date date) {
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get(attribute), date);
    }

    /**
     * 小于等于
     *
     * @param attribute - 用于匹配的属性
     * @param date      - 日期值
     */
    public static <T> Specification<T> smallerOrEqual(String attribute, Date date) {
        return (root, query, cb) -> cb.lessThanOrEqualTo(root.get(attribute), date);
    }

    /**
     * 大于
     *
     * @param attribute - 用于匹配的属性
     * @param date      - 日期值
     */
    public static <T> Specification<T> greaterThan(String attribute, Date date) {
        return (root, query, cb) -> cb.greaterThan(root.get(attribute), date);
    }

    public static <T> Specification<T> greaterThan(String attribute, Integer date) {
        return (root, query, cb) -> cb.greaterThan(root.get(attribute), date);
    }

    public static <T> Specification<T> greaterThan(String attribute, Double date) {
        return (root, query, cb) -> cb.greaterThan(root.get(attribute), date);
    }

    public static <T> Specification<T> greaterThan(String attribute, BigDecimal date) {
        return (root, query, cb) -> cb.greaterThan(root.get(attribute), date);
    }

    /**
     * 小于
     *
     * @param attribute - 用于匹配的属性
     * @param date      - 日期值
     */
    public static <T> Specification<T> smallerThan(String attribute, Date date) {
        return (root, query, cb) -> cb.lessThan(root.get(attribute), date);
    }
}

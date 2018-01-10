package com.it.project.fee.entity;

import java.util.ArrayList;
import java.util.List;

public class RideFeeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RideFeeExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andMinUnitIsNull() {
            addCriterion("min_unit is null");
            return (Criteria) this;
        }

        public Criteria andMinUnitIsNotNull() {
            addCriterion("min_unit is not null");
            return (Criteria) this;
        }

        public Criteria andMinUnitEqualTo(Integer value) {
            addCriterion("min_unit =", value, "minUnit");
            return (Criteria) this;
        }

        public Criteria andMinUnitNotEqualTo(Integer value) {
            addCriterion("min_unit <>", value, "minUnit");
            return (Criteria) this;
        }

        public Criteria andMinUnitGreaterThan(Integer value) {
            addCriterion("min_unit >", value, "minUnit");
            return (Criteria) this;
        }

        public Criteria andMinUnitGreaterThanOrEqualTo(Integer value) {
            addCriterion("min_unit >=", value, "minUnit");
            return (Criteria) this;
        }

        public Criteria andMinUnitLessThan(Integer value) {
            addCriterion("min_unit <", value, "minUnit");
            return (Criteria) this;
        }

        public Criteria andMinUnitLessThanOrEqualTo(Integer value) {
            addCriterion("min_unit <=", value, "minUnit");
            return (Criteria) this;
        }

        public Criteria andMinUnitIn(List<Integer> values) {
            addCriterion("min_unit in", values, "minUnit");
            return (Criteria) this;
        }

        public Criteria andMinUnitNotIn(List<Integer> values) {
            addCriterion("min_unit not in", values, "minUnit");
            return (Criteria) this;
        }

        public Criteria andMinUnitBetween(Integer value1, Integer value2) {
            addCriterion("min_unit between", value1, value2, "minUnit");
            return (Criteria) this;
        }

        public Criteria andMinUnitNotBetween(Integer value1, Integer value2) {
            addCriterion("min_unit not between", value1, value2, "minUnit");
            return (Criteria) this;
        }

        public Criteria andFeeIsNull() {
            addCriterion("fee is null");
            return (Criteria) this;
        }

        public Criteria andFeeIsNotNull() {
            addCriterion("fee is not null");
            return (Criteria) this;
        }

        public Criteria andFeeEqualTo(Long value) {
            addCriterion("fee =", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeNotEqualTo(Long value) {
            addCriterion("fee <>", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeGreaterThan(Long value) {
            addCriterion("fee >", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeGreaterThanOrEqualTo(Long value) {
            addCriterion("fee >=", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeLessThan(Long value) {
            addCriterion("fee <", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeLessThanOrEqualTo(Long value) {
            addCriterion("fee <=", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeIn(List<Long> values) {
            addCriterion("fee in", values, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeNotIn(List<Long> values) {
            addCriterion("fee not in", values, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeBetween(Long value1, Long value2) {
            addCriterion("fee between", value1, value2, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeNotBetween(Long value1, Long value2) {
            addCriterion("fee not between", value1, value2, "fee");
            return (Criteria) this;
        }

        public Criteria andBickTypeIsNull() {
            addCriterion("bick_type is null");
            return (Criteria) this;
        }

        public Criteria andBickTypeIsNotNull() {
            addCriterion("bick_type is not null");
            return (Criteria) this;
        }

        public Criteria andBickTypeEqualTo(Byte value) {
            addCriterion("bick_type =", value, "bickType");
            return (Criteria) this;
        }

        public Criteria andBickTypeNotEqualTo(Byte value) {
            addCriterion("bick_type <>", value, "bickType");
            return (Criteria) this;
        }

        public Criteria andBickTypeGreaterThan(Byte value) {
            addCriterion("bick_type >", value, "bickType");
            return (Criteria) this;
        }

        public Criteria andBickTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("bick_type >=", value, "bickType");
            return (Criteria) this;
        }

        public Criteria andBickTypeLessThan(Byte value) {
            addCriterion("bick_type <", value, "bickType");
            return (Criteria) this;
        }

        public Criteria andBickTypeLessThanOrEqualTo(Byte value) {
            addCriterion("bick_type <=", value, "bickType");
            return (Criteria) this;
        }

        public Criteria andBickTypeIn(List<Byte> values) {
            addCriterion("bick_type in", values, "bickType");
            return (Criteria) this;
        }

        public Criteria andBickTypeNotIn(List<Byte> values) {
            addCriterion("bick_type not in", values, "bickType");
            return (Criteria) this;
        }

        public Criteria andBickTypeBetween(Byte value1, Byte value2) {
            addCriterion("bick_type between", value1, value2, "bickType");
            return (Criteria) this;
        }

        public Criteria andBickTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("bick_type not between", value1, value2, "bickType");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}
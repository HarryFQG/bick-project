package com.it.project.wallet.entity;

import java.util.ArrayList;
import java.util.List;

public class WalletExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public WalletExample() {
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

        public Criteria andWalletIdIsNull() {
            addCriterion("wallet_id is null");
            return (Criteria) this;
        }

        public Criteria andWalletIdIsNotNull() {
            addCriterion("wallet_id is not null");
            return (Criteria) this;
        }

        public Criteria andWalletIdEqualTo(Long value) {
            addCriterion("wallet_id =", value, "walletId");
            return (Criteria) this;
        }

        public Criteria andWalletIdNotEqualTo(Long value) {
            addCriterion("wallet_id <>", value, "walletId");
            return (Criteria) this;
        }

        public Criteria andWalletIdGreaterThan(Long value) {
            addCriterion("wallet_id >", value, "walletId");
            return (Criteria) this;
        }

        public Criteria andWalletIdGreaterThanOrEqualTo(Long value) {
            addCriterion("wallet_id >=", value, "walletId");
            return (Criteria) this;
        }

        public Criteria andWalletIdLessThan(Long value) {
            addCriterion("wallet_id <", value, "walletId");
            return (Criteria) this;
        }

        public Criteria andWalletIdLessThanOrEqualTo(Long value) {
            addCriterion("wallet_id <=", value, "walletId");
            return (Criteria) this;
        }

        public Criteria andWalletIdIn(List<Long> values) {
            addCriterion("wallet_id in", values, "walletId");
            return (Criteria) this;
        }

        public Criteria andWalletIdNotIn(List<Long> values) {
            addCriterion("wallet_id not in", values, "walletId");
            return (Criteria) this;
        }

        public Criteria andWalletIdBetween(Long value1, Long value2) {
            addCriterion("wallet_id between", value1, value2, "walletId");
            return (Criteria) this;
        }

        public Criteria andWalletIdNotBetween(Long value1, Long value2) {
            addCriterion("wallet_id not between", value1, value2, "walletId");
            return (Criteria) this;
        }

        public Criteria andWalletUseridIsNull() {
            addCriterion("wallet_userId is null");
            return (Criteria) this;
        }

        public Criteria andWalletUseridIsNotNull() {
            addCriterion("wallet_userId is not null");
            return (Criteria) this;
        }

        public Criteria andWalletUseridEqualTo(Long value) {
            addCriterion("wallet_userId =", value, "walletUserid");
            return (Criteria) this;
        }

        public Criteria andWalletUseridNotEqualTo(Long value) {
            addCriterion("wallet_userId <>", value, "walletUserid");
            return (Criteria) this;
        }

        public Criteria andWalletUseridGreaterThan(Long value) {
            addCriterion("wallet_userId >", value, "walletUserid");
            return (Criteria) this;
        }

        public Criteria andWalletUseridGreaterThanOrEqualTo(Long value) {
            addCriterion("wallet_userId >=", value, "walletUserid");
            return (Criteria) this;
        }

        public Criteria andWalletUseridLessThan(Long value) {
            addCriterion("wallet_userId <", value, "walletUserid");
            return (Criteria) this;
        }

        public Criteria andWalletUseridLessThanOrEqualTo(Long value) {
            addCriterion("wallet_userId <=", value, "walletUserid");
            return (Criteria) this;
        }

        public Criteria andWalletUseridIn(List<Long> values) {
            addCriterion("wallet_userId in", values, "walletUserid");
            return (Criteria) this;
        }

        public Criteria andWalletUseridNotIn(List<Long> values) {
            addCriterion("wallet_userId not in", values, "walletUserid");
            return (Criteria) this;
        }

        public Criteria andWalletUseridBetween(Long value1, Long value2) {
            addCriterion("wallet_userId between", value1, value2, "walletUserid");
            return (Criteria) this;
        }

        public Criteria andWalletUseridNotBetween(Long value1, Long value2) {
            addCriterion("wallet_userId not between", value1, value2, "walletUserid");
            return (Criteria) this;
        }

        public Criteria andWalletRemainsumIsNull() {
            addCriterion("wallet_remainsum is null");
            return (Criteria) this;
        }

        public Criteria andWalletRemainsumIsNotNull() {
            addCriterion("wallet_remainsum is not null");
            return (Criteria) this;
        }

        public Criteria andWalletRemainsumEqualTo(Long value) {
            addCriterion("wallet_remainsum =", value, "walletRemainsum");
            return (Criteria) this;
        }

        public Criteria andWalletRemainsumNotEqualTo(Long value) {
            addCriterion("wallet_remainsum <>", value, "walletRemainsum");
            return (Criteria) this;
        }

        public Criteria andWalletRemainsumGreaterThan(Long value) {
            addCriterion("wallet_remainsum >", value, "walletRemainsum");
            return (Criteria) this;
        }

        public Criteria andWalletRemainsumGreaterThanOrEqualTo(Long value) {
            addCriterion("wallet_remainsum >=", value, "walletRemainsum");
            return (Criteria) this;
        }

        public Criteria andWalletRemainsumLessThan(Long value) {
            addCriterion("wallet_remainsum <", value, "walletRemainsum");
            return (Criteria) this;
        }

        public Criteria andWalletRemainsumLessThanOrEqualTo(Long value) {
            addCriterion("wallet_remainsum <=", value, "walletRemainsum");
            return (Criteria) this;
        }

        public Criteria andWalletRemainsumIn(List<Long> values) {
            addCriterion("wallet_remainsum in", values, "walletRemainsum");
            return (Criteria) this;
        }

        public Criteria andWalletRemainsumNotIn(List<Long> values) {
            addCriterion("wallet_remainsum not in", values, "walletRemainsum");
            return (Criteria) this;
        }

        public Criteria andWalletRemainsumBetween(Long value1, Long value2) {
            addCriterion("wallet_remainsum between", value1, value2, "walletRemainsum");
            return (Criteria) this;
        }

        public Criteria andWalletRemainsumNotBetween(Long value1, Long value2) {
            addCriterion("wallet_remainsum not between", value1, value2, "walletRemainsum");
            return (Criteria) this;
        }

        public Criteria andDepositIsNull() {
            addCriterion("deposit is null");
            return (Criteria) this;
        }

        public Criteria andDepositIsNotNull() {
            addCriterion("deposit is not null");
            return (Criteria) this;
        }

        public Criteria andDepositEqualTo(Long value) {
            addCriterion("deposit =", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositNotEqualTo(Long value) {
            addCriterion("deposit <>", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositGreaterThan(Long value) {
            addCriterion("deposit >", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositGreaterThanOrEqualTo(Long value) {
            addCriterion("deposit >=", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositLessThan(Long value) {
            addCriterion("deposit <", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositLessThanOrEqualTo(Long value) {
            addCriterion("deposit <=", value, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositIn(List<Long> values) {
            addCriterion("deposit in", values, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositNotIn(List<Long> values) {
            addCriterion("deposit not in", values, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositBetween(Long value1, Long value2) {
            addCriterion("deposit between", value1, value2, "deposit");
            return (Criteria) this;
        }

        public Criteria andDepositNotBetween(Long value1, Long value2) {
            addCriterion("deposit not between", value1, value2, "deposit");
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
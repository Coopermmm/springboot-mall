package com.cooper.mall.dto;

import com.cooper.mall.constants.ProductCategory;

public class ProductQueryParams {
    ProductCategory category;
    String search;
    String orderBy;
    String sort;

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getOredrBy() {
        return orderBy;
    }

    public void setOredrBy(String oredrBy) {
        this.orderBy = oredrBy;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}

package br.com.bagnascojhoel.demo_sor.contacts.domain;

import java.util.ArrayList;
import java.util.List;

public class Page<T> {
    private final List<T> content;
    private final int totalItens;
    private final int pageSize;
    private final int currentPage;

    public Page(List<T> content, int totalItens, int pageSize, int currentPage) {
        this.content = content;
        this.totalItens = totalItens;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

    public static <T> Page<T> empty(int currentPage, int totalItens, int pageSize) {
        return new Page<>(new ArrayList<>(), totalItens, currentPage, pageSize);
    }

    public List<T> getContent() {
        return content;
    }

    public int getPageSize() {
        return content.size();
    }

    public int getTotalItens() {
        return totalItens;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getTotalPages() {
        return Math.ceilDiv(this.totalItens, this.pageSize);
    }

}

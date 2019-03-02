package com.epam.tariffs.parsing.util;

import com.epam.tariffs.parsing.data.parsers.interfaces.ValuableEnumeration;

import java.util.List;

public class EnumSearcher<T extends ValuableEnumeration> {
    private List<T> elements;

    public EnumSearcher(List<T> elements) {
        this.elements = elements;
    }

    public T search(String searching) {
        T result = null;

        for (T element: elements) {
            String value = element.getValue();

            if (value.equalsIgnoreCase(searching)) {
                result = element;

                break;
            }
        }

        return result;
    }
}

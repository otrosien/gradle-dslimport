package com.epages.dslimport;

import org.gradle.api.Named;
import org.gradle.model.Managed;

@Managed
public interface Product extends Named {

    String getSku();
    void setSku(String sku);

}

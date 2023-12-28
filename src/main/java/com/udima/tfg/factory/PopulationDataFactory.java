package com.udima.tfg.factory;

import com.udima.tfg.model.PopulationData;

public interface PopulationDataFactory<T> {
    T createData(PopulationData populationData);
}

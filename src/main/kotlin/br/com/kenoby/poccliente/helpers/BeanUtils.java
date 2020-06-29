package br.com.kenoby.poccliente.helpers;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.FeatureDescriptor;
import java.util.stream.Stream;

public class BeanUtils {

    public static void copiarNaoNulosDePara(Object origem, Object destino) {
        final BeanWrapper wrapped = new BeanWrapperImpl(origem);
        String[] camposNulos = Stream.of(wrapped.getPropertyDescriptors()).map(FeatureDescriptor::getName)
                .filter(propertyName -> wrapped.getPropertyValue(propertyName) == null)
                .toArray(String[]::new);

        org.springframework.beans.BeanUtils.copyProperties(origem, destino, camposNulos);
    }
}

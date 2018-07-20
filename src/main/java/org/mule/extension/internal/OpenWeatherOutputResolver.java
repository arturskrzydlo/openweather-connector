package org.mule.extension.internal;

import org.mule.metadata.api.builder.BaseTypeBuilder;
import org.mule.metadata.api.model.MetadataFormat;
import org.mule.metadata.api.model.MetadataType;
import org.mule.runtime.api.metadata.resolving.OutputStaticTypeResolver;

public class OpenWeatherOutputResolver extends OutputStaticTypeResolver {

    @Override
    public String getResolverName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String getCategoryName() {
        return OpenWeatherOutputResolver.class.getCanonicalName();
    }

    @Override
    public MetadataType getStaticMetadata() {
       return BaseTypeBuilder.create(MetadataFormat.JSON).objectType().build();
    }
}

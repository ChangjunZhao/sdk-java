package org.openstack4j.openstack.telemetry.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.openstack4j.model.telemetry.TraitDescription;

public class CeilometerTraitDescription implements TraitDescription {

    @JsonProperty("name")
    private String name;
    @JsonProperty("type")
    private String type;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getType() {
        return type;
    }

    /**
     * {@inheritDoc}
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * {@inheritDoc}
     */
    public void setType(String type) {
        this.type = type;
    }

}
package de.dreier.mytargets.shared.models;

import java.io.Serializable;

/**
 * Created by Florian on 13.03.2015.
 */
public abstract class IdProvider implements Serializable {
    public static final String ID = "_id";
    protected long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getParentId() {
        return 0;
    }

    @Override
    public boolean equals(Object another) {
        return another instanceof IdProvider &&
                getClass().equals(another.getClass()) &&
                id == ((IdProvider) another).id;
    }
}
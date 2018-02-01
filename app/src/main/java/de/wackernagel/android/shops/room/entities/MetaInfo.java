package de.wackernagel.android.shops.room.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Ignore;
import android.support.annotation.NonNull;

import java.util.Date;

public class MetaInfo {

    @ColumnInfo
    @NonNull
    private Date created;

    @ColumnInfo
    @NonNull
    private Date modified;

    @Ignore
    MetaInfo( @NonNull final Date created ) {
        this.created = created;
        this.modified = created;
    }

    public MetaInfo( @NonNull final Date created, @NonNull final Date modified ) {
        this.created = created;
        this.modified = modified;
    }

    @NonNull
    public Date getCreated() {
        return created;
    }

    @NonNull
    public Date getModified() {
        return modified;
    }

    public void setModified( @NonNull final Date modified) {
        this.modified = modified;
    }

}

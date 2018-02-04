package de.wackernagel.android.shops.room.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;

@Entity( tableName = "brands",
         indices = { @Index( value = { "name" }, unique = true ) } )
public class Brand {

    @PrimaryKey( autoGenerate = true)
    private long id;

    @ColumnInfo
    @NonNull
    private String name;

    @ColumnInfo( name = "website_url" )
    private String websiteUrl;

    @ColumnInfo( name = "image_url" )
    private String imageUrl;

    @Embedded
    private MetaInfo metaInfo;

    @Ignore
    public Brand( @NonNull final String name ) {
        this.id = 0;
        this.name = name;
        this.metaInfo = new MetaInfo( new Date() );
    }

    public Brand( final long id, @NonNull final String name, final MetaInfo metaInfo ) {
        this.id = id;
        this.name = name;
        this.metaInfo = metaInfo;
    }

    public long getId() {
        return id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public MetaInfo getMetaInfo() {
        return metaInfo;
    }

    @Override
    public String toString() {
        return "Brand [" + id + ", " + name + ", " + websiteUrl + ", " + imageUrl + "]";
    }
}

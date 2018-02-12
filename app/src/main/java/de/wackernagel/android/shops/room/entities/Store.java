package de.wackernagel.android.shops.room.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity( tableName = "stores",
         foreignKeys = @ForeignKey(entity = Brand.class, parentColumns = "id", childColumns = "brandId", onDelete = CASCADE) )
public class Store {
    @PrimaryKey( autoGenerate = true)
    private final long id;
    @ColumnInfo
    private final long brandId;

    @ColumnInfo
    private final String street;
    @ColumnInfo
    private final String number;
    @ColumnInfo
    private final String zip;
    @ColumnInfo
    private final String town;
    @ColumnInfo
    private final String region;
    @ColumnInfo
    private final String country;
    @ColumnInfo
    private final double latitude;
    @ColumnInfo
    private final double longitude;
    @ColumnInfo
    private final String phone;

    @ColumnInfo( name = "image_url" )
    private String imageUrl;

    @Ignore
    public Store(long brandId, String street, String number, String zip, String town, String region, String country, double latitude, double longitude, String phone) {
        this.id = 0;
        this.brandId = brandId;
        this.street = street;
        this.number = number;
        this.zip = zip;
        this.town = town;
        this.region = region;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
        this.phone = phone;
    }

    public Store(long id, long brandId, String street, String number, String zip, String town, String region, String country, double latitude, double longitude, String phone, String imageUrl) {
        this.id = id;
        this.brandId = brandId;
        this.street = street;
        this.number = number;
        this.zip = zip;
        this.town = town;
        this.region = region;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
        this.phone = phone;
        this.imageUrl = imageUrl;
    }

    public long getId() {
        return id;
    }

    public long getBrandId() {
        return brandId;
    }

    public String getStreet() {
        return street;
    }

    public String getNumber() {
        return number;
    }

    public String getZip() {
        return zip;
    }

    public String getTown() {
        return town;
    }

    public String getRegion() {
        return region;
    }

    public String getCountry() {
        return country;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getPhone() {
        return phone;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

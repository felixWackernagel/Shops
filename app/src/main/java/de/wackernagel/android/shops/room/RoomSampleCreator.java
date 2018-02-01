package de.wackernagel.android.shops.room;

import de.wackernagel.android.shops.room.entities.Brand;

public final class RoomSampleCreator {

    public static void createSamples( final AppDatabase db ) {
        brand( db, "Lidl Deutschland", "https://www.lidl.de", "https://upload.wikimedia.org/wikipedia/commons/thumb/9/91/Lidl-Logo.svg/200px-Lidl-Logo.svg.png" );
        brand( db, "ALDI Nord", "https://www.aldi-nord.de", "https://upload.wikimedia.org/wikipedia/commons/2/20/ALDI_Nord_Logo_2015.png" );
    }

    private static void brand( final AppDatabase database, final String name, final String websiteURL, final String imageURL ) {
        final Brand brand = new Brand( name );
        brand.setWebsiteUrl( websiteURL );
        brand.setImageUrl( imageURL );
        database.brandDao().insertBrand( brand );
    }

}

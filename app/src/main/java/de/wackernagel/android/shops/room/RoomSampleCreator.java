package de.wackernagel.android.shops.room;

import de.wackernagel.android.shops.room.entities.Brand;
import de.wackernagel.android.shops.room.entities.Store;

public final class RoomSampleCreator {

    public static void createSamples( final AppDatabase db ) {
        final long lidlId = brand( db, "Lidl Deutschland", "https://www.lidl.de", "https://upload.wikimedia.org/wikipedia/commons/thumb/9/91/Lidl-Logo.svg/200px-Lidl-Logo.svg.png" );
        brand( db, "ALDI Nord", "https://www.aldi-nord.de", "https://upload.wikimedia.org/wikipedia/commons/2/20/ALDI_Nord_Logo_2015.png" );
        final long konsumFridaId = brand( db, "Konsum Dresden", "https://www.konsum.de", "https://upload.wikimedia.org/wikipedia/commons/e/e3/Konsum_logo_R.jpg" );
        brand( db, "Netto Marken-Discount", "https://www.netto-online.de/", "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c5/Netto_logo.svg/320px-Netto_logo.svg.png");
        brand( db, "NETTO", "https://netto.de/", "https://upload.wikimedia.org/wikipedia/de/thumb/8/88/Netto_logo_2.svg/320px-Netto_logo_2.svg.png" );
        brand( db, "REWE", "https://www.rewe.de/", "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4c/Logo_REWE.svg/320px-Logo_REWE.svg.png" );
        brand( db, "diska", "http://www.diska.de/", "https://static05.kaufda.de/Geschaefte/diska-Markt.v1377.JPG" );
        brand( db, "SPAR express", "https://www.spar-express.de/", "https://upload.wikimedia.org/wikipedia/de/thumb/e/e9/Logo_SPAR_express.jpg/320px-Logo_SPAR_express.jpg" );
        brand( db, "EDEKA", "https://www.edeka.de", "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e7/Logo_Edeka.svg/197px-Logo_Edeka.svg.png" );
        brand( db, "PENNY", "http://www.penny.de", "https://upload.wikimedia.org/wikipedia/commons/9/91/Penny_Market_logo_2014.png" );

        store( db, lidlId, "Bautzner Landstraße", "112", "01324", "Dresden", "Sachsen", "Deutschland", 51.062168d, 13.844882d,"0800 4353361");
        store( db, konsumFridaId, "Lahmannring", "19", "01324", "Dresden", "Sachsen", "Deutschland",51.063028, 13.821701,"0351 2632936" );
    }

    private static long brand( final AppDatabase database, final String name, final String websiteURL, final String imageURL ) {
        final Brand brand = new Brand( name );
        brand.setWebsiteUrl( websiteURL );
        brand.setImageUrl( imageURL );
        return database.brandDao().insertBrand( brand );
    }

    private static void store( final AppDatabase database, long brandId, String street, String number, String zip, String town, String region, String country, double latitude, double longitude, String phone) {
        final Store store = new Store(brandId, street, number, zip, town, region, country, latitude, longitude, phone);
        database.storeDao().insertStore(store);
    }
}

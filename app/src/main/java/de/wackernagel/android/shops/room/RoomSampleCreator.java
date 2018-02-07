package de.wackernagel.android.shops.room;

import de.wackernagel.android.shops.room.entities.Brand;

public final class RoomSampleCreator {

    public static void createSamples( final AppDatabase db ) {
        brand( db, "Lidl Deutschland", "https://www.lidl.de", "https://upload.wikimedia.org/wikipedia/commons/thumb/9/91/Lidl-Logo.svg/200px-Lidl-Logo.svg.png" );
        brand( db, "ALDI Nord", "https://www.aldi-nord.de", "https://upload.wikimedia.org/wikipedia/commons/2/20/ALDI_Nord_Logo_2015.png" );
        brand( db, "Konsum Dresden", "https://www.konsum.de", "https://upload.wikimedia.org/wikipedia/commons/e/e3/Konsum_logo_R.jpg" );
        brand( db, "Netto Marken-Discount", "https://www.netto-online.de/", "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c5/Netto_logo.svg/320px-Netto_logo.svg.png");
        brand( db, "NETTO", "https://netto.de/", "https://upload.wikimedia.org/wikipedia/de/thumb/8/88/Netto_logo_2.svg/320px-Netto_logo_2.svg.png" );
        brand( db, "REWE", "https://www.rewe.de/", "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4c/Logo_REWE.svg/320px-Logo_REWE.svg.png" );
        brand( db, "diska", "http://www.diska.de/", "https://static05.kaufda.de/Geschaefte/diska-Markt.v1377.JPG" );
        brand( db, "SPAR express", "https://www.spar-express.de/", "https://upload.wikimedia.org/wikipedia/de/thumb/e/e9/Logo_SPAR_express.jpg/320px-Logo_SPAR_express.jpg" );
        brand( db, "EDEKA", "https://www.edeka.de", "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e7/Logo_Edeka.svg/197px-Logo_Edeka.svg.png" );
        brand( db, "PENNY", "http://www.penny.de", "https://upload.wikimedia.org/wikipedia/commons/9/91/Penny_Market_logo_2014.png" );
    }

    private static void brand( final AppDatabase database, final String name, final String websiteURL, final String imageURL ) {
        final Brand brand = new Brand( name );
        brand.setWebsiteUrl( websiteURL );
        brand.setImageUrl( imageURL );
        database.brandDao().insertBrand( brand );
    }

}

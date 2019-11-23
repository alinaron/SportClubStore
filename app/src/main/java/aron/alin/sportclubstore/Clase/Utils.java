package aron.alin.sportclubstore.Clase;

import java.text.SimpleDateFormat;

public interface Utils {
    String DATE_FORMAT = "dd-MM-yyyy";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);

    String KEY_CLIENT = "addedClient";
    int REQ_GALERIE = 109;
    int REQ_FRAG = 393;

    String SHARED_USER = "usernameShared";
    String SHARED_PASS = "passwordShared";
    String SHARED_PREF_NAME = "sharedPreferences";
    String KEY_LOGIN = "loginKey";
    String NOTIFICATION_CHANNEL_ID = "NOTIFICATION_CHANNEL";
    String KEY_BUNDLE = "bundleKey";
    String KEY_PRODUS = "produsDorit";
    String KEY_CLIENT_PRODUS = "clientProdus";
    String KEY_ADAUGA_POSTARE = "postareNoua";
    String KEY_POST = "refresh";
    String KEY_VERIFICARE_DISCOUNT = "discountSauNu";

    String KEY_CATEGORIE = "idTransmitCatreCategorie";
    String KEY_PENTRU_TINE = "idPentruTine";
    String KEY_LISTA_PRODUSE = "listaProduse";
    String KEY_SEARCH = "searchProduct";

    String KEY_POSTARE = "postareaMea";
    String KEY_CLIENT_POSTARE = "clientPostare";

    String KEY_POSTARI_MAY = "postsMay";
    String KEY_POSTARI_JUN = "postsJun";
    String KEY_POSTARI_JUL = "postsJul";
    String KEY_POSTARI_TOT = "totalPostari";
    String KEY_COS = "cosCumparaturi";

    String KEY_PLASEAZA_COMANDA_LISTA = "listaProduseDeComandat";
    String KEY_PLASEAZA_COMANDA_CLIENT = "comandaPlasataDeClient";

    String KEY_VALOARE_PLATA = "valoareComanda";
    String KEY_CONT = "openMyAccount";

    String KEY_LISTA_URMARITORI = "followersOrFollowing";
    String KEY_FOLLOW_USER = "userFollow";

    String KEY_WISH = "openWishlist";
    String KEY_SETTINGS = "openSettings";

    String KEY_LOCATII_FAVORITE = "locatiiFavorite";
    String KEY_COMPETITII_FAVORITE = "competitiiFavorite";

    String KEY_MY_STATS = "myStats";

    String KEY_RAPORT = "raportComenzi";

    String KEY_COMENZI = "comenzileMele";

    String KEY_PRODUSE_COMENZI = "produseComenzi";
    String KEY_NR_COMANDA = "numarComanda";

    String KEY_MY_POSTS = "myPosts";

    String KEY_MODIFICA_DATE = "modificaDate";
    String KEY_MODIFICA_ADRESA = "modificaAdresa";
}

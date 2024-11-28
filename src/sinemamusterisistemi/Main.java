package sinemamusterisistemi;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Örnek filmler ve salonlar
        ArrayList<Film> filmler = new ArrayList<>();
        filmler.add(new Film("The Green Mile", 189, "Dram,Suç", 8.5));
        filmler.add(new Film("Matrix", 216, "Aksiyon", 8.8));
        filmler.add(new Film("Soul", 100, "Animasyon", 8.0));

        ArrayList<Salon> salonlar = new ArrayList<>();
        salonlar.add(new Salon("Salon 1", filmler.get(0)));
        salonlar.add(new Salon("Salon 2", filmler.get(1)));
        salonlar.add(new Salon("Salon 3", filmler.get(2)));

        // Salonlara seans ekle
        salonlar.get(0).seansEkle("12:00");
        salonlar.get(0).seansEkle("15:00");
        salonlar.get(1).seansEkle("14:00");
        salonlar.get(2).seansEkle("18:00");

        while (true) {
            System.out.println("\n1. Vizyondaki Filmleri Göster");
            System.out.println("2. Salonlardaki Filmleri Listele");
            System.out.println("3. Filme Kayıt Ol");
            System.out.println("4. Filme Kayıtlı Müşteriler");
            System.out.println("5. Admin Girişi");
            System.out.println("6. Çıkış");
            System.out.print("Seçiminiz: ");
            int secim = scanner.nextInt();
            scanner.nextLine();

            switch (secim) {
                case 1:
                    System.out.println("Vizyondaki Filmler:");
                    for (Film film : filmler) {
                        System.out.println(film);
                    }
                    break;
                case 2:
                    System.out.println("Salonlar ve Seanslar:");
                    for (Salon salon : salonlar) {
                        System.out.println(salon.getAd() + " - Film: " + salon.getFilm().getAd());
                        System.out.println("Seanslar: " + salon.getSeanslar());
                    }
                    break;
                case 3:
                    System.out.println("Kayıt Olmak İstediğiniz Filmin Numarasını Girin:");
                    for (int i = 0; i < salonlar.size(); i++) {
                        System.out.println((i + 1) + ". " + salonlar.get(i).getFilm().getAd() +
                                " (Salon: " + salonlar.get(i).getAd() + ")");
                    }
                    int salonSecimi = scanner.nextInt() - 1;
                    scanner.nextLine();

                    if (salonSecimi < 0 || salonSecimi >= salonlar.size()) {
                        System.out.println("Geçersiz Film Seçimi!");
                        break;
                    }

                    Salon secilenSalon = salonlar.get(salonSecimi);
                    System.out.println("Mevcut Seanslar: ");
                    for (int i = 0; i < secilenSalon.getSeanslar().size(); i++) {
                        System.out.println((i + 1) + ". " + secilenSalon.getSeanslar().get(i));
                    }

                    System.out.print("Seçmek İstediğiniz Seansı Girin (1-" + secilenSalon.getSeanslar().size() + " arası): ");
                    int seansSecimi = scanner.nextInt() - 1;
                    scanner.nextLine();

                    if (seansSecimi < 0 || seansSecimi >= secilenSalon.getSeanslar().size()) {
                        System.out.println("Geçersiz Seans Seçimi!");
                        break;
                    }

                    String secilenSeans = secilenSalon.getSeanslar().get(seansSecimi);
                    System.out.print("Ad Soyad Girin: ");
                    String adSoyad = scanner.nextLine();
                    System.out.print("Kaç Kişilik Rezervasyon: ");
                    int kisiSayisi = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("E-posta Adresinizi Girin: ");
                    String email = scanner.nextLine();

                    Musteri musteri = new Musteri(adSoyad, kisiSayisi, email);
                    secilenSalon.musteriEkle(secilenSeans, musteri);

                    System.out.println("Kayıt Başarılı! Rezervasyonunuz Onaylandı.");
                    break;
                case 4:
                    System.out.println("Filme Kayıtlı Müşteriler:");
                    for (Salon salon : salonlar) {
                        System.out.println("Film: " + salon.getFilm().getAd());
                        salon.musterileriListele();
                    }
                    break;
                case 5:
                    System.out.print("Kullanıcı Adı: ");
                    String kullaniciAdi = scanner.nextLine();
                    System.out.print("Şifre: ");
                    String sifre = scanner.nextLine();

                    if (kullaniciAdi.equals("admin") && sifre.equals("admin")) {
                        System.out.println("Admin Girişi Başarılı!");
                        while (true) {
                            System.out.println("\n1. Film Ekle");
                            System.out.println("2. Film Sil");
                            System.out.println("3. Seans Ekle");
                            System.out.println("4. Seans Sil");
                            System.out.println("5. Geri Dön");
                            System.out.print("Seçiminiz: ");
                            int adminSecim = scanner.nextInt();
                            scanner.nextLine();

                            switch (adminSecim) {
                                case 1:
                                    System.out.print("Film Adı: ");
                                    String yeniFilmAd = scanner.nextLine();
                                    System.out.print("Film Süresi (dk): ");
                                    int yeniFilmSure = scanner.nextInt();
                                    scanner.nextLine();
                                    System.out.print("Film Türü: ");
                                    String yeniFilmTur = scanner.nextLine();
                                    System.out.print("IMDB Puanı: ");
                                    double yeniFilmImdb = scanner.nextDouble();
                                    scanner.nextLine();

                                    Film yeniFilm = new Film(yeniFilmAd, yeniFilmSure, yeniFilmTur, yeniFilmImdb);
                                    filmler.add(yeniFilm);
                                    salonlar.add(new Salon("Salon " + (salonlar.size() + 1), yeniFilm));
                                    System.out.println("Yeni Film ve Salon Başarıyla Eklendi!");
                                    break;
                                case 2:
                                    System.out.println("Silmek İstediğiniz Filmin Numarasını Girin:");
                                    for (int i = 0; i < filmler.size(); i++) {
                                        System.out.println((i + 1) + ". " + filmler.get(i).getAd());
                                    }
                                    int silinecekFilm = scanner.nextInt() - 1;
                                    scanner.nextLine();

                                    if (silinecekFilm < 0 || silinecekFilm >= filmler.size()) {
                                        System.out.println("Geçersiz Seçim!");
                                        break;
                                    }

                                    filmler.remove(silinecekFilm);
                                    salonlar.remove(silinecekFilm);
                                    System.out.println("Film ve İlgili Salon Başarıyla Silindi!");
                                    break;
                                case 3:
                                    System.out.println("Seans Eklemek İstediğiniz Salonun Numarasını Girin:");
                                    for (int i = 0; i < salonlar.size(); i++) {
                                        System.out.println((i + 1) + ". " + salonlar.get(i).getAd());
                                    }
                                    int seansEkleSalon = scanner.nextInt() - 1;
                                    scanner.nextLine();

                                    if (seansEkleSalon < 0 || seansEkleSalon >= salonlar.size()) {
                                        System.out.println("Geçersiz Seçim!");
                                        break;
                                    }

                                    System.out.print("Yeni Seans Saati: ");
                                    String yeniSeans = scanner.nextLine();
                                    salonlar.get(seansEkleSalon).seansEkle(yeniSeans);
                                    System.out.println("Seans Başarıyla Eklendi!");
                                    break;
                                case 4:
                                    System.out.println("Seans Silmek İstediğiniz Salonun Numarasını Girin:");
                                    for (int i = 0; i < salonlar.size(); i++) {
                                        System.out.println((i + 1) + ". " + salonlar.get(i).getAd());
                                    }
                                    int seansSilSalon = scanner.nextInt() - 1;
                                    scanner.nextLine();

                                    if (seansSilSalon < 0 || seansSilSalon >= salonlar.size()) {
                                        System.out.println("Geçersiz Seçim!");
                                        break;
                                    }

                                    System.out.println("Silmek İstediğiniz Seansı Girin:");
                                    List<String> mevcutSeanslar = salonlar.get(seansSilSalon).getSeanslar();
                                    for (int i = 0; i < mevcutSeanslar.size(); i++) {
                                        System.out.println((i + 1) + ". " + mevcutSeanslar.get(i));
                                    }
                                    int silinecekSeans = scanner.nextInt() - 1;
                                    scanner.nextLine();

                                    if (silinecekSeans < 0 || silinecekSeans >= mevcutSeanslar.size()) {
                                        System.out.println("Geçersiz Seçim!");
                                        break;
                                    }

                                    mevcutSeanslar.remove(silinecekSeans);
                                    System.out.println("Seans Başarıyla Silindi!");
                                    break;
                                case 5:
                                    System.out.println("Admin Menüsünden Çıkılıyor...");
                                    break;
                                default:
                                    System.out.println("Geçersiz Seçim!");
                            }
                            if (adminSecim == 5) break;
                        }
                    } else {
                        System.out.println("Hatalı Kullanıcı Adı veya Şifre!");
                    }
                    break;
                case 6:
                    System.out.println("Çıkış Yapılıyor...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Geçersiz Seçim!");
            }
        }
    }
}

class Film {
    private String ad;
    private int sure;
    private String tur;
    private double imdbPuani;

    public Film(String ad, int sure, String tur, double imdbPuani) {
        this.ad = ad;
        this.sure = sure;
        this.tur = tur;
        this.imdbPuani = imdbPuani;
    }

    public String getAd() {
        return ad;
    }

    @Override
    public String toString() {
        return ad + " (" + tur + ", " + sure + " dk, IMDB: " + imdbPuani + ")";
    }
}

class Salon {
    private String ad;
    private Film film;
    private List<String> seanslar = new ArrayList<>();
    private HashMap<String, List<Musteri>> seansMusteriler = new HashMap<>();

    public Salon(String ad, Film film) {
        this.ad = ad;
        this.film = film;
    }

    public void seansEkle(String seans) {
        seanslar.add(seans);
        seansMusteriler.put(seans, new ArrayList<>());
    }

    public String getAd() {
        return ad;
    }

    public Film getFilm() {
        return film;
    }

    public List<String> getSeanslar() {
        return seanslar;
    }

    public void musteriEkle(String seans, Musteri musteri) {
        if (seansMusteriler.containsKey(seans)) {
            seansMusteriler.get(seans).add(musteri);
        }
    }

    public void musterileriListele() {
        for (String seans : seanslar) {
            System.out.println("Seans: " + seans);
            List<Musteri> musteriler = seansMusteriler.get(seans);
            if (musteriler.isEmpty()) {
                System.out.println("Kayıtlı müşteri yok.");
            } else {
                for (Musteri musteri : musteriler) {
                    System.out.println("- " + musteri);
                }
            }
        }
    }
}

class Musteri {
    private String adSoyad;
    private int kisiSayisi;
    private String email;

    public Musteri(String adSoyad, int kisiSayisi, String email) {
        this.adSoyad = adSoyad;
        this.kisiSayisi = kisiSayisi;
        this.email = email;
    }

    @Override
    public String toString() {
        return adSoyad + " (" + kisiSayisi + " kişi, Email: " + email + ")";
    }
}

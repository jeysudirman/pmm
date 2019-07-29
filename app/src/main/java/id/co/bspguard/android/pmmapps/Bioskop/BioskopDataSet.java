package id.co.bspguard.android.pmmapps.Bioskop;

public class BioskopDataSet {

    String id, nama_film, nomor_teater, jam_mulai, jam_selesai, harga;

    public BioskopDataSet(){}

    public BioskopDataSet(String id, String nama_film, String nomor_teater, String jam_mulai, String jam_selesai, String harga)
    {
        this.id = id;
        this.nama_film = nama_film;
        this.nomor_teater = nomor_teater;
        this.jam_mulai = jam_mulai;
        this.jam_selesai = jam_selesai;
        this.harga = harga;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama_film() {
        return nama_film;
    }

    public void setNama_film(String nama_film) {
        this.nama_film = nama_film;
    }

    public String getNomor_teater() {
        return nomor_teater;
    }

    public void setNomor_teater(String nomor_teater) {
        this.nomor_teater = nomor_teater;
    }

    public String getJam_mulai() {
        return jam_mulai;
    }

    public void setJam_mulai(String jam_mulai) {
        this.jam_mulai = jam_mulai;
    }

    public String getJam_selesai() {
        return jam_selesai;
    }

    public void setJam_selesai(String jam_selesai) {
        this.jam_selesai = jam_selesai;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }
}

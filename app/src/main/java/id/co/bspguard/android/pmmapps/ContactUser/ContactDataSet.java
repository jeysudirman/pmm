package id.co.bspguard.android.pmmapps.ContactUser;

public class ContactDataSet {

    String id, nama, alamat, nohp, email;

    public ContactDataSet(){}

    public ContactDataSet(String id, String nama, String alamat, String nohp, String email) {
        this.id = id;
        this.nama = nama;
        this.alamat = alamat;
        this.nohp = nohp;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNohp() {
        return nohp;
    }

    public void setNohp(String nohp) {
        this.nohp = nohp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

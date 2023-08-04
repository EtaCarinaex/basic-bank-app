package org.example.modal;

import jakarta.persistence.*;
import org.example.constants.HesapTuru;
import org.example.interfaces.IVadeliBankaHesabi;
import org.example.interfaces.IVadesizBankaHesabi;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "banka_hesabi")
public class BankaHesabi implements IVadesizBankaHesabi, IVadeliBankaHesabi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "hesap_no")
    private String hesapNo;

    @Column(name = "bakiye")
    private Double bakiye;

    @Enumerated
    @Column(name = "hesap_turu")
    private HesapTuru hesapTuru;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "vade_tarihi")
    private Date vadeTarihi;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BankaHesabi() {
    }

    public BankaHesabi(String hesapNo) {
        this.hesapNo = hesapNo;
    }

    @Override
    public HesapTuru hesapTuru() {
        return hesapTuru;
    }

    @Override
    public void vadeyiUzat(Date yeniVadeTarihi) throws IllegalAccessException {

        if (!hesapTuru.equals(HesapTuru.VADELI))
            throw new IllegalAccessException("Vadeyi uzatmak için hesap türünün Vadeli olması gereklidir.");

        if (vadeTarihi.after(yeniVadeTarihi)) {
            throw new IllegalAccessException("Yeni vade tarihi eskisinden ileri olmalıdır.");
        }

        vadeTarihi = yeniVadeTarihi;


    }

    @Override
    public UUID getVergiNumarasi() {
        return null;
    }


    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Hesap NO: ").append(this.hesapNo)
                .append("\n Hesap Türü: ").append(hesapTuru.toString())
                .append("\n bakiye:").append(bakiye);

        if (hesapTuru.equals(HesapTuru.VADELI)) {
            stringBuilder.append("\n Vade tarihi :").append(vadeTarihi.toString());
        }

        return stringBuilder.toString();
    }


    public String getHesapNo() {
        return hesapNo;
    }

    public Double getBakiye() {
        return bakiye;
    }

    public void setBakiye(Double bakiye) {
        this.bakiye = bakiye;
    }

    public HesapTuru getHesapTuru() {
        return hesapTuru;
    }

    public void setHesapTuru(HesapTuru hesapTuru) {
        this.hesapTuru = hesapTuru;
    }


    public void setVadeTarihi(Date vadeTarihi){
        this.vadeTarihi = vadeTarihi;
    }

    public void setHesapNo(String hesapNo) {
        this.hesapNo = hesapNo;
    }

    public Date getVadeTarihi() {
        return vadeTarihi;
    }

}

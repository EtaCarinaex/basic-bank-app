package org.example.modal;

import org.example.constants.HesapTuru;
import org.example.interfaces.IVadeliBankaHesabi;
import org.example.interfaces.IVadesizBankaHesabi;

import java.util.Date;
import java.util.UUID;

public class BankaHesabi implements IVadesizBankaHesabi, IVadeliBankaHesabi {


    private final UUID hesapNo;

    private Double bakiye;

    private HesapTuru hesapTuru;

    private Date vadeTarihi;

    private UUID vergiNo;

    public BankaHesabi(UUID hesapNo) {
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
        return vergiNo;
    }


    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Hesap NO: ").append(this.hesapNo)
                .append("\n Vergi Numarası: ").append(vergiNo)
                .append("\n Hesap Türü: ").append(hesapTuru.toString())
                .append("\n bakiye:").append(bakiye);

        if (hesapTuru.equals(HesapTuru.VADELI)) {
            stringBuilder.append("\n Vade tarihi :").append(vadeTarihi.toString());
        }

        return stringBuilder.toString();
    }


    public UUID getHesapNo() {
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

    public void setVergiNo(UUID vergiNo){
        this.vergiNo = vergiNo;
    }

    public void setVadeTarihi(Date vadeTarihi){
        this.vadeTarihi = vadeTarihi;
    }

}

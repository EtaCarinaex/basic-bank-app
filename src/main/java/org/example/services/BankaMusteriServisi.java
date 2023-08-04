package org.example.services;

import org.example.constants.HesapTuru;
import org.example.modal.BankaHesabi;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class BankaMusteriServisi {

    private String musteriAdi;

    private String musteriSonAd;

    private List<BankaHesabi> hesaplarim = new ArrayList<>();


    public BankaHesabi hesapAc(Double bakiye, HesapTuru hesapTuru) {

        BankaHesabi bankaHesabi = new BankaHesabi(UUID.randomUUID());
        bankaHesabi.setBakiye(bakiye);
        bankaHesabi.setHesapTuru(hesapTuru);
        bankaHesabi.setVergiNo(UUID.randomUUID());


        if (hesapTuru.equals(HesapTuru.VADELI))
            bankaHesabi.setVadeTarihi(new Date());


        hesaplarim.add(bankaHesabi);

        return bankaHesabi;
    }


    public void hesapKapat(UUID hesapNumarasi) {
        hesaplarim.remove(hesapKontrol(hesapNumarasi));
    }


    public void paraYatir(UUID hesapNumarasi, Double miktar) {

        BankaHesabi bankaHesabi = hesapKontrol(hesapNumarasi);

        bankaHesabi.setBakiye(bankaHesabi.getBakiye() + miktar);

    }

    public void paraCek(UUID hesapNumarasi, Double miktar) {
        BankaHesabi bankaHesabim = hesapKontrol((hesapNumarasi));

        if (bankaHesabim.getBakiye() < miktar) {
            System.out.println("Girilen miktar bakiyeden büyük olamaz");

        } else {
            bankaHesabim.setBakiye(bankaHesabim.getBakiye() - miktar);
        }

    }


    private BankaHesabi hesapKontrol(UUID hesapNumarasi) {
        BankaHesabi tempHesap = null;

        for (BankaHesabi bankaHesabi : hesaplarim) {
            if (bankaHesabi.getHesapNo() == hesapNumarasi) {
                tempHesap = bankaHesabi;
                break;
            }
        }

        if (tempHesap == null) {
            throw new IllegalStateException(String.format("%s numaralı hesap bulunamamıştır", hesapNumarasi.toString()));
        }
        return tempHesap;
    }


    public List<BankaHesabi> hesaplariListele() {
        if (hesaplarim == null) {
            throw new IllegalStateException("Hiç bir hesabınız bulunmamakta lütfen hesap oluşturun.");
        }
        hesaplarim.forEach(hesap -> System.out.println(hesap.toString()));

        return hesaplarim;
    }


    public BankaHesabi hesapBilgileri(UUID hesapNumarası) {
        BankaHesabi hesap = hesapKontrol(hesapNumarası);

        System.out.println(hesap);
        return hesap;
    }


}

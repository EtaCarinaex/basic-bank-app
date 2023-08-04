package org.example;

import org.example.constants.HesapTuru;
import org.example.modal.BankaHesabi;
import org.example.services.BankaMusteriServisi;

public class Main {
    public static void main(String[] args) {

        BankaMusteriServisi bankaMusteriServisi = new BankaMusteriServisi();

        bankaMusteriServisi.hesapAc(0D,HesapTuru.VADESIZ);

        bankaMusteriServisi.hesapAc(0D,HesapTuru.VADELI);


        bankaMusteriServisi.hesapAc(500.55,HesapTuru.VADELI);


        bankaMusteriServisi.hesapAc(7500D,HesapTuru.VADESIZ);


        BankaHesabi bankaHesabi = bankaMusteriServisi.hesaplariListele().get(3);


        System.out.println("\n \n -------------------------------");


        System.out.println(bankaHesabi.getHesapNo());

        bankaMusteriServisi.paraCek(bankaHesabi.getHesapNo(),55.68);


        bankaMusteriServisi.hesapBilgileri(bankaHesabi.getHesapNo());


        bankaMusteriServisi.paraYatir(bankaHesabi.getHesapNo(),6000D);


        bankaMusteriServisi.hesapBilgileri(bankaHesabi.getHesapNo());



    }
}
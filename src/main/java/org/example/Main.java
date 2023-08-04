package org.example;

import org.example.constants.HesapTuru;
import org.example.modal.BankaHesabi;
import org.example.services.BankaMusteriServisi;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {

    private static SessionFactory factory;

    public static void main(String[] args) {


        try {

            factory = new Configuration().configure().addAnnotatedClass(BankaHesabi.class).buildSessionFactory();

            factory.openSession();

            Main main = new Main();


            BankaMusteriServisi bankaMusteriServisi = new BankaMusteriServisi();

            bankaMusteriServisi.hesapAc(0D, HesapTuru.VADESIZ);

            BankaHesabi bankaHesabi = bankaMusteriServisi.hesaplariListele().get(0);

            main.addbankaHesabi(bankaHesabi);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        BankaMusteriServisi bankaMusteriServisi = new BankaMusteriServisi();

        bankaMusteriServisi.hesapAc(0D, HesapTuru.VADESIZ);

        bankaMusteriServisi.hesapAc(0D, HesapTuru.VADELI);


        bankaMusteriServisi.hesapAc(500.55, HesapTuru.VADELI);


        bankaMusteriServisi.hesapAc(7500D, HesapTuru.VADESIZ);


        BankaHesabi bankaHesabi = bankaMusteriServisi.hesaplariListele().get(3);


        System.out.println("\n \n -------------------------------");


        System.out.println(bankaHesabi.getHesapNo());

        bankaMusteriServisi.paraCek(bankaHesabi.getHesapNo(), 55.68);


        bankaMusteriServisi.hesapBilgileri(bankaHesabi.getHesapNo());


        bankaMusteriServisi.paraYatir(bankaHesabi.getHesapNo(), 6000D);


        bankaMusteriServisi.hesapBilgileri(bankaHesabi.getHesapNo());

    }


    public void addbankaHesabi(BankaHesabi bankaHesabi) {
        Session session = factory.openSession();

        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.save(bankaHesabi);

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }


}